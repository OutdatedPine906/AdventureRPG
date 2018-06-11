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
	private int shopImgX = 612, shopImgY = 150, shopImgWidth = 64, shopImgHeight = 64;
	private int shopPriceX = 644, shopPriceY = 257;
	
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
			int sellStart = 0;
			
			for(Item i : shopItems) {
				if(i == Item.selling) {
					sellStart = shopItems.indexOf(i);
				}
			}
			
			switch(shopItems.get(selectedItem).getName()) {
			case "Iron Sword":
				if(handler.getWorld().getEntityManager().getPlayer().getGold() >= Item.silverSword.getBuyPrice() && selectedItem < sellStart) {
					handler.getWorld().getEntityManager().getPlayer().subtractGold(Item.silverSword.getBuyPrice());
					handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(Item.silverSword.createNew(1));
					handler.getWorld().getEntityManager().getPlayer().setAttack(handler.getWorld().getEntityManager().getPlayer().getAttack() + 2);
				}
				else if(handler.getWorld().getEntityManager().getPlayer().getInventory().getItemCount(Item.silverSword) > 0 && selectedItem >= sellStart) {
					handler.getWorld().getEntityManager().getPlayer().addGold(Item.silverSword.getSellPrice());
					handler.getWorld().getEntityManager().getPlayer().getInventory().removeItem(Item.silverSword);
					handler.getWorld().getEntityManager().getPlayer().setAttack(handler.getWorld().getEntityManager().getPlayer().getAttack() - 2);
				}
				break;
			case "Wooden Shield":
				if(handler.getWorld().getEntityManager().getPlayer().getGold() >= Item.woodenShield.getBuyPrice()  && selectedItem < sellStart) {
					handler.getWorld().getEntityManager().getPlayer().subtractGold(Item.woodenShield.getBuyPrice());
					handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(Item.woodenShield.createNew(1));
					handler.getWorld().getEntityManager().getPlayer().setMaxHealth(handler.getWorld().getEntityManager().getPlayer().getMaxHealth() + 5);
					handler.getWorld().getEntityManager().getPlayer().setHealth(handler.getWorld().getEntityManager().getPlayer().getHealth() + 5);
				}
				else if(handler.getWorld().getEntityManager().getPlayer().getInventory().getItemCount(Item.woodenShield) > 0 && selectedItem >= sellStart) {
					System.out.println("Sell");
					handler.getWorld().getEntityManager().getPlayer().addGold(Item.woodenShield.getSellPrice());
					handler.getWorld().getEntityManager().getPlayer().getInventory().removeItem(Item.woodenShield);
					handler.getWorld().getEntityManager().getPlayer().setMaxHealth(handler.getWorld().getEntityManager().getPlayer().getMaxHealth() - 5);
				}
				break;
			case "Wood":
				if(handler.getWorld().getEntityManager().getPlayer().getInventory().getItemCount(Item.woodItem) > 0 && selectedItem >= sellStart) {
					handler.getWorld().getEntityManager().getPlayer().addGold(Item.woodItem.getSellPrice());
					handler.getWorld().getEntityManager().getPlayer().getInventory().removeItem(Item.woodItem);
				}
				break;
			case "Rock":
				if(handler.getWorld().getEntityManager().getPlayer().getInventory().getItemCount(Item.rockItem) > 0 && selectedItem >= sellStart) {
					handler.getWorld().getEntityManager().getPlayer().addGold(Item.rockItem.getSellPrice());
					handler.getWorld().getEntityManager().getPlayer().getInventory().removeItem(Item.rockItem);
				}
				break;
			default: break;
			}
		}
	}

	public void render(Graphics g){
		if(!active)
			return;

		int sellStart = 0;
		
		for(Item i : shopItems) {
			if(i == Item.selling) {
				sellStart = shopItems.indexOf(i);
			}
		}
		
		g.drawImage(Assets.shopScreen, shopX, shopY, shopWidth, shopHeight, null);
		
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
		g.drawImage(item.getTexture(), shopImgX, shopImgY, shopImgWidth, shopImgHeight, null);
		
		if(selectedItem < sellStart && item != Item.buying && item != Item.selling) {
			Text.drawString(g, "Buy", shopPriceX, shopPriceY, true, Color.white, Assets.font28);
			Text.drawString(g, Integer.toString(item.getBuyPrice()), shopPriceX, shopPriceY + 45, true, Color.white, Assets.font28);
			
			if(item.getBonusType() == null){
				Text.drawString(g, "None", shopPriceX, shopPriceY + 140, true, Color.white, Assets.font28);
			}
			else {
				Text.drawString(g, item.getBonusType(), shopPriceX, shopPriceY + 140, true, Color.white, Assets.font28);
				Text.drawString(g,  "+" + Integer.toString(item.getBonus()), shopPriceX, shopPriceY + 190, true, Color.white, Assets.font28);
			}
		}
		else if(item != Item.buying && item != Item.selling){
			Text.drawString(g, "Sell", shopPriceX, shopPriceY, true, Color.white, Assets.font28);
			Text.drawString(g, Integer.toString(item.getSellPrice()), shopPriceX, shopPriceY + 45, true, Color.white, Assets.font28);
			
			if(item.getBonusType() == null){
				Text.drawString(g, "None", shopPriceX, shopPriceY + 140, true, Color.white, Assets.font28);
			}
			else {
				Text.drawString(g, item.getBonusType(), shopPriceX, shopPriceY + 140, true, Color.white, Assets.font28);
				Text.drawString(g,  "-" + Integer.toString(item.getBonus()), shopPriceX, shopPriceY + 190, true, Color.white, Assets.font28);
			}	
		}		
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