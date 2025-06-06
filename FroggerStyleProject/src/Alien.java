import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Alien {
    protected Image forward; // made protected
    protected AffineTransform tx; // made protected
    protected Image backward;
    protected int dir = 0;
    protected int width, height;
    protected int x, y;
    protected int vx, vy;
    protected double scaleWidth = 0.2;
    protected double scaleHeight = 0.2;

    public Alien() {
        forward = getImage("/imgs/CSAblue1.png");
        backward = getImage("/imgs/CSAblue2.png");
        width = 100;
        height = 100;
        x = 600 / 2 - width / 2;
        y = 600;
        vx = 0;
        vy = 0;

        tx = AffineTransform.getTranslateInstance(0, 0);
        init(x, y);
        
       
    }

    public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public Alien(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    public boolean collided(shipBullet bullet) {
		//represent each object as a reactagle
		//then check if they are intersecting
		
    	Rectangle main = bullet.getBounds();


		
		Rectangle thisObject = new Rectangle(x,y,width,height);
		//use bult in method to check intersecion(collision)
		return main.intersects(thisObject);
    }
		

    
    //getters and setters 
    public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}

	public void setHeight(int width) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
    
    
    

    protected void init(double a, double b) {
        tx.setToTranslation(a, b);
        tx.scale(scaleWidth, scaleHeight);
    }

	protected Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = Alien.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
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
			g.drawRect(x+85, y+50, width-48, height-40);
			//break;
		}
        
        
    }
}
