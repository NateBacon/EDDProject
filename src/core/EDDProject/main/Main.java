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
	public static int test;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HEIGHT = 600;
		WIDTH = 1000;
		
		
		//Window frame = new Window(HEIGHT-100,WIDTH/2+100, "I <3 Luis");
		Window frame2 = new Window(HEIGHT,WIDTH,"UI Window"); 
		//Zelda zelda = new Zelda(frame2);
		//frame2.getContentPane().setBackground(Color.gray);
		
		JPanel daPanel = (JPanel) frame2.getContentPane();	
		JComboBox menu1 = new JComboBox();
		JComboBox menu2 = new JComboBox();
		JLabel text1 = new JLabel();
		JLabel partDescription_Label = new JLabel();
		
		daPanel.setSize((WIDTH/2)+100, HEIGHT/2+100); //engine needs to be 600 by 400
		String partName = "PC Component: ";
		String partDescription_Text = "Description: ";
		text1.setText(partName);
		text1.setForeground(Color.BLACK);
		text1.setBorder(null);
		partDescription_Label.setText(partDescription_Text);
		partDescription_Label.setForeground(Color.BLACK);
		partDescription_Label.setBorder(null);
		frame2.setLayout(null);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setSize((WIDTH/2)-100, HEIGHT);
		JPanel bottomPanel = (JPanel)frame2.getContentPane();
		bottomPanel.setLayout(null);
		bottomPanel.setSize((WIDTH/2)+100,(HEIGHT/2)-100);
		
		Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		Engine engine = new Engine(canvas, daPanel);
		Transform3D Vector01 =  new Transform3D();
		Transform3D TestBench_Top_Vector =  new Transform3D();
		Transform3D TestBench_Leg1_Vector =  new Transform3D();
		Transform3D TestBench_Leg2_Vector =  new Transform3D();
		//Vector01.setTranslation(new Vector3d(0.05,0.0,0.0));
		TestBench_Top_Vector.setTranslation(new Vector3d(0.0, 0.0, 0.0));
		TestBench_Leg1_Vector.setTranslation(new Vector3d(-.45, -0.125, 0.0));
		TestBench_Leg2_Vector.setTranslation(new Vector3d(0.45, -0.125, 0.0));
		engine.addShape(0.6f, 0.025f, 0.6f, new Color3f(.2f, .05f, .01f), TestBench_Top_Vector );
		engine.addShape(0.025f, 0.1f, 0.5f, new Color3f(.2f, .05f, .01f), TestBench_Leg1_Vector );
		engine.addShape(0.025f, 0.1f, 0.5f, new Color3f(.2f, .05f, .01f), TestBench_Leg2_Vector );
		//engine.addShape(0f,.5f,.5f, new Color3f(.2f, .1f, .05f), TestBench_Top_Vector);
		//engine.addShape(0f,.5f,.5f, new Color3f(.2f, .1f, .05f), Vector01);
		//engine.addShape(0.5f,.5f,.0f, new Color3f(.2f, .1f, .05f), TestBench_Top_Vector);
		//engine.addShape(0.3f, new Color3f(0,0, 1), new Transform3D());
		
		
		text1.setBounds(100,20,200,40);
		daPanel.setLocation(0, 0);
		rightPanel.setLocation((WIDTH/2)+100, 0);
		menu1.setBounds(50, 80, 300, 50);
		menu2.setBounds(50, 170, 300, 50);
		rightPanel.setBackground(Color.gray);
		
		partDescription_Label.setBounds(100,20,200,40);
		rightPanel.add(menu1);
		rightPanel.add(menu2);
		rightPanel.add(text1);
		//button1.setLocation(0, 0);
		//rightPanel.add(button1);
		
		bottomPanel.setLocation(0,(HEIGHT/2)+300);
		bottomPanel.setBackground(Color.lightGray);	
		bottomPanel.add(partDescription_Label);
		
		menu1.addActionListener(new ActionListener(){
			
		

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cB = (JComboBox)e.getSource();
				String select = (String)cB.getSelectedItem();
				if(select.equals("Motherboard")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"Motherboard"); 
					Transform3D ATX_Vector = new Transform3D();
					ATX_Vector.setTranslation(new Vector3d(-.06,.06,.06));
					engine.addShape(0.5f, 0.025f, 0.5f, new Color3f(.01f, .1f, .6f), ATX_Vector);
//					engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); //SPHERE for testing purposes
					
				}
				
				if(select.equals("CPU")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"CPU"); 
					Transform3D CPU_Pos = new Transform3D();
					CPU_Pos.setTranslation(new Vector3d(-.1,.09,-.22));
					test = engine.addShape(0.09f, 0.01f, 0.09f, new Color3f(.5f, .2f, .2f), CPU_Pos);
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					
				}
				
				if(select.equals("RAM")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"RAM");
					Transform3D RAM_Vector = new Transform3D();
					RAM_Vector.setTranslation(new Vector3d(.3,.13,-.1));
					engine.addShape(0.02f, 0.05f, 0.3f, new Color3f(.4f, .2f, .1f), RAM_Vector);
					engine.removeShape(test);//for testing purposes
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					
				}
				
				if(select.equals("Graphics Card")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"Graphics Card"); 
					Transform3D GPU_Vector  =new Transform3D();
					//shorten, shrink width, raise, move -x
					GPU_Vector.setTranslation(new Vector3d(-.2,.13,.3));
					engine.addShape(0.35f, 0.07f, 0.04f, new Color3f(.4f, 1f, .1f), GPU_Vector);
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					
				}
				
				if(select.equals("Power Supply")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"Power Supply"); 
					Transform3D PSU_Vector = new Transform3D();
					PSU_Vector.setTranslation(new Vector3d(-.3,-.1,0));
					engine.addShape(0.1f, 0.1f, 0.4f, new Color3f(.4f, .2f, .1f), PSU_Vector);
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					
				}
				
			}});
		menu1.addItem(new String("Motherboard"));
		menu1.addItem(new String("CPU"));
		menu1.addItem(new String("RAM"));
		menu1.addItem(new String("Graphics Card"));
		menu1.addItem(new String("Power Supply"));
		menu1.setEnabled(true);
		menu1.setVisible(true);
		
		
		
		
		frame2.add(rightPanel);
		rightPanel.repaint();
		rightPanel.setVisible(true);
		bottomPanel.repaint();
		bottomPanel.setVisible(true);
		frame2.setVisible(true);
		
	}
	

	
}
