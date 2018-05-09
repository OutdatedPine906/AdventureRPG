package dev.apcsa.rpg.states;

import java.awt.Graphics;

import dev.apcsa.rpg.Handler;
import dev.apcsa.rpg.gfx.Assets;
import dev.apcsa.rpg.ui.ClickListener;
import dev.apcsa.rpg.ui.UIImageButton;
import dev.apcsa.rpg.ui.UIManager;

public class MenuState extends State {

	private UIManager uiManager;
	
	public MenuState(Handler handler){
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(416, 400, 128, 64, Assets.btn_start, new ClickListener(){

			@Override
			public void onClick(){
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGameState());
			}}));
	}

	@Override
	public void tick() {
		uiManager.tick();
		
		//TEMP
		handler.getMouseManager().setUIManager(null);
		State.setState(handler.getGameState());
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}
	
}
