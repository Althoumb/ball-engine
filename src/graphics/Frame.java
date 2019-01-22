package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	JFrame f = this;
	JPanel jp;
	public static BufferedImage img;
	
	private int width;
	private int height;
	
	Camera camera;
	
	public Frame(int width, int height) {
		this.width = width;
		this.height = height;
		
		f.setTitle("Simple Drawing");
	    f.setSize(width, height);
	    f.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    f.setUndecorated(true);
	    
	    jp = new GPanel();
	    f.add(jp);
	    f.setVisible(true);
	    img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    
	    ArrayList<Ball> test = new ArrayList<Ball>();
	    test.add(new Ball(new Vector3d(Math.random(), Math.random(), Math.random()), Math.random()));
	    test.add(new Ball(new Vector3d(Math.random(), Math.random(), Math.random()), Math.random()));
	    test.add(new Ball(new Vector3d(Math.random(), Math.random(), Math.random()), Math.random()));
	    
	    ArrayList<Vector3d> lights = new ArrayList<Vector3d>();
	    lights.add(new Vector3d(-10, -5, 0));
	    lights.add(new Vector3d(10, -5, 5));
	    lights.add(new Vector3d(0, 5, 0));
	    
	    Scene scene = new Scene(test, lights);
	    
	    this.camera = new Camera(scene, new Vector3d(0, 0, 4), 0.0, -90.0, 90.0, width, height);
	}
	
	public void render() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Ray ray = new Ray(x, y, camera);
				Color color = Color.getHSBColor(0f, 0f, (float) ray.runRay());
				img.setRGB(x, y, color.getRGB());
			}
		    f.repaint();
		}
	}
	
	private class GPanel extends JPanel {
	    public GPanel() {
	    }
	
	    @Override
	    public void paintComponent(Graphics g) {
	        g.drawImage(img, 0, 0, null);
	    }
	}
}