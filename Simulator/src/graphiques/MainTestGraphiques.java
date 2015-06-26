package graphiques;

import java.util.ArrayList;
import java.util.Date;

//import org.jfree.ui.RefineryUtilities;

import modele.Puit;

public class MainTestGraphiques {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		/*Dataset du camembert...*/
		Puit puitClientParti = new Puit();
		Puit puitClientServi = new Puit();

		/* Valeurs au hasard... */
		puitClientParti.setCompteur(300);
		puitClientParti.setName("Clients partis");
		puitClientServi.setCompteur(350);
		puitClientServi.setName("Clients servis");

		ArrayList<Puit> puits = new ArrayList<Puit>();
		puits.add(puitClientServi);
		puits.add(puitClientParti);

		/*Creation d'un camembert*/
		final Camembert test = new Camembert("Satisfaction des clients", puits);
		test.pack();
		test.setVisible(true);
		
		
		/*Dataset de la courbe...*/
		ArrayList<Donnees> tableau = new ArrayList<Donnees>();
		
		Date date = new Date();
		Donnees donnee1 = new Donnees(date, 20);
		long duree = 1000000;
		Date date2 = new Date();
		date2.setTime(date.getTime()+duree);
		
		Donnees donnee2 = new Donnees(date2, 40);
		tableau.add(donnee1);
		tableau.add(donnee2);
		
		//System.out.println(tableau.size());
		
		
		/*Creation d'une courbe*/
		final Courbe demo = new Courbe("Evolution du nombre de clients", tableau);
        demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

	    
	}

}