package game1.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
//import javax.swing.JList;
//import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
public class Fonts {

	private Font title;
	private Font startFont;
	private Font digital;
	private Font smallDigital;
	
	public Fonts() {	
		title = new Font("Quite Magical", Font.BOLD,150);
		startFont = new Font("Quite Magical", Font.PLAIN, 50);	
		digital = new Font("Digital-7", Font.BOLD, 100);
		smallDigital = new Font("Digital-7", Font.BOLD, 50);
	}
	
	public Font getTitleFont() {
		return title;
	}
	
	public Font getStartFont() {
		return startFont;
	}
	
	public Font getDigital() {
		return digital;
	}
	
	public Font getSmallDigital() {
		return smallDigital;
	}
	
	public void getNewFont(String url) {
		Font font;
		 try {
			URL fontUrl = new URL(url);			
			font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			
			//JList fonts = new JList( ge.getAvailableFontFamilyNames() );
	        //JOptionPane.showMessageDialog(null, new JScrollPane(fonts));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to get font");		
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to get font");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to get font");
		}
	}
	
	public void downloadFonts() {
		getNewFont("https://get.fontspace.co/download/font/1e1Z/MGVjZTc2NDY5NGIzNDI2YWEzMzVkZDI3NDQ4OWI0ODMudHRm/Digital7-1e1Z.ttf");
		getNewFont("https://get.fontspace.co/download/font/8VA2/MzI4MThkYmY4NjAzNDkyZTg1MTE3MDU1ZmQ2N2U3ZTEudHRm/QuiteMagicalRegular-8VA2.ttf");
	}
}
