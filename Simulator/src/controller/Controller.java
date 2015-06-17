package controller;

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
import javax.swing.JTextField;

//Classe permettant de controller ce qui se passe quand on appuie sur un bouton, c'est elle qui
//interrogera la base de donnee

public class Controller
{
	private Window window;
	private String duree;// Ce tableau permet de connaitre la duree a afficher

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
		// System.out.println("before :" + actualview) ;
		this.actualview = actualview;
		//System.out.println("actual :" + actualview);
	}

	public void clearNextStacks()
	{
		/*System.out.println("is stacknextview empty ?"
				+ stacknextview.empty());*/
		while (!stacknextview.empty()) 
		{

			stacknextview.pop();
		}
		window.getNextButton().setForeground(Color.GRAY);
	}
	
	public void clearPreviousStacks()
	{
		/*System.out.println("is stackpreviousview empty ?"
				+ stackpreviousview.empty());*/
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
		//System.out.println("test " + stackpreviousview.empty()) ;
		
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

						"OptiBar a pour objectif de fournir à un bar des outils novateurs et pratiques d'utilisation permettant d'en faciliter la gestion. \n"
								+ "Il permettra au patron de gérer ses stocks et aussi de savoir quelles sont les habitudes de consommation de ses clients de façon claire et précise. \n"
								+ "Puisque la gestion des stocks est une dépense importante pour les bars, notre projet permettra au bar de minimiser ses stocks, sans jamais être à court. \n"
								+ "Enfin, notre système fournit aussi une aide au barman en lui indiquant les quantités qu'il a versées ce qui lui permet à la fois de préparer de meilleures boissons \n "
								+ "mais lui facilite également la production de l'addition, gain de temps toujours utile à l'heure de pointe."

						, "A propos d'OptiBar", JOptionPane.INFORMATION_MESSAGE);

	}

	public void afficherAide() 
	{
		this.fonctionnaliteNonImplementee("afficherAide()");

	}

	public void changementSystemeMetrique() 
	{
		this.fonctionnaliteNonImplementee("changementSystemeMetrique()");

	}

	public void changementSystemeMonetaire() 
	{
		this.fonctionnaliteNonImplementee("changementSystemeMonetaire()");

	}

	public void changementLanguage() 
	{
		this.fonctionnaliteNonImplementee("changementLanguage()");

	}

	/****************************************************************************************************/
	/** ViewWelcome */
	public void boutonBarman() 
	{
		// Methode appelee quand on appuie sur Barman sur l'ecran d'accueil
		this.clearStacks() ;
		
		/*ViewBarmanHome vbh = new ViewBarmanHome(this);*/
		
		Container cp = new Container() ;		
		cp.setLayout(new GridBagLayout());
		/*cp.add(vbh) ;*/
		window.setContentPane(cp);
		
		this.setActualView(cp);
		window.validate();
	}

	public void boutonGestionnaire() 
	{
		// Methode appelee quand on appuie sur Barman sur l'ecran d'accueil
		this.clearStacks() ;
		
		/*ViewBossLogin vbh = new ViewBossLogin(this);*/
		
		Container cp = new Container() ;		
		cp.setLayout(new GridBagLayout());
		/*cp.add(vbh) ;*/
		window.setContentPane(cp);
		
		this.setActualView(cp);
		window.validate();
	}


}
