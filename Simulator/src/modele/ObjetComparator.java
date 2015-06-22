package modele;

import java.util.Comparator;

public class ObjetComparator implements Comparator<Objet> {

	@Override
	public int compare(Objet e1, Objet e2) 
	{
		return e1.getDate().compareTo(e2.getDate());
	}

}
