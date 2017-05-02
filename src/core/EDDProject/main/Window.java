package core.EDDProject.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window(int height, int width, String title){
		super(title);
		this.setPreferredSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		//this.getContentPane().setLayout(null);
		this.setVisible(true);
	}

}
