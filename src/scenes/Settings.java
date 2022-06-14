package scenes;

import static main.GameStates.*;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import ui.MyButton;

public class Settings extends GameScene implements SceneMethods {
	private MyButton bMenu;

	public Settings(Game game) {
		super(game);
		initButtons();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 640, 640);		
		drawButtons(g);
	}
	
	private void initButtons() {
		int w = 120;
		int h = w / 3;
		int x = 5;
		int y = 5;
		
		bMenu = new MyButton("Menu", x, y, w, h);		
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			SetGameState(MENU);
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);

		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y)) {
			bMenu.setMousePressed(true);
		}		
	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
