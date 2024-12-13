import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Math;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	
	//for any debugging code we add
	public static boolean debugging = true;
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	
	//font and music creation for all sounds
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
	SimpleAudioPlayer fart = new SimpleAudioPlayer("fartt.wav",false);
	SimpleAudioPlayer crash = new SimpleAudioPlayer("Crash.wav",false);
	SimpleAudioPlayer fall = new SimpleAudioPlayer("falling.wav",false);
	SimpleAudioPlayer w = new SimpleAudioPlayer("win.wav",false);
	SimpleAudioPlayer s = new SimpleAudioPlayer("scream.wav",false);




//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	//background and screen object creation
	Background background = new Background(0,0);
	End ending = new End(0,0);
	Death d = new Death(0,0);

	public Font newfont = new Font("Courier", Font.BOLD, 40);
	
	//setting rows for objects
	SFScrolling[] row1 = new SFScrolling[10];
	FFScrolling[] row2 = new FFScrolling[4];
	FTFScrolling[] row3 = new FTFScrolling[10];
	HoverboardFull[] row4 = new HoverboardFull[5];
	ArrayList<HL> row5list = new ArrayList<HL>();
	//HL[] row5 = new HL[5];
	HL3[] row6 = new HL3[5];
	Door[] row7 = new Door[1];
	Fart[] row8 = new Fart[1];
	Fart2[] row9 = new Fart2[1];
	Spawner[] l = new Spawner[1];
	Bike[] n = new Bike[1];
	G[] m = new G[4];
	Bike2[] gr = new Bike2[1];


	//frame width/height
	static int width = 1000;
	static int height = 1025;	
	//scoring counters
	int death = 0;
	int score=0;
	String elf = "";
	


	public void paint(Graphics g) {
		
		super.paintComponent(g);
		background.paint(g);
		//paint the other objects on the screen
		g.setFont(newfont);
		g.drawString("Lives:" + (40-death), 120, 40);
		
		//have the row1 object paint on the screen
		//for each obj, painting an object
		for(SFScrolling obj : row1) {
			obj.paint(g);
		}
		for(FFScrolling obj1 : row2) {
			obj1.paint(g);
		}
		for(FTFScrolling obj2 : row3) {
			obj2.paint(g);
		}
		for(HoverboardFull obj3 : row4) {
			obj3.paint(g);
		}
		
		//for(HL obj4 : row5) {
		//	obj4.paint(g);
		//}
		for(HL obj : row5list) {
			obj.paint(g);
		}
		for(HL3 obj5 : row6) {
			obj5.paint(g);
		}
		for(Door obj6 : row7) {
			obj6.paint(g);
		}
		for(Fart obj7 : row8) {
			obj7.paint(g);
		}
		for(Fart2 obj8 : row9) {
			obj8.paint(g);
		}
		for(Spawner obj9 : l) {
			obj9.paint(g);
		}
		for(Bike ob : n) {
			ob.paint(g);
		}
		for(G o : m) {
			o.paint(g);
		}
		for(Bike2 oo : gr) {
			oo.paint(g);
		}
		boolean r = false;
		//more collision code for Fart objects
		for(FTFScrolling obj: row3) {
			if(obj.collided(minion)) {
				System.out.println("ouch");
				minion.x = 450;
				minion.y = 900;
				death++;
				fart.play();

				
			}
			
		}
		for(FFScrolling obj: row2) {
			if(obj.collided(minion)) {
				System.out.println("ouch");
				minion.x = 450;
				minion.y = 900;
				death++;
				fart.play();

			}
			
		}
		for(SFScrolling obj: row1) {
			if(obj.collided(minion)) {
				System.out.println("ouch");
				minion.x = 450;
				minion.y = 900;
				death++;
				fart.play();

			}
			
		}
		for(HoverboardFull obj: row4) {
			if(obj.collided(minion)) {
				minion.vx = 3;
				r = true;
			}
			
			
		}
		//setting winning minions
		Minion2 minions = new Minion2(140,25);
		Minion3 min = new Minion3(250,25);
		Minion4 v = new Minion4(335,28);
		Minion5 gg = new Minion5(620,33);
		Minion6 ggg = new Minion6(710,33);
		Minion7 ggk = new Minion7(800,33);

		//collision code for objects

		for(Door obj: row7) {
			if(obj.collided(minion)) {
				minion.x = 450;
				minion.y = 900;
				score++;

			}
			
			
		}
		
		for(G obj: m) {
			if(obj.collided(minion)) {
				System.out.println("ouch");
				minion.x = 450;
				minion.y = 900;
				death++;
			}
		}
		for(HL obj: row5list) {
			if(obj.collided(minion)) {
				minion.vx = -1;
				r = true;
			}
			
		}
		for(HL3 obj: row6) {
			if(obj.collided(minion)) {
				minion.vx = 5;
				r = true;
				
			}
			
		}
		for(Fart obj : row8) {
			if(obj.collided(minion)) {
				minion.x = 450;
				minion.y = 900;
				death++;
				fart.play();
			}
		}
		for(Fart2 obj : row9) {
			if(obj.collided(minion)) {
				minion.x = 450;
				minion.y = 900;
				death++;
				fart.play();

			}
		}
		for(Bike obj : n) {
			if(obj.collided(minion)) {
				minion.x = 450;
				minion.y = 900;
				death++;
				crash.play();
			}
		}
		for(Bike2 obj : gr) {
			if(obj.collided(minion)) {
				minion.x = 450;
				minion.y = 900;
				death++;
				crash.play();
			}
		}
		//moving bounderies for crack
		if(minion.y < 250 || minion.y > 425) {
			minion.vx = 0;
		}
		if(minion.y < 400 && minion.y > 250) {
			if (r == false) { 
				minion.x = 450;
				minion.y = 900;
				death++;
				fall.play();

			}
		}
		if((minion.x<0 || minion.x>900)==true) {
			minion.x = 450;
			minion.y = 900;
			death++;
		}
		if((minion.y<0 || minion.y>900)==true) {
			minion.x = 450;
			minion.y = 900;
			death++;
			
		}
		//printing minions
		if ((score>0)) {
			minions.paint(g);
		}
		if ((score >1)) {
			min.paint(g);
		}
		if ((score >2)) {
			gg.paint(g);
		}
		if ((score >3)) {
			v.paint(g);
		}
		if ((score >4)){
			ggg.paint(g);
		}
		if ((score > 5)) {
			ggk.paint(g);
		}
		//winning code and screen
		if ((score ==6)) {
			ending.paint(g);
			minions.paint(g);
			min.paint(g);
			gg.paint(g);
			v.paint(g);
			ggg.paint(g);
			ggk.paint(g);
			w.play();
		
		

		}
		//death screen code which sets after 40 deaths
		if(death >= 40) {
			d.paint(g);
			s.play();
		}
		minion.paint(g);
		

		

	}
	//print minion
	Minion minion = new Minion(450, 900);

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
		
		//backgroundMusic.play();
		
		
		/*
		 * Setup any 1D array here! - create the objects that go in them
		 */
		//traverse the array
		for(int i = 0; i <row1.length; i++) {
			row1[i] = new SFScrolling(i*200+50, 675);
		}
		for(int i = 0; i <m.length; i++) {
			m[i] = new G(i*500, 375);
		}
		for(int i = 0; i < row2.length;i++) {
			row2[i] = new FFScrolling(i*300,800);
		}
		for(int i = 0; i < row3.length; i++) {
			
			row3[i] = new FTFScrolling(825-(i*180),725);
		}
		
		for(int i = 0; i < row4.length; i++) {
			
			row4[i] = new HoverboardFull(1000-(i*500),300);
		}
		//for(int i = 0; i < row5.length; i++) {
			
			//row5[i] = new HL((i*200),359);
		//}
		for(int i = 0; i < row6.length; i++) {
			
			row6[i] = new HL3(1000-(i*300),417);
		}
		//ArrayList practice on Row 5 or Hoverboard
		for(int i = 0; i < 10; i++) {
			this.row5list.add(new HL((i*200), 359));
		}
		n[0] = new Bike(875,525);
		gr[0] = new Bike2(25,585);
		
		row7[0] = new Door(463, 35);
		row8[0] = new Fart(20, 825);
		row9[0] = new Fart2(10, 680);
		l[0] = new Spawner(875,725);
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();	
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode()==38) {
			//up
			minion.move(0);
		}
		else if (arg0.getKeyCode() == 40) {
			//down
			minion.move(1);
		}
		else if (arg0.getKeyCode() == 37) {
			//left
			minion.move(2);
		}
		else if (arg0.getKeyCode() == 39) {
			//right
			minion.move(3);
		}
		else if (arg0.getKeyCode() == 82) {
			death = 0;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
