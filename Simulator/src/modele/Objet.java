package modele;

import simulatorpack.ActeurInterface;

public abstract class Objet implements ActeurInterface 
{

	private double timeout; //pour le burger, temps avant d'etre jete; pour le client, temps avant de partir
	private double priority; //
	
	
	
	
	public Objet(double timeout, double priority)
	{
		this.timeout = timeout;
		this.priority = priority ;
		
	}
	
	public double getTimeout()
	{
		return this.timeout;
	}
	
	public void setTimeout()
	{
		
	}
}
