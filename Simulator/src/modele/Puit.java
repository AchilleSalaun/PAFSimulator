package modele;

import simulatorpack.Echeancier;

public class Puit extends Case implements ActeurInterface 
{
	public Puit() 
	{
		super(Integer.MAX_VALUE);
		// TODO Auto-generated constructor stub
	}

private int compteur = 0;
	
	public void incrementer()
	{
		compteur++;
	}
	
	public void evacuer(Echeancier echeancier)
	{
		System.out.println("Evacuation : "+echeancier.getCurrentEvent().getDate());
		(this.getListeObjets()).clear();
	}

	@Override
	public void realise(Echeancier echeancier) 
	{
		// TODO Auto-generated method stub
		int action = echeancier.getCurrentEvent().getAction();
		switch(action)
		{
		  	case 3: this.evacuer(echeancier) ; //generer objet
	
		}
	}
}


