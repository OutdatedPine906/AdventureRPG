package dev.apcsa.rpg.worlds;

import java.util.ArrayList;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.Entity;
import dev.apcsa.rpg.entities.creatures.Creature;
import dev.apcsa.rpg.entities.creatures.enemies.GrayZombie;
import dev.apcsa.rpg.entities.creatures.enemies.Wolf;
import dev.apcsa.rpg.entities.creatures.npcs.ShopKeeper;
import dev.apcsa.rpg.entities.statics.Rock;
import dev.apcsa.rpg.entities.statics.Tree;
import dev.apcsa.rpg.entities.warps.East;
import dev.apcsa.rpg.entities.warps.North;
import dev.apcsa.rpg.entities.warps.South;
import dev.apcsa.rpg.entities.warps.West;
import dev.apcsa.rpg.tiles.Tile;

public class WorldList{

	private World spawn, grass;
	private Handler handler;
	private ArrayList<Entity> spawnEntities, grassEntities;

	public WorldList(Handler handler){
		this.handler = handler;

		// World Creation
		spawn = new World(handler, "res/worlds/Spawn.txt");
		grass = new World(handler, "res/worlds/Grasslands.txt");

		// Entity Spawning
		spawnEntities = new ArrayList<Entity>();
		spawnEntities.add(new North(handler, 576, 0, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT, 1));
		spawnEntities.add(new East(handler, 1216, 576, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2, 1));
		spawnEntities.add(new South(handler, 576, 1216, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT, 1));
		spawnEntities.add(new West(handler, 0, 576, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2, 1));
		spawnEntities.add(new Tree(handler, 700, 450));
		spawnEntities.add(new Tree(handler, 200, 50));
		spawnEntities.add(new Rock(handler, 100, 320));
		spawnEntities.add(new Rock(handler, 900, 350));
		spawnEntities.add(new ShopKeeper(handler, 960, 320, 1));
		spawnEntities.add(new ShopKeeper(handler, 320, 960, 2));

		grassEntities = new ArrayList<Entity>();
		grassEntities.add(new North(handler, Tile.TILE_WIDTH * 19, Tile.TILE_HEIGHT * 16, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 2));
		grassEntities.add(new East(handler, Tile.TILE_WIDTH * 22, Tile.TILE_HEIGHT * 19, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 2));
		grassEntities.add(new South(handler, Tile.TILE_WIDTH * 19, Tile.TILE_HEIGHT * 22, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 2));
		grassEntities.add(new West(handler, Tile.TILE_WIDTH * 16, Tile.TILE_HEIGHT * 19, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 2));
		grassEntities.add(new Tree(handler, 500, 450));
		grassEntities.add(new GrayZombie(handler, 600, 700, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
		grassEntities.add(new GrayZombie(handler, 1200, 750, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
		grassEntities.add(new GrayZombie(handler, 1500, 1400, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
		grassEntities.add(new GrayZombie(handler, 600, 1250, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
		grassEntities.add(new Wolf(handler, 600, 100, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
		
		// Spawn -- First Load
		spawn.entityManager.addEntities(spawnEntities);
	}
	
	public ArrayList getWorldEntities(String currentWorld){
		switch (currentWorld){
		case "res/worlds/Spawn.txt":
			return spawnEntities;
		case "res/worlds/Grasslands.txt":
			return grassEntities;
		default:
			return spawnEntities;
		}
	}

	public World getSpawn(){
		return spawn;
	}

	public World getGrass(){
		return grass;
	}

}
