import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class AlienGreen extends Alien{
	 public AlienGreen() {
	        super();
	        this.forward = getImage("/imgs/CSAgreen1.png");
	        this.backward = getImage("/imgs/CSAgreen2.png");
	    }

	    public AlienGreen(int x, int y) {
	        super(x, y);
	        scaleHeight = 0.22;
	        scaleWidth = 0.22;
	        this.forward = getImage("/imgs/CSAgreen1.png");
	        this.backward  = getImage("/imgs/CSAgreen2.png");
	    }
	    public void paint(Graphics g, boolean alienFrame) {
	        Graphics2D g2 = (Graphics2D) g;
	        x += vx;
	        y += vy;
	        init(x, y);
	        // Switch image based on alienFrame
	        if (alienFrame) {
	            g2.drawImage(forward, tx, null);
	        } else {
	            g2.drawImage(backward, tx, null);
	        }
	           
	        if(Frame.debugging) {
				g.setColor(Color.green);
				//g.drawRect(x+100, y+80, width-48, height-45);
				//break;
			}
	        
	        
	    }
	    public boolean collided(shipBullet bullet) {
			//represent each object as a reactagle
			//then check if they are intersecting
			
			Rectangle main = bullet.getBounds();


			
			Rectangle thisObject = new Rectangle(x+100, y+80, width-48, height-45);
			//use bult in method to check intersecion(collision)
			return main.intersects(thisObject);
	    }
}
