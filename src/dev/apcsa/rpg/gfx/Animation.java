package dev.apcsa.rpg.gfx;

import java.awt.image.BufferedImage;

public class Animation{

	private int index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	private double speed;
	
	public Animation(double speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		lastTime = System.currentTimeMillis();
		timer = 0;
	}

	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer = 0;
			if(index >= frames.length) {
				index = 0;
			}
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
}
