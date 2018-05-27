package dev.apcsa.rpg.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static Font font28, font48;
	
	public static BufferedImage player, dirt, grass, wall, tree, rock, wood, sword;
	public static BufferedImage inventoryScreen;
	
	public static BufferedImage[] player_down, player_up, player_right, player_left;
	public static BufferedImage[] zombie_down, zombie_up, zombie_right, zombie_left;
	public static BufferedImage[] wolf_down, wolf_up, wolf_right, wolf_left;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] water;
	
	public static void init(){
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		font48 = FontLoader.loadFont("res/fonts/slkscr.ttf", 48);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"));
		SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/textures/sheet1.png"));
		SpriteSheet weaponSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Sword.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		//Items
		wood = sheet1.crop(width, height, width, height);
		
		//Buttons
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(0, height * 5, width * 2, height);
		btn_start[1] = sheet.crop(0, height * 4, width * 2, height);
		
		//Player Animations
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_left = new BufferedImage[2];
		
		player_down[0] = sheet.crop(0, 0, width, height);
		player_down[1] = sheet.crop(width, 0, width, height);
		
		player_up[0] = sheet.crop(width * 2, 0, width, height);
		player_up[1] = sheet.crop(width * 3, 0, width, height);
		
		player_right[0] = sheet.crop(0, height, width, height);
		player_right[1] = sheet.crop(width, height, width, height);
		
		player_left[0] = sheet.crop(width * 2, height, width, height);
		player_left[1] = sheet.crop(width * 3, height, width, height);
		
		//Entities
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		zombie_down[0] = sheet.crop(0, height * 2, width, height);
		zombie_down[1] = sheet.crop(width, height * 2, width, height);
		
		zombie_up[0] = sheet.crop(width * 2, height * 2, width, height);
		zombie_up[1] = sheet.crop(width * 3, height * 2, width, height);
		
		zombie_right[0] = sheet.crop(0, height * 3, width, height);
		zombie_right[1] = sheet.crop(width, height * 3, width, height);
		
		zombie_left[0] = sheet.crop(width * 2, height * 3, width, height);
		zombie_left[1] = sheet.crop(width * 3, height * 3, width, height);
		
		wolf_right = new BufferedImage[2];
		
		wolf_right[0] = sheet.crop(width * 4, 0, width, height);
		wolf_right[1] = sheet.crop(width * 4, height, width, height);
		
		//Tiles
		dirt = sheet.crop(width * 3, height * 5, width, height);
		grass = sheet.crop(width * 4, height * 4, width, height);
		wall = sheet.crop(width * 4, height * 5, width, height);
		tree = sheet.crop(width * 4, width * 2, width, height * 2);
		rock = sheet.crop(width * 2, height * 5, width, height);
		sword = weaponSheet.crop(0, height, width, height);
		
		//Tile Animated (NOT WORKING)
		water = new BufferedImage[2];
		water[0] = sheet.crop(width * 2, height * 4, width, height);
		water[1] = sheet.crop(width * 3, height * 4, width, height);
	}
	
}
