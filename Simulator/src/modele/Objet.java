package modele;

import java.util.Date;
import java.util.LinkedList;

import modele.Puit;
import alea.Alea;
import simulatorpack.Echeancier;
import simulatorpack.Evenement;

public  class Objet extends Acteur 
{
	private Case etat ;
	private long timeout; // le temps limite au dela duquel l'objet quitte une file d'attente en ms 
	private int nombremax ; // tolerance au nombre dans une file
	private double lambda ; 
	
		
	public Objet(Case etat, long timeout,  int nombremax)
	{
		this.etat = etat ;
		this.timeout = timeout;

		this.nombremax = nombremax ;
		
	}
	
	/********************************************************************************************************/
	/** getters and setters **/
	
	public Case getEtat()
	{
		return etat ;
	}
	
	public void setEtat(Case etat)
	{
		this.etat = etat ;
	}
	
	public double getLambda()
	{
		return this.lambda;
	}
	
	public void setLambda(double coeff)
	{
		this.lambda = coeff ;
	}
	
	public long getTimeout()
	{
		return this.timeout;
	}
	
	public void setTimeout(long timeout)
	{
		this.timeout = timeout;
	}

	
	public int getNombreMax()
	{
	return nombremax ;
	}
	
	public void setNombreMax(int nombremax)
	{
		this.nombremax = nombremax ;
	}
	
	/*****************************************************************************************************/
	/** Champ d'actions **/

	//parametre des event à vérifier
	//@Override
	public void passer( Echeancier echeancier)
	{
		super.passer(echeancier);
		System.out.println("Démarrage passer : "+ echeancier.getCurrentEvent().getDate());
		
		Case lieu = this.getEtat() ;
		
		// je veux quitter une case dont je suis deja parti
		if (lieu != echeancier.getCurrentEvent().getcaseActuelle())
		{
			System.out.println("Evenement obsolete : "+ echeancier.getCurrentEvent().getDate());
			return;
		}
		
		//je suis dans un puit
		if (this.getEtat() instanceof Puit)
		{
			if(this.isIn(this.getEtat()))
			{
				Date nextDate= new Date();
				long nextTime = echeancier.getCurrentEvent().getDate().getTime() ;
				nextDate.setTime(nextTime);
				Evenement newEvent= new Evenement(this.getEtat(),1,nextDate,this.getEtat());
				echeancier.add(newEvent);
				System.out.println("Demande evacuation : "+ echeancier.getCurrentEvent().getDate());
				return;
			}
			else return ;
		}
		
		// je ne suis pas le premier dans la file
		if(/*this.getEtat() instanceof FileAttente && */this != (this.getEtat().getFirstObjet()))
		{
			Date nextDate= new Date();
			double tpsAleatoireDouble = Alea.exponentielle(this.getLambda());
			long tpsAleatoireLong = (long) tpsAleatoireDouble ;
			nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + tpsAleatoireLong);
			Evenement newEvent= new Evenement(this,2,nextDate,this.getEtat());
			echeancier.add(newEvent);
			System.out.println("Attente de mon tour : "+ echeancier.getCurrentEvent().getDate());
			return;
		}
		// c'est mon tour
		else //if (/*this.getEtat() instanceof FileAttente && */this == (this.getEtat().getFirstObjet()))
		{
			System.out.println("premier");
			Case sortieMoinsRemplie = this.getEtat().compareSortie();
			// pas de sortie praticable
			if (sortieMoinsRemplie.getListeObjets().size()>= sortieMoinsRemplie.getCapacity())
			{
				Date nextDate= new Date();
				double tpsAleatoireDouble = Alea.exponentielle(this.getLambda());
				long tpsAleatoireLong = (long) tpsAleatoireDouble ;
				nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + tpsAleatoireLong);
				Evenement newEvent= new Evenement(this,2,nextDate,this.getEtat());
				echeancier.add(newEvent);
				System.out.println("Attente d'une sortie : "+ echeancier.getCurrentEvent().getDate());
				return;
			}
			else if (sortieMoinsRemplie.getListeObjets().size() < sortieMoinsRemplie.getCapacity())
			{
				this.getEtat().getListeObjets().remove(this);
				this.setEtat(sortieMoinsRemplie);
				sortieMoinsRemplie.getListeObjets().add(this);
			
				Date nextDate= new Date();
				double tpsAleatoireDouble = Alea.exponentielle(this.getLambda());
				long tpsAleatoireLong = (long) tpsAleatoireDouble ;
				nextDate.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + tpsAleatoireLong);
				Evenement newEvent= new Evenement(this,2,nextDate,this.getEtat());
				echeancier.add(newEvent);
				System.out.println("Passé : "+ echeancier.getCurrentEvent().getDate());
				
				// mise en place Time Out eventuel
				if (sortieMoinsRemplie instanceof FileAttente)
				{
					Date nextDate2= new Date();
					nextDate2.setTime(((echeancier.getCurrentEvent()).getDate()).getTime() + this.getTimeout());
					Evenement event= new Evenement(this,3,nextDate2,this.getEtat());
					echeancier.add(event);
					System.out.println("Création TimeOut : "+ echeancier.getCurrentEvent().getDate());
					return;
				}
			}
		}
	}

	private boolean isIn(Case etat) 
	{
		LinkedList<Objet> list = etat.getListeObjets() ;
		for(Objet objet : list )
		{
			if(objet==this)
			{
				return true ;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}
	
}
