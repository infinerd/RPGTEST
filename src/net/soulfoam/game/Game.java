package net.soulfoam.game;


import java.applet.*;
import java.awt.*;


import javax.swing.*;

import net.soulfoam.game.entities.Mob;
import net.soulfoam.game.entities.Player;

import net.soulfoam.game.level.Level;
import net.soulfoam.game.level.Spawner;
import net.soulfoam.game.weapons.Projectile;

import java.util.*;

//Component is working area for drawing things
public class Game extends Applet implements Runnable {

	private static final long serialVersionUID = 1L; 

	public static int pixelSize = 5;


	public static double camX = 0, camY = -5; // the
																	

	
	public boolean isRunning = false;
	
	
	
	public static Dimension realSize = new Dimension (0,0); 
	public static Dimension size = new Dimension(700, 560);
	public static Dimension pixel = new Dimension(size.width / pixelSize,
			size.height / pixelSize);
	
	public static int height = 700;
	public static int width = 560;

	public static Point mse = new Point(0, 0);

	public static String name = "MRPG";
	public static String deathText = "YOU ARE DEAD!";


	private Image screen; 
	
	public int fps;
	public int totalTime;

	public Level level;
	public Player player;

	public Spawner spawner;
	public KeyInput input;
	public ArrayList<Mob> mob = new ArrayList<Mob>();
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();


	public Game() {

		setPreferredSize(size);

	}


	public void start() {
		
		frame = new JFrame();						
		realSize = new Dimension(frame.getWidth(), frame.getHeight());
		
		frame.setTitle(name);
		frame.setSize(new Dimension(WIDTH, HEIGHT));
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		frame.setVisible(true);		
		frame.setIconImage(new ImageIcon("res/tile_icon.png").getImage());		
	
		new Tile(); 
		
		level = new Level(this);
		input = new KeyInput(this);
		player = new Player(Tile.tileSize, Tile.tileSize, this, input, level);
	

		spawner = new Spawner(this, level);


		isRunning = true;
		new Thread(this).start();


	}

	public void stop() {
		isRunning = false;

	}
	
	private static JFrame frame;

	public static void main(String args[]) {

		
		Game component = new Game();
		
		new Menu(component);
	}

	public void tick() {
		
		if(frame.getWidth() != realSize.width || frame.getHeight() != realSize.height){
			frame.pack();
		}
		
		player.tick((int) camX, (int) camY, (pixel.width / Tile.tileSize) + 2,
				(pixel.height / Tile.tileSize) + 2);

	

		
		for(int i = 0; i < mob.toArray().length; i++){
			mob.get(i).tick();
		}
		
		for(int i = 0; i < projectiles.toArray().length; i++){
			projectiles.get(i).tick();
		}
	}
		


	public void render() {
		Graphics g = screen.getGraphics();

		g.setColor(new Color(0,0,200));
		g.fillRect(0, 0, Game.pixel.width, Game.pixel.height);
		level.render(g, (int) camX, (int) camY, (pixel.width / Tile.tileSize) + 2,
				(pixel.height / Tile.tileSize) + 2);
		
		for(int i = 0; i < projectiles.toArray().length; i++){
			projectiles.get(i).render(g);
		}
		
		for(int i = 0; i < mob.toArray().length; i++){
			mob.get(i).render(g);
		}

		player.render(g);
		
		
		g.setColor(Color.GREEN);
		g.drawString(fps + " fps", 1, 10);
		g.drawString("pX: " + (int)player.x, 1, 20);
		g.drawString("pY: " + (int)player.y, 1, 30);
		g.drawString("CamX: " + (int)camX, 1, 40);
		g.drawString("CamY: " + (int)camY, 1, 50);
		

		g = getGraphics();

		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width,
				pixel.height, null);	
		g.dispose();
				
		
	}

	public void run() {

		screen = createVolatileImage(pixel.width, pixel.height);

		int frames = 0;
		double nonProcessedSeconds = 0;
		long previousTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;
		boolean hasTicked = false;
		int totalTime = 0;
		
		while (isRunning) {
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			nonProcessedSeconds += passedTime / 1000000000.0;
			
			while (nonProcessedSeconds > secondsPerTick){
				tick();
				nonProcessedSeconds -= secondsPerTick;
				hasTicked = true;
				tickCount++;
					if (tickCount % 60 == 0){
						previousTime += 1000;
						fps = frames;
						frames = 0;
					}
			}
			
	
			if(hasTicked){
			frames++;
			}
			
			frames++;		
			requestFocus();
			tick();
			render();
			
			try {
				Thread.sleep(5);
			} catch (Exception e) {

			}

		}

	}

}
