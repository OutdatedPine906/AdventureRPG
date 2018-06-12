package dev.apcsa.rpg.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int widthSmall = 32, heightSmall = 32;
	private static final int widthLarge = 64, heightLarge = 64;
	public static Font font28, font48;
	
	public static BufferedImage dirt, grass, wall, tree, rock, water, bridgeHorizontal, bridgeVertical, woodFloor, bush;
	public static BufferedImage  wood, silverSword, goldSword, rubySword, woodenShield, ironShield, sapphireShield;
	public static BufferedImage inventoryScreen, shopScreen;
	public static BufferedImage shopkeeper;
	
	public static BufferedImage[] player_down, player_up, player_right, player_left, player_attack_down, player_attack_up, player_attack_right, player_attack_left;
	public static BufferedImage[] zombie_down, zombie_up, zombie_right, zombie_left;
	public static BufferedImage[] wolf_down, wolf_up, wolf_right, wolf_left;
	public static BufferedImage[] bat_down, bat_up, bat_right, bat_left, bat_attack_down, bat_attack_up, bat_attack_right, bat_attack_left;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] hearts;
	
	public static void init(){
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		font48 = FontLoader.loadFont("res/fonts/slkscr.ttf", 48);
		
		SpriteSheet spritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"));
		SpriteSheet weaponSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Swords.png"));
		SpriteSheet tileMap = new SpriteSheet(ImageLoader.loadImage("/textures/blockTileMap.png"));
		SpriteSheet wolf = new SpriteSheet(ImageLoader.loadImage("/textures/Wolf.png"));
		SpriteSheet zombie = new SpriteSheet(ImageLoader.loadImage("/textures/Zombie.png"));
		SpriteSheet bat = new SpriteSheet(ImageLoader.loadImage("/textures/Bat.png"));
		SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/textures/Player.png"));
		SpriteSheet heart = new SpriteSheet(ImageLoader.loadImage("/textures/GUI.png"));
		SpriteSheet shields = new SpriteSheet(ImageLoader.loadImage("/textures/Shields.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		shopScreen = ImageLoader.loadImage("/textures/shopScreen.png");
		
		//Items
		wood = spritesheet.crop(widthSmall, heightSmall * 2, widthSmall, heightSmall);
		silverSword = weaponSheet.crop(0, 0, widthLarge, heightLarge);
		goldSword = weaponSheet.crop(widthLarge, 0, widthLarge, heightLarge);
		rubySword = weaponSheet.crop(widthLarge * 2, 0, widthLarge, heightLarge);
		woodenShield = shields.crop(0, 0, widthSmall, widthSmall);
		ironShield = shields.crop(widthSmall, 0, widthSmall, widthSmall);
		sapphireShield = shields.crop(widthSmall * 2, 0, widthSmall, widthSmall);
		
		//Buttons
		btn_start = new BufferedImage[2];
		btn_start[0] = spritesheet.crop(0, heightSmall, widthSmall * 2, heightSmall);
		btn_start[1] = spritesheet.crop(0, 0, widthSmall * 2, heightSmall);
		
		//Player Animations
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_left = new BufferedImage[2];
		
		player_down[0] = player.crop(0, heightSmall * 4, widthSmall, heightSmall);
		player_down[1] = player.crop(0, heightSmall * 5, widthSmall, heightSmall);
		
		player_up[0] = player.crop(widthSmall * 2, heightSmall * 4, widthSmall, heightSmall);
		player_up[1] = player.crop(widthSmall * 2, heightSmall * 5, widthSmall, heightSmall);
		
		player_right[0] = player.crop(widthSmall, heightSmall * 4, widthSmall, heightSmall);
		player_right[1] = player.crop(widthSmall, heightSmall * 5, widthSmall, heightSmall);
		
		player_left[0] = player.crop(widthSmall * 3, heightSmall * 4, widthSmall, heightSmall);
		player_left[1] = player.crop(widthSmall * 3, heightSmall * 5, widthSmall, heightSmall);
		
		player_attack_down = new BufferedImage[4];
		player_attack_up = new BufferedImage[4];
		player_attack_right = new BufferedImage[4];
		player_attack_left = new BufferedImage[4];
		
		player_attack_down[0] = player.crop(widthSmall, 0, widthSmall, heightSmall);
		player_attack_down[1] = player.crop(widthSmall, heightSmall, widthSmall, heightSmall);
		player_attack_down[2] = player.crop(widthSmall, heightSmall * 2, widthSmall, heightSmall);
		player_attack_down[3] = player.crop(widthSmall, heightSmall * 3, widthSmall, heightSmall);
		
		player_attack_up[0] = player.crop(0, 0, widthSmall, heightSmall);
		player_attack_up[1] = player.crop(0, heightSmall, widthSmall, heightSmall);
		player_attack_up[2] = player.crop(0, heightSmall * 2, widthSmall, heightSmall);
		player_attack_up[3] = player.crop(0, heightSmall * 3, widthSmall, heightSmall);
		
		player_attack_right[0] = player.crop(widthSmall * 2, 0, widthSmall, heightSmall);
		player_attack_right[1] = player.crop(widthSmall * 2, heightSmall, widthSmall, heightSmall);
		player_attack_right[2] = player.crop(widthSmall * 2, heightSmall * 2, widthSmall, heightSmall);
		player_attack_right[3] = player.crop(widthSmall * 2, heightSmall * 3, widthSmall, heightSmall);
		
		player_attack_left[0] = player.crop(widthSmall * 3, 0, widthSmall, heightSmall);
		player_attack_left[1] = player.crop(widthSmall * 3, heightSmall, widthSmall, heightSmall);
		player_attack_left[2] = player.crop(widthSmall * 3, heightSmall * 2, widthSmall, heightSmall);
		player_attack_left[3] = player.crop(widthSmall * 3, heightSmall * 3, widthSmall, heightSmall);
		
		// Health
		hearts = new BufferedImage[11];
		hearts[0] = heart.crop(widthSmall * 4, heightSmall, widthSmall, heightSmall);
		hearts[1] = heart.crop(widthSmall, 0, widthSmall, heightSmall);
		hearts[2] = heart.crop(widthSmall * 2, 0, widthSmall, heightSmall);
		hearts[3] = heart.crop(widthSmall * 3, 0, widthSmall, heightSmall);
		hearts[4] = heart.crop(widthSmall * 4, 0, widthSmall, heightSmall);
		hearts[5] = heart.crop(widthSmall * 5, 0, widthSmall, heightSmall);
		hearts[6] = heart.crop(0, heightSmall, widthSmall, heightSmall);
		hearts[7] = heart.crop(widthSmall, heightSmall, widthSmall, heightSmall);
		hearts[8] = heart.crop(widthSmall * 2, heightSmall, widthSmall, heightSmall);
		hearts[9] = heart.crop(widthSmall * 3, heightSmall, widthSmall, heightSmall);
		hearts[10] = heart.crop(0, 0, widthSmall, heightSmall);
		
		//Entities
		shopkeeper = spritesheet.crop(0, heightSmall * 2, widthSmall, heightSmall);
		
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		zombie_down[0] = zombie.crop(0, 0, widthSmall, heightSmall);
		zombie_down[1] = zombie.crop(widthSmall, 0, widthSmall, heightSmall);
		
		zombie_up[0] = zombie.crop(widthSmall * 2, 0, widthSmall, heightSmall);
		zombie_up[1] = zombie.crop(widthSmall * 3, 0, widthSmall, heightSmall);
		
		zombie_right[0] = zombie.crop(0, heightSmall, widthSmall, heightSmall);
		zombie_right[1] = zombie.crop(widthSmall, heightSmall, widthSmall, heightSmall);
		
		zombie_left[0] = zombie.crop(widthSmall * 2, heightSmall, widthSmall, heightSmall);
		zombie_left[1] = zombie.crop(widthSmall * 3, heightSmall, widthSmall, heightSmall);
		
		wolf_down = new BufferedImage[2];
		wolf_up = new BufferedImage[2];
		wolf_left = new BufferedImage[2];
		wolf_right = new BufferedImage[2];
		
		wolf_down[0] = wolf.crop(widthSmall, 0, widthSmall, heightSmall);
		wolf_down[1] = wolf.crop(widthSmall, heightSmall, widthSmall, heightSmall);
		
		wolf_up[0] = wolf.crop(0, 0, widthSmall, heightSmall);
		wolf_up[1] = wolf.crop(0, heightSmall, widthSmall, heightSmall);
		
		wolf_left[0] = wolf.crop(widthSmall * 2, 0, widthSmall, heightSmall);
		wolf_left[1] = wolf.crop(widthSmall * 2, heightSmall, widthSmall, heightSmall);
		
		wolf_right[0] = wolf.crop(widthSmall * 3, 0, widthSmall, heightSmall);
		wolf_right[1] = wolf.crop(widthSmall * 3, heightSmall, widthSmall, heightSmall);
		
		bat_down = new BufferedImage[2];
		bat_up = new BufferedImage[2];
		bat_left = new BufferedImage[2];
		bat_right = new BufferedImage[2];
		
		bat_left[0] = bat.crop(widthLarge * 2, heightSmall * 2, widthLarge, heightSmall);
		bat_left[1] = bat.crop(widthLarge * 2, heightSmall * 3, widthLarge, heightSmall);
		
		bat_down[0] = bat.crop(widthLarge, heightSmall * 2, widthLarge, heightSmall);
		bat_down[1] = bat.crop(widthLarge, heightSmall * 3, widthLarge, heightSmall);
		
		bat_up[0] = bat.crop(0, heightSmall * 2, widthLarge, heightSmall);
		bat_up[1] = bat.crop(0, heightSmall * 3, widthLarge, heightSmall);
		
		bat_right[0] = bat.crop(widthLarge * 3, heightSmall * 2, widthLarge, heightSmall);
		bat_right[1] = bat.crop(widthLarge * 3, heightSmall * 3, widthLarge, heightSmall);
		
		bat_attack_down = new BufferedImage[2];
		bat_attack_up = new BufferedImage[2];
		bat_attack_right = new BufferedImage[2];
		bat_attack_left = new BufferedImage[2];
		
		bat_attack_down[0] = bat.crop(widthLarge * 3, 0, widthLarge, heightSmall);
		bat_attack_down[1] = bat.crop(widthLarge * 3, heightSmall, widthLarge, heightSmall);
		
		bat_attack_up[0] = bat.crop(widthLarge * 2, 0, widthLarge, heightSmall);
		bat_attack_up[1] = bat.crop(widthLarge * 2, heightSmall, widthLarge, heightSmall);
		
		bat_attack_right[0] = bat.crop(0, 0, widthLarge, heightSmall);
		bat_attack_right[1] = bat.crop(0, heightSmall, widthLarge, heightSmall);
		
		bat_attack_left[0] = bat.crop(widthLarge, 0, widthLarge, heightSmall);
		bat_attack_left[1] = bat.crop(widthLarge, heightSmall, widthLarge, heightSmall);
		
		//Tiles
		dirt = tileMap.crop(0, heightLarge, widthLarge, heightLarge);
		grass = tileMap.crop(widthLarge, heightLarge, widthLarge, heightLarge);
		wall = tileMap.crop(widthLarge * 3, 0, widthLarge, heightLarge);
		water = tileMap.crop(widthLarge * 3, heightLarge, widthLarge, heightLarge);
		bridgeVertical = tileMap.crop(widthLarge * 4, heightLarge, widthLarge, heightLarge);
		bridgeHorizontal = ImageLoader.rotateBy90(tileMap.crop(widthLarge * 4, heightLarge, widthLarge, heightLarge));
		woodFloor = tileMap.crop(widthLarge * 4, 0, widthLarge, heightLarge);
		
		// Static Entities
		rock = tileMap.crop(widthLarge, 0, widthLarge, heightLarge);
		tree = tileMap.crop(widthLarge * 2, 0, widthLarge, heightLarge * 2);
		bush = tileMap.crop(0, 0, widthLarge, heightLarge);
	}
	
}
