package simulatorpack;


import java.util.PriorityQueue;

import modele.Acteur;

public class Echeancier extends PriorityQueue<Evenement>{

	private static final long serialVersionUID = 1L;
	
	static EventComparator comparator = new EventComparator();
	

	public Echeancier()
	{
		super(Integer.MAX_VALUE, comparator);
	}
	
	public void nextEvent()
	{
		Evenement nextevent = this.poll() ;
		Acteur acteur = nextevent.getActeur() ;
		int action = nextevent.getAction();
		acteur.realise(action);
	}
	

	
}