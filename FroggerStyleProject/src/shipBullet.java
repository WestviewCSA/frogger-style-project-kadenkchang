import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class shipBullet {
    private BufferedImage image; // Scaled bullet image

    private int x, y; // Bullet center position
    private int width, height; // Actual image dimensions (after scaling)
    private int speed = 15; // Speed of bullet

    private boolean active = true;

    private double scaleWidth = 0.1;   // Scale image width
    private double scaleHeight = 0.1;  // Scale image height

    public shipBullet(int startX, int startY) {
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void update() {
        y -= speed;
        if (y + height < 0) {
            active = false;
        }
    }

    public void paint(Graphics g) {
        if (!active) return;

        int drawX = x - width / 2;
        int drawY = y - height / 2;
        g.drawImage(image, drawX, drawY, null);

    }

    public Rectangle getBounds() {
        int hitboxWidth = (int)(width * 0.1);
        int hitboxHeight = (int)(height * 0.1);
        int drawX = x - hitboxWidth / 2;
        int drawY = y - hitboxHeight / 2;

        return new Rectangle(drawX, drawY, hitboxWidth, hitboxHeight);
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
}
