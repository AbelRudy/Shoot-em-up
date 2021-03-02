import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import GameElements.Bouton;

public class Accueil extends BasicGameState {
	
	private Bouton[] boutons;
	private Font font;
	private TrueTypeFont ttf;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		boutons = new Bouton[2];
		boutons[0] = new Bouton(400, 150, "JOUER");
		boutons[1] = new Bouton(400, 450, "QUITTER");
		font = new Font("Verdana", Font.BOLD, 30);
	    ttf = new TrueTypeFont(font, true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("Images/background.png"), 0, 0);
		for(int i = 0; i < boutons.length; i++) {
			g.setColor(Color.white);
			boutons[i].dessiner(g, ttf);
		}
		g.setColor(Color.pink);
		g.drawString("Par les frères NDJOUENKEU", 0, 700);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input inp = gc.getInput();
		if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if(boutons[0].estClique(gc))
				sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
			if(boutons[1].estClique(gc))
				gc.exit();
		}
	}

	@Override
	public int getID() {
		return 0;
	}
	

}
