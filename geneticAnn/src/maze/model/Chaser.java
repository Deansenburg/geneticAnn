package maze.model;

import java.util.Random;


public class Chaser extends Runner {

	Runner run;

	public Chaser(int x, int y) {
		super(x, y);
	}
	public Chaser(Block b)
	{
		super(b);
	}
	

	public void chase(Runner r) {
		run = r;
		r.chased(this);
	}
	

	Random rGen = new Random();
	
	@Override
	public void doAction() {
		double x = this.x - run.x;
		double y = this.y - run.y;

		double z = Math.abs(x) - Math.abs(y);
		
		if (z == 0)
		{
			int choice =rGen.nextInt(3); 
			if (choice==0)
				z = 1;
			else if (choice==1)
				z = -1;
		}
		
		if (z > 0) {
			if (x < 0)
				move(0);
			else if (x > 0)
				move(1);
		} else if (z<0){
			if (y < 0)
				move(2);
			else if (y > 0)
				move(3);
		}

	}

}
