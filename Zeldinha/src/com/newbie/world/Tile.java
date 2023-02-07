package com.newbie.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.newbie.main.Game;

public class Tile {

	public static BufferedImage TILE_FLOOR = Game.summerTile.getSprite(24, 0, 24, 24);
	public static BufferedImage TILE_WALL = Game.summerTile.getSprite(0, 48, 24, 24);
	
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
