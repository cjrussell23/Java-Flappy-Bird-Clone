package game1.entities;
import java.awt.Color;
import java.awt.Graphics;
import game1.Game;
import game1.gfx.Assets;

public class Player extends Entity {
	
	///////////////////////
	// Fields
	///////////////////////	
	private int yBoundGap = 40;

	///////////////////////
	// Constructors
	///////////////////////
	
	public Player(Game game, float x, float y) {
		super(game, x, y, Entity.DEFAULT_WIDTH, Entity.DEFAULT_HEIGHT);
		
		bounds.x = (int)x+50;
		bounds.y = (int)y+ yBoundGap;
		bounds.width = 120;
		bounds.height = 140;
	}
	
	///////////////////////
	// Getters and Setters
	///////////////////////
	
	public void setPlayerPosition(float x, float y) {
		super.x = x;
		super.y = y;
	}
	
	///////////////////////
	// Other Methods
	///////////////////////
	
	private void getInput() {

		if(game.getKeyManager().up || game.getMouseManager().isLeftPressed()) {
			upSpeed = Entity.DEFAULT_UP_SPEED;
		}
		else {
			upSpeed = 0;
		}
		
		deltaY += downSpeed + upSpeed;
		
		if(deltaY < Entity.MAX_SPEED) {
			deltaY = Entity.MAX_SPEED; //limit speed to -10
		}
		else if(deltaY > Entity.GRAVITY) {
			deltaY = Entity.GRAVITY;
		}
		if(y > game.getGameHeight()-height) {
			deltaY = 0;
		}
		
		
	}

	@Override
	public void tick() {
		getInput();
		move();
	}

	@Override
	public void render(Graphics g) {
		if(deltaY == 0) {
			g.drawImage(Assets.birb, (int)x, (int)y, width, height, null);
		}
		else if(deltaY < 0) {
			g.drawImage(Assets.birb_up, (int)x, (int)y, width, height, null);
		}
		else {
			g.drawImage(Assets.birb_down, (int)x, (int)y, width, height, null);
		}
		
		//Bounding box
//		g.setColor(Color.red);
//		g.fillRect((int)(bounds.x), (int)(bounds.y), (int) bounds.width, (int) bounds.height);
		//System.out.println("x: " + (int)(x + bound1.x)+" , y:"+ (int)(y + bound1.y) + " , w:" + (int) bound1.width + " , h:" + (int) bound1.height);
		
	}
	
	@Override
	public void move() {
		y += deltaY;
		if(y > game.getGameHeight()-height) {
			y = game.getGameHeight()-height;
			deltaY = 0;
		}
		else if(y < 0) {
			y = 0;
			deltaY = 0;
		}
		bounds.y = (int)y+yBoundGap;
		//System.out.println(bounds.y);
	}
	


}
