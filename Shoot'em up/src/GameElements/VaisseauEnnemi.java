package GameElements;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class VaisseauEnnemi extends Vaisseau {

	public static int nbreEnnemi = 0;
	private int lvlEnnemi, vies;
	private float vitesseDeplacement;
	private float xmin, xmax, ymin, ymax;
	private int direction;
	
	public VaisseauEnnemi(GameContainer gc, String refImage, Color couleurProjectiles, int lvlEnnemi) throws SlickException {
		super(refImage, couleurProjectiles);
		this.lvlEnnemi = lvlEnnemi;
		if(lvlEnnemi == 1)
			vitesseProjectile = vitesseDeplacement = 100;
		else if(lvlEnnemi == 2)
			vitesseProjectile = vitesseDeplacement = 150;
		else
			vitesseProjectile = vitesseDeplacement = 200;
		vies = lvlEnnemi;
		xmin = (float)(Math.random() * 101);
		xmax = (float)(Math.random() * (1080 - 1000 + 1) + 1000) - largeur;
		ymin = 0;
		ymax = (float)(Math.random() * (475 - 350 + 1) + 350);
		x = (float)(Math.random() * (xmax - xmin + 1) + xmin);
		y = (float)(Math.random() * (ymax - ymin + 1) + ymin);
		direction = (int)(Math.random() * 2) == 0 ? 1 : -1;
		nbreEnnemi++;
	}
	
	public int getVies() {
		return vies;
	}
	
	public void diminuerVies() {
		vies--;
	}

	public int getLvlEnnemi() {
		return lvlEnnemi;
	}
	
	@Override
	public void tirer() {
		projectiles.add(new Projectile(couleurProjectiles, x + largeur/2, y + 5, vitesseProjectile));
	}

	@Override
	public void dessiner(Graphics g) {
		g.drawImage(image, x, y, x + largeur, y + hauteur, 0, 0, largeur, hauteur);
	}
	
	public void deplacer(int delta) {
		x += direction * vitesseDeplacement * delta / 1000f;
		if(x >= xmax || x <= xmin) {
			direction = direction * (-1);
			y = (float)(Math.random() * (ymax - ymin + 1) + ymin);
		}
	}

}
