package test;

import java.io.IOException;

import maze.MazeController;
import maze.MazeView;
import maze.Setup;
import maze.model.Chaser;
import maze.model.GeneticRunner;
import maze.model.MazeModel;

import annGaExtentions.GANetwork;

//sample that shows running the ga ann from a file
public class RunFromFile {

	public static void main(String[] args) throws InterruptedException,
			IOException {

		Setup s = new Setup(10, 10);
		s.setRunner(5,5);
		
		String path = "networks/net.dat";

		GANetwork net = new GANetwork();
		
		try {
			net.loadFromFile(path);
		} catch (IOException e1) {
			GeneticRunner r = new GeneticRunner(Setup.getRunnerPos(), net);

			net.addInputLayer(r.getPerceptions());
			net.addHiddenLayer(2);
			net.addOutputLayer(r.getActions());
			net.createNetwork();
		}

		net.setDebug(false);
		MazeModel model = new MazeModel(10, 10);
		MazeView view = new MazeView(model);

		MazeController controller = new MazeController(model, view);

		controller.add(new GeneticRunner(Setup.getRunnerPos(), net), new Chaser(Setup.getChaserPos()));
		view.createGUI();

		net.outputNetwork();
		
		model.setMaxScore(-1);
		
		controller.setSpeed(100);
		while (true) {
			controller.run();
			controller.reset();
		}
	}

}
