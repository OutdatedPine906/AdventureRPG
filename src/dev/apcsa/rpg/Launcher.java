package dev.apcsa.rpg;

public class Launcher {

	public static void main(String[] args){
		Game game = new Game("Tile Game!", 960, 640);
		game.start();
	}
	
}