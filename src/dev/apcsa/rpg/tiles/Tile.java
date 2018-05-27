package dev.apcsa.rpg.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.apcsa.rpg.gfx.Animation;

public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new WallTile(2);
	public static Tile water = new WaterTile(3);
	
	//CLASS
	
	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	
	protected BufferedImage texture;
	protected Animation animation;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public Tile(Animation animation, int id){
		this.animation = animation;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		if(this.isAnimated()){
			tileChanger();
			g.drawImage(animation.getCurrentFrame(), x, y, TILE_WIDTH, TILE_HEIGHT, null);
		}
		else
			g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	private void tileChanger(){
		animation.tick();
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public boolean isAnimated(){
		return false;
	}
	
	public int getId(){
		return id;
	}
	
}
