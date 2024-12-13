import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Minion{
	private Image forward;//, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = .08;		//change to scale image
	double scaleHeight = .08; 		//change to scale image

	public Minion() {
		forward = getImage("/imgs/"+"Minion.png"); //load the image for Tree
		

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
	public Minion(int x, int y) {
		//call the default constructor, do the specific task for THIS constructer
		this();
		this.x = x;
		this.y = y;
	}
	
	public void move(int a) {
		switch(a) {
		case 0: //up
			y -= (height/2)+8; //move up a body length
			break;
		case 1: //down
			y+= (height/2)+8;
			break;
		case 2://left
			x -= 15;
			break;
		case 3://right
			x+= 15;
			break;
		}
	}
	
	//Getters
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		init(x,y);
		
		
		g2.drawImage(forward, tx, null);
		
		//if(Frame.debugging) {
			//g.setColor(Color.green);
			//g.drawRect(x+15, y+30, width-48, height-40);
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
			URL imageURL = Minion.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
