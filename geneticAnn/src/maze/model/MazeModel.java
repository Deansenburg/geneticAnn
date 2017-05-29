package maze.model;

import java.util.ArrayList;


public class MazeModel {

	private ArrayList<Runner> mazeRunners = new ArrayList<Runner>();
	private ArrayList<Runner> mazeChasers = new ArrayList<Runner>();
	private ArrayList<Block> walls = new ArrayList<Block>();

	private int width;
	private int height;

	int maxScore = 100;
	
	public MazeModel(int w, int h) {
		setWidth(w);
		setHeight(h);
		ArrayList<Block> walls = getWalls();
		for (int i = 0; i < getWidth(); i++) {
			walls.add(new Block(i, 0));
			walls.add(new Block(i, getHeight()));
		}
		for (int i = 0; i < getHeight(); i++) {
			walls.add(new Block(0, i));
			walls.add(new Block(getWidth(), i));
		}
	}

	public void addRunner(Runner run, Chaser chase) {
		getMazeRunners().add(run);
		chase.chase(run);
		getMazeChasers().add(chase);
	}

	private int isObstruction(int x, int y, Chaser c) {
		for (Block b : getWalls()) {
			if (b.getX() == x && b.getY() == y)
				return 1;
		}
		if (c != null)
			if (c.x == x && c.y == y)
				return 2;
		return 0;
	}

	private void update(Runner r) {
		int[] view = new int[r.pX.length];
		for (int i = 0; i < r.pX.length; i++) {
			view[i] = isObstruction(r.x + r.pX[i], r.y + r.pY[i], r.chasedBy);
		}
		r.see(view);
		// choose move
		r.doAction();
		r.update(isObstruction(r.x, r.y, r.chasedBy)==0);
	}

	public void run() {
		for (Runner r : getMazeRunners()) {
			update(r);
		}
		for (Runner c : getMazeChasers()) {
			update(c);
		}
	}

	public void setMaxScore(int s)
	{
		maxScore = s;
	}
	
	public boolean runUntilEnd() {
		run();
		int caught = 0;
		for (int i = 0; i < getMazeRunners().size(); i++) {
			Runner r = getMazeRunners().get(i);
			Runner c = getMazeChasers().get(i);
			if (r.x == c.x && r.y == c.y) {
				caught++;
				r.dead = true;
			}
			else if(r.getScore()>maxScore && maxScore != -1)
			{
				caught++;
				r.dead = true;
			}
			else if (maxScore != -1)
				r.setScore(r.getScore() + 1);
		}
		if (caught >= getMazeRunners().size())
			return true;
		else
			return false;
	}

	public void reset() {
		for (Runner r : getMazeRunners()) {
			r.reset();
		}
		for (Runner c : getMazeChasers()) {
			c.reset();
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<Block> getWalls() {
		return walls;
	}

	public void setWalls(ArrayList<Block> walls) {
		this.walls = walls;
	}

	public ArrayList<Runner> getMazeRunners() {
		return mazeRunners;
	}

	public void setMazeRunners(ArrayList<Runner> mazeRunners) {
		this.mazeRunners = mazeRunners;
	}

	public ArrayList<Runner> getMazeChasers() {
		return mazeChasers;
	}

	public void setMazeChasers(ArrayList<Runner> mazeChasers) {
		this.mazeChasers = mazeChasers;
	}
}
