package view;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class ViewModelisation extends JPanel
{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Controller controller;
	
	public ViewModelisation(Controller controller)
	{
		this.controller = controller ;
		
		// definition du layout
		GroupLayout layout = new GroupLayout(this) ;
		this.setLayout(layout);
		
		
	}
}
