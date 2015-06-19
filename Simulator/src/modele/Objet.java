package modele;

import java.util.ArrayList;
import java.util.Date;

import simulatorpack.Echeancier;
import simulatorpack.Evenement;

public  class Objet implements ActeurInterface 
{
	private Case etat ;
	private double timeout; // le temps limite au dela duquel l'objet quitte une file d'attente
	private double priority; // priorite intrinseque a l'interieur d'une file d'attente
	private int nombremax ; // tolerance au nombre dans une file
		
	public Objet(Case etat, double timeout, double priority, int nombremax)
	{
		this.etat = etat ;
		this.timeout = timeout;
		this.priority = priority ;
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
	
	public double getTimeout()
	{
		return this.timeout;
	}
	
	public void setTimeout(double timeout)
	{
		this.timeout = timeout;
	}
	
	public double getPriority()
	{
		return priority ;
	}
	
	public void setPriority(double priority)
	{
		this.priority = priority ;
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
	public void realise(int action)
	{
		switch(action)
		{
		  	case 1: this.patienter() ; //patienter
		  	case 2: this.passer() ; //passer a� la case suivante
			default : // ne rien faire  	
		}
	}
	
	private void patienter()
	{
		Case caseactuelle = this.getEtat() ;
		long attente = caseactuelle.getWait();
		long currentTimeMs = (Echeancier.getCurrentDate()).getTime();
		Date nextDate= null;
		nextDate.setTime(attente + currentTimeMs);	
		// Définir création de l'évenement à venir
		Evenement newEvent= new Evenement(this,nextDate,2);
	}
	
	private void passer()
	{
		this.setEtat((this.getEtat()).getSortie()
	}
	
	
	
}
