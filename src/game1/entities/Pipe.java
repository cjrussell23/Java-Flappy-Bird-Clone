package game1.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game1.Game;

public class Pipe extends Entity {
	
	///////////////////////
	// Fields
	///////////////////////	
	
	// Constants
	public static final int pipeGap = 425;
	public static final Color pipeColor = new Color(154,229,84,255);
	public static final int pipeWidth = 100;
	
	// Variables
	private int topPipeHeight;
	private int bottomPipeHeight;
	private int bottomPipeY;
	
	// Second bounding box for bottom pipe
	private Rectangle bounds2;
	
	///////////////////////
	// Constructors
	///////////////////////	

	public Pipe(Game game, int random) {
		super(game, game.getGameWidth(), 0, pipeWidth, random);
		
		
		//////////////
		// Top Pipe
		//////////////
		
			// Height
			topPipeHeight = random;

		//////////////
		// Bottom Pipe
		//////////////
		
			// Height
			bottomPipeHeight = game.getGameHeight() - (topPipeHeight + pipeGap + Background.groundHeight);
		
			// Y
			//this.bottomPipeY = this.topPipeHeight + pipeGap;
			bottomPipeY = game.getGameHeight() - Background.groundHeight - bottomPipeHeight;
			
		this.bounds2 = new Rectangle(0, bottomPipeY, width, bottomPipeHeight);
				

	}
	
	///////////////////////
	// Getters and Setters
	///////////////////////	
	

	///////////////////////
	// Other Methods
	///////////////////////	

	@Override
	public void move() {
		x-=5;
		bounds.x = (int)x;
		bounds2.x = (int)x;
	}

	@Override
	public void tick() {
		move();
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(pipeColor);
		//top pipe
		g.fillRect((int)x, (int)y, width, topPipeHeight);
		//bottom pipe
		g.fillRect((int)x, bottomPipeY , width, bottomPipeHeight);
		
		
	}
	
	
	
	

	public boolean detectCollision(Player player) {
		if(player.bounds.intersects(this.bounds2)) {
			return true;
		}
		if(player.bounds.intersects(this.bounds)) {
			return true;			
		}			
			return false;		
	}
}
