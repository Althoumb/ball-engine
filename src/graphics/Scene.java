package graphics;

import java.util.ArrayList;

public class Scene {
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	
	public Scene(ArrayList<Ball> balls) {
		this.balls = balls;
	}
	
	public ArrayList<Ball> getBalls() {
		return balls;
	}

}
