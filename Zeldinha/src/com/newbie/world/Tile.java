package com.newbie.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.newbie.main.Game;

public class Tile {
	
	//Mapa padrão 16x16 com texturas 1x1 - Grama e parede
	public static BufferedImage TILE_FLOOR2 = Game.tileMap.getSprite(32, 32, 16, 16);
	public static BufferedImage TILE_FLOOR1 = Game.tileMap.getSprite(16, 32, 16, 16);
	public static BufferedImage TILE_FLOOR3 = Game.tileMap.getSprite(48, 32, 16, 16);
	public static BufferedImage TILE_WALL1 = Game.tileMap.getSprite(192, 0, 16, 16);
	public static BufferedImage TILE_WALL2 = Game.tileMap.getSprite(192, 16, 16, 16);
	public static BufferedImage TILE_WALL3 = Game.tileMap.getSprite(192, 32, 16, 16);
	public static BufferedImage TILE_WALL4 = Game.tileMap.getSprite(192, 48, 16, 16);
	
	//Mapa de verão 16x16 com texturas 3x3 - Grama
	public static BufferedImage SUM_GRASSTL = Game.summerMap.getSprite(32, 0, 16, 16);
	public static BufferedImage SUM_GRASSTM = Game.summerMap.getSprite(48, 0, 16, 16);
	public static BufferedImage SUM_GRASSTR = Game.summerMap.getSprite(64, 0, 16, 16);
	public static BufferedImage SUM_GRASSML = Game.summerMap.getSprite(32, 16, 16, 16);
	public static BufferedImage SUM_GRASSMM = Game.summerMap.getSprite(48, 16, 16, 16);
	public static BufferedImage SUM_GRASSMR = Game.summerMap.getSprite(64, 16, 16, 16);
	public static BufferedImage SUM_GRASSBL = Game.summerMap.getSprite(32, 32, 16, 16);
	public static BufferedImage SUM_GRASSBM = Game.summerMap.getSprite(48, 32, 16, 16);
	public static BufferedImage SUM_GRASSBR = Game.summerMap.getSprite(64, 32, 16, 16);
	
	//Mapa de verão 16x16 com texturas 3x3 - Terra
	public static BufferedImage SUM_DIRTTL = Game.summerMap.getSprite(80, 160, 16, 16);
	public static BufferedImage SUM_DIRTTM = Game.summerMap.getSprite(96, 160, 16, 16);
	public static BufferedImage SUM_DIRTTR = Game.summerMap.getSprite(112, 160, 16, 16);
	public static BufferedImage SUM_DIRTML = Game.summerMap.getSprite(80, 176, 16, 16);
	public static BufferedImage SUM_DIRTMM = Game.summerMap.getSprite(96, 176, 16, 16);
	public static BufferedImage SUM_DIRTMR = Game.summerMap.getSprite(112, 176, 16, 16);
	public static BufferedImage SUM_DIRTBL = Game.summerMap.getSprite(80, 192, 16, 16);
	public static BufferedImage SUM_DIRTBM = Game.summerMap.getSprite(96, 192, 16, 16);
	public static BufferedImage SUM_DIRTBR = Game.summerMap.getSprite(112, 192, 16, 16);
	
	//Mapa de verão 16x16 com texturas 2x2 - Terra
	public static BufferedImage SUM_INNERGRASSTL = Game.summerMap.getSprite(0, 48, 16, 16);
	public static BufferedImage SUM_INNERGRASSTR = Game.summerMap.getSprite(16, 48, 16, 16);
	public static BufferedImage SUM_INNERGRASSBL = Game.summerMap.getSprite(0, 64, 16, 16);
	public static BufferedImage SUM_INNERGRASSBR = Game.summerMap.getSprite(16, 64, 16, 16);
		
	//Mapa de verão 16x16 com texturas 3x1 - Terra
	public static BufferedImage SUM_DIRTTO = Game.summerMap.getSprite(64, 160, 16, 16);
	public static BufferedImage SUM_DIRTMI = Game.summerMap.getSprite(64, 176, 16, 16);
	public static BufferedImage SUM_DIRTBO = Game.summerMap.getSprite(64, 192, 16, 16);
	
	//Mapa de verão 16x16 com texturas 2x2 - Parede
	public static BufferedImage SUM_WALLTL = Game.inside.getSprite(0, 0, 16, 16);
	public static BufferedImage SUM_WALLTR = Game.inside.getSprite(16, 0, 16, 16);
	public static BufferedImage SUM_WALLBL = Game.inside.getSprite(0, 16, 16, 16);
	public static BufferedImage SUM_WALLBR = Game.inside.getSprite(16, 16, 16, 16);
	
	//Items 16x16 - Cerca
	public static BufferedImage ITEM_TL = Game.items.getSprite(0, 112, 16, 16);
	public static BufferedImage ITEM_TM = Game.items.getSprite(16, 112, 16, 16);
	public static BufferedImage ITEM_TR = Game.items.getSprite(32, 112, 16, 16);
	public static BufferedImage ITEM_ML = Game.items.getSprite(0, 128, 16, 16);
	public static BufferedImage ITEM_MM = Game.items.getSprite(16, 128, 16, 16);
	public static BufferedImage ITEM_MR = Game.items.getSprite(32, 128, 16, 16);
	public static BufferedImage ITEM_BL = Game.items.getSprite(0, 144, 16, 16);
	public static BufferedImage ITEM_BM = Game.items.getSprite(16, 144, 16, 16);
	public static BufferedImage ITEM_BR = Game.items.getSprite(32, 144, 16, 16);
	public static BufferedImage ITEM_MM2 = Game.items.getSprite(0, 96, 16, 16);
	
	
	private BufferedImage sprite;
	private int x, y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
		
	}
	
}
