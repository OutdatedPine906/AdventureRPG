package dev.apcsa.rpg.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.gfx.Text;
import dev.apcsa.rpg.ui.ClickListener;
import dev.apcsa.rpg.ui.UIImageButton;
import dev.apcsa.rpg.ui.UIManager;

public class MenuState extends State{

	private boolean firstRun = true;
	private UIManager uiManager;
	private UIImageButton start = new UIImageButton(416, 400, 128, 64, Assets.btn_start, new ClickListener(){
		@Override
		public void onClick(){
			if(!firstRun) {
				State gameState = new GameState(handler);
				
				handler.getMouseManager().setUIManager(null);
				State.setState(gameState);
			}
			else {
				firstRun = false;
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
			
		}
	});

	public MenuState(Handler handler){
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		uiManager.addObject(start);
	}

	@Override
	public void tick(){
		if(handler.getMouseManager().getUIManager() == null)
			handler.getMouseManager().setUIManager(uiManager);

		uiManager.tick();

		// Temporarily just go directly to the GameState, skip the menu state!
		//handler.getMouseManager().setUIManager(null);
		//State.setState(handler.getGame().getGameState());
	}

	@Override
	public void render(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, 960, 640);
		Text.drawString(g, "RPG Adventure", 480, 320, true, Color.green, Assets.font48);
		uiManager.render(g);
	}
}