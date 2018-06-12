package dev.apcsa.rpg.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.Entity;
import dev.apcsa.rpg.entities.creatures.npcs.ShopKeeper;
import dev.apcsa.rpg.gfx.Animation;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.inventory.Inventory;
import dev.apcsa.rpg.states.State;

public class Player extends Creature{

	// Animations
	private Animation down, up, left, right, attack_down, attack_up, attack_left, attack_right;
	
	//Attack Timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	
	//Heal Timer
	private long lastHealTimer, healCooldown = 2500, healTimer = healCooldown, lastHealth = health;
	
	//Inventory
	private Inventory inventory;
	
	private int gold, maxHealth, attack, constitution;
	
	public Player(Handler handler, int x, int y, int startingGold){
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		gold = startingGold;
		maxHealth = health;
		attack = 1;
		constitution = 0;
		
		bounds.x = 22;
		bounds.y = 30;
		bounds.width = 19;
		bounds.height = 33;

		// Animations
		down = new Animation(500, Assets.player_down);
		up = new Animation(500, Assets.player_up);
		right = new Animation(500, Assets.player_right);
		left = new Animation(500, Assets.player_left);
		
		attack_down = new Animation(250, Assets.player_attack_down);
		attack_up = new Animation(250, Assets.player_attack_up);
		attack_right = new Animation(250, Assets.player_attack_right);
		attack_left = new Animation(250, Assets.player_attack_left);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick(){
		// Animations
		down.tick();
		up.tick();
		left.tick();
		right.tick();
		
		attack_down.tick();
		attack_up.tick();
		attack_left.tick();
		attack_right.tick();

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
		while(!handler.getWorld().getEntityManager().getEntities().isEmpty()) {
			handler.getWorld().getEntityManager().getEntities().remove(0);
		}
		State.setState(handler.getGame().getGameOverState());
	}

	private void heal() {
		healTimer += System.currentTimeMillis() - lastHealTimer;
		lastHealTimer = System.currentTimeMillis();
		
		if(health > maxHealth) {
			health = maxHealth;
		}
		
		if(handler.getWorld().getCurrentWorld() == handler.getWorldList().getSpawn().getPath())
			healCooldown = 200;
		else
			healCooldown = 5000;
		
		if(health < lastHealth) {
			healTimer = 0;
			lastHealth = health;
		}
		
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

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_G)){
			gold += 100;
		}
		
		if(inventory.isActive())
			return;
		
		if(handler.getGame().getWeapons().isActive() && handler.getGame().getWeapons() != null)
			return;
		if(handler.getGame().getArmor().isActive() && handler.getGame().getArmor() != null)
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
			return attack_up.getCurrentFrame();
		}
		else if(handler.getKeyManager().aDown) {
			return attack_down.getCurrentFrame();
		}
		else if(handler.getKeyManager().aLeft) {
			return attack_left.getCurrentFrame();
		}
		else if(handler.getKeyManager().aRight) {
			return attack_right.getCurrentFrame();
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
	
	public void subtractGold(int amt) {
		gold -= amt;
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
