package annGaExtentions;

import java.util.ArrayList;
import java.util.Random;

import annModel.Layer;
import annModel.NeuralConnection;
import annNetwork.BaseNetwork;

public class GANetwork extends BaseNetwork{

	public String createTemplateGene()
	{
		ArrayList<NeuralConnection> cons = getAllConnections();
		String gene = "";
		Random r = new Random();
		for (NeuralConnection nc:cons)
		{
			gene += (double)r.nextInt(10)/10+"_";
		}
		return gene;
	}
	
	public String createTemplateFromNetwork()
	{
		ArrayList<NeuralConnection> cons = getAllConnections();
		String gene = "";
		for (NeuralConnection nc:cons)
		{
			gene += nc.Weight()+"_";
		}
		return gene;
	}
	
	private ArrayList<NeuralConnection> getAllConnections()
	{
		ArrayList<NeuralConnection> cons = new ArrayList<NeuralConnection>();
		ArrayList<Layer> layers = network.LayersForward();
		for (Layer l:layers)
		{
			cons.addAll(l.Connections());
		}
		return cons;
	}
	
	public void updateWeights(ArrayList<Float> w)
	{
		ArrayList<NeuralConnection> cons = getAllConnections();
		
		for (int i=0;i<w.size();i++)
		{
			cons.get(i).setWeight(w.get(i));
		}
		resetNodes(network);
	}
	
}
