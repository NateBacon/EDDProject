package core.EDDProject.main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Engine extends SimpleUniverse {
	
	private PickCanvas pickCanvas;//we will need this
	
	private Canvas3D canvas;//this is what will display everything
	private BranchGroup group;//the root group
	private TransformGroup transformGroup;//the transform group
	private Container container;//the container we want to contain the canvas in
	
	//used for mouse movement
	private MouseTranslate translateBehaviour;
	private MouseRotate rotateBehaviour;
	
	private Transform3D transform;
	
	//used to store all possible shapes
	private ArrayList<Node> nodeList;
	
	public Engine(Canvas3D canvasIn, Container containerIn){
		
		super(canvasIn);//construct the SimpleUniverse
		canvas = canvasIn;
		container = containerIn;
		container.add(canvas);//add the canvas to the container
		canvas.setSize(new Dimension(600, 400));//set the canvas to 600 by 400
		
		group = new BranchGroup();//create the root group
		group.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		group.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		group.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		group.setCapability(BranchGroup.ALLOW_DETACH);
		
		nodeList = new ArrayList<Node>();//adds a list of nodes for easy removal
		
		transformGroup = new TransformGroup();//create the transform group
		//allow read and write for the transform
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		
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
		
		//lazy lights
		DirectionalLight light = new DirectionalLight(new Color3f(1.0f, 1.0f, 1.0f), new Vector3f(1.0f, 1.0f, 1.0f));
		AmbientLight temp = new AmbientLight(new Color3f(1.0f, 1.0f, 1.0f));
		temp.setInfluencingBounds(bounds);
		light.setInfluencingBounds(bounds);
		group.addChild(light);
		group.addChild(temp);
		
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
	
	public int addShape(float width, float length, float height, Color3f color, Transform3D initPosition){//method to add rectangles to the scene
		
		//the material that the shape will have
		Material m = new Material();
		m.setEmissiveColor(color);
		m.setLightingEnable(true);
		Appearance app = new  Appearance();
		app.setMaterial(m);
		
		//actually make the shape
		Box box = new Box(width, length, height, app);
		
		//add the shape to the group and other bureaucracy
		group.detach();
		TransformGroup thisGroup  = new TransformGroup(initPosition);
		nodeList.add(thisGroup);
		thisGroup.addChild(box);
		transformGroup.addChild(thisGroup);
		this.addBranchGraph(group);
		
		//return the identifying number for this shape
		return nodeList.indexOf(thisGroup);
		
		
	}
	
	public int addShape(float radius, Color3f color, Transform3D initPosition){//same thing here
		Material m = new Material();
		m.setEmissiveColor(color);
		m.setLightingEnable(true);
		Appearance app = new  Appearance();
		app.setMaterial(m);
		Sphere sphere = new Sphere(radius, app);
		group.detach();
		TransformGroup thisGroup = new  TransformGroup(initPosition);
		nodeList.add(thisGroup);
		thisGroup.addChild(sphere);
		transformGroup.addChild(thisGroup);
		this.addBranchGraph(group);
		return nodeList.indexOf(thisGroup);
	}
	
	public int addShape(int nodeIndex){//add a shape that has already been created
		group.detach();
		transformGroup.addChild(nodeList.get(nodeIndex));
		this.addBranchGraph(group);
		return nodeIndex;
	}
	
	public void removeShape(int nodeIndex){//remove a shape based on an identifying number
		group.detach();
		transformGroup.removeChild(nodeList.get(nodeIndex));
		this.addBranchGraph(group);
	}
	
}
