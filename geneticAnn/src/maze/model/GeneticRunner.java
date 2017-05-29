package maze.model;


import java.util.ArrayList;

import annGaExtentions.GANetwork;

import data.DataNormaliser;



public class GeneticRunner extends Runner {

	GANetwork net;

	private int actions = 4;

	private int perceptions = pX.length;

	ArrayList<Float> weights;

	public GeneticRunner(int x, int y, GANetwork n) {
		super(x, y);
		net = n;
	}
	
	public GeneticRunner(Block b, GANetwork n) {
		super(b);
		net = n;
	}

	public void setWeights(ArrayList<Float> f) {
		weights = f;
	}

	@Override
	public void doAction() {
		super.doAction();
		if (dead)return;
		ArrayList<Float> f = new ArrayList<Float>();
		for (int b : sees) {
			f.add(DataNormaliser.normalise(0, 2, b));
		}
		if (weights != null)
			net.updateWeights(weights);
		net.addInputData(f);
		Float[] out = net.outputNetwork();
		int pos = 1;
		double max = 0;
		for (int i = 1; i < out.length; i++) {
			max = Math.max(max, out[i]);
			if (max == out[i])
				pos = i;
		}
		move(pos - 1);
	}

	public int getPerceptions() {
		return perceptions;
	}

	public void setPerceptions(int perceptions) {
		this.perceptions = perceptions;
	}

	public int getActions() {
		return actions;
	}

	public void setActions(int actions) {
		this.actions = actions;
	}

}
