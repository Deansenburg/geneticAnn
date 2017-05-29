package annGaExtentions;

import geneticBase.Genome;
import helper.AnnGaConversion;

import java.util.ArrayList;

import eval.IEvaluator;


public class AnnEvaluator implements IEvaluator{

	GANetwork network;
	
	int valueAccuracy;
	
	public AnnEvaluator(GANetwork net, int accuracy)
	{
		network = net;
		valueAccuracy = accuracy;
	}
	
	@Override
	public void evaluate(ArrayList<Genome> genes) {
		for (Genome g:genes)
		{
			ArrayList<Float> weights = AnnGaConversion.StringToWeight(g.String());
			
			network.updateWeights(weights);
			g.setScore((int)(network.singleRun(0)*valueAccuracy));
		}
	}


}
