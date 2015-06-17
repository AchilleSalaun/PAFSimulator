package modele;

import simulatorpack.ActeurInterface;

public abstract class Objet implements ActeurInterface 
{
	private Case etat ;
	
	private double timeout; // le temps limite au dela duquel l'objet quitte une file d'attente
	private double priority; // priorite intrinseque a l'interieur d'une file d'attente
	private int nombremax ; // tolerance au nombre dans une file
		
	public Objet(Case etat, double timeout, double priority)
	{
		this.etat = etat ;
		this.timeout = timeout;
		this.priority = priority ;
		
	}
	
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
	
}
