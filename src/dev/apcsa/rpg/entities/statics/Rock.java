package dev.apcsa.rpg.entities.statics;

import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.tiles.Tile;

public class Rock extends StaticEntity{

	public Rock(Handler handler, float x, float y){
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);
		
		bounds.x = 3;
		bounds.y = (int) (height / 2f);
		bounds.width = width - 6;
		bounds.height = (int) (height - height / 2f);
	}

	@Override
	public void tick(){
		
	}

	@Override
	public void render(Graphics g){
		g.drawImage(Assets.rock,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die(){
		
	}

}
