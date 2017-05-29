package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import maze.MazeController;
import maze.MazeView;
import maze.Setup;
import maze.model.Chaser;
import maze.model.GeneticRunner;
import maze.model.MazeModel;
import mazeGenetics.MazeEvaluatorStrict;
import mutate.IMutator;
import annGaExtentions.AnnMutator;
import annGaExtentions.AnnWeightBreeder;
import annGaExtentions.GANetwork;
import breed.IBreeder;
import cull.CullLowest;
import cull.ICuller;
import eval.IEvaluator;
import geneticBase.GAController;
import geneticBase.Genome;
import helper.AnnGaConversion;

//sample that shows training a ga ann from file
public class TrainFromFile {

	public static void main(String[] args) {
		String path = "networks/net.dat";

		Setup s = new Setup(10,10);
		s.setRunner(5, 5);

		GANetwork net = new GANetwork();

		try {
			net.loadFromFile(path);
		} catch (IOException e1) {
			GeneticRunner r = new GeneticRunner(Setup.getRunnerPos(), net);

			net.addInputLayer(r.getPerceptions());
			net.addHiddenLayer(10);
			net.addOutputLayer(r.getActions());
			
			net.createNetwork();
		}
		net.setDebug(false);

		MazeModel model = new MazeModel(10, 10);
		MazeView view = new MazeView(model);

		MazeController controller = new MazeController(model, view);

		model.setMaxScore(100);

		controller.add(new GeneticRunner(Setup.getRunnerPos(), net),
				new Chaser(Setup.getChaserPos()));

		IBreeder b = new AnnWeightBreeder();
		IMutator m = new AnnMutator(null);
		ICuller c = new CullLowest();

		IEvaluator e = new MazeEvaluatorStrict(controller, net, 20);

		GAController gac = new GAController(b, m, c, e);

		gac.setMaxPop(25);
		gac.setBreedRate(10);
		gac.setMutationRate(0.05);

		String geneTemplate = net.createTemplateFromNetwork();
		gac.addGene(new Genome(geneTemplate));
		gac.addGene(new Genome(geneTemplate));

		view.createGUI();

		// net.outputNetwork();

		controller.setSpeed(1);

		Genome end = gac.runUntil(18);
		System.out.println("Done " + end.Score() + " " + end.String());

		controller.clear();
		GeneticRunner endRunner = new GeneticRunner(Setup.getRunnerPos(), net);
		endRunner.setWeights(AnnGaConversion.StringToWeight(end.String()));
		controller.add(endRunner, new Chaser(Setup.getChaserPos()));

		controller.setSpeed(100);

		try {
			net.saveToFile(path);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * net.setDebug(true); net.outputNetwork();
		 */

		while (true) {
			controller.run();
			controller.reset();
		}
	}

}
