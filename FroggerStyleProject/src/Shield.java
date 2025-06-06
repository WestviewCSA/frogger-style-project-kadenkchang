import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Shield{
	private Image forward;//, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = .1;		//change to scale image
	double scaleHeight = .1; 		//change to scale image
	
	int lives = 4;

	public Shield() {
		forward = getImage("/imgs/"+"shield.png"); //load the image for Tree
		

		//width/height of hit box
		width = 100;
		height = 100;
		//used for placement on the JFrame
		x = 600/2-width/2;
		y = 600;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	
	//2nd constructor - setting x and y
	public Shield(int x, int y) {
		//call the default constructor, do the specific task for THIS constructer
		this();
		this.x = x;
		this.y = y;
	}
	
	
	//Getters
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void paint(Graphics g) {
		   if (lives <= 0) {
		        return; // Don't draw if shield is "dead"
		    }
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		if(x> 950) {
			x=950;
		}
		if(x < -40) {
			x = -40;
		}
		
		init(x,y);
		
		
		g2.drawImage(forward, tx, null);
		
		if(Frame.debugging) {
			g.setColor(Color.green);
			//g.drawRect(x+15, y+15, width-48, height-40);
			//break;
		}
	}
	public Rectangle getBounds() {
	    return new Rectangle(x, y, width, height);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Ship.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
