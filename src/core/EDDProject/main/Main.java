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
import java.io.File;
import java.io.FileNotFoundException;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Group;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;



public class Main {
	
	public static int WIDTH = 1000;
	public static int HEIGHT = 600;
	private int PSU;
	private int motherboard;
	private int CPU_Fan;
	private int CPU;
	private int GPU;
	private Engine engine;
	private int[] indexes;
	
	public Main(){
		
		Window frame2 = new Window(HEIGHT,WIDTH,"UI Window");
		JPanel daPanel = (JPanel) frame2.getContentPane();	
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
		engine = new Engine(canvas, daPanel);
		
		//Import the obj files from libs
		
		indexes = engine.importObjs();
		
		//motherboard = engine.addNodeToScene(indexes[0]);
		//CPU = engine.addNodeToScene(indexes[1]);
		//GPU = engine.addNodeToScene(indexes[2]);
//		PSU = engine.addNodeToScene(indexes[3]);
		
		Transform3D Vector01 =  new Transform3D();
		Transform3D TestBench_Top_Vector =  new Transform3D();
		Transform3D TestBench_Leg1_Vector =  new Transform3D();
		Transform3D TestBench_Leg2_Vector =  new Transform3D();
		//Vector01.setTranslation(new Vector3d(0.05,0.0,0.0));
		TestBench_Top_Vector.setTranslation(new Vector3d(0.0, 0.0, 0.0));
		TestBench_Leg1_Vector.setTranslation(new Vector3d(-.45, -0.20, 0.0));
		TestBench_Leg2_Vector.setTranslation(new Vector3d(0.45, -0.20, 0.0));
		int testBenchTop = engine.createShape(0.6f, 0.025f, 0.6f, new Color3f(.2f, .05f, .01f), TestBench_Top_Vector );
		int testBenchLeg1 = engine.createShape(0.025f, 0.2f, 0.5f, new Color3f(.2f, .05f, .01f), TestBench_Leg1_Vector );
		int testBenchLeg2 = engine.createShape(0.025f, 0.2f, 0.5f, new Color3f(.2f, .05f, .01f), TestBench_Leg2_Vector );
		int[] test = {testBenchTop, testBenchLeg1, testBenchLeg2};
		int t1 = engine.addNodeToScene(engine.joinNodes(test));
		
		//motherboard
		Transform3D ATX_Vector = new Transform3D();
		ATX_Vector.setTranslation(new Vector3d(-.06,.06,.06));
		//int motherboard = engine.createShape(0.5f, 0.025f, 0.5f, new Color3f(.01f, .1f, .6f), ATX_Vector);

		//CPU
		Transform3D CPU_Pos = new Transform3D();
		CPU_Pos.setTranslation(new Vector3d(-.1,.09,-.22));
		int CPU = engine.createShape(0.09f, 0.01f, 0.09f, new Color3f(.5f, .2f, .2f), CPU_Pos);
		
		//RAM
		Transform3D RAM_Vector = new Transform3D();
		RAM_Vector.setTranslation(new Vector3d(.3,.13,-.1));
//		int RAM = engine.createShape(0.02f, 0.05f, 0.3f, new Color3f(.4f, .2f, .1f), RAM_Vector);
		
		//GPU
		Transform3D GPU_Vector  =new Transform3D();
		GPU_Vector.setTranslation(new Vector3d(-.2,.13,.3));
//		int GPU = engine.createShape(0.35f, 0.07f, 0.04f, new Color3f(.4f, 1f, .1f), GPU_Vector);
		
		//PSU
		Transform3D PSU_Vector = new Transform3D();
		PSU_Vector.setTranslation(new Vector3d(-.3,-.1,0));
//		int PSU = engine.createShape(0.1f, 0.1f, 0.4f, new Color3f(.4f, .2f, .1f), PSU_Vector);
		
		//test
		
		
		text1.setBounds(100,20,200,40);
		daPanel.setLocation(0, 0);
		rightPanel.setLocation((WIDTH/2)+100, 0);
//		
		rightPanel.setBackground(Color.gray);
		
		partDescription_Label.setBounds(10,420,500,200);
		
		bottomPanel.setLocation(0,(HEIGHT/2)+300);
		bottomPanel.setBackground(Color.WHITE);	
		bottomPanel.add(partDescription_Label);
		partDescription_Label.setVisible(true);


		rightPanel.add(text1);
		
		
		twiddleSticks(rightPanel, text1, partName, partDescription, partDescription_Label);
		
		frame2.add(rightPanel);
		rightPanel.repaint();
		rightPanel.setVisible(true);
		bottomPanel.repaint();
		bottomPanel.setVisible(true);
		frame2.setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
		
	       
	}
	
	public void twiddleSticks(JPanel rightPanel, JLabel text1, String partName, String partDescription, JTextPane partDescription_Label){
		JComboBox menu1 = new JComboBox();
		JComboBox menu2 = new JComboBox();
		JComboBox menu3 = new JComboBox();
		JComboBox menu4 = new JComboBox();
		JComboBox menu5 = new JComboBox();
		
		menu1.setBounds(50, 80, 300, 50);
		menu2.setBounds(50, 170, 300, 50);
		menu3.setBounds(50, 260, 300, 50);
		menu4.setBounds(50, 350, 300, 50);
		menu5.setBounds(50, 440, 300, 50);
		
		rightPanel.add(menu1);
		rightPanel.add(menu2);
		rightPanel.add(menu3);
		rightPanel.add(menu4);
		rightPanel.add(menu5);
		
		menu1.addActionListener(new ActionListener(){
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cB = (JComboBox)e.getSource();
				String select = (String)cB.getSelectedItem();
				if(select.equals("Motherboard 1")){
					//add code to perform whatever tricks you want

					text1.setText(partName+"Motherboard"); 
					motherboard = engine.addNodeToScene(indexes[0]);
					partDescription_Label.setText("Motherboard "+partDescription+"A motherboard is a printed circuit board containing the principal components of a computer or other device, with connectors into which other circuit boards can be slotted.");

					
				}
				
				
				
				
			
				
			}});
		menu1.addItem(new String(""));
		menu1.addItem(new String("Motherboard 1"));
		menu1.setEnabled(true);
		menu1.setVisible(true);
		
		menu2.addActionListener(new ActionListener(){
			
			

			

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cB = (JComboBox)e.getSource();
				String select = (String)cB.getSelectedItem();
				if(select.equals("CPU")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"CPU"); 
					//add CPU node
					//engine.addNodeToScene(CPU);
					partDescription_Label.setText("CPU "+partDescription+" The Central Processing Unit, or CPU, is the \"brains\" of the computer. "
							+ "The CPU is a chip made from silicon wafers that handles the instructions of a program by computing the basic arithmetic, "
							+ "logical, contral and input/output operations specified by the instructions.");
					
				}
				
				if(select.equals("CPU Fan")){
					//add code to perform whatever tricks you want
					text1.setText(partName+"CPU Fan");
					//add CPU fan node to scene
					
					CPU_Fan = engine.addNodeToScene(indexes[1]);
					
					partDescription_Label.setText("CPU Fan "+partDescription+" The CPU fan serves the purpose of displacing heat from the CPU. The fan usually consists of a large heatsink and the fanblade itself, and genrally sits ontop of the processor with a thin film of thermal paste between the two.");
					
					
				}
				
				
				
			}});
		
		menu2.addItem(new String("CPU 1"));
		menu2.addItem(new String("CPU Fan"));
		menu2.setEnabled(true);
		menu2.setVisible(true);
		
		menu3.addActionListener(new ActionListener(){
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cB = (JComboBox)e.getSource();
				String select = (String)cB.getSelectedItem();
				if(select.equals("RAM 1")){
					//add code to perform whatever tricks you want

					text1.setText(partName+"RAM"); 

					partDescription_Label.setText("RAM "+partDescription+" RAM (pronounced ramm) is an acronym for random access memory, a type of computer memory that can be accessed randomly; that is, any byte of memory can be accessed without touching the preceding bytes. RAM is the most common type of memory found in computers and other devices, such as printers.");

					
				}
				
				if(select.equals("RAM 2")){
					//add code to perform whatever tricks you want

					text1.setText(partName+"RAM"); 
					partDescription_Label.setText("RAM "+partDescription+" RAM (pronounced ramm) is an acronym for random access memory, a type of computer memory that can be accessed randomly; that is, any byte of memory can be accessed without touching the preceding bytes. RAM is the most common type of memory found in computers and other devices, such as printers.");
					
				}
				
				
			
				
			}});
		menu3.addItem(new String(""));
		menu3.addItem(new String("RAM 1"));
		menu3.setEnabled(true);
		menu3.setVisible(true);
		
		menu4.addActionListener(new ActionListener(){
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cB = (JComboBox)e.getSource();
				String select = (String)cB.getSelectedItem();
				if(select.equals("GPU 1")){
					//add code to perform whatever tricks you want

					text1.setText(partName+"GPU"); 
					GPU = engine.addNodeToScene(indexes[2]);
					partDescription_Label.setText("GPU "+partDescription+" (Graphics Processing Unit) A programmable logic chip (processor) specialized for display functions. The GPU renders images, animations and video for the computer's screen. GPUs are located on plug-in cards, in a chipset on the motherboard or in the same chip as the CPU.");

					
				}
				
				if(select.equals("GPU 2")){
					//add code to perform whatever tricks you want

					text1.setText(partName+"GPU"); 
					GPU = engine.addNodeToScene(indexes[2]);
					partDescription_Label.setText("GPU "+partDescription+" (Graphics Processing Unit) A programmable logic chip (processor) specialized for display functions. The GPU renders images, animations and video for the computer's screen. GPUs are located on plug-in cards, in a chipset on the motherboard or in the same chip as the CPU.");
					
				}
				
				
			
				
			}});
		menu4.addItem(new String(""));
		menu4.addItem(new String("GPU 1"));
		menu4.setEnabled(true);
		menu4.setVisible(true);
		
		menu5.addActionListener(new ActionListener(){
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cB = (JComboBox)e.getSource();
				String select = (String)cB.getSelectedItem();
				if(select.equals("Power Supply 1")){
					//add code to perform whatever tricks you want

					text1.setText(partName+"Power Supply"); 
					PSU = engine.addNodeToScene(indexes[3]);
					partDescription_Label.setText("Power Supply "+partDescription+" A power supply unit (or PSU) converts mains AC to low-voltage regulated DC power for the internal components of a computer. Modern personal computers universally use switched-mode power supplies. Some power supplies have a manual switch for selecting input voltage, while others automatically adapt to the mains voltage.");

					
				}
				
				
				
				
			
				
			}});
		
		menu5.addItem(new String(""));
		menu5.addItem(new String("Power Supply 1"));
		menu5.setEnabled(true);
		menu5.setVisible(true);
	}
	
	
	
	
}



	

