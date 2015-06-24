package main;

import java.util.Date;

import simulatorpack.Echeancier;
import simulatorpack.Evenement;
import modele.FileAttente;
import modele.Objet;
import modele.Puit;
import modele.Source;
public class MainTest {

	public static void main(String[] args) 
	{
		
		// Creation du modele
				Source source = new Source();
				FileAttente file = new FileAttente(1);
				FileAttente fileBloquee1 = new FileAttente(0);
				FileAttente fileBloquee2 = new FileAttente(1);
				FileAttente fileBloquee3 = new FileAttente(0);
				Puit puit = new Puit();
				file.relierEchappatoire(puit);
				file.relierEchappatoire(fileBloquee1);
				file.relierEchappatoire(fileBloquee2);
				file.relierEchappatoire(fileBloquee3);
				long duree = 10000;
				
		// Creation de l'echeancier
				Echeancier echeancier = new Echeancier(source, duree);
				/** ATTENTION : Constructeur Temporaire !!! **/

		// Operations
				Objet objet = new Objet(file, 0, 0);
				Date date = new Date();
				Evenement event = new Evenement(objet, 3, date, file);
				echeancier.setCurrentEvent(event);
				objet.realise(echeancier);
	}
}
