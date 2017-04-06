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
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
