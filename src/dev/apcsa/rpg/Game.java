package dev.apcsa.rpg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.apcsa.rpg.display.Display;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.gfx.GameCamera;
import dev.apcsa.rpg.input.KeyManager;
import dev.apcsa.rpg.input.MouseManager;
import dev.apcsa.rpg.shop.Shop;
import dev.apcsa.rpg.states.GameOver;
import dev.apcsa.rpg.states.GameState;
import dev.apcsa.rpg.states.MenuState;
import dev.apcsa.rpg.states.State;
import dev.apcsa.rpg.ui.UIManager;

public class Game implements Runnable{

	private Display display;
	private int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	// States
	public State gameState;
	public State menuState;
	public State gameOverState;

	// Shops
	public Shop weapons;
	public Shop armor;
	
	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//UI Manager
	public UIManager uiManager;

	// Camera
	private GameCamera gameCamera;

	// Handler
	private Handler handler;

	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}

	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		display.getCanvas().setBackground(Color.black);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		uiManager = new UIManager(handler);
		
		gameState = new GameState(handler);
		gameOverState = new GameOver(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
	}

	private void tick(){
		keyManager.tick();

		if(State.getState() != null)
			State.getState().tick();
	}

	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw Here!

		if(State.getState() != null)
			State.getState().render(g);

		// End Drawing!
		bs.show();
		g.dispose();
	}

	public void run(){

		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}

			if(timer >= 1000000000){
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();

	}

	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try{
			thread.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public GameCamera getGameCamera(){
		return gameCamera;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}
	
	public State getGameState() {
		return gameState;
	}
	
	public State getMenuState() {
		return menuState;
	}
	
	public State getGameOverState() {
		return gameOverState;
	}

	public Graphics getG() {
		return g;
	}

	public Shop getWeapons(){
		return weapons;
	}

	public void setWeapons(Shop weapons){
		this.weapons = weapons;
	}

	public Shop getArmor(){
		return armor;
	}

	public void setArmor(Shop armor){
		this.armor = armor;
	}

}