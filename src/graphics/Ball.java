package graphics;

public class Ball {
	private Vector3d ballpos;
	private double radius;
	
	public Ball(Vector3d ballpos, double radius) {
		this.ballpos = ballpos;
		this.radius = radius;
	}
	
	public double getDistance(Vector3d point) {
		return point.getDistance(ballpos) - radius;
	}
}
