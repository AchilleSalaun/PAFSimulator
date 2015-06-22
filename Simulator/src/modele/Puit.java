package modele;

import simulatorpack.Echeancier;

public class Puit extends Case implements ActeurInterface {
private int compteur = 0;
	
	public void incrementer(){
		compteur++;
	}

	@Override
	public void realise(Echeancier echeancier) {
		// TODO Auto-generated method stub
		
	}
}


