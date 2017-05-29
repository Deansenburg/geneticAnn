package mazeGenetics;

import eval.IEvaluator;
import geneticBase.Genome;
import helper.AnnGaConversion;

import java.util.ArrayList;

import maze.MazeController;
import maze.Setup;
import maze.model.Chaser;
import maze.model.GeneticRunner;

import annGaExtentions.GANetwork;

public class MazeEvaluatorStrict implements IEvaluator{

	MazeController con;
	GANetwork net;

	int iterations;
	
	public MazeEvaluatorStrict(MazeController c, GANetwork net, int iter) {
		con = c;
		this.net = net;
		iterations = iter;
	}

	@Override
	public void evaluate(ArrayList<Genome> genes) {
		int[] scores = new int[genes.size()];

		for (int ii = 0; ii < iterations; ii++) {
			con.clear();
			for (Genome g : genes) {
				GeneticRunner r = new GeneticRunner(Setup.getRunnerPos(), net);
				r.setWeights(AnnGaConversion.StringToWeight(g.String()));
				con.add(r, new Chaser(Setup.getChaserPos()));
			}

			int place = 0;
			for (Integer i : con.getScores()) {
				if (scores[place] == 0)
					scores[place] = i;
				else
					scores[place] = Math.min((int) scores[place], i);
				place++;
			}
		}

		for (int i = 0; i < scores.length; i++) {
			genes.get(i).setScore((int) scores[i]);
		}
	}

}
