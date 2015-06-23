package modele;

import java.util.ArrayList;
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

	@Override
	public void passer( Echeancier echeancier)
	{
		super.passer(echeancier);
		System.out.println("Démarrage passer : "+ echeancier.getCurrentEvent().getDate());
		Date nextDateEvacuation = new Date();
		nextDateEvacuation.setTime(echeancier.getCurrentEvent().getDate().getTime());
		Date nextDate1 = this.creationNextDate(echeancier, 2);
		Date nextDateTimeOut = this.creationNextDate(echeancier, 1);
		
		Case lieu = this.getEtat() ;
		
		// je veux quitter une case dont je suis deja passé
		if (lieu != echeancier.getCurrentEvent().getcaseActuelle())
		{
			System.out.println("Evenement obsolete (passé)");
			return;
		}
		
		//je suis dans un puit
		if (this.getEtat() instanceof Puit)
		{
			if(this.isIn(this.getEtat()))
			{
				Evenement newEvent= new Evenement(this.getEtat(),1,nextDateEvacuation,this.getEtat());
				echeancier.add(newEvent);
				System.out.println("Demande evacuation : prevue pour "+ nextDateEvacuation);
				return;
			}
			else
			{
				System.out.println("Evenement obsolete (évacué)");
				return ;
			} 
			
		}
		
		// je ne suis pas le premier dans la file
		if(!this.isFirst())
		{
			Evenement newEvent= new Evenement(this,2,nextDate1,this.getEtat());
			echeancier.add(newEvent);
			System.out.println("Attente de mon tour : prochain essai le "+ nextDate1);
			System.out.println("Nombre total d'objets dans la file : "+this.getEtat().getListeObjets().size());
			return;
		}
		// c'est mon tour
		
		else
		{
			System.out.println("Enfin premier !");
			Case sortieMoinsRemplie = this.getEtat().compareSortie();
			// pas de sortie praticable
			if (sortieMoinsRemplie.getListeObjets().size()>= sortieMoinsRemplie.getCapacity())
			{
				Evenement newEvent= new Evenement(this,2,nextDate1,this.getEtat());
				echeancier.add(newEvent);
				System.out.println("Attente d'une sortie : prochain essai le "+ nextDate1);
				return;
			}
			else
			{
				this.getEtat().getListeObjets().remove(this);
				this.setEtat(sortieMoinsRemplie);
				sortieMoinsRemplie.getListeObjets().add(this);
			
				Evenement newEvent= new Evenement(this,2,nextDate1,this.getEtat());
				echeancier.add(newEvent);
				System.out.println("Passé dans "+ sortieMoinsRemplie+", prochain passage prevu pour "+nextDate1);
				
				// mise en place Time Out eventuel
				if ((sortieMoinsRemplie instanceof FileAttente) && (!sortieMoinsRemplie.getEchappatoire().isEmpty()))
				{
					Evenement event= new Evenement(this,3,nextDateTimeOut,this.getEtat());
					echeancier.add(event);
					System.out.println("Création TimeOut prevu pour "+nextDateTimeOut);
					return;
				}
			}
		}
	}
	
	@Override
	public void partir( Echeancier echeancier)
	{
		super.passer(echeancier);
		System.out.println("Démarrage partir : "+ echeancier.getCurrentEvent().getDate());
		Date nextDateEvacuation = new Date();
		nextDateEvacuation.setTime(echeancier.getCurrentEvent().getDate().getTime());
		Date nextDate1 = this.creationNextDate(echeancier, 2);
		Date nextDate2 = this.creationNextDate(echeancier, 1);
		Case lieu = this.getEtat() ;
		
		// je veux quitter une case dont je suis deja parti
		if (lieu != echeancier.getCurrentEvent().getcaseActuelle())
		{
			System.out.println("Evenement obsolete (parti)");
			return;
		}
		
		// c'est mon tour
		else
		{
			System.out.println("C'est décidé, je pars de "+this.getEtat()+" !");
			ArrayList<Case> liste = new ArrayList<Case>();
			liste.addAll(this.getEtat().getEchappatoire()) ;
			System.out.println("J'ai le choix de "+liste);
			boolean available = false ;
			Case choix = this.getEtat();
			
			int s = liste.size();
			//System.out.println("size = "+s);
			//int ctr = 0 ;
			
			while(s>0 && !available )
			{
				//System.out.println("ctr = "+ctr);
				//ctr++;			
				int i = Alea.getRandomIndex(liste) ;
				//System.out.println("i = "+i);				
				choix = liste.remove(i);
				s= liste.size();
				//System.out.println("size = "+s);
				available = (choix.getCapacity()>choix.getListeObjets().size());
				System.out.println("available = "+available);
			}
					
			// pas de sortie praticable
			if (!available)
			{
				Evenement newEvent= new Evenement(this,3,nextDate2,this.getEtat());
				echeancier.add(newEvent);
				System.out.println("Attente d'une echappatoire : prochain essai le "+ nextDate2);
				return ;
			}
			else
			{
				this.getEtat().getListeObjets().remove(this);
				this.setEtat(choix);
				choix.getListeObjets().add(this);
			
				Evenement newEvent= new Evenement(this,2,nextDate1,this.getEtat());
				echeancier.add(newEvent);
				System.out.println("Parti pour "+choix+", prochain départ le "+nextDate1);
				
				// mise en place Time Out eventuel
				if (choix instanceof FileAttente && !choix.getEchappatoire().isEmpty())
				{
					Evenement event= new Evenement(this,3,nextDate2,this.getEtat());
					echeancier.add(event);
					System.out.println("Création TimeOut prevu pour "+nextDate2);
					return;
				}
			}
		}
	}

	private boolean isFirst() 
	{
		Case etat = this.getEtat() ;
		Objet objet = etat.getFirstObjet() ;
		return (this==objet);
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
		return false;
	}
	
}
