package dev.apcsa.rpg.entities.warps;

import java.awt.Color;
import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.tiles.Tile;

public class North extends WarpPoint{

	private int ID;
	
	public North(Handler handler, float x, float y, int width, int height, int ID){
		super(handler, x, y, width, height, ID);
		
		this.ID = ID;
		
		bounds.x = (int) this.x;
		bounds.y = (int) this.y;
		bounds.width = (int) this.width;
		bounds.height = (int) this.height;
	}

	@Override
	public void tick(){
		this.heal();
		
		if(checkPlayerCollision()) {
			
			if(checkPlayerCollision()){			
				if(ID == 1)
					warp(handler.getWorldList().getGrass().getPath(), 1216, 960);
				else if(ID == 2)
					warp(handler.getWorldList().getSpawn().getPath(), 608f, 64);
				else
					warp(handler.getWorldList().getSpawn().getPath(), 608f, 64);
			}	
		}
			
	}

	@Override
	public void render(Graphics g){
		g.setColor(Color.red);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die(){}
}