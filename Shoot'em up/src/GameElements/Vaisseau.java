package GameElements;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Vaisseau {

	protected Image image;
	protected float x, y;//position
	protected ArrayList<Projectile> projectiles;
	protected Color couleurProjectiles;
	protected float vitesseProjectile;
	//On définit la taille de chaque vaisseau
	protected static final float largeur = 70;
	protected static final float hauteur = 70;
	
	abstract public void tirer();
	abstract public void dessiner(Graphics g);
	
	Vaisseau(String refImage, Color couleurProjectiles) throws SlickException{
		this.image = new Image(refImage);
		this.couleurProjectiles = couleurProjectiles;
		projectiles = new ArrayList<>();
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}
	
	public void setX(float x, GameContainer gc) {
		if(x >= 0 && x <= gc.getWidth() - largeur)
			this.x = x;
	}
	
	public void setY(float y, GameContainer gc) {
		if(y >= gc.getHeight() /2f && y <= gc.getHeight() - hauteur)
			this.y = y;
	}
	
	public static float getLargeur() {
		return largeur;
	}
	
	public static float getHauteur() {
		return hauteur;
	}
}
