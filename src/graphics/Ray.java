package graphics;

import java.util.ArrayList;

public class Ray {
	Vector3d position;
	Vector3d direction;
	Scene scene;
	
	public Ray(int screenx, int screeny, Camera camera) {		
		this.scene = camera.getScene();
		
		int screenwidth = camera.getScreenWidth();
		int screenheight = camera.getScreenHeight();
		double aspectratio = (double) screenwidth / screenheight;
		
		double horizontalfov = camera.getFOV();
		double verticalfov = 2 * Math.atan(Math.tan(Math.toRadians(horizontalfov) / 2.0) * aspectratio);
		
		verticalfov = Math.toDegrees(verticalfov);
		
		double raypan = horizontalfov * (((double) screenx / screenwidth) - 0.5);
		double raytilt = verticalfov * (((double) screeny / screenheight) - 0.5);
		
		raypan = Math.toRadians(raypan);
		raytilt = Math.toRadians(raytilt);
		
		double x = Math.cos(raytilt) * Math.sin(raypan); //conventions: z is the vertical axis, +y is the forwards direction, pan is clockwise rotation from the +y direction
		double y = Math.cos(raytilt) * Math.cos(raypan);
		double z = Math.sin(raytilt);
		
		this.position = camera.getCameraPos();
		this.direction = new Vector3d(x, y, z);
	}
	
	public boolean runRay() {
		double mindistance = 100;
		int iterations = 0;
		ArrayList<Ball> balls = scene.getBalls();
		for (Ball ball : balls) {
			if (ball.getDistance(position) < mindistance) {
				mindistance = ball.getDistance(position);
			}
		}
		
		while((mindistance > 0.01)&&(iterations < 1000)) {
			for (Ball ball : balls) {
				if (ball.getDistance(position) < mindistance) {
					mindistance = ball.getDistance(position);
				}
			}
			this.position = position.add(direction.scale(mindistance));
			iterations++;
		}
		return (iterations < 1000);
	}
}
