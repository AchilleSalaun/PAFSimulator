package modele;

import java.util.ArrayList;
import java.util.Date;

import alea.Alea;
import simulatorpack.Echeancier;
import simulatorpack.Evenement;

public  class Objet implements ActeurInterface 
{
	private Case etat ;
	private long timeout; // le temps limite au dela duquel l'objet quitte une file d'attente en ms 

	private int nombremax ; // tolerance au nombre dans une file
	private double lambda ;
	
		
	public Objet(Case etat, long timeout,  int nombremax)
	{
		this.etat = etat ;
		this.timeout = timeout;

		this.nombremax = nombremax ;
		
	}
	
	/** getters and setters **/
	
	public Case getEtat()
	{
		return etat ;
	}
	
	public void setEtat(Case etat)
	{
		this.etat = etat ;
	}
	
	public double getLambda()
	{
		return this.lambda;
	}
	
	public void setLambda(double coeff)
	{
		this.lambda = coeff ;
	}
	
	public long getTimeout()
	{
		return this.timeout;
	}
	
	public void setTimeout(long timeout)
	{
		this.timeout = timeout;
	}

	
	public int getNombreMax()
	{
	return nombremax ;
	}
	
	public void setNombreMax(int nombremax)
	{
		this.nombremax = nombremax ;
	}
	
	/*****************************************************************************************************/
	/** Champ d'actions **/
	
	@Override
	public void realise( Echeancier echeancier)
	{
	/*	switch(action)
		{
		  	case 1: this.passer(echeancier) ;//passer a� la case suivante
			default : // ne rien faire  	
		} */
	}
	
	
	
	private void passer( Echeancier echeancier)
	{
		Case lieu = this.getEtat() ;
		if (lieu != (echeancier.getCurrentEvent()).getcaseActuelle()){
			return;
		}
		else if (this.getEtat() instanceof Puit){
		Date nextDate= null;
		nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + 3000);
		Evenement newEvent= new Evenement(this,3,nextDate,this.getEtat());
		echeancier.add(newEvent);
		return;
		}
		else if(this.getEtat() instanceof FileAttente && this != (this.getEtat()).getFirstObjet()){
			Date nextDate= null;
			double tpsAleatoireDouble = Alea.exponentielle(this.getLambda());
			long tpsAleatoireLong = (long) tpsAleatoireDouble ;
			nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + tpsAleatoireLong);
			Evenement newEvent= new Evenement(this,(echeancier.getCurrentEvent()).getAction(),nextDate,this.getEtat());
			echeancier.add(newEvent);
			return;
		}
		else if (this.getEtat() instanceof FileAttente && this == (this.getEtat()).getFirstObjet()){
			Case sortieMoinsRemplie = this.getEtat().compareSortie();
			if (sortieMoinsRemplie.getListeObjets().size()>= sortieMoinsRemplie.getCapacity()){
				Date nextDate= null;
				double tpsAleatoireDouble = Alea.exponentielle(this.getLambda());
				long tpsAleatoireLong = (long) tpsAleatoireDouble ;
				nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + tpsAleatoireLong);
				Evenement newEvent= new Evenement(this,(echeancier.getCurrentEvent()).getAction(),nextDate,this.getEtat());
				echeancier.add(newEvent);
				return;
			}
			else if (sortieMoinsRemplie.getListeObjets().size() < sortieMoinsRemplie.getCapacity()){
			this.getEtat().getListeObjets().remove(this);
			this.setEtat(sortieMoinsRemplie);
			sortieMoinsRemplie.getListeObjets().add(this);
				if(sortieMoinsRemplie instanceof FileAttente){
					Date nextDate= null;
					double tpsAleatoireDouble = Alea.exponentielle(this.getLambda());
					long tpsAleatoireLong = (long) tpsAleatoireDouble ;
					nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + tpsAleatoireLong);
					Evenement newEvent= new Evenement(this,(echeancier.getCurrentEvent()).getAction(),nextDate,this.getEtat());
					echeancier.add(newEvent);
					return;
				}
				else if (sortieMoinsRemplie instanceof Puit){
					Date nextDate= null;
					nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + this.getTimeout());
					Evenement newEvent= new Evenement(this,(echeancier.getCurrentEvent()).getAction(),nextDate,this.getEtat());
					echeancier.add(newEvent);
					return;
				}
			}
		}
	}
	
	/* private void partir( Echeancier echeancier)
	{
		this.setEtat((this.getEtat()).getSortie()
	} */
	
}
