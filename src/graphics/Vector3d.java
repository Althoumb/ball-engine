package graphics;

public class Vector3d {
	private double x;
	private double y;
	private double z;
	
	public Vector3d() {		
	}
	
	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public double dotProduct(Vector3d vector) {
		return ((vector.getX() * x) + (vector.getY() * y) + (vector.getZ() * z));
	}
	
	public double getDistance(Vector3d vector) {
		double xdist = vector.getX() - x;
		double ydist = vector.getY() - y;
		double zdist = vector.getZ() - z;
		xdist *= xdist;
		ydist *= ydist;
		zdist *= zdist;
		return Math.sqrt(xdist+ydist+zdist);
	}
	
	public double getLength() {
		return getDistance(new Vector3d(0, 0, 0));
	}
	
	public Vector3d add(Vector3d vector) {
		return new Vector3d(x + vector.getX(), y + vector.getY(), z + vector.getZ());
	}
	
	public Vector3d subtract(Vector3d vector) {
		return new Vector3d(x - vector.getX(), y - vector.getY(), z - vector.getZ());
	}
	
	public Vector3d scale(double scalefactor) {
		return new Vector3d(x * scalefactor, y * scalefactor, z * scalefactor);
	}
}
