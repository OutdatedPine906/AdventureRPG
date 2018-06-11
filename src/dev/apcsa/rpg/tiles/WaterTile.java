package dev.apcsa.rpg.tiles;

import java.awt.image.BufferedImage;

import dev.apcsa.rpg.gfx.Animation;
import dev.apcsa.rpg.gfx.Assets;

public class WaterTile extends Tile{
	
	public WaterTile(int id){
		super(Assets.water, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
