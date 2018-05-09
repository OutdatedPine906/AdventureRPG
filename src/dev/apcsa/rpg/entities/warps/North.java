package dev.apcsa.rpg.entities.warps;

import java.awt.Color;
import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.tiles.Tile;

public class North extends WarpPoint{

	public North(Handler handler){
		super(handler, 576, 0, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT);
		
		bounds.x = (int) this.x;
		bounds.y = (int) this.y;
		bounds.width = (int) this.width;
		bounds.height = (int) this.height;
	}

	@Override
	public void tick(){
		this.heal();
		
		if(this.checkEntityCollisions(0, 0))
			this.warp(handler.getWorldList().getSpawn().getPath(), 640f, 1152f);
	}

	@Override
	public void render(Graphics g){
		g.setColor(Color.red);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die(){}
}
