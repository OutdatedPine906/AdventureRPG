package dev.apcsa.rpg.entities.warps;

import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.Entity;

public abstract class WarpPoint extends Entity{

	public WarpPoint(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
		this.health = 2147483647;
	}
	
	public void heal(){
		if(this.health != 2147483647)
			this.health = 2147483647;
	}
	
	public boolean checkPlayerCollision() {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(this.bounds))
			return true;
		return false;
	}
	
	public void warp(float newX, float newY){
		handler.getWorld().switchWorld(handler.getWorldList().getSpawn_West().getPath(), newX, newY);
	}
}
