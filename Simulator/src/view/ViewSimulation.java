package view;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import controller.Controller;

public class ViewSimulation extends JPanel
{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Controller controller;
	
	public mxGraphComponent setVierge()
	{
		mxGraph graph = new mxGraph();
		graph.setEnabled(false);
		Object parent = graph.getDefaultParent() ;
		
		graph.getModel().endUpdate();
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		
		return graphComponent ;
	}
	
	public mxGraphComponent setModel1()
	{
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent() ;
		Object source = graph.insertVertex(parent, null, "Entrée", 10, 50, 40, 40);
		Object fileAttente1 = graph.insertVertex(parent, null, "File d'Attente n°1", 150, 0, 40, 40);
		Object fileAttente2 = graph.insertVertex(parent, null, "File d'Attente n°2", 150, 100, 40, 40);
		
		Object caisse1 = graph.insertVertex(parent, null, "Caisse n°1", 450, 0, 40, 40);
		Object caisse2 = graph.insertVertex(parent, null, "Caisse n°2", 450, 100, 40, 40);
		
		Object puitLivre = graph.insertVertex(parent, null, "Livré", 600, 50, 40, 40);
		Object puitRageQuit = graph.insertVertex(parent, null, "Enervé", 300, 50, 40, 40);
		
		graph.insertEdge(parent, null, "sortie",source, fileAttente1);
		graph.insertEdge(parent, null, "sortie",source, fileAttente2);
		
		graph.insertEdge(parent, null, "sortie",fileAttente1, caisse1);
		graph.insertEdge(parent, null, "sortie",fileAttente2, caisse2);
		
		graph.insertEdge(parent, null, "sortie",caisse1, puitLivre);
		graph.insertEdge(parent, null, "sortie",caisse2, puitLivre);
		
		graph.insertEdge(parent, null, "échappatoire",fileAttente1, puitRageQuit);
		graph.insertEdge(parent, null, "échappatoire",fileAttente2, puitRageQuit);
		graph.insertEdge(parent, null, "échappatoire",caisse1, puitRageQuit);
		graph.insertEdge(parent, null, "échappatoire",caisse2, puitRageQuit);
				
		graph.getModel().endUpdate();
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		
		return graphComponent ;
	}
	
	public mxGraphComponent setModel2()
	{
		mxGraph graph = new mxGraph();
		
		Object parent = graph.getDefaultParent() ;
		Object source = graph.insertVertex(parent, null, "Entrée", 10, 50, 40, 40);
		Object fileAttente = graph.insertVertex(parent, null, "File d'Attente", 150, 50, 40, 40);
		
		Object caisse1 = graph.insertVertex(parent, null, "Caisse n°1", 450, 0, 40, 40);
		Object caisse2 = graph.insertVertex(parent, null, "Caisse n°2", 450, 100, 40, 40);
		
		Object puitLivre = graph.insertVertex(parent, null, "Livré", 600, 50, 40, 40);
		Object puitRageQuit = graph.insertVertex(parent, null, "Enervé", 350, 50, 40, 40);
		
		graph.insertEdge(parent, null, "sortie",source, fileAttente);
		
		graph.insertEdge(parent, null, "sortie",fileAttente, caisse1);
		graph.insertEdge(parent, null, "sortie",fileAttente, caisse2);
		
		graph.insertEdge(parent, null, "sortie",caisse1, puitLivre);
		graph.insertEdge(parent, null, "sortie",caisse2, puitLivre);
		
		graph.insertEdge(parent, null, "échappatoire",fileAttente, puitRageQuit);
		graph.insertEdge(parent, null, "échappatoire",caisse1, puitRageQuit);
		graph.insertEdge(parent, null, "échappatoire",caisse2, puitRageQuit);
		
		graph.getModel().endUpdate();
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		
		return graphComponent ;
	}
	
	//Classe interne implémentant l'interface ItemListener
	class ItemState implements ItemListener
	{
		public void itemStateChanged(ItemEvent e) 
		{	
			System.out.println(e.getItem().toString());
			switch("1"+e.getItem().toString())
			{
				case "Pré-Enregistré 1" : System.out.println(e.getItem().toString());//controller.chargerPE(1);
					break ;
				case "Pré-Enregistré 2" : System.out.println(e.getItem().toString());//controller.chargerPE(2);
					break ;
			}
			System.out.println("2"+e.getItem().toString());
		}
	}
	
	public ViewSimulation(Controller controller, int modele)
	{
		this.controller = controller ;
		
		/** definition du layout **/
		GroupLayout layout = new GroupLayout(this) ;
		this.setLayout(layout);
		
		/** définition des objets **/
		JLabel txt = new JLabel("Choix du modèle :  ");
		
		String[] tab ={"Pré-Enregistré 1","Pré-Enregistré 2"};
		JComboBox choixModele = new JComboBox(tab);
		choixModele.addItemListener(new ItemState());
		
		mxGraphComponent graphComponent ;
		
		switch(modele)
		{
			case 1 : graphComponent = this.setModel1();
				break ;
			case 2 : graphComponent = this.setModel2();
				break ;	
			default : graphComponent = this.setModel1();
		}
		
		JPanel scroll = new JPanel();
		scroll.add(graphComponent);
		
		JButton lancer = new JButton("Lancer la simulation");
		
		lancer.addActionListener(new ActionListener()
		{					
			public void actionPerformed(ActionEvent arg0)
			{
				controller.lancerSimulation();
			}	
		});
		
		lancer.addActionListener(new ActionListener()
		{					
			public void actionPerformed(ActionEvent arg0)
			{
				controller.lancerSimulation();
			}	
		});
	
	/*****************************************/
	
	layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(scroll)
					.addGroup(layout.createSequentialGroup()
							.addComponent(txt)
							.addComponent(choixModele))
					.addComponent(lancer));
	layout.setVerticalGroup(layout.createSequentialGroup()
					.addComponent(scroll,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(txt)
							.addComponent(choixModele))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(lancer));
	}
}
