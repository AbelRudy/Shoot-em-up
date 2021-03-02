package GameElements;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class VaisseauJoueur extends Vaisseau {

	private int nbreVies;
	private int score;
	private boolean vivant;
	private int moduloScore;

	public VaisseauJoueur(GameContainer gc, String refImage, Color couleurProjectiles) throws SlickException {
		super(refImage, couleurProjectiles);
		this.x = gc.getWidth() / 2f;
		this.y = gc.getHeight() - hauteur - 20;
		this.vitesseProjectile = 400;
		nbreVies = 3;
		score = 0;
		vivant = true;
		moduloScore = 100;
	}
	
	public void ajouteVie() {
		if(score / moduloScore > 0) {
			nbreVies++;
			moduloScore += 100;
		}
	}

	public boolean estVivant() {
		return vivant;
	}
	
	public void meurt() {
		vivant = false;
	}
	
	public int getNbreVies() {
		return nbreVies;
	}

	public void diminuerVie() {
		nbreVies--;
	}

	public int getScore() {
		return score;
	}

	public void ajouterScore(int score) {
		this.score += score;
	}

	@Override
	public void tirer() {
		if(projectiles.size() < 6)
			projectiles.add(new Projectile(couleurProjectiles, x + largeur / 2, y - 5, vitesseProjectile));
	}

	@Override
	public void dessiner(Graphics g) {
		g.drawImage(image, x, y);
	}

}
