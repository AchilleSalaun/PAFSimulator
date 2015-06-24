package simulatorpack;


import java.util.Date;
import java.util.PriorityQueue;

import modele.Acteur;
import modele.Source;

public class Echeancier extends PriorityQueue<Evenement>{

	private static final long serialVersionUID = 1L;
	private Evenement currentEvent ;
	private static EventComparator comparator = new EventComparator();
	

	public Echeancier(Source source, long duree)
	{
		super(/*Integer.MAX_VALUE*/ 1000, comparator);
		/*Creation de l'evenement de depart*/
		Date date = new Date();
		Evenement amorce = new Evenement(source,0,date,source);
		//this.currentEvent=amorce;
		this.add(amorce);
		
		/*Creation de l'evenement de fin*/
		Date endDate = new Date();
		endDate.setTime(date.getTime()+duree);
		Evenement end = new Evenement(source,4,endDate,source);
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

	
	
}