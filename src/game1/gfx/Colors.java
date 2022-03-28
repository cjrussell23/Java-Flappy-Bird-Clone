package game1.gfx;

import java.awt.Color;

public class Colors {
	
	private Color groundColor;
	private Color skyColor;
	private Color black;
	private Color white;
	
	public Colors() {
		groundColor = new Color(252,196,134,255);
		skyColor = new Color(78,192,202,255);
		white = new Color(255,255,255);
		black = new Color(0,0,0);
	}
	
	public Color getGroundColor() {
		return groundColor;
	}
	
	public Color getSkyColor() {
		return skyColor;
	}
	
	public Color getBlack() {
		return black;
	}
	
	public Color getWhite() {
		return white;
	}
}
