package test;

import java.io.IOException;
import java.util.ArrayList;

import mutate.IMutator;
import annGaExtentions.AnnEvaluator;
import annGaExtentions.AnnMutator;
import annGaExtentions.AnnWeightBreeder;
import annGaExtentions.GANetwork;
import breed.IBreeder;
import cull.CullHighest;
import cull.ICuller;
import eval.IEvaluator;
import geneticBase.GAController;
import geneticBase.Genome;
import helper.AnnGaConversion;

public class TrainFromFile {

	public static void main(String[] args) {
		
		String path = "networks/trainFromFile.dat";

		GANetwork net = new GANetwork();
		
		try {
			net.loadFromFile(path);
		} catch (IOException e1) {
			//init network
			net.addInputLayer(5);//5 inputs
			net.addHiddenLayer(10);
			net.addOutputLayer(3);//3 outputs
			
			ArrayList<Float> input = new ArrayList<>();
			for (int i=0;i<net.countIns();i++)
			{
				input.add(1f);
			}
			ArrayList<Float> output = new ArrayList<>();
			for (int i=0;i<net.countOuts();i++)
			{
				output.add(0f);
			}
			
			net.addInputData(input);
			net.addOutputData(output);
			net.createNetwork();
		}

		net.setDebug(true);

		IBreeder b = new AnnWeightBreeder();
		IMutator m = new AnnMutator(null);
		ICuller c = new CullHighest();

		IEvaluator e = new AnnEvaluator(net, 10000);

		GAController gac = new GAController(b, m, c, e);
		gac.setMaxPop(25);
		gac.setBreedRate(10);
		gac.setMutationRate(0.05);

		String geneTemplate = net.createTemplateFromNetwork();
		gac.addGene(new Genome(geneTemplate));
		gac.addGene(new Genome(geneTemplate));
		
		Genome end = gac.runUntil(50);//runs the gnn until required score
		System.out.println("Done " + end.Score() + " " + end.String());
		net.updateWeights(AnnGaConversion.StringToWeight(end.String()));
		net.outputNetwork();
	}

}
