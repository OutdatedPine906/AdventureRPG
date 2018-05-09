package dev.apcsa.rpg.worlds;

import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.EntityManager;
import dev.apcsa.rpg.entities.creatures.Player;
import dev.apcsa.rpg.entities.statics.Rock;
import dev.apcsa.rpg.entities.statics.Tree;
import dev.apcsa.rpg.entities.warps.East;
import dev.apcsa.rpg.entities.warps.North;
import dev.apcsa.rpg.entities.warps.South;
import dev.apcsa.rpg.entities.warps.West;
import dev.apcsa.rpg.tiles.Tile;
import dev.apcsa.rpg.utils.Utils;

public class World{

	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private Handler handler;
	private String path;
	private boolean hasRan = false;
	private boolean loaded = false;

	// Entities
	protected EntityManager entityManager;

	public World(Handler handler, String path){
		this.handler = handler;
		this.path = path;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));

		//Warp Point Spawning
		entityManager.addEntity(new North(handler));
		entityManager.addEntity(new East(handler));
		entityManager.addEntity(new South(handler));
		entityManager.addEntity(new West(handler));		
		
		loadWorld(path);

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}

	public void tick(){
		entityManager.tick();
	}

	public void render(Graphics g){

		int xStart = (int) Math.max(0, (int) (handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH));
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = Math.max(0, (int) (handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT));
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

		for(int y = yStart; y < yEnd; y++){
			for(int x = xStart; x < xEnd; x++){
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		// Entities
		entityManager.render(g);
	}

	public Tile getTile(int x, int y){

		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		if(x == 0 && y == 10) {
			return Tile.testing;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}

	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);			
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public void switchWorld(String path, float x, float y){
		entityManager.getPlayer().setX(x);
		entityManager.getPlayer().setY(y);
		loadWorld(path);
	}
	
	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	public String getPath() {
		return path;
	}

}
