package annGaExtentions;

import geneticBase.Genome;
import breed.BaseBreeder;

public class AnnWeightBreeder extends BaseBreeder{

	@Override
	protected Genome breed(String g1, String g2) {
		//should be same length, el refers to elements
		String[] el1 = g1.split("_"), el2 = g2.split("_");
		int split = r.nextInt(g1.length());
		String child = "";
		for (int i=0;i<el1.length;i++)
		{
			if(i<split)
			{
				child += el1[i]+"_";
			}
			else
			{
				child += el2[i]+"_";
			}
		}
		return new Genome(child);
	}


}
