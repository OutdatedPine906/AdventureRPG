package dev.apcsa.rpg.worlds;

import java.awt.Color;
import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.EntityManager;
import dev.apcsa.rpg.entities.creatures.Player;
import dev.apcsa.rpg.gfx.Animation;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.gfx.Text;
import dev.apcsa.rpg.items.ItemManager;
import dev.apcsa.rpg.tiles.Tile;
import dev.apcsa.rpg.utils.Utils;

public class World{

	public static String currentWorld;
	
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private Handler handler;
	private String path;

	// Entities
	protected EntityManager entityManager;
	//Items
	protected ItemManager itemManager;

	public World(Handler handler, String path){
		this.handler = handler;
		this.path = path;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100, 10));	
		itemManager = new ItemManager(handler);
		loadWorld(path);

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}

	public void tick(){
		itemManager.tick();
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
		
		//Items
		itemManager.render(g);
		
		// Entities
		entityManager.render(g);
		
		//Health Bar
		playerHealthDisplay(g, entityManager.getPlayer().getHealth(), 0);
		Text.drawString(g, "HP: " + Integer.toString(entityManager.getPlayer().getHealth()), 10, handler.getHeight() - 40, false, Color.black, Assets.font28);
	}

	public void playerHealthDisplay(Graphics g, int playerHealth, int count) {
		Animation health = new Animation(500, Assets.hearts);
		
		if(playerHealth > 10) {			
			health.setCurrentFrame(10);
			g.drawImage(health.getCurrentFrame(), 110 + (count * 32), handler.getHeight() - 64, null);
			playerHealthDisplay(g, playerHealth - 10, count + 1);
		}
		else {
			health.setCurrentFrame(10 - (playerHealth % 10));
			g.drawImage(health.getCurrentFrame(), 110 + (count * 32), handler.getHeight() - 64, null);	
		}
	}
	
	public Tile getTile(int x, int y){

		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
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
		this.spawnX = Utils.parseInt(tokens[2]);
		this.spawnY = Utils.parseInt(tokens[3]);			
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public void switchWorld(String path, float x, float y){
		currentWorld = path;
		entityManager.getPlayer().setX(x);
		entityManager.getPlayer().setY(y);
		loadWorld(path);
		entityManager.changeEntities();
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

	public String getCurrentWorld() {
		return currentWorld;
	}

	public ItemManager getItemManager(){
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager){
		this.itemManager = itemManager;
	}
	
	public int getSpawnX() {
		return spawnX;
	}
	
	public int getSpawnY() {
		return spawnY;
	}
	
}
