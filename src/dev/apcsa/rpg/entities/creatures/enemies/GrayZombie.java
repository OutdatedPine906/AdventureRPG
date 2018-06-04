package dev.apcsa.rpg.entities.creatures.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.Entity;
import dev.apcsa.rpg.entities.creatures.Creature;
import dev.apcsa.rpg.gfx.Animation;
import dev.apcsa.rpg.gfx.Assets;

public class GrayZombie extends Enemy{

	// Animations
	private Animation down, up, left, right;

	public GrayZombie(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, 2, 50, 800);

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
		movementChecks();
		move();
		
		// Attack
		checkAttacks();
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
	
	private boolean wait = true;
	
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
