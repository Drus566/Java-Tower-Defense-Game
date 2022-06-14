package main;

import javax.swing.JFrame;

import helpz.LoadSave;
import inputs.KeyboardListener;
import inputs.MyMouseListener;
import managers.TileManager;
import scenes.Editing;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

public class Game extends JFrame implements Runnable {
	
	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	private GameScreen gameScreen;
	private Thread gameThread;
	
	
	private Render render;
	
	private Menu menu; 
	private Playing playing;
	private Settings settings;
	private Editing editing;
	
	private TileManager tileManager;
	
	public Render getRender() {
		return render;
	}
	
	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}
	
	public Editing getEditor() {
		return editing;
	}
	
	public TileManager getTileManager() {
		return tileManager;
	}
	
	public Game() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		 
		initClasses();
		CreateDefaultLevel();

		
		setResizable(false);
		add(gameScreen);
		pack();
		
		setLocationRelativeTo(null); 
		setVisible(true);
	}
	
	private void CreateDefaultLevel() {
		int[] arr = new int[400];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}
		
		LoadSave.CreateLevel("new level", arr);
	}

	private void initClasses() {
		tileManager = new TileManager();
		render = new Render(this);
		gameScreen = new GameScreen(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
		editing = new Editing(this);
	}
	
	private void start() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	private void updateGame() {
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.gameScreen.initInputs(); 
		// старт цикла игры
		game.start();

	}

	@Override
	public void run() {
//		1 000 000 000 / 60
//		= 1 666 6666.6667 nanosec
//		= 0.01666.. seconds
//		PER FRAME @ 60 FPS
// 		Чтобы было 60 фпс надо чтобы каждые 0.01666 секунд отрисовывался кадр
		double timePerFrame = 1_000_000_000.0 / FPS_SET;
		double timePerUpdate = 1_000_000_000.0 / UPS_SET;
		
		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;
		
		long now;

		while (true) {
			now = System.nanoTime();
			
			if (now - lastFrame >= timePerFrame) {
				repaint();
				lastFrame = now;
				frames++; 
			}  
			
			if (now - lastUpdate >= timePerUpdate) {
				updateGame();
				lastUpdate = now;
				updates++;
			}
			
			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}
	}
}
