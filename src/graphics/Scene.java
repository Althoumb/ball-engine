package graphics;

import java.util.ArrayList;

public class Scene {
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private ArrayList<Vector3d> lights = new ArrayList<Vector3d>();
	
	public Scene(ArrayList<Ball> balls, ArrayList<Vector3d> lights) {
		this.balls = balls;
		this.lights = lights;
	}
	
	public ArrayList<Ball> getBalls() {
		return balls;
	}
	
	public ArrayList<Vector3d> getLights() {
		return lights;
	}
}
