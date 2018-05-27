package dev.apcsa.rpg.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.Entity;
import dev.apcsa.rpg.gfx.Animation;
import dev.apcsa.rpg.gfx.Assets;

public class GrayZombie extends Creature{

	// Animations
	private Animation down, up, left, right;

	// Attack Timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	public GrayZombie(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		this.speed = 1;
		this.health = 6;
		
		bounds.x = 22;
		bounds.y = 30;
		bounds.width = 22;
		bounds.height = 33;

		// Animations
		down = new Animation(500, Assets.zombie_down);
		up = new Animation(500, Assets.zombie_up);
		right = new Animation(500, Assets.zombie_right);
		left = new Animation(500, Assets.zombie_left);
	}

	@Override
	public void tick(){
		// Animations
		down.tick();
		up.tick();
		left.tick();
		right.tick();

		//Movement
		movement();
		move();
		
		// Attack
		checkAttacks();
	}

	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = 40;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getWorld().getEntityManager().getPlayer().getY() - this.y < arSize && handler.getWorld().getEntityManager().getPlayer().getY() < this.y) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		}
		else if(handler.getWorld().getEntityManager().getPlayer().getY() - this.y < arSize && handler.getWorld().getEntityManager().getPlayer().getY() > this.y) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		}
		else if(handler.getWorld().getEntityManager().getPlayer().getX() - this.x < arSize && handler.getWorld().getEntityManager().getPlayer().getX() < this.x) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else if(handler.getWorld().getEntityManager().getPlayer().getX() - this.x < arSize && handler.getWorld().getEntityManager().getPlayer().getX() > this.x) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		}
		else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e: handler.getWorld().getEntityManager().getEntities()) {
			 if(e.getCollisionBounds(0, 0).intersects(ar) && e.equals(handler.getWorld().getEntityManager().getPlayer())){
				 e.hurt(2);
				 return;
			 }
		}
	}
	
	private void movement() {
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
	
	private BufferedImage getCurrentAnimationFrame(){
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
	
	@Override
	public void render(Graphics g){
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		g.setColor(Color.green);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int)
		(y - 5 - handler.getGameCamera().getyOffset()), health * 10,
		5);//*/
	}

	@Override
	public void die(){
		handler.getWorld().getEntityManager().getPlayer().addGold(20);;
	}
}
