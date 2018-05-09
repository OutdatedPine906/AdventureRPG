package dev.apcsa.rpg.entities.warps;

import java.awt.Color;
import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.tiles.Tile;

public class East extends WarpPoint{

	public East(Handler handler){
		super(handler, 1216, 576, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);
		
		bounds.x = (int) this.x;
		bounds.y = (int) this.y;
		bounds.width = (int) this.width;
		bounds.height = (int) this.height;
	}

	@Override
	public void tick(){
		this.heal();
		
		if(checkPlayerCollision())
			warp(handler.getWorldList().getSpawn_West().getPath(), 64f, 640f);
	}

	@Override
	public void render(Graphics g){
		g.setColor(Color.green);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die(){}
}
