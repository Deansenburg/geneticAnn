package helper;

import java.util.ArrayList;

public class AnnGaConversion {

	public static ArrayList<Float> StringToWeight(String gene) {
		ArrayList<Float> weights = new ArrayList<Float>();
		String[] s = gene.split("_");
		for (String s1 : s) {
			weights.add(Float.parseFloat(s1));
		}
		return weights;
	}

}
