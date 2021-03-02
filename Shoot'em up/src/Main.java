import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	public Main() {
		super("Shoot'em up");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		Music bgmusic = new Music("Sounds/backgroundMusic.ogg");
		bgmusic.loop();
		this.addState(new Accueil());
		this.addState(new Jouer());
		this.addState(new Partie());
		this.addState(new GameOver());
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main(), 1080, 720, false);
		app.setShowFPS(false);
		app.start();
	}

}
