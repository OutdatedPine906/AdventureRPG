package dev.apcsa.rpg.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.gfx.Assets;

public class Item{

	//Handler
	public static Item[] items = new Item[256];
	public static Item woodItem = new Item(Assets.wood, "Wood", 0, 2, 1);
	public static Item rockItem = new Item(Assets.rock, "Rock", 1, 3, 2);
	
	public static Item silverSword = new Item(Assets.sword, "Silver Sword", 2, 15, 10);
	public static Item goldSword = new Item(Assets.sword, "Gold Sword", 3, 15, 10);
	public static Item bloodDiamondSword = new Item(Assets.sword, "Silver Sword", 4, 15, 10);
	public static Item woodenShield = new Item(Assets.shield, "Wooden Shield", 5, 10, 8);
	
	
	public static Item buying = new Item(null, "----Buying----", 254, 9999, 0);
	public static Item selling = new Item(null, "----Selling----", 255, 9999, 0);
	
	//Class
	public static final int ITEM_WIDTH = 32;
	public static final int ITEM_HEIGHT = 32;

	protected Handler handler;
	protected BufferedImage texture;
	protected String name, bonusType;
	protected final int id;
	
	protected Rectangle bounds;

	protected int x, y, count;
	protected int buyPrice, sellPrice, bonus;
	protected boolean pickedUp = false;

	public Item(BufferedImage texture, String name, int id, int buyPrice, int sellPrice){
		this.texture = texture;
		this.name = name;
		this.id = id;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		
		bonusInfo();
		
		count = 1;
		
		bounds = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);
		
		items[id] = this;
	}

	public void tick(){
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(bounds)) {
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}

	public void render(Graphics g){
		if(handler == null)
			return;
		render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
	}

	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
	}

	public Item createNew(int x, int y) {
		Item i = new Item(texture, name, id, buyPrice, sellPrice);
		i.setPosition(x, y);
		return i;
	}
	
	public Item createNew(int count) {
		Item i = new Item(texture, name, id, buyPrice, sellPrice);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	
	public void bonusInfo() {
		switch(name) {
		case "Iron Sword": this.bonusType = "Atk"; this.bonus = 2; break;
		case "Wooden Shield": this.bonusType = "HP"; this.bonus = 5; break;
		}
	}
	
	// Getters and Setters

	public Handler getHandler(){
		return handler;
	}

	public void setHandler(Handler handler){
		this.handler = handler;
	}

	public BufferedImage getTexture(){
		return texture;
	}

	public void setTexture(BufferedImage texture){
		this.texture = texture;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getX(){
		return x;
	}

	public void setX(int x){
		this.x = x;
	}

	public int getY(){
		return y;
	}

	public void setY(int y){
		this.y = y;
	}

	public int getCount(){
		return count;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getId(){
		return id;
	}

	public boolean isPickedUp(){
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp){
		this.pickedUp = pickedUp;
	}

	public int getBuyPrice(){
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice){
		this.buyPrice = buyPrice;
	}

	public int getSellPrice(){
		return sellPrice;
	}

	public void setSellPrice(int sellPrice){
		this.sellPrice = sellPrice;
	}

	public String getBonusType(){
		return bonusType;
	}

	public void setBonusType(String bonusType){
		this.bonusType = bonusType;
	}

	public int getBonus(){
		return bonus;
	}

	public void setBonus(int bonus){
		this.bonus = bonus;
	}
}
