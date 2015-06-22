package modele;

import simulatorpack.Echeancier;

public class Puit extends Case implements ActeurInterface {
private int compteur = 0;
	
	public void incrementer(){
		compteur++;
	}
	
	public void evacuer(){
		(this.getListeObjets()).clear();
	}

	@Override
	public void realise(Echeancier echeancier) {
		// TODO Auto-generated method stub
		
	}
}


