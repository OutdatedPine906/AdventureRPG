package dev.apcsa.rpg.shop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.gfx.Text;
import dev.apcsa.rpg.items.Item;

public class Shop{

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> shopItems;

	private int shopX = 224, shopY = 128, shopWidth = 512, shopHeight = 384, shopListCenterX = shopX + 178,
			shopListCenterY = shopY + shopHeight / 2 + 5, shopListSpacing = 30;
	private Rectangle shopBoundry;
	private int selectedItem = 0;
	
	public Shop(Handler handler, Rectangle boundry){
		this.handler = handler;
		shopItems = new ArrayList<Item>();
		shopBoundry = boundry;
	}

	public void tick(){
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(shopBoundry)
				&& handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
			active = !active;
		}
		
		if(!active) {
			return;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			selectedItem++;

		if(selectedItem < 0)
			selectedItem = shopItems.size() - 1;
		else if(selectedItem >= shopItems.size())
			selectedItem = 0;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
			switch(shopItems.get(selectedItem).getName()) {
			case "Wood":
				if(handler.getWorld().getEntityManager().getPlayer().getGold() >= 2) {
					handler.getWorld().getEntityManager().getPlayer().subtractGold(2);
					handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(Item.woodItem.createNew(1));
				}
				break;
			case "Rock":
				if(handler.getWorld().getEntityManager().getPlayer().getGold() >= 3) {
					handler.getWorld().getEntityManager().getPlayer().subtractGold(3);
					handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(Item.rockItem.createNew(1));
				}
				break;
			default: break;
			}
		}
	}

	public void render(Graphics g){
		if(!active)
			return;

		g.drawImage(Assets.inventoryScreen, shopX, shopY, shopWidth, shopHeight, null);

		int length = shopItems.size();
		if(length == 0)
			return;
		
		for(int i = -5; i < 6; i++){
			if(selectedItem + i < 0 || selectedItem + i >= length)
				continue;
			if(i == 0){
				Text.drawString(g, "> " + shopItems.get(selectedItem + i).getName() + " <", shopListCenterX,
						shopListCenterY + i * shopListSpacing, true, Color.yellow, Assets.font28);
			} else{
				Text.drawString(g, shopItems.get(selectedItem + i).getName(), shopListCenterX,
						shopListCenterY + i * shopListSpacing, true, Color.white, Assets.font28);
			}

		}
		
		Item item = shopItems.get(selectedItem);		
	}

	// Shop Methods

	public void addItem(Item item){
		item.setHandler(handler);
		shopItems.add(item);
	}

	// Getters and Setters

	public Handler getHandler(){
		return handler;
	}

	public void setHandler(Handler handler){
		this.handler = handler;
	}

	public boolean isActive(){
		return active;
	}

}