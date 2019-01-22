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
		return this.tilt(tilt).pan(pan);
	}
	
	private Vector3d pan(double pan) {
		double cosx = Math.cos(Math.toRadians(pan));
		double sinx = Math.sin(Math.toRadians(pan));
		double newx = this.x * cosx - this.y * sinx;
		double newy = this.y * cosx + this.x * sinx;
		return new Vector3d(newx, newy, this.z);
	}
	
	private Vector3d tilt(double tilt) {
		double cosy = Math.cos(Math.toRadians(-tilt));
		double siny = Math.sin(Math.toRadians(-tilt));
		double newz = this.z * cosy + this.y * siny;
		double newy = this.y * cosy - this.z * siny;
		return new Vector3d(this.x, newy, newz);
	}
}
