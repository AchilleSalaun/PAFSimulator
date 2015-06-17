package simulatorpack;

import java.util.Comparator;

public class EventComparator implements Comparator<Evenement> {

	@Override
	public int compare(Evenement e1, Evenement e2) {
		return e1.getDate().compareTo(e2.getDate());
	}

}
