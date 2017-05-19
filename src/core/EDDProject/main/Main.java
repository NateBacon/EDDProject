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
		
		//Window frame = new Window(HEIGHT-100,WIDTH/2+100, "I <3 Luis");
		Window frame2 = new Window(HEIGHT,WIDTH,"UI Window");
		//Zelda zelda = new Zelda(frame2);
		//frame2.getContentPane().setBackground(Color.gray);
		JPanel daPanel = (JPanel) frame2.getContentPane();
		JComboBox button1 = new JComboBox();
		JComboBox button2 = new JComboBox();
		JLabel text1 = new JLabel();
		JLabel partDescription = new JLabel();
		daPanel.setSize((WIDTH/2)+100, HEIGHT/2+200);
		String partName = "";
		text1.setText("PC Component");
		text1.setForeground(Color.WHITE);
		text1.setBorder(null);
		frame2.setLayout(null);
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setSize((WIDTH/2)-100, HEIGHT);
		
		Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		Engine engine = new Engine(canvas, daPanel);
		Transform3D TestBench_Top_Vector =  new Transform3D();
		Transform3D TestBench_Leg1_Vector =  new Transform3D();
		Transform3D TestBench_Leg2_Vector =  new Transform3D();
		TestBench_Top_Vector.setTranslation(new Vector3d(0.0, 0.0, 0.0));
		TestBench_Leg1_Vector.setTranslation(new Vector3d(-.45, -0.1, 0.0));
		TestBench_Leg2_Vector.setTranslation(new Vector3d(0.45, -0.1, 0.0));
		engine.addShape(0.6f, 0.025f, 0.6f, new Color3f(.35f, .2f, .001f), TestBench_Top_Vector );
		engine.addShape(0.025f, 0.1f, 0.5f, new Color3f(.02f, .5f, .02f), TestBench_Leg1_Vector );
		engine.addShape(0.025f, 0.1f, 0.5f, new Color3f(.02f, .5f, .02f), TestBench_Leg2_Vector );
		//engine.addShape(0.3f, new Color3f(0,0, 1), new Transform3D());
		
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
				if(select.equals("Motherboard")){
					//add code to perform whatever tricks you want
					text1.setText("Motherboard"); 
					Transform3D MTX_Vector = new Transform3D();
					MTX_Vector.setTranslation(new Vector3d(-.06,.06,.06));
					engine.addShape(0.5f, 0.025f, 0.5f, new Color3f(.01f, .1f, .6f), MTX_Vector);
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					
				}
				
				if(select.equals("CPU")){
					//add code to perform whatever tricks you want
					text1.setText("CPU"); 
					Transform3D CPU_Pos = new Transform3D();
					CPU_Pos.setTranslation(new Vector3d(-.1,.09,-.22));
					engine.addShape(0.09f, 0.01f, 0.09f, new Color3f(.5f, .2f, .2f), CPU_Pos);
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					
				}
				
				if(select.equals("RAM")){
					//add code to perform whatever tricks you want
					text1.setText("RAM");
					Transform3D RAM_Vector = new Transform3D();
					RAM_Vector.setTranslation(new Vector3d(.05,.1,.3));
					engine.addShape(0.4f, 0.06f, 0.02f, new Color3f(.4f, .2f, .1f), RAM_Vector);
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					
				}
				
				if(select.equals("Graphics Card")){
					//add code to perform whatever tricks you want
					text1.setText("Graphics Card"); 
					Transform3D GPU_Vector  =new Transform3D();
					//shorten, shrink width, raise, move -x
					GPU_Vector.setTranslation(new Vector3d(-.2,.1,-.1));
					engine.addShape(0.075f, 0.15f, 0.5f, new Color3f(.4f, 1f, .1f), GPU_Vector);
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					
				}
				
				if(select.equals("Power Supply")){
					//add code to perform whatever tricks you want
					text1.setText("Power Supply"); 
					
					engine.addShape(0.5f, 0.2f, 0.2f, new Color3f(.4f, .2f, .1f), new Transform3D());
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					
				}
				
			}});
		button1.addItem(new String("Motherboard"));
		button1.addItem(new String("CPU"));
		button1.addItem(new String("RAM"));
		button1.addItem(new String("Graphics Card"));
		button1.addItem(new String("Power Supply"));
		button1.setEnabled(true);
		button1.setVisible(true);
		
		
		
		
		frame2.add(rightPanel);
		rightPanel.repaint();
		rightPanel.setVisible(true);
		frame2.setVisible(true);
		
	}
	

	
}
