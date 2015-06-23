package modele;

import java.util.Date;

import alea.Alea;
import simulatorpack.Echeancier;
import simulatorpack.Evenement;



public class Source extends Case
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
	@Override
	public void generer(Echeancier echeancier)
	{	
		super.generer(echeancier);
		Date nextDateGeneration = this.creationNextDate(echeancier, 2);
		Date nextDatePassage = this.creationNextDate(echeancier, 2);
		Date nextDateTimeOut = this.creationNextDate(echeancier, 1);
		Case sortieMoinsRemplie = this.compareSortie();
		
		if (sortieMoinsRemplie.getListeObjets().size() >= sortieMoinsRemplie.getCapacity())
		{
			Evenement newEvent= new Evenement(this,0,nextDateGeneration,this);
			echeancier.add(newEvent);
			System.out.println("Generation en attente : "+echeancier.getCurrentEvent().getDate());
		}
		else 
		{
			Objet obj = new Objet(sortieMoinsRemplie,(long)(10000 + Alea.exponentielle(100)), (int)Alea.exponentielle(30));
			sortieMoinsRemplie.getListeObjets().add(obj);
			Evenement newGeneration= new Evenement(this,0,nextDateGeneration,this);
			Evenement newPassage = new Evenement(obj,2,nextDatePassage,sortieMoinsRemplie);
			Evenement newTimeOut = new Evenement(obj,3,nextDateTimeOut,sortieMoinsRemplie);
			echeancier.add(newGeneration);
			echeancier.add(newPassage);
			echeancier.add(newTimeOut);
			System.out.println("Génération : "+ echeancier.getCurrentEvent().getDate());
		}
	}

}
