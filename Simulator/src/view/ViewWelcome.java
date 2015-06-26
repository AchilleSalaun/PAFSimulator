package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;


public class ViewWelcome extends JPanel {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Controller controller;
	
	public ViewWelcome(Controller controller) 
	{
		this.controller = controller;
				
		// definition du layout
		GroupLayout layout = new GroupLayout(this) ;
		this.setLayout(layout);
		
		
		JLabel lign1 = new JLabel("Bienvenue sur DiscreteSimulator !",JLabel.CENTER);
		Font f1 = new Font(Font.DIALOG,Font.PLAIN,28);
		lign1.setFont(f1);
		
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(lign1)
				);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(lign1)
				);
	}
}
