package com.newbie.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newbie.entities.*;
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
//					for(int i = 0; i < (16*16); i++) {
//						int rand = (int)(Math.random()*3)+1;
//						if(rand==1) {
//							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR1);
//						}else if(rand==2) {
//							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR2);
//						}else {
//							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR3);
//						}
//					}
					//Chão padrão
					tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_DIRTMM);
					
					//Chão
					if(pixelAtual == 0xFF880015) {
//						for(int i = 0; i < (16*16); i++) {
//							int rand = (int)(Math.random()*3)+1;
//							if(rand==1) {
//								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR1);
//							}else if(rand==2) {
//								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR2);
//							}else {
//								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR3);
//							}
//						}
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSMM);
					}
					
					//Parede
					else if(pixelAtual == 0xFF7F7F7F) {
						for(int i = 0; i < (16*16); i++) {
							int rand = (int)(Math.random()*4)+1;
							if(rand==1) {
								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_WALLTL);
							}else if(rand==2) {
								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_WALLTR);
							}else if(rand==3) {
								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_WALLBL);
							}else {
								tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_WALLBR);
							}
						}
					}
					
					//Grama BM
					else if(pixelAtual == 0xFF00A2E8) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSBM);
					}
					//Grama TM
					else if(pixelAtual == 0xFF99D9EA) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSTM);
					}
					//Grama Interior TR
					else if(pixelAtual == 0xFF3F48CC) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_INNERGRASSTR);
					}
					//Grama Interior TL
					else if(pixelAtual == 0xFF22B14C) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_INNERGRASSTL);
					}
					//Grama Interior BR
					else if(pixelAtual == 0xFF7092BE) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_INNERGRASSBR);
					}
					//Grama Interior BL
					else if(pixelAtual == 0xFFB5E61D) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_INNERGRASSBL);
					}
					//Grama ML
					else if(pixelAtual == 0xFFA349A4) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSML);
					}
					//Grama MR
					else if(pixelAtual == 0xFFC8BFE7) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSMR);
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
						Game.entities.add(new Heal(xx*16, yy*16, 16, 16, Entity.apple));

					}
					
					//Espada
					else if(pixelAtual == 0xFF22B14C) {
						Game.entities.add(new Weapon(xx*16, yy*16, 16, 16, Entity.weaponOne));
					}
					
					//Buff
					else if(pixelAtual == 0xFFFF7F27) {
						Game.entities.add(new Buff(xx*16, yy*16, 16, 16, Entity.weaponTwo));

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
