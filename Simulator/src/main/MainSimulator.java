package main;

import java.util.ArrayList;
import java.util.Date;

import simulatorpack.Echeancier;
import simulatorpack.Evenement;
import modele.Case;
import modele.FileAttente;
import modele.Puit;
import modele.Source;

public class MainSimulator 
{

	public static void main(String[] args) 
	{
		// Creation du modele
		Source source = new Source();
		Puit puit = new Puit();
		FileAttente file =new FileAttente(1);
		source.relierSortie(file);
		file.relierSortie(puit);
		
		// Creation de l'echeancier
		Echeancier echeancier = new Echeancier(source);
		/** ATTENTION : Constructeur Temporaire !!! **/

		
		System.out.println("début");
	
		int s = 0;
		int ctr = 0;
		do
		{
			ctr++ ;
			System.out.println("******************************************************");
			System.out.println("Début boucle n°"+ctr);
			s = echeancier.size();
			echeancier.nextEvent();
			System.out.println("Fin boucle n°"+ctr+": taille puit = " + puit.getListeObjets().size());
		}
		while(s>0);
		
		System.out.println("fin");
	}

}
