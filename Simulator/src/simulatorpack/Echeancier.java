package simulatorpack;


import graphiques.Donnees;

import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

import modele.Acteur;
import modele.Source;

public class Echeancier extends PriorityQueue<Evenement>{

	private static final long serialVersionUID = 1L;
	private Evenement currentEvent ;
	private static EventComparator comparator = new EventComparator();

	/*Attributs pour la creation de graphiques*/
	private ArrayList<Donnees> tableau = new ArrayList<Donnees>();
	private int compteurClients = 0; //nombre de clients qu'il y a dans le magasin a une date donnee
	

	public Echeancier(ArrayList<Source> sourceListe, long duree)
	{
		super(/*Integer.MAX_VALUE*/ 1000, comparator);
		/*Creation des evenements de depart*/
		Date date = new Date();
		for(Source source : sourceListe)
		{
			Evenement amorce = new Evenement(source,0,date,source, source);
			this.setCurrentEvent(amorce);
			this.add(amorce);
			date.setTime(date.getTime()+1);
		}
		
		
		
		
		//Creation de l'evenement de fin
 		Date endDate = new Date();
 		Source source = sourceListe.get(0);
		endDate.setTime(date.getTime()+duree);
		Evenement end = new Evenement(source,4,endDate,source, source);
		this.add(end);
		
	}
	
	public void nextEvent()
	{
		this.currentEvent = this.poll() ;
		this.currentEvent.getActeur().realise(this);
	}
	
	public Evenement getCurrentEvent() {
		return currentEvent;
	}

	public void setCurrentEvent(Evenement currentEvent) {
		this.currentEvent = currentEvent;
	}

	/** Mesure déchets**/
	private long obsolete = 0 ;

	public void incrementeObsolete()
	{
		obsolete++;
	}
	
	public long getObsolete()
	{
		return this.obsolete;
	}
	
	/** ajouter des valeurs au tableau **/
	public void addDonnee()
	{
		tableau.add(new Donnees(this.getCurrentEvent().getDate(),compteurClients));
	}
	
	public void incrementerClients()
	{
		compteurClients++;
	}
	
	public void decrementerClients()
	{
		compteurClients--;
	}
	
	public ArrayList<Donnees> getTableau()
	{
		return tableau;
	}
}