package dev.apcsa.rpg.entities.warps;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.Entity;

public abstract class WarpPoint extends Entity{

	public WarpPoint(Handler handler, float x, float y, int width, int height, int id){
		super(handler, x, y, width, height, id);
		this.health = 2147483647;
	}
	
	public void heal(){
		if(this.health != 2147483647)
			this.health = 2147483647;
	}
	
	public boolean checkPlayerCollision() {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(this.getBounds())) {
			return true;
		}
		return false;
	}
	
	public void warp(String path, float newX, float newY){
		handler.getWorld().switchWorld(path, newX, newY);
	}
}
