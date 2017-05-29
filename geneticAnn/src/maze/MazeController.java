package maze;

import java.util.ArrayList;

import maze.model.Chaser;
import maze.model.MazeModel;
import maze.model.Runner;

public class MazeController {
	
	MazeModel model;MazeView view;
	
	int sleep = 1;
	
	public MazeController(MazeModel m,MazeView v) {
		model = m;
		view = v;
	}
	//less is faster
	public void setSpeed(int i)
	{
		sleep = i;
	}
	
	public void add(Runner r, Chaser c)
	{
		model.addRunner(r, c);
	}
	
	public void reset()
	{
		model.reset();
		view.repaint();
	}
	
	public void clear()
	{
		model.getMazeRunners().clear();
		model.getMazeChasers().clear();
	}
	
	public void run() 
	{
		boolean finished;
		do {
			finished = model.runUntilEnd();
			if (sleep ==-1)continue;
			view.repaint();
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!finished);
		
	}
	
	public ArrayList<Integer> getScores()
	{
		run();
		ArrayList<Integer> scores = new ArrayList<Integer>();
		for (Runner r:model.getMazeRunners())
		{
			scores.add(r.getScore());
		}
		reset();
		return scores;		
	}

}
