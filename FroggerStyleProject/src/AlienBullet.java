import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class AlienBullet {
    private int x, y;
    private int speed = 5;
    private boolean active = true;
    private BufferedImage image; // Scaled bullet image

     // Bullet center position
    private int width, height; // Actual image dimensions (after scaling)
    

    private double scaleWidth = 0.1;   // Scale image width
    private double scaleHeight = 0.1;  // Scale image height
    public AlienBullet(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        BufferedImage original = loadImage("/imgs/shipshot.png");

        if (original != null) {
            width = (int) (original.getWidth() * scaleWidth);
            height = (int) (original.getHeight() * scaleHeight);
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = image.createGraphics();
            g2.drawImage(original, 0, 0, width, height, null);
            g2.dispose();
        } else {
            width = 20;
            height = 40;
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = image.createGraphics();
            g2.setColor(Color.RED);
            g2.fillRect(0, 0, width, height);
            g2.dispose();
        }
    }
    public void paint(Graphics g) {
        if (!active) return;

        int drawX = x - width / 2;
        int drawY = y - height / 2;
        g.drawImage(image, drawX, drawY, null);

        // Debug hitbox
        /*if (Frame.debugging) {
            g.setColor(Color.GREEN);
            Rectangle hitbox = getBounds();
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }*/
    }
    public void update() {
        y += speed;
        if (y > Frame.height) {
            active = false;
        }
    }
    private BufferedImage loadImage(String path) {
        try {
            URL imageURL = getClass().getResource(path);
            return ImageIO.read(imageURL);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean a) {
        active = a;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 5, 15);
    }
}
