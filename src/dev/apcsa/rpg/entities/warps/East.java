package dev.apcsa.rpg.entities.warps;

import java.awt.Graphics;

import dev.apcsa.rpg.Handler;

public class East extends WarpPoint{

	private int ID;
	
	public East(Handler handler, float x, float y, int width, int height, int ID){
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
					warp(handler.getWorldList().getGrass().getPath(), 1472, 1216);
				else if(ID == 2)
					warp(handler.getWorldList().getSpawn().getPath(), 1152, 608);
				else
					warp(handler.getWorldList().getSpawn().getPath(), 1152, 608);
			}	
		}
	}

	@Override
	public void render(Graphics g){}

	@Override
	public void die(){}
}
