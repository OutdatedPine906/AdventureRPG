package dev.apcsa.rpg.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.gfx.Text;
import dev.apcsa.rpg.items.Item;

public class Inventory{

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;

	private int invX = 224, invY = 128, invWidth = 512, invHeight = 384, invListCenterX = invX + 178,
			invListCenterY = invY + invHeight / 2 + 5, invListSpacing = 30;
	private int invImgX = 612, invImgY = 150, invImgWidth = 64, invImgHeight = 64;
	private int invCountX = 644, invCountY = 245;

	private int selectedItem = 0;

	public Inventory(Handler handler){
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
	}

	public void tick(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
		if(!active)
			return;

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			selectedItem++;

		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;
	}

	public void render(Graphics g){
		if(!active)
			return;

		g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

		Text.drawString(g, "ATK", invCountX, invCountY + 48, true, Color.white, Assets.font28);
		Text.drawString(g, Integer.toString(handler.getWorld().getEntityManager().getPlayer().getAttack()),
				invCountX, invCountY + 91, true, Color.white, Assets.font28);
		Text.drawString(g, "HP", invCountX, invCountY + 139, true, Color.white, Assets.font28);
		Text.drawString(g, Integer.toString(handler.getWorld().getEntityManager().getPlayer().getHealth()),
				invCountX, invCountY + 182, true, Color.white, Assets.font28);
		Text.drawString(g, Integer.toString(handler.getWorld().getEntityManager().getPlayer().getGold()) + "g", invCountX, invCountY + 230, true, Color.yellow, Assets.font28);
		
		int length = inventoryItems.size();
		if(length == 0)
			return;
		
		for(int i = -5; i < 6; i++){
			if(selectedItem + i < 0 || selectedItem + i >= length)
				continue;
			if(i == 0){
				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX,
						invListCenterY + i * invListSpacing, true, Color.yellow, Assets.font28);
			} else{
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX,
						invListCenterY + i * invListSpacing, true, Color.white, Assets.font28);
			}

		}
		
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImgX, invImgY, invImgWidth, invImgHeight, null);
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.white, Assets.font28);		
	}

	// Inventory Methods

	public void addItem(Item item){
		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}

		inventoryItems.add(item);
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
