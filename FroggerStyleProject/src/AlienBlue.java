import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class AlienBlue extends Alien {

    public AlienBlue() {
        super();
        this.forward = getImage("/imgs/CSAblue1.png");
    }

    public AlienBlue(int x, int y) {
        super(x, y);
        scaleHeight = 0.2;
        scaleWidth = 0.2;
        this.forward = getImage("/imgs/CSAblue1.png");
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
			//g.drawRect(x+80, y+85, width-43, height-60);
			//break;
		}
        
        
    }
    public boolean collided(shipBullet bullet) {
		//represent each object as a reactagle
		//then check if they are intersecting
		
		Rectangle main = bullet.getBounds();


		
		Rectangle thisObject = new Rectangle(x+80, y+85, width-43, height-60);
		
		//use bult in method to check intersecion(collision)
		return main.intersects(thisObject);
    }
}
