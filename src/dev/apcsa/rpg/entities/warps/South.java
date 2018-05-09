package dev.apcsa.rpg.entities.warps;

import java.awt.Color;
import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.tiles.Tile;

public class South extends WarpPoint{

	public South(Handler handler){
		super(handler, 576, 1216, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT);
		
		bounds.x = (int) this.x;
		bounds.y = (int) this.y;
		bounds.width = (int) this.width;
		bounds.height = (int) this.height;
	}

	@Override
	public void tick(){
		this.heal();
		
		if(checkPlayerCollision())
			warp(handler.getWorldList().getSpawn().getPath(), 1152f, 640);
	}

	@Override
	public void render(Graphics g){
		g.setColor(Color.blue);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die(){}
}