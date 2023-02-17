/**
 * 
 */
package com.newbie.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
//import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.newbie.entities.Enemy;
import com.newbie.entities.Entity;
import com.newbie.entities.Player;
import com.newbie.graficos.Spritesheet;
import com.newbie.graficos.Ui;
import com.newbie.world.World;

/**
 * @author samuel.sps
 *
 */

public class Game extends Canvas implements Runnable, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 340;
	public static final int HEIGHT = 240;
	private final int SCALE = 2;
	private BufferedImage image;
	public static int frames = 0;
	
	public static List<Entity> staticEntities;
	public static List<Entity> livingEntities;
	public static List<Enemy> blueEnemies;
	public static List<Enemy> brownEnemies;
	public static Spritesheet charAnim, atkDie, tileMap, objects, enemyOne, enemyTwo, summerMap, inside, items;
	
//	public static Spritesheet atkdie;
//	public static Spritesheet tilemap;
//	public static Spritesheet objects;
//	public static Spritesheet enemyOne;
//	public static Spritesheet enemyTwo;
//	
	public static World world;
	public static Player player;
	public static Random rand;
	
	public static boolean last = true, start = false, pause = false, dead = false, win = false, restart = false;
	
	public Ui ui;
	
	public Game() {
		rand = new Random();
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		//Inicializando objetos
		livingEntities = new ArrayList<Entity>();
		staticEntities = new ArrayList<Entity>();
		blueEnemies = new ArrayList<Enemy>();
		brownEnemies = new ArrayList<Enemy>();
		
		ui = new Ui();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		objects = new Spritesheet("/objects.png");
		enemyOne = new Spritesheet("/bluemush.png");
		enemyTwo = new Spritesheet("/brownmush.png");
		charAnim = new Spritesheet("/charAnim.png");
		atkDie = new Spritesheet("/atkdie.png");
		player = new Player(0, 0, 16, 16, charAnim.getSprite(70, 0, 24, 24));
		livingEntities.add(player);
		summerMap = new Spritesheet("/mapSummer.png");
		inside = new Spritesheet("/inside.png");
		items = new Spritesheet("/sumitems.png");
		tileMap = new Spritesheet("/tilemap.png");
		world = new World("/newMap2.png");

	}
	
	public void initFrame() {
		frame = new JFrame("Felix's Misfortunes - Demo");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}
	
	public void tick() {
		for (int i = 0; i < livingEntities.size(); i++){
			Entity lE = livingEntities.get(i);
			lE.tick();
		}
		for (int i = 0; i < staticEntities.size(); i++){
			Entity sE = staticEntities.get(i);
			sE.tick();
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		Graphics s = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		/*renderização*/
		
		//Graphics2D g2 = (Graphics2D) g;
		world.render(g);
		for (int i = 0; i < livingEntities.size(); i++){
			Entity lE = livingEntities.get(i);
			lE.render(g);
		}
		for (int i = 0; i < staticEntities.size(); i++){
			Entity sE = staticEntities.get(i);
			sE.render(g);
		}
		ui.render(g, s);
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) /ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				Game.frames = frames;
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		
		stop();
	}
	
	public void restartGame() {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			start = true;
			
		}
		
		if((e.getKeyCode() == KeyEvent.VK_ESCAPE) && (((!pause) && (start) && (dead)) || (win))) {

			System.exit(1);
			
		}
		
		if((e.getKeyCode() == KeyEvent.VK_ESCAPE) && (!pause) && (start) && (!dead)) {
			
			pause = true;
			
		}else if((e.getKeyCode() == KeyEvent.VK_ESCAPE) && (pause) && (start) && (!dead)) {
			
			pause = false;
			
		}else if((e.getKeyCode() == KeyEvent.VK_R) && (((!pause) && (start) && (dead)) || (win))) {
			
			restart = true;
			
		}
		
		if((!pause) && (start) && (!dead) && (!win)) {
			
			if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				player.right = true;
				last = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				player.left = true;
				last = false;
			}
	
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				player.up = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				player.down = true;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_C) {
				
			}
			
			if(e.getKeyCode() == KeyEvent.VK_X) {
				
			}
			
			if(e.getKeyCode() == KeyEvent.VK_F) {
				player.atk = true;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_E) {
				
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if((!pause) && (start) && (!dead) && (!win)) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
				player.right = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
				player.left = false;
			}
	
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				player.up = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				player.down = false;
			}
		}
		
	}
	
}
