package core.EDDProject.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
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
		JComboBox menu1 = new JComboBox();
		JComboBox menu2 = new JComboBox();
		JComboBox menu3 = new JComboBox();
		JComboBox menu4 = new JComboBox();
		JComboBox menu5 = new JComboBox();
		JLabel text1 = new JLabel();
		JTextPane partDescription_Label = new JTextPane();
		partDescription_Label.setFont(new Font("Arial",Font.BOLD,15));
		
		daPanel.setSize((WIDTH/2)+100, HEIGHT/2+100); //engine needs to be 600 by 400
		String partName = "PC Component: ";
		String partDescription = "Description: ";
		text1.setText(partName);
		text1.setForeground(Color.BLACK);
		text1.setBorder(null);
		partDescription_Label.setText(partDescription);
		partDescription_Label.setForeground(Color.BLACK);
		partDescription_Label.setBorder(null);
		frame2.setLayout(null);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setSize((WIDTH/2)-100, HEIGHT);
		JPanel bottomPanel = (JPanel)frame2.getContentPane();
		//JPanel bottomPanel = new JPanel();
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
		int testBenchTop = engine.createShape(0.6f, 0.025f, 0.6f, new Color3f(.2f, .05f, .01f), TestBench_Top_Vector );
		int testBenchLeg1 = engine.createShape(0.025f, 0.1f, 0.5f, new Color3f(.2f, .05f, .01f), TestBench_Leg1_Vector );
		int testBenchLeg2 = engine.createShape(0.025f, 0.1f, 0.5f, new Color3f(.2f, .05f, .01f), TestBench_Leg2_Vector );
		int[] test = {testBenchTop, testBenchLeg1, testBenchLeg2};
		int t1 = engine.addNodeToScene(engine.joinNodes(test));
		
		//motherboard
		Transform3D ATX_Vector = new Transform3D();
		ATX_Vector.setTranslation(new Vector3d(-.06,.06,.06));
		int motherboard = engine.createShape(0.5f, 0.025f, 0.5f, new Color3f(.01f, .1f, .6f), ATX_Vector);

		//CPU
		Transform3D CPU_Pos = new Transform3D();
		CPU_Pos.setTranslation(new Vector3d(-.1,.09,-.22));
		int CPU = engine.createShape(0.09f, 0.01f, 0.09f, new Color3f(.5f, .2f, .2f), CPU_Pos);
		
		//RAM
		Transform3D RAM_Vector = new Transform3D();
		RAM_Vector.setTranslation(new Vector3d(.3,.13,-.1));
		int RAM = engine.createShape(0.02f, 0.05f, 0.3f, new Color3f(.4f, .2f, .1f), RAM_Vector);
		
		//GPU
		Transform3D GPU_Vector  =new Transform3D();
		GPU_Vector.setTranslation(new Vector3d(-.2,.13,.3));
		int GPU = engine.createShape(0.35f, 0.07f, 0.04f, new Color3f(.4f, 1f, .1f), GPU_Vector);
		
		//PSU
		Transform3D PSU_Vector = new Transform3D();
		PSU_Vector.setTranslation(new Vector3d(-.3,-.1,0));
		int PSU = engine.createShape(0.1f, 0.1f, 0.4f, new Color3f(.4f, .2f, .1f), PSU_Vector);
		
		
		text1.setBounds(100,20,200,40);
		daPanel.setLocation(0, 0);
		rightPanel.setLocation((WIDTH/2)+100, 0);
		menu1.setBounds(50, 80, 300, 50);
		menu2.setBounds(50, 170, 300, 50);
		menu3.setBounds(50, 260, 300, 50);
		menu4.setBounds(50, 350, 300, 50);
		menu5.setBounds(50, 440, 300, 50);
		rightPanel.setBackground(Color.gray);
		
		partDescription_Label.setBounds(10,420,500,200);
		
		bottomPanel.setLocation(0,(HEIGHT/2)+300);
		bottomPanel.setBackground(Color.WHITE);	
		bottomPanel.add(partDescription_Label);
		partDescription_Label.setVisible(true);

		rightPanel.add(menu1);
		rightPanel.add(menu2);
		rightPanel.add(menu3);
		rightPanel.add(menu4);
		rightPanel.add(menu5);
		rightPanel.add(text1);
		//bottomPanel.add(partDescription_Label);
		//button1.setLocation(0, 0);
		//rightPanel.add(button1);
		
		
		menu1.addActionListener(new ActionListener(){
			
		

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cB = (JComboBox)e.getSource();
				String select = (String)cB.getSelectedItem();
				if(select.equals("Motherboard 1")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"Motherboard 1"); 
					engine.addNodeToScene(motherboard);
					
				}
				
				if(select.equals("Motherboard 2")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"Motherboard 2");
					
					//add a diff motherboard
					//engine.addNodeToScene(CPU);
					
					//set text for motherboard
					partDescription_Label.setText("CPU"+partDescription+"The Central Processing Unit, or CPU, is the \"brains\" of the computer. "
							+ "/nThe CPU is a chip made from silicon wafers that handles the instructions of a program by computing the basic arithmetic, "
							+ "logical, contral and input/output operations specified by the instructions.");
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					
				}
				
				if(select.equals("Motherboard 3")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"Motherboard 3");
					//engine.addNodeToScene(RAM);
				}
				
				if(select.equals("Motherboard 4")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"Motherboard 4"); 
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					//engine.addNodeToScene(GPU);
				}
				
				if(select.equals("Motherboard 5")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"Motherboard 5"); 
					//engine.addNodeToScene(PSU);
					
				}
				
			}});
		menu1.addItem(new String("Motherboard 1"));
		menu1.addItem(new String("Motherboard 2"));
		menu1.addItem(new String("Motherboard 3"));
		menu1.addItem(new String("Motherboard 4"));
		menu1.addItem(new String("Motherboard 5"));
		menu1.setEnabled(true);
		menu1.setVisible(true);
		
		menu2.addActionListener(new ActionListener(){
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cB = (JComboBox)e.getSource();
				String select = (String)cB.getSelectedItem();
				if(select.equals("CPU 1")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"CPU 1"); 
					//engine.addNodeToScene(motherboard);
					
				}
				
				if(select.equals("CPU 2")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"CPU 2");
					
					//add a diff motherboard
					engine.addNodeToScene(CPU);
					
					//set text for motherboard
					partDescription_Label.setText("CPU "+partDescription+" The Central Processing Unit, or CPU, is the \"brains\" of the computer. "
							+ "The CPU is a chip made from silicon wafers that handles the instructions of a program by computing the basic arithmetic, "
							+ "logical, contral and input/output operations specified by the instructions.");
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					
				}
				
				if(select.equals("CPU 3")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"CPU 3");
					//engine.addNodeToScene(RAM);
				}
				
				if(select.equals("CPU 4")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"CPU 4"); 
					//engine.addShape(.7f, new Color3f(.2f,.5f,.3f), new Transform3D() ); SPHERE
					//engine.addNodeToScene(GPU);
				}
				
				if(select.equals("CPU 5")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"CPU 5"); 
					//engine.addNodeToScene(PSU);
					
				}
				
			}});
		
		menu2.addItem(new String("CPU 1"));
		menu2.addItem(new String("CPU 2"));
		menu2.addItem(new String("CPU 3"));
		menu2.addItem(new String("CPU 4"));
		menu2.addItem(new String("CPU 5"));
		menu2.setEnabled(true);
		menu2.setVisible(true);
		
		
		frame2.add(rightPanel);
		rightPanel.repaint();
		rightPanel.setVisible(true);
		bottomPanel.repaint();
		bottomPanel.setVisible(true);
		frame2.setVisible(true);
		
	}
	

	
}
