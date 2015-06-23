package graphiques;

import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;

import modele.Puit;

public class MainTestGraphiques {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Puit puitClientParti = new Puit();
		Puit puitClientServi = new Puit();

		/* Valeurs au hasard... */
		puitClientParti.setCompteur(20);
		puitClientServi.setCompteur(350);

		ArrayList<Puit> puits = new ArrayList<Puit>();
		puits.add(puitClientServi);
		puits.add(puitClientParti);

		final PuitPieChart test = new PuitPieChart("Satisfaction des clients", puits);
		test.pack();
		RefineryUtilities.centerFrameOnScreen(test);
		test.setVisible(true);

	    
	}

}
