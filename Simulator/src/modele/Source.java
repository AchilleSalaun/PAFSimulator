package modele;

import java.util.ArrayList;
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
		System.out.println("Démarrage générer : "+ echeancier.getCurrentEvent().getDate());
		
		Objet objet = new Objet(this, lambda, lambdaTimeOut, nombremax);
		this.getListeObjets().add(objet);
		
		/** Forward **/
		// Est ce que je peux sortir ?		
		if(this.getC_Out())
		{
			
			ArrayList<Case> liste = new ArrayList<Case>();
			liste.addAll(this.getSortie()) ;
			Case forward = this ;
			int s = liste.size() ;
			boolean available = false ;
			
			while(s>0 && !available )
			{	
				// choix destination passage : loi uniforme
				int i = Alea.getRandomIndex(liste) ;		
				forward = liste.remove(i);
				available = ((forward.getCapacity()>forward.getListeObjets().size()) && (forward.getC_In()));
				s = liste.size();
				System.out.println("available = "+available);
			}
						
			// si on trouve une sortie
			if (available)
			{
				Date nextDate1 = this.creationNextDate(echeancier, lambda);
			
				Evenement newEvent= new Evenement(objet,2,nextDate1,this,forward);
				echeancier.add(newEvent);
				System.out.println(objet+" partira de "+this+" pour "+forward+" le "+nextDate1);
			}
		}
	}
		
	
	@Override
	public void arreter(Echeancier echeancier)
	{
		super.arreter(echeancier);
		echeancier.clear();
		System.out.println("Evenement de fin");
	}

}
