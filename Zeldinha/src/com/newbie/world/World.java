package com.newbie.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newbie.entities.Entity;
import com.newbie.entities.Enemy;
import com.newbie.main.Game;

public class World {

	private Tile[] tiles;
	public static int WIDTH, HEIGHT;
	
	public World(String path) {
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH*HEIGHT];
			tiles = new Tile[WIDTH*HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			for(int xx = 0; xx < WIDTH; xx++) {
				for(int yy = 0; yy < HEIGHT; yy++) {
					
					int pixelAtual = pixels[xx + (yy * WIDTH)];
					for(int i = 0; i < (16*16); i++) {
						int rand = (int)(Math.random()*3)+1;
						if(rand==1) {
							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR1);
						}else if(rand==2) {
							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR2);
						}else {
							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR3);
						}
					}
					
					//Chão
					if(pixelAtual == 0xFFC3C3C3) {
						for(int i = 0; i < (16*16); i++) {
							int rand = (int)(Math.random()*3)+1;
							if(rand==1) {
								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR1);
							}else if(rand==2) {
								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR2);
							}else {
								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR3);
							}
						}
					}
					
					//Parede
					else if(pixelAtual == 0xFF7F7F7F) {
						for(int i = 0; i < (16*16); i++) {
							int rand = (int)(Math.random()*4)+1;
							if(rand==1) {
								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_WALL1);
							}else if(rand==2) {
								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_WALL2);
							}else if(rand==3) {
								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_WALL3);
							}else {
								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_WALL4);
							}
						}
					}
					
					//Player
					else if(pixelAtual == 0xFF00A2E8) {
						Game.player.setX(xx*16);
						System.out.println(Game.player.getX());
						Game.player.setY(yy*16);
						System.out.println(Game.player.getY());
					}
					
					//Inimigo
					else if(pixelAtual == 0xFFED1C24) {
						for(int i = 0; i < 6; i++) {
							int rand = (int)(Math.random()*2)+1;
							if(rand%2==0) {
								//Inimigo Azul
								Game.entities.add(new Enemy(xx*16, yy*16, 16, 16, Entity.enemyBlue));
							}
							else {
								//Inimigo Marrom
								Game.entities.add(new Enemy(xx*16, yy*16, 16, 16, Entity.enemyBrown));
							}
						}
					}
					
					//Cura
					else if(pixelAtual == 0xFFFFF200) {
						
					}
					
					//Espada
					else if(pixelAtual == 0xFF22B14C) {
						
					}
					
					//Buff
					else if(pixelAtual == 0xFFFF7F27) {
						
					}
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
			
	}
	
	public void render(Graphics g) {
		
		for(int xx = 0; xx < WIDTH; xx++) {
			for(int yy = 0; yy < HEIGHT; yy++) {
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
		
	}
	
}
