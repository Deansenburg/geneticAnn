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



public class MazeEvaluator implements IEvaluator{

	MazeController con;
	GANetwork net;
	
	public MazeEvaluator(MazeController c, GANetwork net) {
		con = c;
		this.net = net;
	}
	
	@Override
	public void evaluate(ArrayList<Genome> genes) {
		con.clear();
		for (Genome g:genes)
		{
			GeneticRunner r = new GeneticRunner(Setup.getRunnerPos(), net);
			r.setWeights(AnnGaConversion.StringToWeight(g.String()));
			con.add(r, new Chaser(Setup.getChaserPos()));
		}
		ArrayList<Integer> scores = con.getScores();
		for (int i=0;i<scores.size();i++)
		{
			genes.get(i).setScore(scores.get(i));
		}
	}


}
