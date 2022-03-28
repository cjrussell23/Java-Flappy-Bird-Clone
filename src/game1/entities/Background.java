package game1.entities;
import java.awt.Graphics;
import game1.Game;
import game1.gfx.Colors;

public class Background {
	
	///////////////////////
	// Fields
	///////////////////////	
	
	// Constants
	public final static int groundHeight = 100;
	
	// Variables
	private Game game;
	private Colors colors;
	
	///////////////////////
	// Constructors
	///////////////////////	
	
	public Background(Game game) {
		this.game = game;
		this.colors = new Colors();
	}
	
	///////////////////////
	// Other Methods
	///////////////////////	
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		//draw ground
		g.setColor(colors.getGroundColor());
		g.fillRect(0, game.getGameHeight()-groundHeight, game.getGameWidth(), groundHeight);
		//draw sky
		g.setColor(colors.getSkyColor());
		g.fillRect(0,0, game.getGameWidth(), game.getGameHeight() - groundHeight);
	}
}
