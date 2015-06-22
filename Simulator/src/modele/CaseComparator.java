package modele;

import java.util.Comparator;

public class CaseComparator implements Comparator<Case> {

	@Override
	public int compare(Case e1, Case e2) {
		return e1.getDate().compareTo(e2.getDate());
	}

}
