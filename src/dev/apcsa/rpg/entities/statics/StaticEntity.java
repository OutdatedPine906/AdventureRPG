package dev.apcsa.rpg.entities.statics;


import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.Entity;

public abstract class StaticEntity extends Entity{

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
}
