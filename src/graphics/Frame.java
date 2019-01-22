package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	JFrame f = this;
	JPanel jp;
	public static BufferedImage img;
	
	private int width;
	private int height;
	
	private int msaasqrt;
	
	Camera camera;
	
	public Frame(int width, int height, int msaasqrt) {
		this.width = width;
		this.height = height;
		this.msaasqrt = msaasqrt;
		
		f.setTitle("Simple Drawing");
	    f.setSize(width, height);
	    f.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    f.setUndecorated(true);
	    
	    jp = new GPanel();
	    f.add(jp);
	    f.setVisible(true);
	    img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    
	    ArrayList<Ball> test = new ArrayList<Ball>();
	    test.add(new Ball(new Vector3d(-1, -1, 0), 1, Color.white));
	    test.add(new Ball(new Vector3d(1, 1, 0), 1, Color.red));
	    test.add(new Ball(new Vector3d(1, -1, 0), 1, Color.green));
	    test.add(new Ball(new Vector3d(-1, 1, 0), 1, Color.blue));
	    
	    ArrayList<Vector3d> lights = new ArrayList<Vector3d>();
	    lights.add(new Vector3d(-1, -1, 0));
	    lights.add(new Vector3d(1, 1, 0));
	    lights.add(new Vector3d(1, -1, 0));
	    lights.add(new Vector3d(-1, 1, 0));
	    
	    Scene scene = new Scene(test, lights);
	    
	    this.camera = new Camera(scene, new Vector3d(0, -5, 5), 0.0, -45.0, 45.0, width, height);
	}
	
	public void render() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int red = 0;
				int blue = 0;
				int green = 0;
				for (int msaax = 1; msaax <= msaasqrt; msaax++) {
					for (int msaay = 1; msaay <= msaasqrt; msaay++) {
						Ray ray = new Ray(x + ((double) msaax / (msaasqrt + 1)), y + ((double) msaay / (msaasqrt + 1)), camera);
						Color color = ray.runRay();
						red += color.getRed();
						blue += color.getBlue();
						green += color.getGreen();
					}
				}
				red /= (msaasqrt * msaasqrt);
				blue /= (msaasqrt * msaasqrt);
				green /= (msaasqrt * msaasqrt);
				img.setRGB(x, y, new Color(red, blue, green).getRGB());
			}
		    f.repaint();
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		Date date = new Date();
		File outputfile = new File("renders/".concat(dateFormat.format(date)).concat(".png"));
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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