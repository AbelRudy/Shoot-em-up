import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState {
	Font font;
	TrueTypeFont ttf;
	File score;
	//File nbScore;
	BufferedWriter bf, bf2;
	BufferedReader br;
	int nbreScore;
	boolean flag = false;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		font = new Font("Verdana", Font.BOLD, 30);
	    ttf = new TrueTypeFont(font, true);
	    try {
	    	score = new File("src/Fichiers/scores.txt");
	    	if(!score.exists())
	    		score.createNewFile();
//	    	nbScore = new File("src/Fichiers/nbscores.txt");
//	    	if(!nbScore.exists())
//	    		nbScore.createNewFile();
			bf = new BufferedWriter(new FileWriter(score, true));
//			br = new BufferedReader(new FileReader(nbScore, Charset.forName("UTF-8")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(new Image("Images/background.png"), 0, 0);
		g.setColor(Color.white);
		ttf.drawString(450, 10, "GAME OVER");
		if(Jouer.nbreJoueur == 1)
			ttf.drawString(450, 330, "Score : " +Partie.vJ1.getScore());
		else {
			ttf.drawString(450, 330, "Score Joueur 1 : " + Partie.vJ1.getScore());
			ttf.drawString(450, 360, "Score Joueur 2 : " + Partie.vJ2.getScore());
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(!flag) {
			flag = true;
			try {
//				String line = br.readLine();
//				System.out.println(line);
//				if(line != null)
//					nbreScore = Integer.parseInt(line) + 1;
//				else
//					nbreScore = 1;
//				br.close();
//				bf2 = new BufferedWriter(new FileWriter(nbScore, false));
//				bf2.write("" + nbreScore);
//				bf2.close();
				bf.write(Partie.vJ1.getScore() + " Joueur1");
				bf.newLine();
				if(Jouer.nbreJoueur == 2) {
					bf.write(Partie.vJ2.getScore() + " Joueur2");
					bf.newLine();
				}
				bf.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
