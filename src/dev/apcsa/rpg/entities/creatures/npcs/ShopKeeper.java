package dev.apcsa.rpg.entities.creatures.npcs;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.creatures.Creature;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.items.Item;
import dev.apcsa.rpg.shop.Shop;
import dev.apcsa.rpg.tiles.Tile;

public class ShopKeeper extends Creature{

	private Rectangle shopBoundry;
	private Shop shop;
	private int ID;
	
	public ShopKeeper(Handler handler, float x, float y, int id){
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_WIDTH);
		health = 2147483647;
		this.ID = id;
		
		if(ID == 0) {
			if(x > 608){
				ID = 1;
			}
			else{
				ID = 2;
			}
		}
		
		//Bounds
		bounds.x = 22;
		bounds.y = 30;
		bounds.width = 19;
		bounds.height = 33;
		
		shopBoundry = new Rectangle((int)(x - Tile.TILE_WIDTH * 2), (int)(y - Tile.TILE_HEIGHT * 2), Tile.TILE_WIDTH * 4, Tile.TILE_HEIGHT * 4);
		
		//Shop Creation
		shop = new Shop(handler, shopBoundry);
		
		if(ID == 1)
			handler.getGame().setWeapons(shop);
		if(ID == 2)
			handler.getGame().setArmor(shop);
		
		addShopItems();
	}

	public void addShopItems() {
		shop.addItem(Item.buying);
		if(ID == 1) {
			shop.addItem(Item.silverSword);
			shop.addItem(Item.goldSword);
			shop.addItem(Item.rubySword);
		}
		else if(ID == 2) {
			shop.addItem(Item.woodenShield);
			shop.addItem(Item.ironShield);
			shop.addItem(Item.sapphireShield);
		}
		
		shop.addItem(Item.selling);
		shop.addItem(Item.woodItem);
		shop.addItem(Item.rockItem);
		
		if(ID == 1) {
			shop.addItem(Item.silverSword);
			shop.addItem(Item.goldSword);
			shop.addItem(Item.rubySword);
		}
		else if(ID == 2) {
			shop.addItem(Item.woodenShield);
			shop.addItem(Item.ironShield);
			shop.addItem(Item.sapphireShield);
		}
	}
	
	@Override
	public void tick(){		
		heal();
		shop.tick();
	}

	@Override
	public void render(Graphics g){
		g.drawImage(Assets.shopkeeper, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	public void postRender(Graphics g) {
		shop.render(g);
	}

	@Override
	public void die(){}

	public void heal(){
		if(this.health != 2147483647)
			this.health = 2147483647;
	}
	
	public boolean isShopActive() {
		return shop.isActive();
	}
}
