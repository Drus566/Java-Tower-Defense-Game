package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

import main.Game;
import main.GameStates;

public class KeyboardListener implements KeyListener {
	private Game game;
	
	public KeyboardListener(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (GameStates.gameState == EDIT) {
			game.getEditor().KeyPressed(e);
		}
		
//		switch (GameStates.gameState) {
//		case MENU:
//			game.getMenu().mouseClicked(e.getX(), e.getY());
//			break;
//		case PLAYING:
//			game.getPlaying().mouseClicked(e.getX(), e.getY());
//			break;
//		case SETTINGS:
//			game.getSettings().mouseClicked(e.getX(), e.getY());
//			break;
//		case EDIT:
//			game.getEditor().mouseClicked(e.getX(), e.getY());
//			break;
//		default:
//			break;
//		
//		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
