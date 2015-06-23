package modele;

import java.util.Date;

import alea.Alea;
import simulatorpack.Echeancier;
import simulatorpack.Evenement;



public class Source extends Case
{
	private double lambdaGene;
	private double lambda ; 
	private double lambdaTimeOut; // le temps limite au dela duquel l'objet quitte une file d'attente en ms 
	private int nombremax ; // tolerance au nombre dans une file
	
	
	public double getlambdaGene()
	{
		return this.lambdaGene;
	}

	public Source(double lambdaGene, double lambda, double lambdaTimeOut, int nombremax)
	{
		super(0);
		this.lambdaGene = lambdaGene ;
		this.lambda = lambda ;
		this.lambdaTimeOut = lambdaTimeOut ;
		this.nombremax = nombremax ;
	}
	
	@Override
	public void generer(Echeancier echeancier)
	{	
		super.generer(echeancier);
		Date nextDateGeneration = this.creationNextDate(echeancier, lambdaGene);
		Date nextDatePassage = this.creationNextDate(echeancier, lambda);
		Date nextDateTimeOut = this.creationNextDate(echeancier, lambdaTimeOut);
		Case sortieMoinsRemplie = this.compareSortie();
		
		if (sortieMoinsRemplie.getListeObjets().size() >= sortieMoinsRemplie.getCapacity())
		{
			Evenement newEvent= new Evenement(this,0,nextDateGeneration,this);
			echeancier.add(newEvent);
			System.out.println("Generation en attente : "+echeancier.getCurrentEvent().getDate());
		}
		else 
		{
			Objet obj = new Objet(sortieMoinsRemplie,lambda, lambdaTimeOut, nombremax);
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
	
	@Override
	public void arreter(Echeancier echeancier)
	{
		super.arreter(echeancier);
		echeancier.clear();
	}

}
