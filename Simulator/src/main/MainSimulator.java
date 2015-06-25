package main;

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
		//Puit puit = new Puit();
		Puit poubelle = new Puit() ;
		FileAttente file1 = new FileAttente(50);
		FileAttente file2 = new FileAttente(1);

		source.relierSortie(file1);
		//file1.relierSortie(puit);
		file1.relierEchappatoire(file2);
		file2.relierSortie(poubelle);
		
		/*file2.relierSortie(puit);
		file1.relierEchappatoire(poubelle);
		file2.relierEchappatoire(poubelle);*/
		
		long duree = 1000000000;
		
		// Creation de l'echeancier
		Echeancier echeancier = new Echeancier(source/*, duree*/);
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
			System.out.println("taille poubelle = "+poubelle.getListeObjets().size()+" /"+poubelle);
			//System.out.println("taille source = "+source.getListeObjets().size()+" /"+source);
			System.out.println("taille file1 = "+file1.getListeObjets().size()+" /"+file1);
			System.out.println("TO file1 = "+file1.hasTimeOut()+" /"+file1);
			//System.out.println("premier file1 = "+file1.getFirstObjet());
			System.out.println("taille file2 = "+file2.getListeObjets().size()+" /"+file2);
			System.out.println("premier file2 = "+file2.getFirstObjet());
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
