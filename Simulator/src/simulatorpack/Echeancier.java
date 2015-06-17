package simulatorpack;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import modele.Client;

public class Echeancier extends PriorityQueue<Evenement>{

	private static final long serialVersionUID = 1L;
	
	static EventComparator comparator = new EventComparator();
	
	public Echeancier(){
		super(comparator);
	}
	

	
}