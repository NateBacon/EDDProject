package core.EDDProject.main;

import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Window frame = new Window(420,640, "I <3 Luis");
		
		Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		Engine engine = new Engine(canvas, frame);
		engine.addShape(0.3f, 0.4f, 0.3f, new Color3f(0.3f, 0.3f, 1.0f));
	}

}
