package graphics;

import java.awt.Color;
import java.util.ArrayList;

public class Ray {
	Vector3d position;
	Vector3d direction;
	Scene scene;
	
	public Ray(double screenx, double screeny, Camera camera) {		
		this.scene = camera.getScene();
		
		int screenwidth = camera.getScreenWidth();
		int screenheight = camera.getScreenHeight();
		double aspectratio = (double) screenwidth / screenheight;
		
		double horizontalfov = camera.getFOV();
		double verticalfov = 2.0 * Math.atan(Math.tan(Math.toRadians(horizontalfov) / 2.0) / aspectratio);
		
		verticalfov = Math.toDegrees(verticalfov);
		
		double raypan = horizontalfov * ((screenx / screenwidth) - 0.5);
		double raytilt = -(verticalfov * ((screeny / screenheight) - 0.5));
		
		raypan = Math.toRadians(raypan);
		raytilt = Math.toRadians(raytilt);
		
		double x = Math.cos(raytilt) * Math.sin(raypan); //conventions: z is the vertical axis, +y is the forwards direction, pan is clockwise rotation from the +y direction
		double y = Math.cos(raytilt) * Math.cos(raypan);
		double z = Math.sin(raytilt);
		
		this.position = camera.getCameraPos();
		this.direction = new Vector3d(x, y, z).rotate(-camera.getPan(), -camera.getTilt());
	}
	
	public Color runRay() {
		double maxiterations = 1000;
		Vector3d initialposition = position;
		double maxdistance = 10;
		double mindistance = 100;
		int iterations = 0;
		Ball closestball = null;
		double lighting = 0;
		
		ArrayList<Ball> balls = scene.getBalls();
		for (Ball ball : balls) {
			if (ball.getDistance(position) < mindistance) {
				mindistance = ball.getDistance(position);
				closestball = ball;
			}
		}
		
		while((mindistance > 0.01)&&(iterations < maxiterations)&&(initialposition.getDistance(position) < maxdistance)) {
			for (Ball ball : balls) {
				if (ball.getDistance(position) < mindistance) {
					mindistance = ball.getDistance(position);
					closestball = ball;
				}
			}
			this.position = position.add(direction.scale(mindistance));
			iterations++;
		}
		
		if (mindistance <= 0.01) {
			Vector3d surfacenormal = closestball.getNormal(this.position);
			Vector3d reflection = this.direction.subtract(surfacenormal.scale(2 * this.direction.dotProduct(surfacenormal)));
			for (Vector3d lightsource : scene.getLights()) {
				Vector3d normallightvector = lightsource.subtract(position);
				normallightvector = normallightvector.scale(1.0 / normallightvector.getLength());
				double light = normallightvector.dotProduct(reflection);
				if (light < 0) {
					light = 0;
				}				
				lighting += light;
			}
		}
		if (lighting > 1) {
			lighting = 1;
		}
		Color ballcolor = closestball.getColor();
		float[] hsb = new float[3];
		Color.RGBtoHSB(ballcolor.getRed(), ballcolor.getBlue(), ballcolor.getGreen(), hsb);
		return Color.getHSBColor(hsb[0], hsb[1], (float) (hsb[2] * lighting));
		//return lighting / scene.getLights().size();
	}
}
