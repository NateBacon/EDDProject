package core.EDDProject.main;

public class Engine {
	private static Engine single = new Engine();//there can only be one
	
	public static Engine getEngine(){
		return single;
		//penis
	}
}
