package dev.apcsa.rpg.entities.creatures.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.entities.creatures.Creature;
import dev.apcsa.rpg.gfx.Animation;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.states.State;

public class Bat extends Enemy{

	// Animations
	private Animation down, up, left, right;

	public Bat(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, 10, 50, 400);

		this.speed = 2.7f;
		this.health = 200;
		
		bounds.x = 12;
		bounds.y = 30;
		bounds.width = 35;
		bounds.height = 20;

		// Animations
		down = new Animation(500, Assets.wolf_down);
		up = new Animation(500, Assets.wolf_up);
		right = new Animation(500, Assets.wolf_right);
		left = new Animation(500, Assets.wolf_left);
	}

	@Override
	public void tick(){
		// Animations
		down.tick();
		up.tick();
		left.tick();
		right.tick();

		//Movement
		moveToPlayer();
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
	
	@Override
	public void render(Graphics g){
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		g.setColor(Color.green);
		g.fillRect(280, 16, health * 2,32);
	}

	@Override
	public void die(){
		State.setState(handler.getGame().getGameOverState());
	}
}
