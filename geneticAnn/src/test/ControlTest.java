package test;

import java.io.IOException;

import maze.MazeController;
import maze.MazeView;
import maze.Setup;
import maze.model.GeneticRunner;
import maze.model.MazeModel;
import maze.model.UserChaser;

import annGaExtentions.GANetwork;

//sample showing user control against the network
public class ControlTest {

	public static void main(String[] args) throws IOException {

		Setup s = new Setup(10, 10);
		s.setRunner(5,5);
		
		String path = "networks/net.dat";

		GANetwork net = new GANetwork();
		net.loadFromFile(path);
		net.setDebug(false);		
		
		MazeModel model = new MazeModel(10, 10);
		MazeView view = new MazeView(model);

		MazeController controller = new MazeController(model, view);
		controller.setSpeed(100);

		model.setMaxScore(-1);

		UserChaser user = new UserChaser(Setup.getChaserPos());

		controller.add(new GeneticRunner(Setup.getRunnerPos(), net), user);

		view.createGUI();

		view.addKeyListener(user);

		while (true) {
			controller.run();
			controller.reset();
		}
	}

}
