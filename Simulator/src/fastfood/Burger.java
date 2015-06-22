package fastfood;

import modele.Case;
import modele.Objet;

public class Burger extends Objet 
{

	public Burger(Case etat, double timeout) 
	{
		super(etat, timeout, 1000) ;
	}

}
