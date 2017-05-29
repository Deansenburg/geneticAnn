package maze.model;


public class Runner {

	// where it is
	private Block sprite;

	// where it wants to move
	int x, y;

	int homeX, homeY;
	
	//should be paired
	protected int[] pX = {1, -1, 0, 0}, pY = {0, 0, 1, -1};
	
	protected int sees[];
	
	private int score = 0;
	
	protected boolean dead = false;
	
	Chaser chasedBy;
	
	public Runner(int x, int y) {
		setSprite(new Block(x, y));
		this.x = x;
		this.y = y;
		homeX = x;
		homeY = y;
	}
	
	public Runner(Block b)
	{
		setSprite(b);
		this.x = b.getX();
		this.y = b.getY();
		homeX = b.getX();
		homeY = b.getY();
	}

	public void chased(Chaser c)
	{
		chasedBy = c;
	}
	
	public void see(int[] b)
	{
		sees = b;
	}
	
	public void move(int d) {
		switch (d) {
		case 0:
			x += 1;
			break;
		case 1:
			x -= 1;
			break;
		case 2:
			y += 1;
			break;
		case 3:
			y -= 1;
			break;
		}
	}

	public void update(boolean allowed) {
		Block b = getSprite();
		if (allowed) {
			b.setX(x);
			b.setY(y);
		} else {
			x = b.getX();
			y = b.getY();
		}
	}
	
	public void reset()
	{
		x = homeX;
		y = homeY;
		Block b = getSprite();
		b.setX(homeX);
		b.setY(homeY);
		dead = false;
		setScore(0);
		update(true);
	}
	
	public void doAction()
	{
	}

	public Block getSprite() {
		return sprite;
	}

	public void setSprite(Block sprite) {
		this.sprite = sprite;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
