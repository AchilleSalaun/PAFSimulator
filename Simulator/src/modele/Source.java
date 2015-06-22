package modele;

import java.util.Date;

import alea.Alea;
import simulatorpack.Echeancier;
import simulatorpack.Evenement;



public class Source extends Case implements ActeurInterface
{
	private long retry;
	
	public long getRetry(){
		return this.retry;
	}

	public void realise(int action, Echeancier echeancier)
	{
		switch(action)
		{
		  	case 1: this.generer(echeancier) ; //generer objet
	
		}
	}
	
	
	//il sera peut-etre plus simlpe de faire deux methode generer pour burger et client avec des paramétres différents
	//Il faudrait donc modifier les int des actions dans le case et dans ce code
	private void generer(Echeancier echeancier)
	{	
		Case sortieMoinsRemplie = this.compareSortie();
		if (sortieMoinsRemplie.getListeObjets().size() >= sortieMoinsRemplie.getCapacity()){
			Date nextDate= null;
			nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + this.getRetry());
			Evenement newEvent= new Evenement(this,0,nextDate,this);
			echeancier.add(newEvent);
		}
		else {
			Objet obj =new Objet(sortieMoinsRemplie,(long)(10000 + Alea.exponentielle(100)), (int)Alea.exponentielle(30));
			Date nextDate= null;
			nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + this.getRetry());
			Evenement newEvent= new Evenement(this,0,nextDate,this);
			echeancier.add(newEvent);
		}
	}

	@Override
	public void realise(Echeancier echeancier) {
		// TODO Auto-generated method stub
		
	}




}
