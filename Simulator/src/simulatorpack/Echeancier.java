package simulatorpack;


import java.util.PriorityQueue;

public class Echeancier extends PriorityQueue<Evenement>{

	private static final long serialVersionUID = 1L;
	
	static EventComparator comparator = new EventComparator();
	
	public Echeancier(){
		super(Integer.MAX_VALUE, comparator);
	}
	

	
}