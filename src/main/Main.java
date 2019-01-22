package main;

import java.awt.Color;
import java.util.ArrayList;

import graphics.Ball;
import graphics.Camera;
import graphics.Frame;
import graphics.Scene;
import graphics.Vector3d;

public class Main {
	public static final int WIDTH = 160;
	public static final int HEIGHT = 90;
	public static final int ANTIALIASLEVEL = 1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    ArrayList<Ball> balls = new ArrayList<Ball>();
	    balls.add(new Ball(new Vector3d(-1, -1, 0), 1, Color.white));
	    balls.add(new Ball(new Vector3d(1, 1, 0), 1, Color.red));
	    balls.add(new Ball(new Vector3d(1, -1, 0), 1, Color.green));
	    balls.add(new Ball(new Vector3d(-1, 1, 0), 1, Color.blue));
	    
	    ArrayList<Vector3d> lights = new ArrayList<Vector3d>();
	    lights.add(new Vector3d(-1, -1, 0));
	    lights.add(new Vector3d(1, 1, 0));
	    lights.add(new Vector3d(1, -1, 0));
	    lights.add(new Vector3d(-1, 1, 0));
	    lights.add(new Vector3d(0, 0, 5));

	    Scene scene = new Scene(balls, lights);
	    
	    Camera camera = new Camera(scene, new Vector3d(0, 0, 5), 0.0, -90.0, 45.0, WIDTH, HEIGHT);
	    
		Frame frame = new Frame(WIDTH, HEIGHT, ANTIALIASLEVEL, false, camera);
		
		for (int i = 0; i < 1000; i++) {
			double theta = 360*i/1000.0;
			frame.setCamera(new Camera(scene, new Vector3d(5 * Math.cos(Math.toRadians(-theta - 90)), 5 * Math.sin(Math.toRadians(-theta - 90)), 5), theta, -45, 90.0, WIDTH, HEIGHT));
			frame.render();
		}
	}

}
