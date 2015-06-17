package fastfood;

import modele.Case;
import modele.Objet;

public class Burger extends Objet 
{

	public Burger(Case etat, double timeout, double priority) 
	{
		super(etat, timeout, priority, 1000) ;
	}

}
