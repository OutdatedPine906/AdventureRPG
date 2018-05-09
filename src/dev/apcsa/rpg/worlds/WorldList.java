package dev.apcsa.rpg.worlds;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.statics.Rock;
import dev.apcsa.rpg.entities.statics.Tree;

public class WorldList{

	private World spawn, spawn_West;
	private Handler handler;
	
	public WorldList(Handler handler) {
		this.handler = handler;
		
		//World Creation
		spawn = new World(handler, "res/worlds/world1.txt");
		spawn_West = new World(handler, "res/worlds/world2.txt");
		
		// Entity Spawning
		spawn.entityManager.addEntity(new Tree(handler, 100, 250));
		spawn.entityManager.addEntity(new Rock(handler, 100, 450));
	}

	public World getSpawn(){
		return spawn;
	}

	public World getSpawn_West(){
		return spawn_West;
	}
	
}
