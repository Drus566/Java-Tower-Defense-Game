package main;

import javax.swing.JFrame;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

public class Game extends JFrame implements Runnable {
	
	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	private GameScreen gameScreen;
	private Thread gameThread;
	
	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;
	
	private Render render;
	
	private Menu menu; 
	private Playing playing;
	private Settings settings;
	
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
	
	public Game() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		 
		initClasses();
		
		add(gameScreen);
		pack();
		
		setLocationRelativeTo(null); 
		setVisible(true);
	}

	private void initClasses() {
		render = new Render(this);
		gameScreen = new GameScreen(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
	}
	
	private void initInputs() {
		myMouseListener = new MyMouseListener();
		keyboardListener = new KeyboardListener(); 
		
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);
		
		requestFocus();
	}
	

	
	private void start() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	private void updateGame() {
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.initInputs(); 
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
			
			if (System.nanoTime() - lastUpdate >= timePerUpdate) {
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
