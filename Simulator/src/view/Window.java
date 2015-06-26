package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import controller.Controller;

public class Window extends JFrame 
{
	private static final long serialVersionUID = 1L;
	String title = "DiscreteSimulator";
	private int width = 1000;
	private int height = 800;
	private Controller controller ;

	private JMenuBar menubar = new JMenuBar();
	private JToolBar toolbar = new JToolBar();
	
	private JButton simulation = new JButton("Simulation");
	private JButton previous = new JButton("⇦");
	private JButton next = new JButton("⇨");	


	private JMenu help = new JMenu("Aide");
	private JMenuItem help_help = new JMenuItem("Aide");
	private JMenuItem help_about = new JMenuItem("A propos de DiscreteSimulator");

	public void setWSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public JButton getNextButton()
	{
		return next ;
	}
	public JButton getPreviousButton()
	{
		return previous ;
	}

	public Window() 
	{
		Container cont = this.getContentPane();
        cont.setLayout(new GridBagLayout());
        
		this.setTitle(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		simulation.setBackground(Color.RED);
		previous.setBackground(Color.YELLOW);
		next.setBackground(Color.BLUE);
		
		
		toolbar.setFloatable(false);
		
		/** Navigation */

			this.previous.addActionListener(new ActionListener()
			{					
				public void actionPerformed(ActionEvent arg0)
				{
					controller.previousView(controller.getActualView());
				}
			});

			this.next.addActionListener(new ActionListener()
			{					
				public void actionPerformed(ActionEvent arg0)
				{
					controller.nextView(controller.getActualView());
				}
			});
			
		/** **/
			this.simulation.addActionListener(new ActionListener()
			{					
				public void actionPerformed(ActionEvent arg0)
				{
					controller.boutonSimulation(controller.getModele());
				}
			});

		/** Menu help */
		this.help.add(this.help_help);
		this.help_help.addActionListener(new ActionListener()
		{					
			public void actionPerformed(ActionEvent arg0)
			{
				controller.afficherAide();
			}
		});
		this.help.add(this.help_about);
		this.help_about.addActionListener(new ActionListener()
		{					
			public void actionPerformed(ActionEvent arg0)
			{
				controller.aProposOptibar();
			}
		});
		
		previous.setForeground(Color.GRAY);
		next.setForeground(Color.GRAY);
		
		Font f = new Font(Font.DIALOG,Font.BOLD,15);
		
		previous.setFont(f);
		next.setFont(f);
		this.toolbar.add(previous);
		this.toolbar.add(next);
		this.toolbar.add(simulation);
		this.menubar.add(toolbar);

		this.menubar.add(help);

		this.setJMenuBar(menubar);
		
		
		controller=new Controller(this);
		ViewWelcome vw = new ViewWelcome(controller);
		Container cp = new Container() ;
			
		cp.setLayout(new GridBagLayout());
		cp.add(vw) ;
		this.setContentPane(cp);
		controller.setActualView(cp);
		this.setVisible(true);
	}
}
