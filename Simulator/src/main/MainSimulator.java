package main;

import graphiques.Courbe;
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
		/** Creation du modele **/
		
		/** Rappel attributs source **/ 
		/* lambdaGene    = 4
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
		
	
		/*****************************************************************************************************/
		/** Creation de l'echeancier **/
		
		long duree = 1000000000;
		
		ArrayList<Source> sourceListe = new ArrayList<Source>();
		sourceListe.add(source);
		sourceListe.add(sourcebis);
		
		Echeancier echeancier = new Echeancier(sourceListe, duree) ;
		
		/*****************************************************************************************************/
		/** Simulation **/
		System.out.println("début");
						
		int s = 0;
		int ctr = 0;
				
		/** Regime permanent **/
		
		do
		{
			System.out.println("****************************************************************************");
			System.out.println("Début boucle n°"+ctr);
			echeancier.nextEvent();
			s = echeancier.size();
			System.out.println("Fin boucle : taille echancier = "+s+" / event traités = "+ctr+" / déchets = "+echeancier.getObsolete());
			ctr++ ;
		}
		while(s>0);
		
		/*****************************************************************************************************/
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
		
		/** Creation de la courbe des cliens **/
		final Courbe demo = new Courbe("Evolution du nombre de clients", echeancier.getTableau());
        demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
	}

}
