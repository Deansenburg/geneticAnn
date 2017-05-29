package annGaExtentions;

import geneticBase.Genome;

import java.util.ArrayList;

import mutate.BaseMutator;
import mutate.IMutator;

public class AnnMutator extends BaseMutator{
	
	public AnnMutator(IMutator m) {
		super(m);
	}
	
	private float randomAmount()
	{
		return ((float)r.nextInt(200)/100) - 1;
	}
	
	@Override
	public void mutate(double percentage, ArrayList<Genome> genes) {
		for (Genome g:mutateList(percentage, genes))
		{
			String[] s = g.String().split("_");
			String newGen = "";
			for (String s1:s)
			{
				if (r.nextInt((int) (1d / percentage))==0)
				{
					newGen += Float.parseFloat(s1)+randomAmount();
				}
				else
				{
					newGen += s1;
				}
				newGen += "_";
			}
			g.setString(newGen);
		}
		super.mutate(percentage, genes);
	}

}
