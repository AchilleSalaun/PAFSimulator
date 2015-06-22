package main;

import java.util.ArrayList;
import java.util.Date;

import simulatorpack.Echeancier;
import simulatorpack.Evenement;
import modele.Case;
import modele.Puit;
import modele.Source;

public class MainSimulator 
{

	public static void main(String[] args) 
	{
		// Creation du modele
		Source source = new Source();
		Puit puit = new Puit();
		source.relierSortie(puit);
		
		// Creation de l'echeancier
		Echeancier echeancier = new Echeancier();
		Date date = new Date();
		Evenement amorce = new Evenement(source,0,date,source);
		echeancier.add(amorce);
		
		System.out.println("début");
	
		int s = 0;
		do
		{
			s = echeancier.size();
			echeancier.nextEvent();
			System.out.println("Fin de boucle : taille puit = " + puit.getListeObjets().size());
		}
		while(s>0);
		
		System.out.println("fin");
	}

}
