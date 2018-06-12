package dev.apcsa.rpg;

import java.awt.Graphics;

import dev.apcsa.rpg.gfx.GameCamera;
import dev.apcsa.rpg.input.KeyManager;
import dev.apcsa.rpg.input.MouseManager;
import dev.apcsa.rpg.states.State;
import dev.apcsa.rpg.worlds.World;
import dev.apcsa.rpg.worlds.WorldList;

public class Handler{

	private Game game;
	private World world;

	public Handler(Game game){
		this.game = game;
	}

	public int getWidth(){
		return game.getWidth();
	}

	public int getHeight(){
		return game.getHeight();
	}

	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}

	public Game getGame(){
		return game;
	}

	public void setGame(Game game){
		this.game = game;
	}
	
	public World getWorld(){
		return world;
	}

	public void setWorld(World world){
		this.world = world;
	}
	
	public State getGameState() {
		return game.getGameState();
	}
	
	public State getMenuState() {
		return game.getMenuState();
	}
	
	public WorldList getWorldList(){
		return game.getGameState().getWorldList();
	}
}
