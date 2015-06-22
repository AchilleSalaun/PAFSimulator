package simulatorpack;


import java.util.Date;
import java.util.PriorityQueue;

import modele.ActeurInterface;

public class Echeancier extends PriorityQueue<Evenement>{

	private static final long serialVersionUID = 1L;
	private static Evenement currentEvent ;
	private static EventComparator comparator = new EventComparator();
	

	public Echeancier()
	{
		super(Integer.MAX_VALUE, comparator);
	}
	
	public void nextEvent()
	{
		this.currentEvent = this.poll();
		this.currentEvent.getActeur().realise(this);
	}
	
	public static Evenement getCurrentEvent() {
		return currentEvent;
	}

	public static void setCurrentEvent(Evenement currentEvent) {
		Echeancier.currentEvent = currentEvent;
	}

	
	
}