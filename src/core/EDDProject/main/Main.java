package core.EDDProject.main;

import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Window frame = new Window(420,640, "I <3 Luis");
		
		

		JFrame frame = new JFrame("test");
		Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		Engine engine = new Engine(canvas, frame);
	}

}
