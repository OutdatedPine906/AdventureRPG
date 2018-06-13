package dev.apcsa.rpg.worlds;

import java.util.ArrayList;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.Entity;
import dev.apcsa.rpg.entities.creatures.Creature;
import dev.apcsa.rpg.entities.creatures.enemies.Bat;
import dev.apcsa.rpg.entities.creatures.enemies.GrayZombie;
import dev.apcsa.rpg.entities.creatures.enemies.Wolf;
import dev.apcsa.rpg.entities.creatures.npcs.ShopKeeper;
import dev.apcsa.rpg.entities.statics.Bush;
import dev.apcsa.rpg.entities.statics.Rock;
import dev.apcsa.rpg.entities.statics.Tree;
import dev.apcsa.rpg.entities.warps.East;
import dev.apcsa.rpg.entities.warps.North;
import dev.apcsa.rpg.entities.warps.South;
import dev.apcsa.rpg.entities.warps.West;
import dev.apcsa.rpg.tiles.Tile;

public class WorldList{

 private World spawn, grass, bossCave;
 private Handler handler;
 private ArrayList<Entity> spawnEntities, grassEntities, bossCaveEntities;

 public WorldList(Handler handler){
  this.handler = handler;

  // World Creation
  spawn = new World(handler, "res/worlds/Spawn.txt");
  grass = new World(handler, "res/worlds/Grasslands.txt");
  bossCave = new World(handler, "res/worlds/BossCave.txt");

  // Entity Spawning 1152-1344 576-704
  spawnEntities = new ArrayList<Entity>();
  spawnEntities.add(new North(handler, 576, 0, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT, 1));
  spawnEntities.add(new East(handler, 1216, 576, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2, 1));
  spawnEntities.add(new South(handler, 576, 1216, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT, 1));
  spawnEntities.add(new West(handler, 0, 576, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2, 1));
  spawnEntities.add(new Tree(handler, 450, 450));
  spawnEntities.add(new Tree(handler, 200, 50));
  spawnEntities.add(new Tree(handler, 900, 850));
  spawnEntities.add(new Tree(handler, 1140, 1050));
  spawnEntities.add(new Rock(handler, 100, 320));
  spawnEntities.add(new Rock(handler, 900, 850));
  spawnEntities.add(new ShopKeeper(handler, 960, 320, 1));
  spawnEntities.add(new ShopKeeper(handler, 320, 960, 2));
  spawnEntities.add(new Bush(handler, 900, 1050));
  spawnEntities.add(new Bush(handler, 1150, 900));
  spawnEntities.add(new Bush(handler, 500, 300));

  grassEntities = new ArrayList<Entity>();
  grassEntities.add(new North(handler, Tile.TILE_WIDTH * 19, Tile.TILE_HEIGHT * 16, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 2));
  grassEntities.add(new North(handler, Tile.TILE_WIDTH * 18, 0, Tile.TILE_WIDTH * 3, Tile.TILE_HEIGHT, 3));
  grassEntities.add(new East(handler, Tile.TILE_WIDTH * 22, Tile.TILE_HEIGHT * 19, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 2));
  grassEntities.add(new South(handler, Tile.TILE_WIDTH * 19, Tile.TILE_HEIGHT * 22, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 2));
  grassEntities.add(new West(handler, Tile.TILE_WIDTH * 16, Tile.TILE_HEIGHT * 19, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 2));
  grassEntities.add(new Tree(handler, 500, 450));
  grassEntities.add(new Tree(handler, 190, 150));
  grassEntities.add(new Tree(handler, 1600, 950));
  grassEntities.add(new Tree(handler, 900, 950));
  grassEntities.add(new Tree(handler, 300, 900));
  grassEntities.add(new Rock(handler, 200, 1500));
  grassEntities.add(new Rock(handler, 1800, 1400));
  grassEntities.add(new Rock(handler, 1900, 500));
  grassEntities.add(new Rock(handler, 150, 350));
  grassEntities.add(new Rock(handler, 1700, 2100));
  grassEntities.add(new Rock(handler, 100, 150));
  grassEntities.add(new Rock(handler, 2400, 2300));
  grassEntities.add(new Rock(handler, 1000, 1450));
  grassEntities.add(new GrayZombie(handler, 600, 700, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new GrayZombie(handler, 300, 1500, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new GrayZombie(handler, 1500, 750, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new GrayZombie(handler, 1500, 1400, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new GrayZombie(handler, 600, 1350, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new GrayZombie(handler, 100, 100, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new GrayZombie(handler, 2000, 100, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new GrayZombie(handler, 1800, 1800, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new Wolf(handler, 600, 100, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new Wolf(handler, 100, 1000, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new Wolf(handler, 900, 250, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new Wolf(handler, 500, 1000, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new Wolf(handler, 600, 1500, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new Wolf(handler, 2000, 2000, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  grassEntities.add(new Wolf(handler, 100, 2000, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  
  bossCaveEntities = new ArrayList<Entity>();
  bossCaveEntities.add(new South(handler, Tile.TILE_WIDTH * 6, Tile.TILE_HEIGHT * 9, Tile.TILE_WIDTH * 3, Tile.TILE_HEIGHT, 3));
  bossCaveEntities.add(new Bat(handler, 320, 448, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT));
  
  // Spawn -- First Load
  spawn.entityManager.addEntities(spawnEntities);
 }
 
 public ArrayList<Entity> getWorldEntities(String currentWorld){
  switch (currentWorld){
  case "res/worlds/Spawn.txt":
   return spawnEntities; 
  case "res/worlds/Grasslands.txt":
   return grassEntities;
  case "res/worlds/BossCave.txt":
   return bossCaveEntities;
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

 public World getBossCave(){
  return bossCave;
 }
}
