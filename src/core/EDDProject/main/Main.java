package core.EDDProject.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class Main {
	
	public static int WIDTH, HEIGHT;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WIDTH = 1200;
		HEIGHT = 800;
		//El Chapo is my dad		
		//Window frame = new Window(HEIGHT-100,WIDTH/2+100, "I <3 Luis");
		Window frame2 = new Window(HEIGHT,WIDTH,"UI Window");
		Zelda zelda = new Zelda(frame2);
		frame2.getContentPane().setBackground(Color.gray);
		JPanel daPanel = new JPanel();
		
		
		frame2.setLayout(new BorderLayout());
		frame2.add(daPanel);
		frame2.getContentPane().setLayout(null);
		daPanel.setLocation(0, 0);
		Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		Engine engine = new Engine(canvas, daPanel);
		Transform3D initCubePosition =  new Transform3D();
		initCubePosition.setTranslation(new Vector3d(1.0, -0.2, 0.0));
		engine.addShape(0.3f, 0.4f, 0.3f, new Color3f(.35f, .2f, .001f), initCubePosition );
		engine.addShape(0.3f, new Color3f(0,0, 1), new Transform3D());
	}

}
