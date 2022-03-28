package game1.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage birb_up, birb_down, birb;
	public static final int width = 500, height = 500;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spriteImages.png"));
		
		birb_down = sheet.crop(0, 0, width, height);
		birb_up = sheet.crop(width, 0, width, height);
		birb = sheet.crop(0, height, width, height);
				
	}
}
