package modele;

import java.util.ArrayList;

import simulatorpack.Echeancier;

public abstract class Objet implements ActeurInterface 
{
	private Case etat ;
	private ArrayList<Integer> listeactions ; 
	
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
	public void realise(int action, Echeancier echeancier)
	{
		switch(action)
		{
		  	case 1: this.patienter(echeancier) ; //patienter
		  	case 2: this.passer(echeancier) ; //passer a  la case suivante
		  	case 3: this.partir(echeancier) ; // partir 
			default : // ne rien faire  	
		}
	}
	
	private void patienter( Echeancier echeancier)
	{
		Case caseactuelle = this.getEtat() ;
		
		
	}
	
	private void passer( Echeancier echeancier)
	{
		Case caseactuelle = this.getEtat() ;
	}
	
	private void partir( Echeancier echeancier)
	{
		Case caseactuelle = this.getEtat() ;
	}
	
}
