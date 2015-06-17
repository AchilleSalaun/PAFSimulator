package modele;

import simulatorpack.ActeurInterface;

public abstract class Objet implements ActeurInterface {

	private double timeout; //pour le burger, temps avant d'etre jete; pour le client, temps avant de partir
	
	public Objet(double timeout){
		this.timeout = timeout;
	}
	
	public double getTimeout(){
		return this.timeout;
	}
}
