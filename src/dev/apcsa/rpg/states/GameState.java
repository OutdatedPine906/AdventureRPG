package dev.apcsa.rpg.states;

import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.worlds.World;
import dev.apcsa.rpg.worlds.WorldList;

public class GameState extends State {

	private World world;
	private WorldList worldList;
	
	public GameState(Handler handler){
		super(handler);	
		worldList = new WorldList(handler);
		
		world = worldList.getSpawn();
		handler.setWorld(world);
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}
	
	public WorldList getWorldList() {
		return this.worldList;
	}

}
