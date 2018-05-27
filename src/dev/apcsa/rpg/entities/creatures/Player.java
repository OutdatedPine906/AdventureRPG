package dev.apcsa.rpg.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.Entity;
import dev.apcsa.rpg.gfx.Animation;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.inventory.Inventory;
import dev.apcsa.rpg.states.State;

public class Player extends Creature{

	// Animations
	private Animation down, up, left, right;
	//Attack Timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	private long lastHealTimer, healCooldown = 2500, healTimer = healCooldown;
	//Inventory
	private Inventory inventory;
	
	private int gold, maxHealth, attack;
	
	public Player(Handler handler, int x, int y, int startingGold){
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		gold = startingGold;
		maxHealth = health;
		attack = 1;
		
		bounds.x = 22;
		bounds.y = 30;
		bounds.width = 19;
		bounds.height = 33;

		// Animations
		down = new Animation(500, Assets.player_down);
		up = new Animation(500, Assets.player_up);
		right = new Animation(500, Assets.player_right);
		left = new Animation(500, Assets.player_left);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick(){
		// Animations
		down.tick();
		up.tick();
		left.tick();
		right.tick();

		//Healing
		heal();
		
		//Gold Cap
		goldCap();
		
		// Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);

		// Attack
		checkAttacks();
		
		inventory.tick();
	}

	@Override
	public void die(){
		State.setState(handler.getGame().getGameOverState());
	}

	private void heal() {
		healTimer += System.currentTimeMillis() - lastHealTimer;
		lastHealTimer = System.currentTimeMillis();
		if(handler.getWorld().getCurrentWorld() == handler.getWorldList().getSpawn().getPath())
			healCooldown = 200;
		else
			healCooldown = 2500;
		if(healTimer < healCooldown)
			return;
		
		if(health < maxHealth)
			this.health += 1;
		
		healTimer = 0;
	}
	
	private void goldCap() {
		if(gold > 999)
			gold = 999;
	}
	
	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		if(inventory.isActive())
			return;

		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = 64;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}
		else if(handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y +  cb.height;
		}
		else if(handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else if(handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e: handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			 if(e.getCollisionBounds(0, 0).intersects(ar)) {
				 e.hurt(attack);
				 return;
			 }
		}
	}

	private void getInput(){
		xMove = 0;
		yMove = 0;

		if(inventory.isActive())
			return;
		
		if(handler.getKeyManager().up){
			yMove = -speed;
		}
		if(handler.getKeyManager().down){
			yMove = speed;
		}
		if(handler.getKeyManager().left){
			xMove = -speed;
		}
		if(handler.getKeyManager().right){
			xMove = speed;
		}
	}

	@Override
	public void render(Graphics g){
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame(){
	
		if(handler.getKeyManager().aUp) {
			return up.getCurrentFrame();
		}
		else if(handler.getKeyManager().aDown) {
			return down.getCurrentFrame();
		}
		else if(handler.getKeyManager().aLeft) {
			return left.getCurrentFrame();
		}
		else if(handler.getKeyManager().aRight) {
			return right.getCurrentFrame();
		}
		
		if(xMove < 0){
			return left.getCurrentFrame();
		} else if(xMove > 0){
			return right.getCurrentFrame();
		} else if(yMove < 0){
			return up.getCurrentFrame();
		} else{
			return down.getCurrentFrame();
		}
	}
	
	public int getGold() {
		return gold;
	}
	
	public void addGold(int amt) {
		gold += amt;
	}

	public int getMaxHealth(){
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}

	public Inventory getInventory(){
		return inventory;
	}

	public void setInventory(Inventory inventory){
		this.inventory = inventory;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
}
