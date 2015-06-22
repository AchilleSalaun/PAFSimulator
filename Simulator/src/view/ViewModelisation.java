package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	public ViewModelisation(Controller controller)
	{
		this.controller = controller ;
		
		// definition du layout
		GroupLayout layout = new GroupLayout(this) ;
		this.setLayout(layout);
		
		JRadioButton txt1 = new JRadioButton("Modélisation Automatique : ");
		txt1.setSelected(true);
		String[] tab ={"Vierge","Pré-Enregistré 1","Pré-Enregistré 2"};
		JComboBox choixmodele = new JComboBox(tab);
		
		JRadioButton txt2 = new JRadioButton("Parcourir : ");
		JTextField adressemodele = new JTextField();
		
		ButtonGroup group = new ButtonGroup();
		group.add(txt1);
		group.add(txt2);
		
		JButton charger = new JButton("Charger");
		
		charger.addActionListener(new ActionListener()
		{					
			public void actionPerformed(ActionEvent arg0)
			{
				controller.charger();
			}
		});
		
		JButton enregistrer = new JButton("Enregistrer");
		
		enregistrer.addActionListener(new ActionListener()
		{					
			public void actionPerformed(ActionEvent arg0)
			{
				controller.enregistrer();
			}
		});
		
		/*****************************************/
		
		mxGraphModel modelSource = new mxGraphModel();
		mxGraph graphSource = new mxGraph();
		graphSource.setEnabled(false);
		Object parentSource = graphSource.getDefaultParent() ;
		Object vSource = graphSource.insertVertex(parentSource, null, "Source", 20, 20, 80, 30);
		
		
		graphSource.getModel().endUpdate();
		
		mxGraphComponent graphComponentSource = new mxGraphComponent(graphSource);
		JScrollPane scrollSource = new JScrollPane();
		scrollSource.add(graphComponentSource);
		/*****************************************/
		
		mxGraphModel modelFile = new mxGraphModel();
		mxGraph graphFile = new mxGraph();
		graphFile.setEnabled(false);
		Object parentFile = graphFile.getDefaultParent() ;
		Object vFile = graphFile.insertVertex(parentFile, null, "File", 20, 20, 80, 30);
		
		graphFile.getModel().endUpdate();
		
		mxGraphComponent graphComponentFile = new mxGraphComponent(graphFile);
		JScrollPane scrollFile = new JScrollPane();
		scrollFile.add(graphComponentFile);
		/*****************************************/
		
		mxGraphModel modelPuit = new mxGraphModel();
		mxGraph graphPuit = new mxGraph();
		graphPuit.setEnabled(false);
		Object parentPuit = graphPuit.getDefaultParent() ;
		Object vPuit = graphPuit.insertVertex(parentPuit, null, "Puit", 20, 20, 10, 10);
		
		graphPuit.getModel().endUpdate();
		
		mxGraphComponent graphComponentPuit = new mxGraphComponent(graphPuit);
		JScrollPane scrollPuit = new JScrollPane();
		scrollPuit.add(graphComponentPuit);
		/*****************************************/
		
		//mxGraphModel model = new mxGraphModel();
		mxGraph graph = new mxGraph();
		graph.setEnabled(true);
		
		Object parent = graph.getDefaultParent() ;
		Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 10, 10);
		graph.getModel().endUpdate();
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		JScrollPane scroll = new JScrollPane();
		scroll.add(graphComponent);
		/*****************************************/
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addComponent(txt1)
						.addComponent(choixmodele))
				.addGroup(layout.createSequentialGroup()
						.addComponent(txt2)
						.addComponent(adressemodele))
				.addComponent(charger)
				.addGroup(layout.createSequentialGroup()
						.addComponent(graphComponent)
						.addGroup(layout.createParallelGroup()
								.addComponent(scrollSource)
								.addComponent(scrollFile)
								.addComponent(scrollPuit)))
				.addComponent(enregistrer)
				);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(txt1)
						.addComponent(choixmodele))
				.addGroup(layout.createParallelGroup()
						.addComponent(txt2)
						.addComponent(adressemodele))
				.addComponent(charger)
				.addGroup(layout.createParallelGroup()
						.addComponent(graphComponent)
						.addGroup(layout.createSequentialGroup()
								.addComponent(scrollSource)
								.addComponent(scrollFile)
								.addComponent(scrollPuit)))
				.addComponent(enregistrer)
				
				);
	}
}
