package graphics;

public class Camera {
	private Scene scene;
	private double pan;
	private double tilt;
	private double fov;
	private int screenwidth;
	private int screenheight;
	private Vector3d camerapos;
	
	public Camera(Scene scene, Vector3d camerapos, double pan, double tilt, double fov, int screenwidth, int screenheight) {
		this.scene = scene;
		this.pan = pan;
		this.tilt = tilt;
		this.fov = fov;
		this.screenwidth = screenwidth;
		this.screenheight = screenheight;
		this.camerapos = camerapos;
	}
	
	public Scene getScene() {
		return scene;
	}

	public double getPan() {
		return pan;
	}

	public double getTilt() {
		return tilt;
	}
	
	public double getFOV() {
		return fov;
	}
	
	public int getScreenWidth() {
		return screenwidth;
	}
	
	public int getScreenHeight() {
		return screenheight;
	}
	
	public Vector3d getCameraPos() {
		return camerapos;
	}
}
