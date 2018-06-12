package dev.apcsa.rpg.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.gfx.Text;

public class VictoryState extends State{
	private long currentTime = 0, lastTime = 0, delay = 3000;
	private boolean hasRun = false;
	
	public VictoryState(Handler handler){
		super(handler);
	}

	@Override
	public void tick() {
		if(!hasRun) {
			lastTime = System.currentTimeMillis();
			hasRun = true;
		}
		
		currentTime += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if(currentTime < delay)
			return;
		
		hasRun = false;
		currentTime = 0;
		lastTime = 0;
		State.setState(handler.getGame().menuState);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 960, 640);
		Text.drawString(g, "Victory", 480, 320, true, Color.green, Assets.font48);
		Text.drawString(g, "Redirecting to Menu", 480, 500, true, Color.green, Assets.font48);
	}
	
	public void setHasRun(boolean run) {
		hasRun = run;
	}
	
}
