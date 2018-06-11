package dev.apcsa.rpg.entities.statics;

import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.items.Item;
import dev.apcsa.rpg.tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y){
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);
		
		health = 4;
		
		getBounds().x = 10;
		getBounds().y = (int) (height / 1.5f);
		getBounds().width = width - 20;
		getBounds().height = (int) (height - height / 1.5f);
	}

	@Override
	public void tick(){

	}

	@Override
	public void render(Graphics g){
		g.drawImage(Assets.tree,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die(){		
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y + 32));
	}

}
