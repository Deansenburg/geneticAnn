package maze.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class UserChaser extends Chaser implements KeyListener {

	public UserChaser(int x, int y) {
		super(x, y);
	}

	public UserChaser(Block chaserPos) {
		super(chaserPos);
	}

	private int action = -1;

	@Override
	public void doAction() {
		move(action);
		action = -1;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_W:
			action =3;
			break;
		case KeyEvent.VK_S:
			action = 2;
			break;
		case KeyEvent.VK_A:
			action = 1;
			break;
		case KeyEvent.VK_D:
			action = 0;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
