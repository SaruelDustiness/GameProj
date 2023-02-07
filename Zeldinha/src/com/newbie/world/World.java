package com.newbie.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
					
					//Chão
					int pixelAtual = pixels[xx + (yy * WIDTH)];
					if(pixelAtual == 0xFFC3C3C3) {
						tiles[pixelAtual] = new FloorTile(xx*24, yy*24, Tile.TILE_FLOOR);
					}
					
					//Parede
					else if(pixelAtual == 0xFF7F7F7F) {
						tiles[pixelAtual] = new FloorTile(xx*24, yy*24, Tile.TILE_WALL);
					}
					
					//Player
					else if(pixelAtual == 0xFF00A2E8) {
						tiles[pixelAtual] = new FloorTile(xx*24, yy*24, Tile.TILE_FLOOR);
					}
					
					//Chão
					else {
						tiles[pixelAtual] = new FloorTile(xx*24, yy*24, Tile.TILE_FLOOR);
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
