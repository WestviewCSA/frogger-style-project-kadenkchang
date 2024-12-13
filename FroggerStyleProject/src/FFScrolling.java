import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class FFScrolling{
	private Image forward;//, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = .1;		//change to scale image
	double scaleHeight = .1; 		//change to scale image

	public FFScrolling() {
		forward = getImage("/imgs/"+"FF.png"); //load the image for Tree
		

		//width/height of hit box
		width = 120;
		height = 75;
		//used for placement on the JFrame
		x = -width;
		y = 600;
		vx = 2;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	public boolean collided(Minion minion) {
		//represent each object asa rectamg;e
		//then check if they are intersecting
		Rectangle main = new Rectangle(
				minion.getX()+15,
				minion.getY()+30,
				minion.getWidth()-48,
				minion.getHeight()-40
				
				);
		
		Rectangle thisObject = new Rectangle (x+10,y+25,width-20,height);
		//checks if intersection
		return main.intersects(thisObject);
	}
	//2nd constructor - setting x and y
	public FFScrolling(int x, int y) {
		//call the default constructor, do the specific task for THIS constructer
		this();
		this.x = x;
		this.y = y;
	}
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		//for infinite scrolling - teleport to the other side
		if(x> 1080) {
			x=80;
		}
		
		init(x,y);
		
		
		g2.drawImage(forward, tx, null);
		
		if(Frame.debugging) {
			//g.setColor(Color.green);
			//g.drawRect(x+10, y+25, width-20, height);
			//break;
		//case 1:
			//g2.drawImage(backward, tx, null);

			//break;
		//case 2:
			//g2.drawImage(left, tx, null);

			//break;
		//case 3:
			//g2.drawImage(right, tx, null);
			//break;
		}

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = FFScrolling.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
