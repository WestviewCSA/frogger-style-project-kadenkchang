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
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
    int count = 0;
    public static boolean debugging = true;
    int animationCounter = 0;
    boolean alienFrame = false;
    int waveTimer = 5;
    long ellapseTime = 0;
    Font timeFont = new Font("Courier", Font.BOLD, 70);
    int alienMoveDelay = 30; // Number of ticks between each move (adjust as needed)
    int alienMoveCounter = 0; // Tracks frames
    int eliminated = 0;
    boolean isDescending = false;
    int descentPixelsLeft = 0;
    int descentSpeed = 1;
    int rate = 30;
    public static int scores;
    Font myFont = new Font("Courier", Font.BOLD, 40);
    SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
    SimpleAudioPlayer fart = new SimpleAudioPlayer("fartt.wav", false);
    SimpleAudioPlayer crash = new SimpleAudioPlayer("Crash.wav", false);
    SimpleAudioPlayer fall = new SimpleAudioPlayer("falling.wav", false);
    SimpleAudioPlayer w = new SimpleAudioPlayer("win.wav", false);
    SimpleAudioPlayer s = new SimpleAudioPlayer("scream.wav", false);

    Background background = new Background(0, 0);
    Ship ship = new Ship(450, 825);

    public Font newfont = new Font("Courier", Font.BOLD, 40);

    static int width = 1000;
    static int height = 1025;
    int death = 0;
    int score = 0;
    String elf = "";
    int shoot = 0;
    
    // Alien rows and movement logic
    AlienGreen[] row1 = new AlienGreen[10];
    AlienBlue[] row2 = new AlienBlue[10];
    AlienBlue[] row3 = new AlienBlue[10];
    AlienPink[] row4 = new AlienPink[10];
    AlienPink[] row5 = new AlienPink[10];
    int alienDx = 10;
    boolean descendNextMove = false;
    
    //SHIELDS
    
    Shield shield1 = new Shield(150, 700);  // Adjust X/Y values as needed
    Shield shield2 = new Shield(350, 700);
    Shield shield3 = new Shield(550, 700);
    Shield shield4 = new Shield(750, 700);
    		
    		
    // BULLETS
    ArrayList<shipBullet> bullets = new ArrayList<>();
    int shootCooldown = 15;  // Frames between shots
    int shootTimer = 0;
    ArrayList<AlienBullet> alienBullets = new ArrayList<>();

    public void paint(Graphics g) {
        super.paintComponent(g);
        background.paint(g);
        ship.paint(g);
        //paint shields
        shield1.paint(g);
        shield2.paint(g);
        shield3.paint(g);
        shield4.paint(g);

        End end = new End();
        g.setFont(newfont);
        g.drawString("Lives:" + (3 - death), 20, 40);
        g.drawString("Score:" + scores, 450, 40);


        for (AlienGreen a : row1) {
        	if(a!=null) {
        		a.paint(g, alienFrame);
        	}
        }
        	
        for (AlienBlue a : row2) if(a!=null) {
    		a.paint(g, alienFrame);
    	}
        for (AlienBlue a : row3) if(a!=null) {
    		a.paint(g, alienFrame);
    	}
        for (AlienPink a : row4) if(a!=null) {
    		a.paint(g, alienFrame);
    	}
        for (AlienPink a : row5) if(a!=null) {
    		a.paint(g, alienFrame);
    	}

        // Draw bullets
        for (shipBullet b : bullets) {
            b.paint(g);
        }
        for (AlienBullet b : alienBullets) {
            b.paint(g);
        }
        
        if (death >= 3) {
        	end.paint(g);
        }
    }

    public static void main(String[] arg) {
        Frame f = new Frame();
    }

    public Frame() {
        JFrame f = new JFrame("Duck Hunt");
        f.setSize(new Dimension(width, height));
        f.setBackground(Color.white);
        f.add(this);
        f.setResizable(false);
        f.addMouseListener(this);
        f.addKeyListener(this);

        initializeAliens();

        Timer t = new Timer(16, this);
        t.start();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    private void initializeAliens() {
        int xGreen = 30; int yGreen = 200;
        for (int i = 0; i < row1.length; i++) {
            row1[i] = new AlienGreen(xGreen, yGreen);
            xGreen += 70;
        }

        int xBlue = 48; 
        int yBlue = 260;
        for (int i = 0; i < row2.length; i++) {
            row2[i] = new AlienBlue(xBlue, yBlue);
            xBlue += 70;
        }

        int xBlue2 = 48;
        int yBlue2 = 330;
        for (int i = 0; i < row3.length; i++) {
            row3[i] = new AlienBlue(xBlue2, yBlue2);
            xBlue2 += 70;
        }

        int xPink = 50;
        int yPink = 410;
        for (int i = 0; i < row4.length; i++) {
            row4[i] = new AlienPink(xPink, yPink);
            xPink += 70;
        }

        int xPink2 = 50;
        int yPink2 = 480;
        for (int i = 0; i < row5.length; i++) {
            row5[i] = new AlienPink(xPink2, yPink2);
            xPink2 += 70;
        }
    }

    private void resetGame() {
        eliminated = 0;
        
        alienMoveDelay = 30;
        rate=30;
        alienDx = 10;
        isDescending = false;
        count = 0;
        
        initializeAliens();
        
        
        bullets.clear();
        alienBullets.clear();
        
        ship.setX(450);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {}

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mousePressed(MouseEvent m) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}

    @Override
    public void actionPerformed(ActionEvent arg0) {
        animationCounter++;
        if (animationCounter % rate == 0) {
            alienFrame = !alienFrame;
        }

        alienMoveCounter++;
        if (alienMoveCounter >= alienMoveDelay) {
            if (isDescending) {
                if(count == 0) {
                    descendAliens(row1);
                    descendAliens(row2);
                    descendAliens(row3);
                    descendAliens(row4);
                    descendAliens(row5);
                    count++;
                }
                else if (count == 1){
                    alienDx *= -1;
                    moveAliens(row1);
                    moveAliens(row2);
                    moveAliens(row3);
                    moveAliens(row4);
                    moveAliens(row5);
                    
                    isDescending = false;
                    count++;
                }
                else {
                    count = 0;
                }
            } else {
                boolean edgeHit = hitsEdge(row1) || hitsEdge(row2) || hitsEdge(row3) || hitsEdge(row4) || hitsEdge(row5);
                if (edgeHit) {
                    isDescending = true;
                } else {
                    moveAliens(row1);
                    moveAliens(row2);
                    moveAliens(row3);
                    moveAliens(row4);
                    moveAliens(row5);
                }
            }
            alienMoveCounter = 0;
        }
        
        //if bullet hits shield
        for (int i = 0; i < alienBullets.size(); i++) {
            AlienBullet bullet = alienBullets.get(i);
            Rectangle bulletBounds = bullet.getBounds();

            for (Shield shield : new Shield[]{shield1, shield2, shield3, shield4}) {
                if (shield.lives > 0 && bulletBounds.intersects(shield.getBounds())) {
                    shield.lives--;
                    alienBullets.remove(i);
                    i--;
                    break;
                }
            }
        }

        if (shootTimer > 0) {
            shootTimer--;
        }

        for (int i = 0; i < bullets.size(); i++) {
            shipBullet b = bullets.get(i);
            b.update();
            if (!b.isActive()) {
                bullets.remove(i);
                i--;
            }
        }
        
        if(eliminated == 40) {
        	alienMoveDelay = 3;
        	rate = 3;
        }
        if(eliminated ==45) {
        	alienMoveDelay = 1;
        	rate = 1;
        }

        // Aliens shooting logic
        if (Math.random() < 0.02) { 
            int column = (int)(Math.random() * 10); 
            for (int row = 4; row >= 0; row--) {
                Object alien = null;
                if (row == 0) alien = row1[column];
                if (row == 1) alien = row2[column];
                if (row == 2) alien = row3[column];
                if (row == 3) alien = row4[column];
                if (row == 4) alien = row5[column];
                if (alien != null) {
                    int ax = 0, ay = 0;
                    if (alien instanceof AlienGreen) {
                        ax = ((AlienGreen)alien).getX();
                        ay = ((AlienGreen)alien).getY();
                    } else if (alien instanceof AlienBlue) {
                        ax = ((AlienBlue)alien).getX();
                        ay = ((AlienBlue)alien).getY();
                    } else if (alien instanceof AlienPink) {
                        ax = ((AlienPink)alien).getX();
                        ay = ((AlienPink)alien).getY();
                    }
                    alienBullets.add(new AlienBullet(ax + 15, ay + 25)); 
                    break;
                }
            }
        }
        for (int i = 0; i < alienBullets.size(); i++) {
            AlienBullet b = alienBullets.get(i);
            b.update();
            if (!b.isActive()) {
                alienBullets.remove(i);
                i--;
            }
        }
        
        Rectangle shipBounds = new Rectangle(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());

        for (int i = 0; i < alienBullets.size(); i++) {
            AlienBullet b = alienBullets.get(i);
            if (b.getBounds().intersects(shipBounds)) {
                alienBullets.remove(i);
                i--;
                death++;
                ship.setX(450);
            }
        }
        
        // Collision check between bullets and aliens
        for (int i = 0; i < bullets.size(); i++) {
            shipBullet bullet = bullets.get(i);

            for (int j = 0; j < row1.length; j++) {
                if (row1[j] != null && row1[j].collided(bullet)) {
                    row1[j] = null;
                    bullet.setActive(false);
                    scores += 100;
                    eliminated ++;
                    break;
                }
            }

            for (int j = 0; j < row2.length; j++) {
                if (row2[j] != null && row2[j].collided(bullet)) {
                    row2[j] = null;
                    bullet.setActive(false);
                    scores += 50;
                    eliminated ++;
                    break;
                }
            }

            for (int j = 0; j < row3.length; j++) {
                if (row3[j] != null && row3[j].collided(bullet)) {
                    row3[j] = null;
                    bullet.setActive(false);
                    scores += 50;
                    eliminated ++;
                    break;
                }	
            }

            for (int j = 0; j < row4.length; j++) {
                if (row4[j] != null && row4[j].collided(bullet)) {
                    row4[j] = null;
                    bullet.setActive(false);
                    scores += 10;
                    eliminated ++;
                    break;
                }
            }

            for (int j = 0; j < row5.length; j++) {
                if (row5[j] != null && row5[j].collided(bullet)) {
                    row5[j] = null;
                    bullet.setActive(false);
                    scores += 10;
                    eliminated ++;
                    break;
                }
            }
        }

        // *** Here is the reset check ***
        if(eliminated == 50) {
            resetGame();
        }

        repaint();
    }

    private void moveAliens(Object[] aliens) {
        for (Object obj : aliens) {
            if (obj instanceof AlienGreen) {
                AlienGreen a = (AlienGreen) obj;
                a.setX(a.getX() + alienDx);
            } else if (obj instanceof AlienBlue) {
                AlienBlue a = (AlienBlue) obj;
                a.setX(a.getX() + alienDx);
            } else if (obj instanceof AlienPink) {
                AlienPink a = (AlienPink) obj;
                a.setX(a.getX() + alienDx);
            }
        }
    }

    private void descendAliens(Object[] aliens) {
        for (Object obj : aliens) {
            if (obj instanceof AlienGreen) {
                AlienGreen a = (AlienGreen) obj;
                a.setY(a.getY() + 20);
                if(a.getY() == 750) {
                	death+=3;
                }
            } else if (obj instanceof AlienBlue) {
                AlienBlue a = (AlienBlue) obj;
                a.setY(a.getY() + 20);
                if(a.getY() == 750) {
                	death+=3;
                }
            } else if (obj instanceof AlienPink) {
                AlienPink a = (AlienPink) obj;
                a.setY(a.getY() + 20);
                if(a.getY() == 750) {
                	death+=3;
                }
            }
        }
    }

    private boolean hitsEdge(Object[] aliens) {
        for (Object obj : aliens) {
            if (obj == null) continue;

            int x = 0, widthAlien = 0;

            if (obj instanceof AlienGreen) {
                AlienGreen a = (AlienGreen) obj;
                x = a.getX();
                widthAlien = a.getWidth();
            } else if (obj instanceof AlienBlue) {
                AlienBlue a = (AlienBlue) obj;
                x = a.getX();
                widthAlien = a.getWidth();
            } else if (obj instanceof AlienPink) {
                AlienPink a = (AlienPink) obj;
                x = a.getX();
                widthAlien = a.getWidth();
            }

            if(x <= 0 || x + widthAlien + 50 >= width) {
                return true;
            }
        }
        return false;
    }



    @Override
    public void keyPressed(KeyEvent arg0) {
        System.out.println(arg0.getKeyCode());

        if (arg0.getKeyCode() == 37) {
            ship.move(2); // left
        } else if (arg0.getKeyCode() == 39) {
            ship.move(3); // right
        } else if (arg0.getKeyCode() == 82) {
            death = 0;
        } else if (arg0.getKeyCode() == 32) {
            // Spacebar pressed - shoot bullet if cooldown passed
            if (shootTimer == 0) {
                int bulletX = ship.getX() + ship.getWidth() / 2 - 10;
                int bulletY = ship.getY();
                bullets.add(new shipBullet(bulletX, bulletY));
                shootTimer = shootCooldown;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {}

    @Override
    public void keyTyped(KeyEvent arg0) {}
}
