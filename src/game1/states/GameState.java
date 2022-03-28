package game1.states;
import java.awt.Graphics;
import java.util.ArrayList;
import game1.Game;
import game1.entities.Background;
import game1.entities.Entity;
import game1.entities.Pipe;
import game1.entities.Player;
import game1.gfx.Colors;
import game1.gfx.Fonts;

import java.util.Random;

public class GameState extends State {
	
	///////////////////////
	// Fields
	///////////////////////		
	
	
	private Player player;
	private Background background;
	private ArrayList<Pipe> pipes;
	private Random random = new Random();
	private int time = 0;
	private Fonts fonts;
	private Colors colors;
	
	///////////////////////S
	// Constructors
	///////////////////////
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 500, 500); //500, 500 is the position of the player
		background = new Background(game);
		pipes = new ArrayList<Pipe>();	
		colors = new Colors();
		fonts = new Fonts();
	}
	
	///////////////////////
	// Getters and Setters
	///////////////////////
	

	
	///////////////////////
	// Other Methods
	///////////////////////
	

	

	@Override
	public void tick() {
		player.tick();
		time++;	
		//System.out.println(time);
		int buffer = 180;
		if((time-buffer)/100>0) {
			game.setScore((time-buffer)/100) ;
		}
		//loop through each pipe in the arraylist
		for(int pipeIndex=0; pipeIndex<pipes.size(); pipeIndex++){
			Pipe p = pipes.get(pipeIndex);
			p.tick();
			
			if(p.detectCollision(player)) {
				game.setHighScore(game.getScore());
				scoreUpdate();
				State.setState(game.getMenuState());
			}
			
			
			//if the pipe is off the screen then remove it
			if(p.getX()<0-Entity.DEFAULT_WIDTH) {
				this.pipes.remove(pipeIndex);
			}
		}
		if(time%100==0 || time==10) { 
			this.pipes.add(new Pipe(super.game, random.nextInt(350)+40));
		}
		
		
		
	}

	@Override
	public void render(Graphics g) {
		background.render(g);
		
		
		
		for(Pipe p : pipes) {
			p.render(g);
		}
		player.render(g);
		
		g.setColor(colors.getWhite());
		g.setFont(fonts.getDigital());
		g.drawString(String.valueOf(game.getScore()), game.getGameWidth()/2-50, 100);
	}
	
	@Override
	public void reset() {
		player.setPlayerPosition(500, 500);
		pipes.clear();
		time = 0;
		game.setScore(0);
		scoreUpdate();
	}
	
	public void scoreUpdate() {
		if(game.getGlobalHighScore()<game.getHighScore()) {
			game.setGlobalHighScore(game.getHighScore());
			game.writeGlobalHighScore(game.getHighScore());
		}
		//System.out.println("updated score");
		//System.out.println("Global: " + game.getGlobalHighScore());
		//System.out.println("Local: " + game.getHighScore());
	}
}
