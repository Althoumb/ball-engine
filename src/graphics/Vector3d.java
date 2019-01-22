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
	
	public Vector3d rotate(double pan, double tilt) {
		double cosy = Math.cos(Math.toRadians(tilt));
		double siny = Math.sin(Math.toRadians(tilt));
		double cosx = Math.cos(Math.toRadians(pan));
		double sinx = Math.sin(Math.toRadians(pan));
		double newx = this.x;
		double newy = this.y * cosy + this.z * siny;
		double newz = -this.y * siny + this.z * cosy;
		newy = newy * cosx - newx * sinx;
		newx = newy * sinx + newx * cosx;
		return new Vector3d(newx, newy, newz);
	}
}
