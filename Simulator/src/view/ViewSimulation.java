package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;

public class ViewSimulation extends JPanel
{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Controller controller;
	
	public ViewSimulation(Controller controller)
	{
		this.controller = controller ;
		
		// definition du layout
		GroupLayout layout = new GroupLayout(this) ;
		this.setLayout(layout);
		
		JButton lancer = new JButton("Lancer la simulation");
		
		lancer.addActionListener(new ActionListener()
		{					
			public void actionPerformed(ActionEvent arg0)
			{
				controller.lancerSimulation();
			}	
		});
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(lancer)
				);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(lancer)
				);
	}
}
