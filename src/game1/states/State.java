package game1.states;

import java.awt.Graphics;

import game1.Game;

public abstract class State {
	
	///////////////////////
	// Fields
	///////////////////////	
	
	private static State currentState = null;
	
	protected Game game;
	
	///////////////////////
	// Constructors
	///////////////////////
	
	public State(Game game) {
		this.game = game;
	}
	
	///////////////////////
	// Getters and Setters
	///////////////////////
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	///////////////////////
	// Abstract Methods
	///////////////////////
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void reset();
	
	
	
}
