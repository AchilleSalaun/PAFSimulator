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
	private boolean bTimeOut = false ;
	private double lambda ; 
	private double lambdaTimeOut; // le temps limite au dela duquel l'objet quitte une file d'attente en ms 
	private int nombremax ; // tolerance au nombre dans une file
	
	
		
	public Objet(Case etat, double lambda, double lambdaTimeOut,  int nombremax)
	{
		this.etat = etat ;
		this.lambda = lambda ;
		this.lambdaTimeOut = lambdaTimeOut;
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
	
	public double getTimeOut()
	{
		return this.lambdaTimeOut;
	}
	
	public void setTimeout(double lambdaTimeOut)
	{
		this.lambdaTimeOut = lambdaTimeOut;
	}

	
	public int getNombreMax()
	{
	return nombremax ;
	}
	
	public void setNombreMax(int nombremax)
	{
		this.nombremax = nombremax ;
	}
	
	public boolean isTimedOut() 
	{
		return this.bTimeOut ;
	}
	
	public void setBTimeOut(boolean b)
	{
		this.bTimeOut = b ;
	}
	
	
	/*****************************************************************************************************/
	/** Tests **/
	
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
	
	private void switchTimeOut()
	{
		this.bTimeOut = !bTimeOut ;
	}
	
	/*****************************************************************************************************/
	/** Champ d'actions **/

	@Override
	public void passer( Echeancier echeancier)
	{
		super.passer(echeancier);
		System.out.println("Démarrage passer : "+ echeancier.getCurrentEvent().getDate());
		
		
		
		Case courante = this.getEtat() ;
		Case cible = echeancier.getCurrentEvent().getCaseCible();
		
		/** Evenement obsolete ? **/
		if (courante != echeancier.getCurrentEvent().getcaseActuelle())
		{
			System.out.println("Evenement obsolete (passé)");
			echeancier.incrementeObsolete();
			return;
		}

		/** Puit ? **/
		if (courante instanceof Puit)
		{
			if(this.isIn(courante))
			{
				Date nextDateEvacuation = new Date();
				nextDateEvacuation.setTime(echeancier.getCurrentEvent().getDate().getTime());
				
				Evenement newEvent= new Evenement(courante,1,nextDateEvacuation,courante,courante);
				echeancier.add(newEvent);
				System.out.println("Demande evacuation : prevue pour "+ nextDateEvacuation);
				return;
			}
			else
			{
				System.out.println("Evenement obsolete (évacué)");
				echeancier.incrementeObsolete();
				return ;			
			}
		}
		
		/** MaJ attributs Cases et Objet **/
		this.getEtat().getListeObjets().remove(this);
		this.setEtat(cible);
		cible.getListeObjets().add(this);
		this.setBTimeOut(false);
		
		Case backward = courante ;
		courante = cible ;

		/** Forward **/
		// Est-ce que je suis le premier ?
		if(this.isFirst())
		{
			// Est ce que je peux sortir ?		
			if(courante.getC_Out())
			{
				ArrayList<Case> liste = courante.getSortie() ;
				Case forward = this.getEtat() ;
				int s = 0 ;
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
				
					Evenement newEvent= new Evenement(this,2,nextDate1,courante,forward);
					echeancier.add(newEvent);
					System.out.println(this+"partira de "+courante+" pour "+forward+" le "+nextDate1);
				}
			}
		}
		// Je suis bloqué : je vais devoir patienter
		else
		{
			Date nextDateTimeOut = this.creationNextDate(echeancier, lambdaTimeOut);
			Evenement timeOut = new Evenement(this,3, nextDateTimeOut, courante, courante);
			echeancier.add(timeOut);
			System.out.println("Le TimeOut de "+this+" situé en "+courante+" est prévu pour"+nextDateTimeOut);
		}
		
		/** Backward **/
		// peut-on rentrer ?
		if(courante.getC_In())
		{
			ArrayList<Case> liste = courante.getEntree() ;
			int s = liste.size();
			boolean available = false ;
			
			while(s>0 && !available )
			{			
				// choix successeur passage : loi uniforme
				int i = Alea.getRandomIndex(liste) ;		
				backward = liste.remove(i);
				available = backward.getC_Out() && (((backward.getSortie().contains(courante))&&(backward.getListeObjets().size()>0))||(backward.hasTimeOut()));
				s= liste.size();
				System.out.println("available = "+available);
			}
			
			// si on trouve une entree
			
			if (available)
			{
				Date nextDate1 = this.creationNextDate(echeancier, lambda);
				Objet nextObjet = new Objet(backward, 0,0,0) ;
				
				// lien de type echappatoire ?
				if(backward.hasTimeOut())
				{
					int i = backward.getIndexTimeOutObjet() ;
					nextObjet = backward.getListeObjets().get(i);
					System.out.println(nextObjet+"passera de "+backward+" à "+courante+" le "+nextDate1);
				}
				// ou lien de type sortie ?
				else
				{
					nextObjet = backward.getFirstObjet() ;
				
					Evenement newEvent = new Evenement(nextObjet,2,nextDate1,backward,courante);
					echeancier.add(newEvent);
					System.out.println(nextObjet+"passera de "+backward+" à "+courante+" le "+nextDate1);
					return ;
				}
			}
		}	
	}
	
	@Override
	public void partir( Echeancier echeancier)
	{
		super.partir(echeancier);
		System.out.println("Démarrage partir : "+ echeancier.getCurrentEvent().getDate());
		Case courante = this.getEtat();

		// Evenement obsolete ?
		if (courante != echeancier.getCurrentEvent().getcaseActuelle())
		{
			System.out.println("Evenement obsolete (parti)");
			echeancier.incrementeObsolete();
			return;
		}
		
		
		/** Forward **/
		{
			ArrayList<Case> liste = courante.getEchappatoire() ;
			Case forward = this.getEtat() ;
			int s = 0 ;
			boolean available = false ;
				
			while(s>0 && !available )
			{			
				// choix destination départ : loi uniforme
				int i = Alea.getRandomIndex(liste) ;		
				forward = liste.remove(i);
				available = ((forward.getCapacity()>forward.getListeObjets().size()) && (forward.getC_In()));
				s = liste.size();
				System.out.println("available = "+available);
			}
					
			// si on trouve une échappatoire : on part sans autre forme de procès
			if (available)
			{
				this.getEtat().getListeObjets().remove(this);
				this.setEtat(forward);
				forward.getListeObjets().add(this);
				this.setBTimeOut(false);
				return ;
			}	
		}
		
		/** switch TimeOut **/
		this.switchTimeOut();
	}
}
