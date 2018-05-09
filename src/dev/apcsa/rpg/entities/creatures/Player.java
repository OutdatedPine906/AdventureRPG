package dev.apcsa.rpg.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.Entity;
import dev.apcsa.rpg.gfx.Animation;
import dev.apcsa.rpg.gfx.Assets;

public class Player extends Creature{

	// Animations
	private Animation down, up, left, right;
	//Attack Timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	public Player(Handler handler, int x, int y){
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 22;
		bounds.y = 30;
		bounds.width = 19;
		bounds.height = 33;

		// Animations
		down = new Animation(500, Assets.player_down);
		up = new Animation(500, Assets.player_up);
		right = new Animation(500, Assets.player_right);
		left = new Animation(500, Assets.player_left);
	}

	@Override
	public void tick(){
		// Animations
		down.tick();
		up.tick();
		left.tick();
		right.tick();

		// Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);

		// Attack
		checkAttacks();
	}

	@Override
	public void die(){
		System.out.println("You Loose.");
	}

	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
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
				 e.hurt(1);
				 return;
			 }
		}
	}

	private void getInput(){
		xMove = 0;
		yMove = 0;

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
		// g.setColor(Color.red);
		// g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int)
		// (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width,
		// bounds.height);
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
}
