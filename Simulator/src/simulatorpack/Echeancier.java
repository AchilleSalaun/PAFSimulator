package simulatorpack;


import java.util.Date;
import java.util.PriorityQueue;

import modele.ActeurInterface;

public class Echeancier extends PriorityQueue<Evenement>{

	private static final long serialVersionUID = 1L;
	private static Date currentDate ;
	private static EventComparator comparator = new EventComparator();
	

	public Echeancier()
	{
		super(Integer.MAX_VALUE, comparator);
	}
	
	public void nextEvent()
	{
		Evenement currentEvent = this.poll() ;
		ActeurInterface acteur = currentEvent.getActeur() ;
		Echeancier.setCurrentDate(currentEvent.getDate());
		int action = currentEvent.getAction();
		acteur.realise(action,this);
	}
	
	public static Date getCurrentDate() {
		return currentDate;
	}

	public static void setCurrentDate(Date currentDate) {
		Echeancier.currentDate = currentDate;
	}

	
	
}