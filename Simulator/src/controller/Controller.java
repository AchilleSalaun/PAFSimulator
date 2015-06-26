package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import modele.FileAttente;
import modele.Puit;
import modele.Source;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import simulatorpack.Echeancier;
import view.ViewModelisation;
import view.ViewSimulation;
import view.Window;

//Classe permettant de controller ce qui se passe quand on appuie sur un bouton, c'est elle qui
//interrogera la base de donnee

public class Controller
{
	private Window window;
	private String duree;// Ce tableau permet de connaitre la duree a afficher

	private int modele = 1 ;
	
	public int getModele()
	{
		return this.modele ;		
	}
	
	public void setModele(int i)
	{
		this.modele = i ;
	}
	
	/** private String temps = "mois"; */

	public Controller(Window window) 
	{
		this.window = window;
	}

	/****************************************************************************************************/
	/** Navigation */
	private Stack<Container> stackpreviousview = new Stack<Container>();
	private Stack<Container> stacknextview = new Stack<Container>();
	private Container actualview;

	public void setActualView(Container actualview) 
	{
		this.actualview = actualview;
	}

	public void clearNextStacks()
	{
		while (!stacknextview.empty()) 
		{

			stacknextview.pop();
		}
		window.getNextButton().setForeground(Color.GRAY);
	}
	
	public void clearPreviousStacks()
	{
		while (!stackpreviousview.empty()) 
		{
			stackpreviousview.pop();
		}
		window.getPreviousButton().setForeground(Color.gray);
	}
	
	public void clearStacks() 
	{
		this.clearNextStacks();
		this.clearPreviousStacks() ;
	}

	public Container getActualView() 
	{
		return actualview;
	}

	public void addPreviousView(Container previousview) 
	{
		if(!stacknextview.empty())
		{
			this.clearNextStacks() ;
		}
		stackpreviousview.push(previousview);
		window.getPreviousButton().setForeground(Color.BLACK);
	}

	public void previousView(Container actualview) 
	{
		if (stackpreviousview.empty()) 
		{
			JOptionPane.showMessageDialog(null,
					"L'opération demandée est impossible", "Attention",
					JOptionPane.WARNING_MESSAGE);
		} 
		else 
		{
			Container previousview = stackpreviousview.pop();
			
			if(stackpreviousview.empty())
			{
				window.getPreviousButton().setForeground(Color.GRAY);
			}
			
			stacknextview.push(actualview);
			window.getNextButton().setForeground(Color.BLACK);
			this.setActualView(previousview);
			window.setContentPane(previousview);
			window.validate();
		}
	}

	public void nextView(Container actualview) 
	{
		if (stacknextview.empty()) 
		{
			JOptionPane.showMessageDialog(null,
					"L'opération demandée est impossible", "Attention",
					JOptionPane.WARNING_MESSAGE);
		} 
		else 
		{
			Container nextview = stacknextview.pop();
			
			if(stacknextview.empty())
			{
				window.getNextButton().setForeground(Color.GRAY);
			}
			
			stackpreviousview.push(actualview);
			window.getPreviousButton().setForeground(Color.BLACK);
			this.setActualView(nextview);
			window.setContentPane(nextview);
			window.validate();
		}
	}

	/*
	 * L'idee est la suivante :
	 * 
	 * 1) a chaque fois qu'on quitte un page par un lien normal (sans passer par
	 * les outils de navigations), il faut ajouter la page quittee a la pile
	 * stackpreviousview. 2) on renseigne au controller la nouvelle page sur
	 * laquelle on se trouve : elle est stockee dans l'attribut actualview ; 3)
	 * si on decide de faire marche arriere la page quittee est stockee dans
	 * stacknextview. 4) si on decide de faire un retour arriere apres 3), la
	 * page quittee est de nouveau stockee dans stacknextview
	 * 
	 * Autrement dit :
	 * 
	 * 1) actual view -> stackpreviousview 2) actual view <- newactualview 3)
	 * actual view -> stacknextview 4) actual view -> stackpreviousview
	 */
	/****************************************************************************************************/
	/** Fonctionnalites barre de menu */
	private void fonctionnaliteNonImplementee(String methodname) 
	{
		System.out.println("WARNING : the method " + methodname
				+ "hasn't been implemented yet !!!");
		JOptionPane.showMessageDialog(null,
				"L'opération demandée n'est pas encore disponible.",
				"Attention", JOptionPane.WARNING_MESSAGE);
	}

	public void aProposOptibar() 
	{
		JOptionPane
				.showMessageDialog(
						null,

						"DiscreteSimulator est un simulateur à évènements discrets."

						, "A propos de DiscreteSimulator", JOptionPane.INFORMATION_MESSAGE);

	}

	public void afficherAide() 
	{
		this.fonctionnaliteNonImplementee("afficherAide()");

	}

	
	/****************************************************************************************************/
	/** ViewWelcome */
	public void boutonSimulation(int i) 
	{
		// Methode appelee quand on appuie sur Simulation sur l'ecran d'accueil
		this.addPreviousView(actualview);
		ViewSimulation vs = new ViewSimulation(this,i);
		
		Container cp = new Container() ;
		cp.setLayout(new GridBagLayout());
		cp.add(vs) ;
		window.setContentPane(cp);
		
		this.setActualView(cp);
		window.validate();
	}

	public void boutonModelisation() 
	{
		// Methode appelee quand on appuie sur Modelisation sur l'ecran d'accueil
		this.addPreviousView(actualview);
		ViewModelisation vm = new ViewModelisation(this);
		
		Container cp = new Container() ;		
		cp.setLayout(new GridBagLayout());
		cp.add(vm) ;
		window.setContentPane(cp);
		
		this.setActualView(cp);
		window.validate();
	}

	

	/****************************************************************************************************/
	/** ViewSimulation */
	
	public void lancerSimulation() 
	{
		
/** Creation du modele **/
		
		/** Rappel attributs source **/ 
		/* lambdaGene    = 4
		 * lambda        = 1
		 * lambdaTimeOut = 2
		 * nombremax     = 30
		 */

		Source source = new Source(4, 1, 2, 30);
		
		Puit puit = new Puit();
		Puit poubelle = new Puit() ;
		
		FileAttente file1 = new FileAttente(20);
		FileAttente file2 = new FileAttente(20);
		FileAttente file  = new FileAttente(20);
		
		FileAttente caisse1 = new FileAttente(1);
		FileAttente caisse2 = new FileAttente(1);
		
		switch(modele)
		{
			case 1 : this.chargerM1(source, file1, file2, caisse1, caisse2, puit, poubelle);
				break ;
			case 2 : this.chargerM2(source, file, caisse1, caisse2, puit, poubelle);
				break ;
			default : this.chargerM1(source, file1, file2, caisse1, caisse2, puit, poubelle);
		}
		

		/*****************************************************************************************************/
		/** Creation de l'echeancier **/
		
		long duree = 1000000000;
		
		ArrayList<Source> sourceListe = new ArrayList<Source>();
		sourceListe.add(source);
		
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
	}

	private void chargerM1(Source source, FileAttente file1, FileAttente file2, FileAttente caisse1, FileAttente caisse2, Puit puit, Puit poubelle) 
	{
		source.relierSortie(file1);
		source.relierSortie(file2);
		
		file1.relierSortie(caisse1);
		file1.relierEchappatoire(poubelle);
		file2.relierSortie(caisse2);
		file2.relierEchappatoire(poubelle);
		
		caisse1.relierSortie(puit);
		caisse1.relierEchappatoire(poubelle);
		caisse2.relierSortie(puit);
		caisse2.relierEchappatoire(poubelle);
	}

	private void chargerM2(Source source, FileAttente file, FileAttente caisse1, FileAttente caisse2, Puit puit, Puit poubelle) 
	{
		source.relierSortie(file);
		
		file.relierSortie(caisse1);
		file.relierEchappatoire(poubelle);
		file.relierSortie(caisse2);
		
		caisse1.relierSortie(puit);
		caisse1.relierEchappatoire(poubelle);
		caisse2.relierSortie(puit);
		caisse2.relierEchappatoire(poubelle);
	}

	public void chargerPE(int i) 
	{
		this.boutonSimulation(i);
		this.setModele(i);
	}
}
