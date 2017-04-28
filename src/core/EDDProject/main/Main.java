package core.EDDProject.main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Window frame = new Window(420,640, "I <3 Luis");
		Window frame2 = new Window(640,640,"UI Window");
		frame2.setBackground(Color.BLACK);
		JPanel daPanel = new JPanel();
		
		frame2.setLayout(new BorderLayout());
		frame2.add(daPanel,BorderLayout.CENTER);
		Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		Engine engine = new Engine(canvas, daPanel);
		engine.addShape(0.3f, 0.4f, 0.3f, new Color3f(.35f, .2f, .001f));
		engine.addShape(0.5f, new Color3f(.5f, .2f, .2f));
	}

}
