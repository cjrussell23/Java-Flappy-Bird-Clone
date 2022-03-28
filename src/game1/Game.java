package game1;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import game1.display.Display;
import game1.gfx.Assets;
import game1.gfx.Fonts;
import game1.input.KeyManager;
import game1.input.MouseManager;
import game1.states.GameState;
import game1.states.MenuState;
import game1.states.State;

public class Game implements Runnable{

	///////////////////////
	// Fields
	///////////////////////	
	
	private Display display;
	private Thread thread;
	private boolean running;
	
	private int gameHeight;
	private int gameWidth;
	private String title;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private int score = 0;
	
	private int highScore;
	
	private int globalHighScore = 0;
	
	private File file = new File("birbHighScore.xml");
	
	//States
	private State gameState;
	private State menuState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	///////////////////////
	// Constructors
	///////////////////////

	public Game(String title, int gameWidth, int gameHeight) {
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	///////////////////////
	// Getters and Setters
	///////////////////////
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int readGlobalHighScore() {
		int globalHighScore = 0;
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(file));
			globalHighScore = (int)decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found when trying to read");
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			writeGlobalHighScore(0);
		}
		return globalHighScore;
	}

	public void writeGlobalHighScore(int globalHighScore) {
		try {
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file));
			encoder.writeObject(globalHighScore);
			encoder.close();
			System.out.println("Global HighScore written to file");
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("File not found when trying to write");
		}
	}
	
	public int getGlobalHighScore() {
		return globalHighScore;
	}
	
	public void setGlobalHighScore(int globalHighScore) {
		if(this.globalHighScore<globalHighScore) {
			this.globalHighScore = globalHighScore;
		}
	}
	
	public void setHighScore(int highScore) {
		if(this.highScore<highScore) {
			this.highScore = highScore;
		}
	}
	
	public int getHighScore() {
		return highScore;
	}
	
	public int getGameHeight() {
		return gameHeight;
	}

	public void setGameHeight(int gameHeight) {
		this.gameHeight = gameHeight;
	}

	public int getGameWidth() {
		return gameWidth;
	}

	public void setGameWidth(int gameWidth) {
		this.gameWidth = gameWidth;
	}
	
	public State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	public State getMenuState() {
		return menuState;
	}

	public void setMenuState(State menuState) {
		this.menuState = menuState;
	}
	
	// Key Manager

	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	// Mouse Manager
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	///////////////////////
	// Other Methods
	///////////////////////

	private void init() {
		display = new Display(title, gameWidth, gameHeight);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);		
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(menuState);
		this.globalHighScore = this.readGlobalHighScore();
		Fonts fonts = new Fonts();
		fonts.downloadFonts();
	}
	
	private void tick() {
		keyManager.tick();
		if(State.getState() != null) {
			State.getState().tick();
		}
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs ==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Clear screen
		
		g.clearRect(0, 0, gameWidth, gameHeight);
		
		//Beginning of Drawing

		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		//End of Drawing
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		//long timer = 0;
		//int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now-lastTime) / timePerTick;
			//timer += now-lastTime;
			lastTime = now;
			
			if(delta>=1) {
				tick();
				render();
				//ticks++;
				delta--;
			}
			
			//fps counter
//			if(timer >= 1000000000) {
//				System.out.println("Ticks and Frames: " + ticks);
//				ticks = 0;
//				timer = 0;
//			}
		}
		
		stop();
	}
	
	
	public synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start(); //calls the run method
	}
	
	public synchronized void stop() {
		if(!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
