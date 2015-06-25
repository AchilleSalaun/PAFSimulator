package modele;

import java.util.Date;

import simulatorpack.Echeancier;


public abstract class Acteur 
{	
	public void realise( Echeancier echeancier)
	{
		int action = echeancier.getCurrentEvent().getAction();
		switch(action)
		{
	  		case 0: this.generer(echeancier) ;// generer un objet
	  			break ;
	  		case 1: this.evacuer(echeancier) ; //evacuer un puit
	  			break ;
	  		case 2: this.passer(echeancier) ; //passer à la case suivante en étant premier
	  			break ;
	  		case 3: this.partir(echeancier)  ; // partir par une échappatoire
	  			break ;
	  		case 4: this.arreter(echeancier); //evenement de fin, l'echeancier est evacue
	  			break;
	  		default : System.out.println("default") ; //ne rien faire  	
		}
	}

	public void arreter(Echeancier echeancier) 
	{
		// TODO Auto-generated method stub
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
	
	public Date creationNextDate(Echeancier echeancier, double lambda)
	{
		Date nextDate = new Date();
		long nextTime = echeancier.getCurrentEvent().getDate().getTime();
		long tau = (long)(Choix.exponentielle(lambda)*100000);
		nextTime = nextTime + tau ;
		nextDate.setTime(nextTime);
		return nextDate;
	}
}
