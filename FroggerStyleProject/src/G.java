import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class G{
	private Image forward;//, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = .075;		//change to scale image
	double scaleHeight = .075; 		//change to scale image

	public G() {
		forward = getImage("/imgs/"+"SF.png"); //load the image for Tree
		

		//width/height of hit box
		width = 120;
		height = 75;
		//used for placement on the JFrame
		x = -width;
		y = 600;
		vx = 1;
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
		Rectangle thisObject = new Rectangle (x+30,y+25,width-45,height-40);
		//checks if intersection
		return main.intersects(thisObject);
	}
	//2nd constructor - setting x and y
	public G(int x, int y) {
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
		if(x> 1400) {
			x=-100;
		}
		
		init(x,y);
		
		
		g2.drawImage(forward, tx, null);
		
		//if(Frame.debugging) {
			//g.setColor(Color.green);
			//g.drawRect(x+20, y+25, width-45, height-30);
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

	//}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = G.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
