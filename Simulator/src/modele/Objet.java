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

		/** MaJ attributs Cases et Objet **/
		if(cible.getCapacity()>cible.getListeObjets().size())
		{
			this.getEtat().getListeObjets().remove(this);
		this.setEtat(cible);
		cible.getListeObjets().add(this);
		this.setBTimeOut(false);
		}
		else
		{
			System.out.println("Evenement obsolete (passé)");
			echeancier.incrementeObsolete();
			return;
		}
		
		
		/** Libération génération **/
		if(courante instanceof Source)
		{
			Date nextDate1 = this.creationNextDate(echeancier, lambda);
			
			Evenement newEvent= new Evenement(courante,0,nextDate1,courante,courante);
			echeancier.add(newEvent);
			System.out.println(courante+" générera à "+nextDate1);
		}
		
		Case backwardCible = courante ;
		
		/** Puit ? **/
		if (cible instanceof Puit)
		{
			if(this.isIn(cible))
			{
				Date nextDateEvacuation = new Date();
				nextDateEvacuation.setTime(echeancier.getCurrentEvent().getDate().getTime());
				
				Evenement newEvent= new Evenement(cible,1,nextDateEvacuation,cible,cible);
				echeancier.add(newEvent);
				System.out.println("Demande evacuation de "+cible+", prevue pour"+ nextDateEvacuation);
			}
			else
			{
				System.out.println("Evenement obsolete (évacué)");
				echeancier.incrementeObsolete();
							
			}
		}
		else
		/** Forward : Est-ce que je peux continuer ?**/
		// Est-ce que je suis le premier ?
		if(this.isFirst())
		{
			// Est ce que je peux sortir ?		
			if(cible.getC_Out())
			{
				ArrayList<Case> liste = new ArrayList<Case>();
				liste.addAll(cible.getSortie()) ;
				Case forward = this.getEtat() ;
				int s = liste.size() ;
				boolean available = false ;
				
				while(s>0 && !available )
				{	
					// choix destination passage : loi uniforme
					int i = Alea.getRandomIndex(liste) ;		
					forward = liste.remove(i);
					available = ((forward.getCapacity()>forward.getListeObjets().size()) && (forward.getC_In()));
					s = liste.size();
					System.out.println("availableF = "+available);
				}
						
				// si on trouve une sortie
				if (available)
				{
					Date nextDate1 = this.creationNextDate(echeancier, lambda);
				
					Evenement newEvent= new Evenement(this,2,nextDate1,cible,forward);
					echeancier.add(newEvent);
					System.out.println(this+" passera de "+cible+" à "+forward+" le "+nextDate1);
				}
			}
		}
		// Je suis bloqué : je vais devoir patienter
		else
		{
			Date nextDateTimeOut = this.creationNextDate(echeancier, lambdaTimeOut);
			Evenement timeOut = new Evenement(this,3, nextDateTimeOut, cible, cible);
			echeancier.add(timeOut);
			System.out.println("Le TimeOut de "+this+" situé en "+cible+" est prévu pour "+nextDateTimeOut);
		}
		
		/** Backward cible : Peut-on me suivre ? **/
		// peut-on rentrer ?
		System.out.println("cible = "+cible);
		if(cible.getC_In())
		{
			ArrayList<Case> liste = new ArrayList<Case>();
			liste.addAll(cible.getEntree()) ;
			int s = liste.size();
			boolean available = false ;
			while(s>0 && !available )
			{			
				// choix successeur passage : loi uniforme
				int i = Alea.getRandomIndex(liste) ;		
				backwardCible = liste.remove(i);
		
				boolean A = backwardCible.getC_Out() ;
				boolean B = (backwardCible.getSortie().contains(cible)) ;
				boolean C = (backwardCible.getListeObjets().size()>0) ;
				boolean D = (backwardCible.hasTimeOut());
				System.out.println(D+" / "+backwardCible);
				available = A && ((B&&C)||D);
				s= liste.size();
				System.out.println("availableB1 = "+available+" / "+cible+" : "+cible.getEntree());
			}
			
			// si on trouve une entree
			if (available)
			{
				Date nextDate1 = this.creationNextDate(echeancier, lambda);
				Objet nextObjet = new Objet(backwardCible, 0,0,0) ;
				System.out.println(backwardCible);
				System.out.println(backwardCible.hasTimeOut());
				// lien de type echappatoire ?
				if(backwardCible.hasTimeOut() && backwardCible.getEchappatoire().contains(cible))
				{
					int i = backwardCible.getIndexTimeOutObjet() ;
					nextObjet = backwardCible.getListeObjets().get(i);
					Evenement newEvent = new Evenement(nextObjet,2,nextDate1,backwardCible,cible);
					echeancier.add(newEvent);
					System.out.println(nextObjet+" passera de "+backwardCible+" à "+cible+" le "+nextDate1);
				}
				// ou lien de type sortie ?
				else
				{
					nextObjet = backwardCible.getFirstObjet() ;
				
					Evenement newEvent = new Evenement(nextObjet,2,nextDate1,backwardCible,cible);
					echeancier.add(newEvent);
					System.out.println(nextObjet+" passera de "+backwardCible+" à "+cible+" le "+nextDate1);
				}
			}
		}
		
		/** Backward courante : Est-ce qu'on peut-me remplacer ? **/
		// peut-on rentrer ?
		Case backwardCourante = backwardCible ;
		System.out.println("courante = "+courante);
				if(courante.getC_In())
				{
					ArrayList<Case> liste = new ArrayList<Case>();
					liste.addAll(courante.getEntree()) ;
					int s = liste.size();
					boolean available = false ;
					
					while(s>0 && !available )
					{			
						// choix successeur passage : loi uniforme
						int i = Alea.getRandomIndex(liste) ;		
						backwardCourante = liste.remove(i);
						System.out.println("backwardCourante = "+backwardCourante);
						boolean A = backwardCourante.getC_Out() ;
						boolean B = (backwardCourante.getSortie().contains(courante)) ;
						boolean C = (backwardCourante.getListeObjets().size()>0) ;
						boolean D = (backwardCourante.hasTimeOut());
						System.out.println(D+" / "+backwardCourante);
						available = A && ((B&&C)||D);
						s= liste.size();
						System.out.println("availableB2 = "+available+" / "+courante+" : "+courante.getEntree());
					}
					
					// si on trouve une entree
					if (available)
					{
						Date nextDate1 = this.creationNextDate(echeancier, lambda);
						Objet nextObjet = new Objet(backwardCourante, 0,0,0) ;
						System.out.println(backwardCourante);
						System.out.println(backwardCourante.hasTimeOut());
						// lien de type echappatoire ?
						if(backwardCourante.hasTimeOut() && backwardCourante.getEchappatoire().contains(courante))
						{
							int i = backwardCourante.getIndexTimeOutObjet() ;
							nextObjet = backwardCourante.getListeObjets().get(i);
							Evenement newEvent = new Evenement(nextObjet,2,nextDate1,backwardCourante,cible);
							echeancier.add(newEvent);
							System.out.println(nextObjet+" passera de "+backwardCourante+" à "+cible+" le "+nextDate1);
						}
						// ou lien de type sortie ?
						else
						{
							nextObjet = backwardCourante.getFirstObjet() ;
						
							Evenement newEvent = new Evenement(nextObjet,2,nextDate1,backwardCourante,courante);
							echeancier.add(newEvent);
							System.out.println(nextObjet+" passera de "+backwardCourante+" à "+courante+" le "+nextDate1);
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
		
		
		/** Partir : trouver une cible **/
		{
			
			ArrayList<Case> liste = new ArrayList<Case>() ;
			liste.addAll(courante.getEchappatoire()) ;
			System.out.println(liste);
			Case cible = this.getEtat() ;
			int s = liste.size() ;
			boolean available = false ;
				
			while(s>0 && !available )
			{			
				// choix destination départ : loi uniforme
				int i = Alea.getRandomIndex(liste) ;		
				cible = liste.remove(i);
				available = ((cible.getCapacity()>cible.getListeObjets().size()) && (cible.getC_In()));
				s = liste.size();
				System.out.println("available = "+available);
			}
					
			// si on trouve une échappatoire : on part sans autre forme de procès
			if (available)
			{
				this.getEtat().getListeObjets().remove(this);
				this.setEtat(cible);
				cible.getListeObjets().add(this);
				this.setBTimeOut(false);
				
				
				/** Puit ? **/
				if (cible instanceof Puit)
				{
					if(this.isIn(cible))
					{
						Date nextDateEvacuation = new Date();
						nextDateEvacuation.setTime(echeancier.getCurrentEvent().getDate().getTime());
						
						Evenement newEvent= new Evenement(cible,1,nextDateEvacuation,cible,cible);
						echeancier.add(newEvent);
						System.out.println("Demande evacuation de "+cible+", prevue pour "+ nextDateEvacuation);
						
					}
					else
					{
						System.out.println("Evenement obsolete (évacué)");
						echeancier.incrementeObsolete();
									
					}
				}
				else
				/** Forward : Si ce n'est pas un Puit, c'est donc une File **/
				// Est-ce que je suis le premier ?
				if(this.isFirst())
				{
					// Est ce que je peux sortir ?		
					if(cible.getC_Out())
					{
						liste.clear();
						liste.addAll(cible.getSortie()) ;
						s = liste.size() ;
						available = false ;
						
						Case forward = cible ;
						while(s>0 && !available )
						{	
							// choix destination passage : loi uniforme
							int i = Alea.getRandomIndex(liste) ;		
							forward = liste.remove(i);
							available = ((forward.getCapacity()>forward.getListeObjets().size()) && (forward.getC_In()));
							s = liste.size();
							System.out.println("availableF = "+available);
						}
								
						// si on trouve une sortie
						if (available)
						{
							Date nextDate1 = this.creationNextDate(echeancier, lambda);
						
							Evenement newEvent= new Evenement(this,2,nextDate1,cible,forward);
							echeancier.add(newEvent);
							System.out.println(this+" passera de "+cible+" à "+forward+" le "+nextDate1);
						}
					}
				}
				// Je suis bloqué : je vais devoir patienter
				else
				{
					Date nextDateTimeOut = this.creationNextDate(echeancier, lambdaTimeOut);
					Evenement timeOut = new Evenement(this,3, nextDateTimeOut, cible, cible);
					echeancier.add(timeOut);
					System.out.println("Le TimeOut de "+this+" situé en "+cible+" est prévu pour "+nextDateTimeOut);
				}
				
				/** Backward cible : Peut-on me suivre ? **/
				// peut-on rentrer ?
				if(cible.getC_In())
				{
					liste.clear();
					liste.addAll(cible.getEntree()) ;
					s = liste.size();
					available = false ;
					
					Case backwardCible = courante ;
					while(s>0 && !available )
					{			
						// choix successeur passage : loi uniforme
						int i = Alea.getRandomIndex(liste) ;		
						backwardCible = liste.remove(i);
				
						boolean A = backwardCible.getC_Out() ;
						boolean B = (backwardCible.getSortie().contains(cible)) ;
						boolean C = (backwardCible.getListeObjets().size()>0) ;
						boolean D = (backwardCible.hasTimeOut());
						System.out.println(D+" / "+backwardCible);
						available = A && ((B&&C)||D);
						s= liste.size();
						System.out.println("availableB1 = "+available+" / "+cible+" : "+cible.getEntree());
					}
					
					// si on trouve une entree
					if (available)
					{
						Date nextDate1 = this.creationNextDate(echeancier, lambda);
						Objet nextObjet = new Objet(backwardCible, 0,0,0) ;
						System.out.println(backwardCible);
						System.out.println(backwardCible.hasTimeOut());
						// lien de type echappatoire ?
						if(backwardCible.hasTimeOut() && backwardCible.getEchappatoire().contains(cible))
						{
							int i = backwardCible.getIndexTimeOutObjet() ;
							nextObjet = backwardCible.getListeObjets().get(i);
							Evenement newEvent = new Evenement(nextObjet,2,nextDate1,backwardCible,cible);
							echeancier.add(newEvent);
							System.out.println(nextObjet+" passera de "+backwardCible+" à "+cible+" le "+nextDate1);
						}
						// ou lien de type sortie ?
						else
						{
							nextObjet = backwardCible.getFirstObjet() ;
						
							Evenement newEvent = new Evenement(nextObjet,2,nextDate1,backwardCible,cible);
							echeancier.add(newEvent);
							System.out.println(nextObjet+" passera de "+backwardCible+" à "+cible+" le "+nextDate1);
						}
					}
				}
				
				/** Backward courante : Est-ce qu'on peut-me remplacer ? **/
				// peut-on rentrer ?
				Case backwardCourante = cible ;
						if(courante.getC_In())
						{
							liste.clear();
							liste.addAll(courante.getEntree()) ;
							s = liste.size();
							available = false ;
							System.out.println("liste = "+liste);
							while(s>0 && !available )
							{			
								// choix successeur passage : loi uniforme
								int i = Alea.getRandomIndex(liste) ;		
								courante = liste.remove(i);
						
								boolean A = backwardCourante.getC_Out() ;
								boolean B = (backwardCourante.getSortie().contains(courante)) ;
								boolean C = (backwardCourante.getListeObjets().size()>0) ;
								boolean D = (backwardCourante.hasTimeOut());
								System.out.println(D+" / "+backwardCourante);
								available = A && ((B&&C)||D);
								s= liste.size();
								System.out.println("availableB2 = "+available+" / "+courante+" : "+courante.getEntree());
							}
							
							// si on trouve une entree
							if (available)
							{
								Date nextDate1 = this.creationNextDate(echeancier, lambda);
								Objet nextObjet = new Objet(backwardCourante, 0,0,0) ;
								System.out.println(backwardCourante);
								System.out.println(backwardCourante.hasTimeOut());
								// lien de type echappatoire ?
								if(backwardCourante.hasTimeOut() && backwardCourante.getEchappatoire().contains(courante))
								{
									int i = backwardCourante.getIndexTimeOutObjet() ;
									nextObjet = backwardCourante.getListeObjets().get(i);
									Evenement newEvent = new Evenement(nextObjet,2,nextDate1,backwardCourante,cible);
									echeancier.add(newEvent);
									System.out.println(nextObjet+" passera de "+backwardCourante+" à "+cible+" le "+nextDate1);
								}
								// ou lien de type sortie ?
								else
								{
									nextObjet = backwardCourante.getFirstObjet() ;
								
									Evenement newEvent = new Evenement(nextObjet,2,nextDate1,backwardCourante,courante);
									echeancier.add(newEvent);
									System.out.println(nextObjet+" passera de "+backwardCourante+" à "+courante+" le "+nextDate1);
									return ;
								}
							}
						}
			}	
		}
		
		/** switch TimeOut **/
		this.switchTimeOut();
	}
}
