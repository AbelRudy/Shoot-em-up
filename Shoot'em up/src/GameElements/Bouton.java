package GameElements;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class Bouton {
	
	private final float largeur = 300, hauteur = 100;
	private float x ,y;
	private String contenu;

	public Bouton(float x, float y, String contenu) {
		this.x = x;
		this.y = y;
		this.contenu = contenu;
	}
	
	public void dessiner(Graphics g,TrueTypeFont ttf) {
		g.drawRect(x, y, largeur, hauteur);
		ttf.drawString(x + 40, y + 30, contenu);
	}

	public boolean estClique(GameContainer gc) {
		int x = gc.getInput().getMouseX();
		int y = gc.getInput().getMouseY();
		return (x >= this.x && x <= this.x + largeur) && (y >=  this.y && y <= this.y + hauteur);
	}
}
