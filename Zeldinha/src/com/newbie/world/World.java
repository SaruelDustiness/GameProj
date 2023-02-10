package com.newbie.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newbie.entities.*;
import com.newbie.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	
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
//						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSMM);
					}
					
					//Parede
					else if(pixelAtual == 0xFF7F7F7F) {
						for(int i = 0; i < (16*16); i++) {
							int rand = (int)(Math.random()*4)+1;
							if(rand==1) {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALL1);
							}else if(rand==2) {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALL2);
							}else if(rand==3) {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALL3);
							}else {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALL4);
							}
						}
					}
					
					//Parede - Chão
					else if(pixelAtual == 0xFF58824D) {
						for(int i = 0; i < (16*16); i++) {
							int rand = (int)(Math.random()*8)+1;
							if(rand==1) {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALLG1);
							}else if(rand==2) {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALLG2);
							}else if(rand==3) {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALLG3);
							}else if(rand==4) {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALLG4);
							}else if(rand==5) {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALLG5);
							}else if(rand==6) {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALLG6);
							}else if(rand==7) {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALLG7);
							}else {
								tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALLG8);
							}
						}
					}
					
					//Manter assim até segunda ordem.
					//Grama BM
					else if(pixelAtual == 0xFF00A2E8) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSBM);
					}
					//Grama TM
					else if(pixelAtual == 0xFF99D9EA) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSTM);
					}
					//Grama Interior TR direita superior
					else if(pixelAtual == 0xFF3F48CC) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_INNERGRASSTR);
					}
					//Grama Exterior BL direita superior
					else if(pixelAtual == 0xFFFF7F27) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSBL);
					}
					//Grama Interior TL esquerda superior
					else if(pixelAtual == 0xFF22B14C) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_INNERGRASSTL);
					}
					//Grama Exterior BR esquerda superior
					else if(pixelAtual == 0xFFFFF200) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSBR);
					}
					//Grama Interior BR direita infeior
					else if(pixelAtual == 0xFF7092BE) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_INNERGRASSBR);
					}
					//Grama Exterior TL direita inferior
					else if(pixelAtual == 0xFFFFC90E) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSTL);
					}
					//Grama Interior BL esquerda inferior
					else if(pixelAtual == 0xFFB5E61D) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_INNERGRASSBL);
					}
					//Grama Exterior TR esquerda inferior
					else if(pixelAtual == 0xFFEFE4B0) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSTR);
					}
					//Grama ML
					else if(pixelAtual == 0xFFA349A4) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSML);
					}
					//Grama MR
					else if(pixelAtual == 0xFFC8BFE7) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.SUM_GRASSMR);
					}
					
					// Cerca lateral esquerda
					else if(pixelAtual == 0xFF831B87) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_ML);
					}
					
					// Cerca lateral direita
					else if(pixelAtual == 0xFF36846A) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_MR);
					}
					
					// Cerca superior e inferior
					else if((pixelAtual == 0xFF467984) || (pixelAtual == 0xFF555587)) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_BM);
					}
					
					// Cerca superior esquerda
					else if(pixelAtual == 0xFF873F4B) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_TL);
					}
					
					// Cerca superior direita
					else if(pixelAtual == 0xFF874F75) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_TR);
					}
					
					// Cerca inferior esquerda
					else if(pixelAtual == 0xFF4E2E84) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_BL);
					}
					
					// Cerca inferior direita
					else if(pixelAtual == 0xFF818751) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_BR);
					}
					
					// Cerca central inversa
					else if(pixelAtual == 0xFF87481B) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_MM2);
					}
					
					// Cerca central
					else if(pixelAtual == 0xFFC1FFE7) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_MM);
					}
					
					// Cerca central direita
					else if(pixelAtual == 0xFF873F4B) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_MR);
					}
					
					// Cerca inferior direita
					else if(pixelAtual == 0xFF846A0A) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_BR);
					}
					
					// Cerca inferior esquerda
					else if(pixelAtual == 0xFF6B8456) {
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.ITEM_BL);
					}
					
					//Inimigo
					else if(pixelAtual == 0xFFED1C24) {
						Enemy enBlue = new Enemy(xx*16, yy*16, 16, 16, Entity.enemyBlue, true);
						Enemy enBrown = new Enemy(xx*16, yy*16, 16, 16, Entity.enemyBrown, false);
						if(((int)(Math.random()*3)+1)==1) {
							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR1);
						}else if(((int)(Math.random()*3)+1)==2) {
							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR2);
						}else {
							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR3);
						}
						
						if(((int)(Game.rand.nextInt(10))%2==0)) {
							//Inimigo Azul
							Game.entities.add(enBlue);
							Game.enemies.add(enBlue);
						}
						else {
							//Inimigo Marrom
							Game.entities.add(enBrown);
							Game.enemies.add(enBrown);
						}
						
					}
					
					//Cura
					else if(pixelAtual == 0xFFFF96CC) {
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
						Game.entities.add(new Heal(xx*16, yy*16, 16, 16, Entity.apple));

					}
					
//					//Espada
					else if(pixelAtual == 0xFFA5FF7F) {
						Game.entities.add(new Weapon(xx*16, yy*16, 16, 16, Entity.weaponOne));
					}
					
//					//Buff
					else if(pixelAtual == 0xFFFFD27F) {
						Game.entities.add(new Buff(xx*16, yy*16, 16, 16, Entity.weaponTwo));

					}
					
					//Player
					else if(pixelAtual == 0xFFC7EDBD) {
						Game.player.setX((xx*16)-5);
						Game.player.setY(yy*16);
					}
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
			
	}
	
	public static boolean isFree(int xNext, int yNext) {
		
		int x1 = (xNext+5)/TILE_SIZE;
		int y1 = (yNext+5)/TILE_SIZE;
		
		int x2 = ((xNext+5)+TILE_SIZE-1)/TILE_SIZE;
		int y2 = (yNext+5)/TILE_SIZE;
		
		int x3 = (xNext+5)/TILE_SIZE;
		int y3 = ((yNext+5)+TILE_SIZE-1)/TILE_SIZE;
		
		int x4 = ((xNext+5)+TILE_SIZE-1)/TILE_SIZE;
		int y4 = ((yNext+5)+TILE_SIZE-1)/TILE_SIZE;
		
		return !((tiles[x1+(y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2+(y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3+(y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4+(y4*World.WIDTH)] instanceof WallTile));
		
	}
	
	public void render(Graphics g) {
		
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
		
	}
	
}
