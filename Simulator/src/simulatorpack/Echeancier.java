package simulatorpack;


import java.util.Date;
import java.util.PriorityQueue;

import modele.Acteur;
import modele.Source;

public class Echeancier extends PriorityQueue<Evenement>{

	private static final long serialVersionUID = 1L;
	private Evenement currentEvent ;
	private static EventComparator comparator = new EventComparator();
	
	public Echeancier(Source source,long timeSimulation) 
	{
		super(100000, comparator);
		Date dateStart = new Date();
		Date dateEnd = new Date();
		dateEnd.setTime(dateEnd.getTime()+timeSimulation);
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
	
}