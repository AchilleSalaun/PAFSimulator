package simulatorpack;

import java.util.Comparator;

public class EventComparator implements Comparator<Evenement> {

	@Override
	public int compare(Evenement e1, Evenement e2) {
		//ATTENTION au sens : on veut récupérer l'évènement le moins tardif
		return e1.getDate().compareTo(e2.getDate());
	}

}
