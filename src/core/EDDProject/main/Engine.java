package core.EDDProject.main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Engine extends SimpleUniverse {
	
	private PickCanvas pickCanvas;//not sure if we need this yet
	
	private Canvas3D canvas;//this is what will display everything
	private BranchGroup group;//the root group
	private TransformGroup transformGroup;//the transform group
	private Container container;//the container we want to contain the canvas in
	private MouseTranslate translateBehaviour;
	private MouseRotate rotateBehaviour;
	private Transform3D transform;
	
	public Engine(Canvas3D canvasIn, Container containerIn){
		
		super(canvasIn);//construct the SimpleUniverse
		canvas = canvasIn;
		container = containerIn;
		container.add(canvas);//add the canvas to the container
		canvas.setSize(new Dimension(600, 400));//set the canvas to 600 by 400
		
		group = new BranchGroup();//create the root group
		transformGroup = new TransformGroup();//create the transform group
		//allow read and write for the transform
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		rotateBehaviour = new MouseRotate();//create the rotation mouse behaviour
		translateBehaviour = new MouseTranslate();//create the translation mouse behaviour
		
		//give the behaviours the transform group
		rotateBehaviour.setTransformGroup(transformGroup);
		translateBehaviour.setTransformGroup(transformGroup);
		
		//weird stuff that i dont know what it does
		BoundingSphere bounds = new BoundingSphere();
		rotateBehaviour.setSchedulingBounds(bounds);
		translateBehaviour.setSchedulingBounds(bounds);
		
		//set the multipliers for  the rotation and translation
		rotateBehaviour.setFactor(0.05);
		translateBehaviour.setFactor(0.005);
		
		//add the behaviours to the transform group
		transformGroup.addChild(rotateBehaviour);
		transformGroup.addChild(translateBehaviour);
		
		//add the transform group to the root group
		group.addChild(transformGroup);
		
		//get  the viewpoint
		this.getViewingPlatform().setNominalViewingTransform();
		
		//add the group to the universe
		this.addBranchGraph(group);
		
		//create the pick canvas
		pickCanvas = new PickCanvas(canvas, group);
		pickCanvas.setMode(PickCanvas.GEOMETRY);
		
		//if our container is a JFrame, set the window to exit on close
		if (container instanceof JFrame){
			((JFrame) container).addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			});
			((JFrame) container).pack();
		}
		
		//set the container to be visible
		container.setVisible(true);
		
	}
	
	public void addShape(float width, float length, float height){
		
	}
	
}
