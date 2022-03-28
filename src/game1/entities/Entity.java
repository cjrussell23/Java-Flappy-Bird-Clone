package game1.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import game1.Game;

public abstract class Entity {
	
	///////////////////////
	// Fields
	///////////////////////	
	
	// Constants
	public static final float DEFAULT_UP_SPEED = -15.0f;
	public static final float DEFAULT_DOWN_SPEED = 10f;
	public static final float MAX_SPEED = -20f;
	public static final float GRAVITY = 10f;
	public static final int DEFAULT_WIDTH = 200;
	public static final int DEFAULT_HEIGHT = 200;
	
	// Variables
	protected float x, y;
	protected int width, height;
	protected float upSpeed;
	protected float downSpeed;
	protected float xMove, yMove;
	protected float deltaY = 0;
	protected Game game;
	protected Rectangle bounds;

	
	
	///////////////////////
	// Constructors
	///////////////////////
	
	public Entity(Game game, float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.upSpeed = DEFAULT_UP_SPEED;
		this.downSpeed = DEFAULT_DOWN_SPEED;
		this.xMove = 0;
		this.yMove = 0;
		this.game = game;
	
		bounds = new Rectangle(0, 0, width, height);
		
	}
	
	///////////////////////
	// Getters and Setters
	///////////////////////
	
	// dy
	public float getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(float deltaY) {
		this.deltaY = deltaY;
	}

	
	// xMove
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	// yMove
	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
	// X coordinate
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	// Up Speed
	public float getUpSpeed() {
		return upSpeed;
	}
	
	public void setUpSpeed(float upSpeed) {
		this.upSpeed = upSpeed;
	}

	// Down Speed
	public float getDownSpeed() {
		return downSpeed;
	}
	
	public void setDownSpeed(float downSpeed) {
		this.downSpeed = downSpeed;
	}

	// Y coordinate
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	// Entity Width
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	// Entity Height
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	///////////////////////
	// Other Methods
	///////////////////////

	///////////////////////
	// Abstract Methods
	///////////////////////
	
	public abstract void move();
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
}
