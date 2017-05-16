package core.EDDProject.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;



public class Main {
	
	public static int WIDTH, HEIGHT;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HEIGHT = 600;
		WIDTH = 1000;
		//El Chapo is my dad		
		//Window frame = new Window(HEIGHT-100,WIDTH/2+100, "I <3 Luis");
		Window frame2 = new Window(HEIGHT,WIDTH,"UI Window");
		//Zelda zelda = new Zelda(frame2);
		//frame2.getContentPane().setBackground(Color.gray);
		JPanel daPanel = (JPanel) frame2.getContentPane();
		JComboBox button1 = new JComboBox();
		JComboBox button2 = new JComboBox();
		JLabel text1 = new JLabel();
		daPanel.setSize((WIDTH/2)+100, HEIGHT/2+200);
		text1.setText("Description");
		text1.setForeground(Color.WHITE);
		text1.setBorder(null);
		frame2.setLayout(null);
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setSize((WIDTH/2)-100, HEIGHT);
		
		Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		Engine engine = new Engine(canvas, daPanel);
		Transform3D initCubePosition =  new Transform3D();
		initCubePosition.setTranslation(new Vector3d(1.0, -0.2, 0.0));
		engine.addShape(0.3f, 0.4f, 0.3f, new Color3f(.35f, .2f, .001f), initCubePosition );
		engine.addShape(0.3f, new Color3f(0,0, 1), new Transform3D());
		
		text1.setBounds(150,10,300,50);
		daPanel.setLocation(0, 0);
		rightPanel.setLocation((WIDTH/2)+100, 0);
		button1.setBounds(50, 50, 300, 50);
		button2.setBounds(50, 150, 300, 50);
		rightPanel.setBackground(Color.gray);
		
		rightPanel.add(button1);
		rightPanel.add(button2);
		rightPanel.add(text1);
		//button1.setLocation(0, 0);
		//rightPanel.add(button1);
		
		button1.addActionListener(new ActionListener(){
			
		

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cB = (JComboBox)e.getSource();
				String select = (String)cB.getSelectedItem();
				if(select.equals("Chip Board")){
					//add code to perform whatever tricks you want
					engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() );
				}
				
			}});
		button1.addItem(new String("Cancer"));
		button1.addItem(new String("Autism"));
		button1.addItem(new String("Chip Board"));
		button1.setEnabled(true);
		button1.setVisible(true);
		
		
		
		
		frame2.add(rightPanel);
		rightPanel.repaint();
		rightPanel.setVisible(true);
		frame2.setVisible(true);
		
	}
	

	
}
