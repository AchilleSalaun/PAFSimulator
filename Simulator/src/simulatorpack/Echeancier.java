package simulatorpack;


import java.util.Date;
import java.util.PriorityQueue;

import modele.ActeurInterface;

public class Echeancier extends PriorityQueue<Evenement>{

	private static final long serialVersionUID = 1L;
	private Evenement currentEvent ;
	private static EventComparator comparator = new EventComparator();
	

	public Echeancier()
	{
		super(/*Integer.MAX_VALUE*/ 1000, comparator);
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