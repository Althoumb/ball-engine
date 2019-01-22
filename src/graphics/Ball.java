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
	
	public Vector3d getNormal(Vector3d position) {
		Vector3d normalvector = position.subtract(ballpos);
		normalvector = normalvector.scale(1.0 / normalvector.getLength());
		return normalvector;
	}
}
