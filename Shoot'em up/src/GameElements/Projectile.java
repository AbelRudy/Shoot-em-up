package GameElements;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Projectile {
	private Color couleur;
	private float x, y;
	private float vy;
	
	public Projectile(Color couleur, float x, float y, float vy) {
		this.couleur = couleur;
		this.x = x;
		this.y = y;
		this.vy = vy;
	}
	
	public void dessiner(Graphics g) {
		g.setColor(couleur);
		g.fillRoundRect(x, y, 8, 10, 3);
	}
	
	public void deplacer(int delta, int direction) {
		//direction vaut 1 si c'est le joueur qui tire et -1 si c'est un ennemi
		y -= direction * vy * delta/1000f;
	}
	
	//On aurait pu prendre un seul vaisseau en paramètre
	
	public boolean testCollision(VaisseauEnnemi e) {
		return (x >= e.getX() && x <= e.getX() + e.largeur) && (y >= e.getY() && y <= e.getY() + e.hauteur);
	}
	
	public boolean testCollision(VaisseauJoueur vj) {
		return (x >= vj.getX() && x <= vj.getX() + vj.largeur) && (y >= vj.getY() && y <= vj.getY() + vj.hauteur);
	}
	
	public boolean dansEcran(GameContainer gc) {
		return (x >= 0 && x <= gc.getWidth() && y >= 0 && y <= gc.getHeight());
	}
}
