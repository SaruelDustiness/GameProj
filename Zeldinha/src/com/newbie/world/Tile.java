package com.newbie.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.newbie.main.Game;

public class Tile {

	public static BufferedImage TILE_FLOOR2 = Game.tilemap.getSprite(32, 32, 16, 16);
	public static BufferedImage TILE_FLOOR1 = Game.tilemap.getSprite(16, 32, 16, 16);
	public static BufferedImage TILE_FLOOR3 = Game.tilemap.getSprite(48, 32, 16, 16);
	public static BufferedImage TILE_WALL1 = Game.tilemap.getSprite(192, 0, 16, 16);
	public static BufferedImage TILE_WALL2 = Game.tilemap.getSprite(192, 16, 16, 16);
	public static BufferedImage TILE_WALL3 = Game.tilemap.getSprite(192, 32, 16, 16);
	public static BufferedImage TILE_WALL4 = Game.tilemap.getSprite(192, 48, 16, 16);
//	public static BufferedImage enemyBlue = Game.enemy.getSprite(0, 0, 16, 16);
	
	private BufferedImage sprite;
	private int x, y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprite, x, y, null);
		
	}
	
}
