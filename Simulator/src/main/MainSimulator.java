package main;

import java.util.ArrayList;
import java.util.Date;

import simulatorpack.Echeancier;
import simulatorpack.Evenement;
import modele.FileAttente;
import modele.Puit;
import modele.Source;

public class MainSimulator 
{

	public static void main(String[] args) 
	{
		// Creation du modele
		
		/*
		 * lambdaGene    = 4
		 * lambda        = 1
		 * lambdaTimeOut = 2
		 * nombremax     = 30
		 */
		
		
		Source source = new Source(4, 1, 2, 30);
		Source sourcebis = new Source(4, 1, 2, 30);
		Puit puit = new Puit();
		Puit poubelle = new Puit() ;
		FileAttente file1 = new FileAttente(5);
		FileAttente file2 = new FileAttente(1);

		source.relierSortie(file1);
		sourcebis.relierSortie(file2);
		
		file1.relierSortie(puit);
		file1.relierEchappatoire(file2);
		file2.relierSortie(poubelle);
		
		/*file2.relierSortie(puit);
		file1.relierEchappatoire(poubelle);
		file2.relierEchappatoire(poubelle);*/
		
		long duree = 1000000000;
		
		// Creation de l'echeancier
		ArrayList<Source> sourceListe = new ArrayList<Source>();
		sourceListe.add(source);
		sourceListe.add(sourcebis);
		Echeancier echeancier = new Echeancier(sourceListe/*, duree*/);
		/** ATTENTION : Constructeur Temporaire !!! **/
		/*Constructeur modifie, l'echeancier a maintenant une date de fin*/

		
		System.out.println("début");
						
		int s = 0;
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
			//System.out.println("taille puit = " + puit.getListeObjets().size()+" /"+puit);
			System.out.println("source = "+source.getListeObjets()+" /"+source);
			System.out.println("file1 = "+file1.getListeObjets()+" /"+file1);
			
			System.out.println("TO file1 = "+file1.hasTimeOut()+" /"+file1);
			//System.out.println("premier file1 = "+file1.getFirstObjet());
			System.out.println("file2 = "+file2.getListeObjets()+" /"+file2);
			System.out.println("premier file2 = "+file2.getFirstObjet());
			System.out.println("poubelle = "+poubelle.getListeObjets().size()+" /"+poubelle);
			
			//System.out.println("Fin boucle n°"+ctr);
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
