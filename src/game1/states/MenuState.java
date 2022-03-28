package game1.states;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import game1.Game;
import game1.gfx.Colors;
import game1.gfx.Fonts;

public class MenuState extends State{
	
	///////////////////////
	// Fields
	///////////////////////	

	private Rectangle startButton;
	private Fonts fonts;
	private Colors colors;
	
	///////////////////////
	// Constructors
	///////////////////////
	
	public MenuState(Game game) {
		super(game);
		this.startButton = new Rectangle(game.getGameWidth()/2-100, game.getGameHeight()-game.getGameHeight()/4, 200, 100);
		this.fonts = new Fonts();
		this.colors = new Colors();
	}
	
	///////////////////////
	// Getters and Setters
	///////////////////////
	
	///////////////////////
	// Other Methods
	///////////////////////

	@Override
	public void tick() {
		//Show mouse coords
		//System.out.println(game.getMouseManager().getMouseX() + " " + game.getMouseManager().getMouseY());				
				
		
		if(game.getMouseManager().isLeftPressed()) {
			if(startButton.contains(game.getMouseManager().getMouseX(), game.getMouseManager().getMouseY())){
				game.getGameState().reset();  
				State.setState(game.getGameState());
			}
		}	
		else if(game.getKeyManager().up) {
			game.getGameState().reset();  
			State.setState(game.getGameState());
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		//Background
		g.setColor(colors.getSkyColor());
		g.fillRect(0, 0, game.getGameWidth(),  game.getGameHeight());
		//Title
		g.setColor(colors.getGroundColor());
		g.setFont(fonts.getTitleFont());
		g.drawString("Flirppy Birb", game.getGameWidth()/2 -275, game.getGameHeight()/2 - 50);
		
		//StartButton		
		g.fillRoundRect(startButton.x, startButton.y, startButton.width, startButton.height, 50, 50);
		g.setColor(colors.getWhite());
		g.setFont(fonts.getStartFont());
		FontMetrics fm = g.getFontMetrics();
		String st = "START";
		g.drawString(st, startButton.x + startButton.width/2 - fm.stringWidth(st)/2, startButton.y + startButton.height/2 + fm.getHeight()/4);
		
		
		
		
		String hs = "HighScore: ";
		String ghs = "Global HighScore: ";
		String s = "Score: ";
		
		g.drawString(s, 100, 100);
		g.drawString(hs, 100, 200);		
		g.drawString(ghs, 100, 300);
			
		g.setFont(fonts.getSmallDigital());
		
		
		g.drawString(String.valueOf(game.getScore()), 100+fm.stringWidth(s), 100);
		g.drawString(String.valueOf(game.getHighScore()), 100+fm.stringWidth(hs), 200);
		g.drawString(String.valueOf(game.getGlobalHighScore()), 100+fm.stringWidth(ghs), 300);		
	}	
	@Override
	public void reset() {		
	}	
}
