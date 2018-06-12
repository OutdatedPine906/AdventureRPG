package dev.apcsa.rpg.entities.creatures.enemies;

import java.awt.Rectangle;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.Entity;
import dev.apcsa.rpg.entities.creatures.Creature;
import dev.apcsa.rpg.tiles.Tile;

public abstract class Enemy extends Creature{
	
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	private boolean runCheck = false;
	private Rectangle movementBounds;
	protected String attackDirection = "";
	
	public Enemy(Handler handler, float x, float y, int width, int height, int attack, int attackRange, long attackCooldown){
		super(handler, x, y, width, height);
		this.attack = attack;
		this.attackRange = attackRange;
		this.attackCooldown = attackCooldown;
	}

	protected void movementChecks() {
		int quadWidth = Tile.TILE_WIDTH * 17;
		int quadHeight = Tile.TILE_WIDTH * 17;
		int pathSpacing = Tile.TILE_WIDTH * 3;
		
		if(!runCheck) {
			if(this.spawnX < quadWidth && this.spawnY < quadHeight) {
				movementBounds = new Rectangle(Tile.TILE_WIDTH, Tile.TILE_HEIGHT, quadWidth, quadHeight);
			}
			else if(this.spawnX > quadWidth && this.spawnY < quadHeight) {
				movementBounds = new Rectangle(quadWidth + pathSpacing + Tile.TILE_WIDTH, Tile.TILE_HEIGHT, quadWidth, quadHeight);
			}
			else if(this.spawnX < quadWidth && this.spawnY > quadHeight) {
				movementBounds = new Rectangle(Tile.TILE_WIDTH, quadHeight + pathSpacing + Tile.TILE_HEIGHT, quadWidth, quadHeight);
			}
			else if(this.spawnX > quadWidth && this.spawnY > quadHeight) {
				movementBounds = new Rectangle(quadWidth + pathSpacing + Tile.TILE_WIDTH, quadHeight + pathSpacing + Tile.TILE_WIDTH, quadWidth, quadHeight);
			}
			else {
				System.out.println("Fail");
				return;
			}
			
			runCheck = true;
		}
		
		if(movementBounds.contains(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0,0))) {
			moveToPlayer();
		}
		else if(this.getCollisionBounds(0, 0).intersects(movementBounds) && !movementBounds.contains(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0,0))) {
			moveToSpawnPoint();
		}
	}
	
	protected void moveToSpawnPoint() {
		xMove = 0;
		yMove = 0;
		
		if(this.spawnY < this.getY()){
			yMove = -speed;
		}
		if(this.spawnY > this.getY()){
			yMove = speed;
		}
		if(this.spawnX < this.getX()){
			xMove = -speed;
		}
		if(this.spawnX > this.getX()){
			xMove = speed;
		}
		if(this.spawnX == this.getX() && this.spawnY == this.getY()) {
			xMove = 0;
			yMove = 0;
		}
	}
	
	protected void moveToPlayer() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getWorld().getEntityManager().getPlayer().getY() < this.y){
			yMove = -speed;
		}
		if(handler.getWorld().getEntityManager().getPlayer().getY() > this.y){
			yMove = speed;
		}
		if(handler.getWorld().getEntityManager().getPlayer().getX() < this.x){
			xMove = -speed;
		}
		if(handler.getWorld().getEntityManager().getPlayer().getX() > this.x){
			xMove = speed;
		}
	}
	
	protected void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = attackRange;
		ar.width = arSize;
		ar.height = arSize;
		
		
		if(handler.getWorld().getEntityManager().getPlayer().getY() - this.y < arSize && handler.getWorld().getEntityManager().getPlayer().getY() < this.y) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
			attackDirection = "Down";
		}
		else if(handler.getWorld().getEntityManager().getPlayer().getY() - this.y < arSize && handler.getWorld().getEntityManager().getPlayer().getY() > this.y) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
			attackDirection = "Up";
		}
		else if(handler.getWorld().getEntityManager().getPlayer().getX() - this.x < arSize && handler.getWorld().getEntityManager().getPlayer().getX() < this.x) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
			attackDirection = "Left";
		}
		else if(handler.getWorld().getEntityManager().getPlayer().getX() - this.x < arSize && handler.getWorld().getEntityManager().getPlayer().getX() > this.x) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
			attackDirection = "Right";
		}
		else {
			attackDirection = "";
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e: handler.getWorld().getEntityManager().getEntities()) {
			 if(e.getCollisionBounds(0, 0).intersects(ar) && e.equals(handler.getWorld().getEntityManager().getPlayer())){
				 e.hurt(attack);
				 return;
			 }
		}
	}
}
