package dev.apcsa.rpg.states;

import java.awt.Graphics;

import dev.apcsa.rpg.Game;
import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.worlds.WorldList;


public abstract class State {

	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	
	protected Handler handler;
	
	public State(Handler handler){
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public WorldList getWorldList() {
		return null;
	}
	
}
