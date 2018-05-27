package dev.apcsa.rpg.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.gfx.Text;
import dev.apcsa.rpg.ui.ClickListener;
import dev.apcsa.rpg.ui.UIImageButton;
import dev.apcsa.rpg.ui.UIManager;

public class GameOver extends State {

	private long currentTime = 0, lastTime = 0, delay = 50000;
	
	public GameOver(Handler handler){
		super(handler);
	}

	@Override
	public void tick() {
		currentTime += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		System.out.println(currentTime);
		if(currentTime < delay)
			return;
		
		currentTime = 0;
		lastTime = 0;
		State.setState(handler.getGame().menuState);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 960, 640);
		Text.drawString(g, "Game Over", 480, 320, true, Color.red, Assets.font48);
		Text.drawString(g, "Redirecting to Menu", 480, 500, true, Color.red, Assets.font48);
	}
	
}