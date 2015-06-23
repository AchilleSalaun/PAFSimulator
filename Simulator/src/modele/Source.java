package modele;

import java.util.Date;

import alea.Alea;
import simulatorpack.Echeancier;
import simulatorpack.Evenement;



public class Source extends Case implements ActeurInterface
{
	private long retry;
	
	public long getRetry()
	{
		return this.retry;
	}

	public Source()
	{
		super(0);
	}
	
	//il sera peut-etre plus simlpe de faire deux methode generer pour burger et client avec des paramétres différents
	//Il faudrait donc modifier les int des actions dans le case et dans ce code
	private void generer(Echeancier echeancier)
	{	
		System.out.println("Début génération : "+echeancier.getCurrentEvent().getDate());
		Case sortieMoinsRemplie = this.compareSortie();
		
		if (sortieMoinsRemplie.getListeObjets().size() >= sortieMoinsRemplie.getCapacity())
		{
			Date nextDate = new Date();
			nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + this.getRetry());
			Evenement newEvent= new Evenement(this,0,nextDate,this);
			echeancier.add(newEvent);
		}
		else 
		{
			Objet obj = new Objet(sortieMoinsRemplie,(long)(10000 + Alea.exponentielle(100)), (int)Alea.exponentielle(30));
			sortieMoinsRemplie.getListeObjets().add(obj);
			Date nextDate= new Date();
			nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + this.getRetry());
			Evenement newGeneration= new Evenement(this,0,nextDate,this);
			Evenement newPassage = new Evenement(obj,1,nextDate,sortieMoinsRemplie);
			echeancier.add(newGeneration);
			echeancier.add(newPassage);
			System.out.println("Event ajouté : "+nextDate);
		}
	}

	@Override
	public void realise(Echeancier echeancier) 
	{
		// TODO Auto-generated method stub
		int action = echeancier.getCurrentEvent().getAction();
		switch(action)
		{
		  	case 0: this.generer(echeancier) ; //generer objet
	
		}
	}




}
