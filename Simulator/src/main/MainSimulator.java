package main;

import java.util.Date;

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
		FileAttente file1 = new FileAttente(50);
		FileAttente file2 = new FileAttente(0);
		source.relierSortie(file1);
		file1.relierSortie(file2);
		file2.relierSortie(puit);
		file1.relierEchappatoire(poubelle);
		file2.relierEchappatoire(poubelle);
		long duree = 100000000;
		
		// Creation de l'echeancier
		Echeancier echeancier = new Echeancier(source, duree);
		/** ATTENTION : Constructeur Temporaire !!! **/
		/*Constructeur modifie, l'echeancier a maintenant une date de fin*/

		
		System.out.println("début");
	
		int s = echeancier.size();
		int ctr = 0;
		
		/***************************************************************************************************************************************/
		/** Regime permanent **/
		
		do
		{
			ctr++ ;
			System.out.println("******************************************************");
			System.out.println("Début boucle n°"+ctr);
			echeancier.nextEvent();
			s = echeancier.size();
			System.out.println("Fin boucle n°"+ctr+": taille puit = " + puit.getListeObjets().size()+" / taille poubelle = "+poubelle.getListeObjets().size());
		}
		while(s>0);
		
		/***************************************************************************************************************************************/
		/** Regime transitoire **/
		
		/*for(int i=0 ; i<20
				; i++)
		{
			ctr++ ;
			System.out.println("******************************************************");
			System.out.println("Début boucle n°"+ctr);
			s = echeancier.size();
			echeancier.nextEvent();
			System.out.println("Fin boucle n°"+ctr+": taille puit = " + puit.getListeObjets().size()+" / taille poubelle = "+poubelle.getListeObjets().size());
		}*/
		
		System.out.println("fin");
	}

}
