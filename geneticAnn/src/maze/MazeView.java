package maze;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import maze.model.Block;
import maze.model.MazeModel;
import maze.model.Runner;

public class MazeView extends Component {

	MazeModel maze;
	
	JFrame main;
	
	public MazeView(MazeModel m)
	{
		maze = m;
	}
	
	
	 @Override
	public synchronized void addKeyListener(KeyListener k) {
		 main.addKeyListener(k);
	}
	public void createGUI()
	{
		main = new JFrame();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.add(this);
		main.setVisible(true);
		main.pack();
		main.repaint();
	}
	
	@Override
	public void paint(Graphics g) {		
		int scale = 5;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, maze.getWidth()*scale, maze.getHeight()*scale);
		
		g.setColor(Color.BLACK);
		for (Block b:(ArrayList<Block>) maze.getWalls().clone())
		{
			g.fillRect(b.getX()*scale, b.getY()*scale,1*scale, 1*scale);
		}
		
		g.setColor(Color.RED);
		for (Runner r:(ArrayList<Runner>)maze.getMazeRunners().clone())
		{
			g.fillRect(r.getSprite().getX()*scale, r.getSprite().getY()*scale, 1*scale, 1*scale);
		}
		
		Color b = Color.BLUE;
		g.setColor(new Color(b.getRed(), b.getGreen(), b.getBlue(), 100));
		for (Runner r:(ArrayList<Runner>)maze.getMazeChasers().clone())
		{
			g.fillRect(r.getSprite().getX()*scale, r.getSprite().getY()*scale, 1*scale, 1*scale);
		}		
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}
	
}
