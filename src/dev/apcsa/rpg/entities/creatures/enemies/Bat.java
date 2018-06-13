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
	private Animation down, up, left, right, attack_down, attack_up, attack_right, attack_left;

	public Bat(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, 10, 50, 400);

		this.speed = 2.7f;
		this.health = 200;
		
		bounds.x = 12;
		bounds.y = 30;
		bounds.width = 35;
		bounds.height = 20;

		// Animations
		down = new Animation(500, Assets.bat_down);
		up = new Animation(500, Assets.bat_up);
		right = new Animation(500, Assets.bat_right);
		left = new Animation(500, Assets.bat_left);
		
		attack_down = new Animation(500, Assets.bat_attack_down);
		attack_up = new Animation(500, Assets.bat_attack_up);
		attack_right = new Animation(500, Assets.bat_attack_right);
		attack_left = new Animation(500, Assets.bat_attack_left);
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
		attack_right.tick(); 
		attack_left.tick();

		//Movement
		moveToPlayer();
		move();
		
		// Attack
		checkAttacks();
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		
		if(attackDirection.equals("Left")) {
			return attack_left.getCurrentFrame();
		}
		else if(attackDirection.equals("Right")) {
			return attack_right.getCurrentFrame();
		}
		else if(attackDirection.equals("Up")) {
			return attack_up.getCurrentFrame();
		}
		else if(attackDirection.equals("Down")) {
			return attack_down.getCurrentFrame();
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
	
	@Override
	public void render(Graphics g){
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		g.setColor(Color.green);
		g.fillRect(280, 16, health * 2,32);
	}

	@Override
	public void die(){
		State.setState(handler.getGame().getVictoryState());
	}
}
