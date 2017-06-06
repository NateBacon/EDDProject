package core.EDDProject.main;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;


public class Engine extends SimpleUniverse {
	
	private PickCanvas pickCanvas;//we will need this
	
	private Canvas3D canvas;//this is what will display everything
	private BranchGroup group;//the root group
	private TransformGroup transformGroup;//the transform group
	private Container container;//the container we want to contain the canvas in
	
	//used for mouse movement
	private MouseTranslate translateBehaviour;
	private MouseRotate rotateBehaviour;
	private MouseZoom zoomBehaviour;
	
	private Transform3D transform;
	
	//used to store all possible shapes
	private ArrayList<Node> nodeList;
	
	private int pickedNode;
	
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
		zoomBehaviour = new MouseZoom();//create the zoom mouse behaviour
		
		//give the behaviours the transform group
		rotateBehaviour.setTransformGroup(transformGroup);
		translateBehaviour.setTransformGroup(transformGroup);
		zoomBehaviour.setTransformGroup(transformGroup);
		
		//weird stuff that i dont know what it does
		BoundingSphere bounds = new BoundingSphere();
		rotateBehaviour.setSchedulingBounds(bounds);
		translateBehaviour.setSchedulingBounds(bounds);
		zoomBehaviour.setSchedulingBounds(bounds);
		
		//set the multipliers for  the rotation and translation
		rotateBehaviour.setFactor(0.05);
		translateBehaviour.setFactor(0.005);
		
		//add the behaviours to the transform group
		transformGroup.addChild(rotateBehaviour);
		transformGroup.addChild(translateBehaviour);
		transformGroup.addChild(zoomBehaviour);
		
		//lazy lights
		DirectionalLight light = new DirectionalLight(new Color3f(1.0f, 1.0f, 1.0f), new Vector3f(10f, 10f, 10f));
		AmbientLight temp = new AmbientLight(new Color3f(1.0f, 1.0f, 1.0f));
		temp.setInfluencingBounds(bounds);
		light.setInfluencingBounds(bounds);
		group.addChild(light);
		group.addChild(temp);
		
		//add the transform group to the root group
		group.addChild(transformGroup);
		
		Transform3D looker = new Transform3D();
		Vector3d lookFrom = new Vector3d(0.0, 1.0, -5.0);
		looker.lookAt(new Point3d(0.0, 0.0, -5.0), new Point3d(0.0d, 0.0d, 0.0d), lookFrom);
		//get  the viewpoint
		this.getViewingPlatform().getViewPlatformTransform().setTransform(looker);
		
		//add the group to the universe
		this.addBranchGraph(group);
		
		//create the pick canvas
		pickCanvas = new PickCanvas(canvas, group);
		pickCanvas.setMode(PickCanvas.GEOMETRY);
		canvas.addMouseListener(new MouseAdapter(){//add picking capability
			
			public void mouseClicked(MouseEvent e){
				pickCanvas.setShapeLocation(e);
				PickResult result = pickCanvas.pickClosest();
				if (result == null){
					System.out.println("This should throw a general kind of defaultt statement");
				} else {
					
					TransformGroup t = (TransformGroup) result.getNode(PickResult.TRANSFORM_GROUP);//grab the transform group that the user picked
					
					if (t != null){
						
						for (Node node : nodeList){
							
							if(t.equals(node)){
								System.out.println("This works");
								pickedNode = nodeList.indexOf(node);//set pickedNode to the index of the node picked
								return;
							}
						}
						
					} else {
						System.out.println("We should never see this");
					}
				}
			}
			
		});
		
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
	
	public int getPickedNode(){
		return pickedNode;//picked node should be used to compare with the shapes created in the main class in order to update information on the screen based on a node picked by the user
	}
	
	public int[] importObjs(){
		ObjectFile motherboardModel = new ObjectFile();
		ObjectFile cpuModel = new ObjectFile();
		ObjectFile gpuModel = new ObjectFile();
		ObjectFile psuModel = new ObjectFile();
		
		Scene[] imports = new Scene[4];
		try{
			imports[0] = loadObjFile(motherboardModel, "motherboard.obj");
			imports[1] = loadObjFile(cpuModel, "cpu.obj");
			imports[2] = loadObjFile(gpuModel, "gpu.obj");
			imports[3] = loadObjFile(psuModel, "psu.obj");
			
		} catch(Exception e){
			e.printStackTrace(System.out);
		}

		Shape3D import1 = new Shape3D();
		Shape3D import2 = new Shape3D();
		Shape3D import3 = new Shape3D();
		Shape3D import4 = new Shape3D();
		
		if (imports[0] != null){
			import1 = (Shape3D) imports[0].getSceneGroup().getChild(0);
		}
		if (imports[1] != null){
			import2 = (Shape3D) imports[1].getSceneGroup().getChild(0);
		}
		if (imports[2] != null){
			import3 = (Shape3D) imports[2].getSceneGroup().getChild(0);
		}
		if (imports[3] != null){
			import4 = (Shape3D) imports[3].getSceneGroup().getChild(0);
		}
		
		addColor(import1, new Color3f(0.0f, 0.3f, 0.0f));
		addColor(import3, new Color3f(0.0f, 0.0f, 0.3f));
		
		//translations
		Transform3D motherboardTranslate = new Transform3D();
		Transform3D cpuTranslate = new Transform3D();
		Transform3D gpuTranslate = new Transform3D();
		Transform3D psuTranslate = new Transform3D();
		
		motherboardTranslate.setTranslation(new Vector3d(-0.2, 0.026, 0.26));
		cpuTranslate.setTranslation(new Vector3d(0.1, 0.07,-0.4));
		gpuTranslate.setTranslation(new Vector3d(-0.1, 0.07, 0.05));
		psuTranslate.setTranslation(new Vector3d(0.2, -0.25, 0.0));
		
		Transform3D motherboardScale = new Transform3D();
		Transform3D cpuScale = new Transform3D();
		Transform3D gpuScale = new Transform3D();
		Transform3D psuScale = new Transform3D();
		
		motherboardScale.setScale(0.03);
		cpuScale.setScale(0.04);
		gpuScale.setScale(0.001);
		psuScale.setScale(0.03);
		
		motherboardTranslate.mul(motherboardScale);
		cpuTranslate.mul(cpuScale);
		gpuTranslate.mul(gpuScale);
		psuTranslate.mul(psuScale);
		
		TransformGroup motherboard = new TransformGroup(motherboardTranslate);
		TransformGroup CPU = new TransformGroup(cpuTranslate);
		TransformGroup GPU = new TransformGroup(gpuTranslate);
		TransformGroup PSU = new TransformGroup(psuTranslate);
		
		
		motherboard.addChild(import1.cloneNode(true));
		CPU.addChild(import2.cloneNode(true));
		GPU.addChild(import3.cloneNode(true));
		PSU.addChild(import4.cloneNode(true));
		
		int[] objs = new int[4];
		
		objs[0] = addNodeToScene(motherboard);
		objs[1] = addNodeToScene(CPU);
		objs[2] = addNodeToScene(GPU);
		objs[3] = addNodeToScene(PSU);
		
		return objs;

	}
	
	public Scene loadObjFile(ObjectFile obj , String ref) throws Exception {
		return obj.load(getClass().getResource(ref));
	}
	
	public void addColor(Shape3D shape, Color3f color){
		Appearance appearance = new Appearance();
		Material mat = new Material();
		mat.setEmissiveColor(color);
		appearance.setMaterial(mat);
		shape.setAppearance(appearance);
	}
	
	public int createShape(float width, float length, float height, Color3f color, Transform3D initPosition){//method to add rectangles to the scene
		
		//the material that the shape will have
		Material m = new Material();
		m.setEmissiveColor(color);
		m.setLightingEnable(true);
		Appearance app = new  Appearance();
		app.setMaterial(m);
		
		//actually make the shape
		Box box = new Box(width, length, height, app);
		
		//add the shape to the group and other bureaucracy
		TransformGroup thisGroup  = new TransformGroup(initPosition);
		thisGroup.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		nodeList.add(thisGroup);
		thisGroup.addChild(box);
		
		//return the identifying number for this shape
		return nodeList.indexOf(thisGroup);
		
		
	}
	
	public int createShape(float radius, Color3f color, Transform3D initPosition){//same thing here
		Material m = new Material();
		m.setEmissiveColor(color);
		m.setLightingEnable(true);
		Appearance app = new  Appearance();
		app.setMaterial(m);
		Sphere sphere = new Sphere(radius, app);
		TransformGroup thisGroup = new  TransformGroup(initPosition);
		thisGroup.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		nodeList.add(thisGroup);
		thisGroup.addChild(sphere);
		return nodeList.indexOf(thisGroup);
	}
	
	public int addNodeToScene(int nodeIndex){//add a shape that has already been created
		group.detach();
		Node node = nodeList.get(nodeIndex);
		if (node.getParent() == null){
			transformGroup.addChild(nodeList.get(nodeIndex));
		}
		this.addBranchGraph(group);
		return nodeIndex;
	}
	
	public int addNodeToScene(Node node){
		group.detach();
		nodeList.add(node);
		int nodeIndex = nodeList.indexOf(node);
		if (node.getParent() == null){
			transformGroup.addChild(nodeList.get(nodeIndex));
		}
		this.addBranchGraph(group);
		return nodeIndex;
	}
	
	public int joinNodes(int[] nodes){
		TransformGroup parentGroup = new TransformGroup();
		parentGroup.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		
		for(int i = 0; i < nodes.length; i++){
			Node tempNode = nodeList.get(nodes[i]);
			parentGroup.addChild(tempNode);
		}
		
		nodeList.add(parentGroup);
		return nodeList.indexOf(parentGroup);
	}
	
	public void removeShape(int nodeIndex){//remove a shape based on an identifying number
		group.detach();
		transformGroup.removeChild(nodeList.get(nodeIndex));
		this.addBranchGraph(group);
	}
	
	
}
