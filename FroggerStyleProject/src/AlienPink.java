import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class AlienPink extends Alien{
	public AlienPink() {
        super();
        this.forward = getImage("/imgs/CSApink1.png");
        this.backward = getImage("/imgs/CSApink2 (1).png");
    }

    public AlienPink(int x, int y) {
        super(x, y);
        scaleHeight = 0.18;
        scaleWidth = 0.18;
        this.forward = getImage("/imgs/CSApink1.png");
        this.backward = getImage("/imgs/CSApink2 (1).png");
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
			//g.drawRect(x+75, y+55, width-43, height-55);
			//break;
		}
        
        
    }
    public boolean collided(shipBullet bullet) {
		//represent each object as a reactagle
		//then check if they are intersecting
		
		Rectangle main = bullet.getBounds();


		
		Rectangle thisObject = new Rectangle(x+75, y+55, width-43, height-55);
		//use bult in method to check intersecion(collision)
		return main.intersects(thisObject);
    }
}