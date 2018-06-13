package dev.apcsa.rpg.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.creatures.Player;
import dev.apcsa.rpg.entities.creatures.enemies.Bat;
import dev.apcsa.rpg.entities.creatures.enemies.GrayZombie;
import dev.apcsa.rpg.entities.creatures.enemies.Wolf;
import dev.apcsa.rpg.entities.creatures.npcs.ShopKeeper;
import dev.apcsa.rpg.entities.statics.Bush;
import dev.apcsa.rpg.entities.statics.Rock;
import dev.apcsa.rpg.entities.statics.Tree;
import dev.apcsa.rpg.entities.warps.East;
import dev.apcsa.rpg.entities.warps.North;
import dev.apcsa.rpg.entities.warps.South;
import dev.apcsa.rpg.entities.warps.West;

public class EntityManager{

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b){
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
				return -1;
			}
			else {
				return 1;
			}
		}
	};

	public EntityManager(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}

	public void tick(){
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
			if(!e.isActive()) {
				entities.remove(e);
			}
		}
		entities.sort(renderSorter);
	}

	public void render(Graphics g){
		for(Entity e : entities) {
			e.render(g);
		}
		for(Entity e : entities) {
			if(!(e instanceof Player))
				e.postRender(g);
		}
		player.postRender(g);
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void addEntities(ArrayList<Entity> a) {
		entities.addAll(a);
	}
	
	public void changeEntities() {
		ArrayList<Entity> temp = new ArrayList<Entity>();
		ArrayList<Entity> temp2 = new ArrayList<Entity>();
		
		for(Entity e : entities) {
			if((e instanceof Player))
				temp.add(e);
		}
		entities.removeAll(entities);
		
		temp2.addAll(handler.getWorldList().getWorldEntities(handler.getWorld().getCurrentWorld()));
		for(Entity e : temp2) {
			Entity entity;
			if(e instanceof North) {
				entity = new North(e.handler, e.x, e.y, e.width, e.height, e.ID);
			}
			else if(e instanceof South) {
				entity = new South(e.handler, e.x, e.y, e.width, e.height, e.ID);
			}
			else if(e instanceof East) {
				entity = new East(e.handler, e.x, e.y, e.width, e.height, e.ID);
			}
			else if(e instanceof West) {
				entity = new West(e.handler, e.x, e.y, e.width, e.height, e.ID);
			}
			else if(e instanceof ShopKeeper) {
				entity = new ShopKeeper(e.handler, e.x, e.y, e.ID);
			}
			else if(e instanceof GrayZombie) {
				entity = new GrayZombie(e.handler, e.x, e.y, e.width, e.height);
			}
			else if(e instanceof Wolf) {
				entity = new Wolf(e.handler, e.x, e.y, e.width, e.height);
			}
			else if(e instanceof Bat) {
				entity = new Bat(e.handler, e.x, e.y, e.width, e.height);
			}
			else if(e instanceof Rock){
				entity = new Rock(e.handler, e.x, e.y);
			}
			else if(e instanceof Bush) {
				entity = new Bush(e.handler, e.x, e.y);
			}
			else {
				entity = new Tree(e.handler, e.x, e.y);
			}
			
			temp.add(entity);
		}
		
		entities.addAll(temp);
	}
	
	// Getters and Setters
	
	public Handler getHandler(){
		return handler;
	}

	public void setHandler(Handler handler){
		this.handler = handler;
	}

	public Player getPlayer(){
		return player;
	}

	public void setPlayer(Player player){
		this.player = player;
	}

	public ArrayList<Entity> getEntities(){
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities){
		this.entities = entities;
	}
}
