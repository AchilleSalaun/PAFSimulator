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
	
	@Override
	public void evacuer(Echeancier echeancier)
	{
		super.evacuer(echeancier);
		System.out.println("Evacuation : "+echeancier.getCurrentEvent().getDate()+" par "+echeancier.getCurrentEvent().getActeur());
		(this.getListeObjets()).clear();
	}

	public int getCompteur() 
	{
		return compteur;
	}
	
	//La methode setCompteur ne sert qu'aux tests des graphiques, ne pas utiliser dans la simulation
	public void setCompteur(int valeur) 
	{
		compteur = valeur;
		
	}
}


