package modele;

import simulatorpack.Echeancier;

public class Puit extends Case
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
		super.evacuer(echeancier);
		System.out.println("Evacuation : "+echeancier.getCurrentEvent().getDate());
		(this.getListeObjets()).clear();
	}
}


