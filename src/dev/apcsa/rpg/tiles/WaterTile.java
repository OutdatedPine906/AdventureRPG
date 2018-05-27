package dev.apcsa.rpg.tiles;

import java.awt.image.BufferedImage;

import dev.apcsa.rpg.gfx.Animation;
import dev.apcsa.rpg.gfx.Assets;

public class WaterTile extends Tile{
	
	private static Animation waterTile = new Animation(1, Assets.water);
	
	public WaterTile(int id){
		super(waterTile, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
	@Override
	public boolean isAnimated(){
		return true;
	}
}
