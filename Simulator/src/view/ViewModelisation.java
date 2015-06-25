package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import controller.Controller;

public class ViewModelisation extends JPanel
{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Controller controller;
	private String modele = "";
	
	JRadioButton txt1 = new JRadioButton("Modélisation Automatique : ");
	JRadioButton txt2 = new JRadioButton("Parcourir : ");

	JComboBox choixmodele ;
	public boolean autoSelected()
	{
		if(txt1.isSelected())
		{
			return true ;
		}
		else return false ;
	}
	
	public ViewModelisation(Controller controller)
	{
		this.controller = controller ;
		this.setPreferredSize(new Dimension(1000,1000));
		
		mxGraph graph = new mxGraph();
		graph.setEnabled(false);
		Object parent = graph.getDefaultParent() ;
		Object source = graph.insertVertex(parent, null, "Entrée", 0, 100, 50, 30);
		Object fileAttente = graph.insertVertex(parent, null, "File d'Attente n°2", 120, 0, 50, 30);
		
		Object caisse1 = graph.insertVertex(parent, null, "Caisse n°1", 240, 0, 50, 30);
		Object caisse2 = graph.insertVertex(parent, null, "Caisse n°2", 240, 200, 50, 30);
		
		Object puitLivre = graph.insertVertex(parent, null, "Livré", 360, 100, 50, 30);
		Object puitRageQuit = graph.insertVertex(parent, null, "Enervé", 360, 100, 50, 30);
		
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
		
		JScrollPane scroll = new JScrollPane(graphComponent) ;
		scroll.setPreferredSize(new Dimension(100,100));
		
		this.add(scroll);
		//scroll.add(graphComponent);
		//scroll.createHorizontalScrollBar();
		//scroll.createVerticalScrollBar();
		
		//scroll.add(txt1);
		//scroll.add(txt2);
		
		
		/** definition du layout **/
		GroupLayout layout = new GroupLayout(this) ;
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(scroll));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(scroll));
		
	}
}
