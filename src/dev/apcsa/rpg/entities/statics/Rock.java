package dev.apcsa.rpg.entities.statics;

import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.items.Item;
import dev.apcsa.rpg.tiles.Tile;

public class Rock extends StaticEntity{

	public Rock(Handler handler, float x, float y){
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);
		
		getBounds().x = 3;
		getBounds().y = (int) (height / 2f);
		getBounds().width = width - 6;
		getBounds().height = (int) (height - height / 2f);
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
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
	}

}
