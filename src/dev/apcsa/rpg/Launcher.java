package dev.apcsa.rpg;

/*
 * All Sources have been referenced in the README document.
 * All Controls are in the README document.
 */

public class Launcher {

	public static void main(String[] args){
		Game game = new Game("Tile Game!", 960, 640);
		game.start();
	}
	
}