import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import GameElements.Projectile;
import GameElements.Vaisseau;
import GameElements.VaisseauEnnemi;
import GameElements.VaisseauJoueur;

public class Partie extends BasicGameState {
	
	//Static pour pouvoir utiliser leurs propriétés dans les autres classes
	static VaisseauJoueur vJ1, vJ2;
	ArrayList<VaisseauEnnemi> ve;
	//Si un ennemi meurt, ses projectiles ne doivent pas disparaitre
	ArrayList<Projectile> projectilesEnnemis;
	int timerlvl1 = 0, timerlvl2 = 0, timerlvl3 = 0;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		vJ1 = new VaisseauJoueur(gc, "Images/VJ1.png", Color.yellow);
		vJ2 = new VaisseauJoueur(gc, "Images/VJ2.png", Color.red);
		ve = new ArrayList<VaisseauEnnemi>();
		projectilesEnnemis = new ArrayList<Projectile>();
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("Images/background.png"), 0, 0);
		//Affichage des informations
		g.setColor(Color.white);
		g.drawString("JOUEUR 1", 5, 1);
		g.drawString("Score : " + vJ1.getScore(), 5, 20);
		g.drawString("Vies : " + vJ1.getNbreVies(), 5, 40);
		if(Jouer.nbreJoueur == 2) {
			g.drawString("JOUEUR 2", 1000, 1);
			g.drawString("Score : " + vJ2.getScore(), 1000, 20);
			g.drawString("Vies : " + vJ2.getNbreVies(), 1000, 40);
		}
		
		//Dessin des vaisseaux
		if(vJ1.estVivant())
			vJ1.dessiner(g);
		if(Jouer.nbreJoueur == 2  && vJ2.estVivant())
			vJ2.dessiner(g);
		for(int i = 0; i < ve.size(); i++)
			ve.get(i).dessiner(g);
		
		//Dessin des projectiles
		if(vJ1.estVivant())
			for(int i = 0; i < vJ1.getProjectiles().size(); i++)
				vJ1.getProjectiles().get(i).dessiner(g);
		if(Jouer.nbreJoueur == 2 && vJ2.estVivant())
			for(int i = 0; i < vJ2.getProjectiles().size(); i++)
				vJ2.getProjectiles().get(i).dessiner(g);
		for(int i = 0; i < projectilesEnnemis.size(); i++)
			projectilesEnnemis.get(i).dessiner(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timerlvl1 += delta;
		timerlvl2 += delta;
		timerlvl3 += delta;
		
		//Gestion des projectiles des projectiles présents à l'écran
		if(vJ1.estVivant())
			for(int i = 0; i < vJ1.getProjectiles().size(); i++) {
				vJ1.getProjectiles().get(i).deplacer(delta, 1);
				if(!vJ1.getProjectiles().get(i).dansEcran(gc)) {
					vJ1.getProjectiles().remove(i--);
					continue;
				}
				for(int j = 0; j < ve.size(); j++) 
					if(vJ1.getProjectiles().get(i).testCollision(ve.get(j))) {
						if(ve.get(j).getLvlEnnemi() == 1)
							vJ1.ajouterScore(1);
						else if (ve.get(j).getLvlEnnemi() == 2)
							vJ1.ajouterScore(3);
						else
							vJ1.ajouterScore(5);
						vJ1.ajouteVie();
						ve.get(j).diminuerVies();
						if(ve.get(j).getVies() <= 0) {
							ve.remove(j--);
							VaisseauEnnemi.nbreEnnemi--;
						}
						vJ1.getProjectiles().remove(i--);
						break;
					}
			}
		if(Jouer.nbreJoueur == 2 && vJ2.estVivant())
			for(int i = 0; i < vJ2.getProjectiles().size(); i++) {
				vJ2.getProjectiles().get(i).deplacer(delta, 1);
				if(!vJ2.getProjectiles().get(i).dansEcran(gc)) {
					vJ2.getProjectiles().remove(i--);
					continue;
				}
				for(int j = 0; j < ve.size(); j++) 
					if(vJ2.getProjectiles().get(i).testCollision(ve.get(j))) {
						if(ve.get(j).getLvlEnnemi() == 1)
							vJ2.ajouterScore(1);
						else if (ve.get(j).getLvlEnnemi() == 2)
							vJ2.ajouterScore(3);
						else
							vJ2.ajouterScore(5);
						vJ2.ajouteVie();
						ve.get(j).diminuerVies();
						if(ve.get(j).getVies() <= 0) {
							ve.remove(j--);
							VaisseauEnnemi.nbreEnnemi--;
						}
						vJ2.getProjectiles().remove(i--);
						break;
					}
			}
		for(int i = 0; i < projectilesEnnemis.size(); i++) {
			projectilesEnnemis.get(i).deplacer(delta, -1);
			if(vJ1.estVivant() && projectilesEnnemis.get(i).testCollision(vJ1)) {
				projectilesEnnemis.remove(i--);
				vJ1.diminuerVie();
				if(vJ1.getNbreVies() <= 0)
					vJ1.meurt();
			}
			else if (Jouer.nbreJoueur == 2 && vJ2.estVivant() && projectilesEnnemis.get(i).testCollision(vJ2)) {
				projectilesEnnemis.remove(i--);
				vJ2.diminuerVie();
				if(vJ2.getNbreVies() <= 0)
					vJ2.meurt();
			}
			else if(!projectilesEnnemis.get(i).dansEcran(gc)) {
				projectilesEnnemis.remove(i--);
			}
			if(!vJ1.estVivant()) {
				if(Jouer.nbreJoueur == 2 && !vJ2.estVivant())
					sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
				else if(Jouer.nbreJoueur == 1)
					sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
					
			}
		}
		
		//On fait en sorte qu'il y'ait toujours 3 vaisseaux sur l'écran
		while(VaisseauEnnemi.nbreEnnemi < 3) {
			int a = (int) (Math.random() * 3) + 1;
			if(a == 1)
				ve.add(new VaisseauEnnemi(gc, "Images/VE1.png", Color.orange, 1));
			else if(a == 2)
				ve.add(new VaisseauEnnemi(gc, "Images/VE2.png", Color.white, 2));
			else
				ve.add(new VaisseauEnnemi(gc, "Images/VE3.png", Color.gray, 3));
		}
		
		Input inp = gc.getInput();
		//Déplacement vaisseau joueur
		if(vJ1.estVivant()) {
			if(inp.isKeyDown(Input.KEY_LEFT))
				vJ1.setX(vJ1.getX() - delta, gc);
			if(inp.isKeyDown(Input.KEY_RIGHT))
				vJ1.setX(vJ1.getX() + delta, gc);
			if(inp.isKeyDown(Input.KEY_UP))
				vJ1.setY(vJ1.getY() - delta, gc);
			if(inp.isKeyDown(Input.KEY_DOWN))
				vJ1.setY(vJ1.getY() + delta, gc);
		}
		
		if(Jouer.nbreJoueur == 2 && vJ2.estVivant()) {
			if(inp.getMouseX() >= 0 && inp.getMouseX() <= gc.getWidth() - Vaisseau.getLargeur())
				vJ2.setX(inp.getMouseX(), gc);
			if(inp.getMouseY() >= 0 && inp.getMouseY() <= gc.getHeight() - Vaisseau.getHauteur())
				vJ2.setY(inp.getMouseY(), gc);
		}
		
		//déplacement vaisseaux ennemis
		for(int i = 0; i < ve.size(); i++)
			ve.get(i).deplacer(delta);
		
		//Les tirs de projectile
		if(vJ1.estVivant() && inp.isKeyPressed(Input.KEY_SPACE))
			vJ1.tirer();
		if(Jouer.nbreJoueur == 2 && vJ2.estVivant() && inp.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			vJ2.tirer();
		//Les lvl1 tirent après 2.5sec
		if(timerlvl1 >= 2500) {
			timerlvl1 = 0;
			for(int i = 0; i < ve.size(); i++)
				if(ve.get(i).getLvlEnnemi() == 1) {
					ve.get(i).tirer();
					projectilesEnnemis.add(ve.get(i).getProjectiles().get(ve.get(i).getProjectiles().size() - 1));
				}
		}
		//Les lvl2 tirent après 1.5sec
		if(timerlvl2 >= 1500) {
			timerlvl2 = 0;
			for(int i = 0; i < ve.size(); i++)
				if(ve.get(i).getLvlEnnemi() == 2) {
					ve.get(i).tirer();
					projectilesEnnemis.add(ve.get(i).getProjectiles().get(ve.get(i).getProjectiles().size() - 1));
				}
		}
		//Les lvl3 tirent après 1sec
		if(timerlvl3 >= 1000) {
			timerlvl3 = 0;
			for(int i = 0; i < ve.size(); i++)
				if(ve.get(i).getLvlEnnemi() == 3) {
					ve.get(i).tirer();
					projectilesEnnemis.add(ve.get(i).getProjectiles().get(ve.get(i).getProjectiles().size() - 1));
				}
		}
			
	}

	@Override
	public int getID() {
		return 2;
	}

}
