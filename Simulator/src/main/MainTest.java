package main;

import simulatorpack.Echeancier;
import modele.Puit;
import modele.Source;
public class MainTest {

	public static void main(String[] args) 
	{
		// Creation du modele
				Source source = new Source();
				Puit puit = new Puit();
				source.relierSortie(puit);
				
				// Creation de l'echeancier
				Echeancier echeancier = new Echeancier(source);
				/** ATTENTION : Constructeur Temporaire !!! **/

				
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
