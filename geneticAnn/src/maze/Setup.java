package maze;

import java.util.Random;

import maze.model.Block;

public class Setup {

	private static int width;

	private static int height;

	static Random rGen = new Random();

	static int rX, rY, cX, cY;

	static boolean r = false, c = false;

	public Setup(int w, int h) {
		width = w;
		height = h;
	}

	public void setChaser(int x, int y) {
		cX = x;
		cY = y;
		c = true;
	}

	public void setRunner(int x, int y) {
		rX = x;
		rY = y;
		r = true;
	}

	public static Block getRunnerPos() {
		if (r) {
			return new Block(rX, rY);
		}
		if (c) {
			int x = rGen.nextInt(width - 3) + 1;
			int y = rGen.nextInt(height - 3) + 1;
			if (x == cX)
				x++;
			if (y == cY)
				y++;
			return new Block(x, y);
		}
		return new Block(rGen.nextInt(width - 2) + 1,
				rGen.nextInt(height - 2) + 1);
	}

	public static Block getChaserPos() {
		if (c) {
			return new Block(cX, cY);
		}
		if (r) {
			int x = rGen.nextInt(width - 3) + 1;
			int y = rGen.nextInt(height - 3) + 1;
			if (x == rX)
				x++;
			if (y == rY)
				y++;
			return new Block(x, y);
		}
		return new Block(rGen.nextInt(width - 2) + 1,
				rGen.nextInt(height - 2) + 1);
	}

	public static void main(String[] arg) {
		Setup s = new Setup(5, 5);
		s.setChaser(2, 2);
		while (true) {
			Block b = Setup.getRunnerPos();
			if (b.getX() == 2 && b.getY() == 2) {
				System.out.println("Hello");
				break;
			}
		}
	}

}
