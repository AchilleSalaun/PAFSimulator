package main;

import simulatorpack.Echeancier;
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
		Puit poubelle = new Puit() ;
		FileAttente file1 = new FileAttente(5);
		FileAttente file2 = new FileAttente(1);
		source.relierSortie(file1);
		file1.relierSortie(file2);
		file2.relierSortie(puit);
		file1.relierEchappatoire(poubelle);
		file2.relierEchappatoire(poubelle);
		
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
