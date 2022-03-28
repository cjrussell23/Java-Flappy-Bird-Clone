package game1.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	///////////////////////
	// Fields
	///////////////////////	
	
	private boolean[] keys;
	public boolean up, down, left, right;
	
	///////////////////////
	// Constructors
	///////////////////////
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	///////////////////////
	// Getters and Setters
	///////////////////////
	
	///////////////////////
	// Other Methods
	///////////////////////
	
	public void tick() {
		
//		up = keys[KeyEvent.VK_W];
//		down = keys[KeyEvent.VK_S];
//		left = keys[KeyEvent.VK_A];
//		right = keys[KeyEvent.VK_D];
		
		up = keys[KeyEvent.VK_SPACE];
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		keys[e.getKeyCode()] = false;
	}

	
	
}
