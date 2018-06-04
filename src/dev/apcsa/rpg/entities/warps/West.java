package dev.apcsa.rpg.entities.warps;

import java.awt.Color;
import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.tiles.Tile;

public class West extends WarpPoint{

	private int ID;
	
	public West(Handler handler, float x, float y, int width, int height, int ID){
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
		
		if(checkPlayerCollision()){			
			if(ID == 1)
				warp(handler.getWorldList().getGrass().getPath(), 960, 1216);
			else if(ID == 2)
				warp(handler.getWorldList().getSpawn().getPath(), 64, 608);
			else
				warp(handler.getWorldList().getSpawn().getPath(), 64, 608);
		}			
	}

	@Override
	public void render(Graphics g){
		g.setColor(Color.orange);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die(){}
}
