package modele;

import java.util.ArrayList;
import java.util.Date;
import modele.Puit;

import alea.Alea;
import simulatorpack.Echeancier;
import simulatorpack.Evenement;

public  class Objet implements ActeurInterface 
{
	private Case etat ;
	private double timeout; // le temps limite au dela duquel l'objet quitte une file d'attente
//	private double priority; // priorite intrinseque a l'interieur d'une file d'attente
	private int nombremax ; // tolerance au nombre dans une file
	private double lambda ;
		
	public Objet(Case etat, double timeout, double priority, int nombremax)
	{
		this.etat = etat ;
		this.timeout = timeout;
	//	this.priority = priority ;
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
	
	public double getTimeout()
	{
		return this.timeout;
	}
	
	public void setTimeout(double timeout)
	{
		this.timeout = timeout;
	}
	
/*	public double getPriority()
	{
		return priority ;
	}
	
	public void setPriority(double priority)
	{
		this.priority = priority ;
	} */
	
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
	
	/*private void patienter( Echeancier echeancier)
	{
		Case caseactuelle = this.getEtat() ;
		long attente = caseactuelle.getWait();
		long currentTimeMs = (Echeancier.getCurrentDate()).getTime();
		Date nextDate= null;
		nextDate.setTime(attente + currentTimeMs);	
		// Définir création de l'évenement à venir
		Evenement newEvent= new Evenement(this,nextDate,2);
	} */
	
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
				
			}
		}
	}
	
	/* private void partir( Echeancier echeancier)
	{
		this.setEtat((this.getEtat()).getSortie()
	} */
	
}
