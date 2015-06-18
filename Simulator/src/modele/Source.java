package modele;

import simulatorpack.Echeancier;


public class Source extends Case implements ActeurInterface
{

	public void realise(int action, Echeancier echeancier)
	{
		switch(action)
		{
		  	case 1: this.generer(echeancier) ; //generer objet
	
		}
	}
	
	private void generer(Echeancier echeancier)
	{	
		
	}




}
