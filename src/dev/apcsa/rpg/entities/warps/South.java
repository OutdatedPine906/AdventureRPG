package dev.apcsa.rpg.entities.warps;

import java.awt.Color;
import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.tiles.Tile;

public class South extends WarpPoint{

	private int ID;
	
	public South(Handler handler, float x, float y, int width, int height, int ID){
		super(handler, x, y, width, height, ID);
		
		this.ID = ID;
		
		getBounds().x = (int) this.x;
		getBounds().y = (int) this.y;
		getBounds().width = (int) this.width;
		getBounds().height = (int) this.height;
	}

	@Override
	public void tick(){
		this.heal();
		
		if(checkPlayerCollision()) {
			
			if(checkPlayerCollision()){			
				if(ID == 1)
					warp(handler.getWorldList().getGrass().getPath(), 1216, 1472);
				else if(ID == 2)
					warp(handler.getWorldList().getSpawn().getPath(), 608, 1152);
				else if(ID == 3)
					warp(handler.getWorldList().getGrass().getPath(), 1216, 64);
				else
					warp(handler.getWorldList().getSpawn().getPath(), 608, 1152);
			}	
		}
			
	}

	@Override
	public void render(Graphics g){}

	@Override
	public void die(){}
}