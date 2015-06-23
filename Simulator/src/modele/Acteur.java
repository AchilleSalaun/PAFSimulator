package modele;

import simulatorpack.Echeancier;


public abstract class Acteur 
{
	
	public void realise( Echeancier echeancier)
	{
		int action = echeancier.getCurrentEvent().getAction();
		System.out.println("realisation "+this);
		switch(action)
		{
	  		case 0: this.generer(echeancier) ;// generer un objet
	  		case 1: this.evacuer(echeancier) ; //evacuer un puit
	  		case 2: this.passer(echeancier) ; //passer à la case suivante
	  		case 3: this.partir(echeancier)  ;      
	  		default : //ne rien faire  	
		}
	}

	public void partir(Echeancier echeancier) 
	{
		// TODO Auto-generated method stub	
	}

	public void passer(Echeancier echeancier) 
	{
		// TODO Auto-generated method stub	
	}

	public void evacuer(Echeancier echeancier) 
	{
		// TODO Auto-generated method stub	
	}

	public void generer(Echeancier echeancier) 
	{
		// TODO Auto-generated method stub	
	}
}
