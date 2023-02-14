package com.newbie.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.newbie.main.Game;
import com.newbie.world.Camera;

public class Entity {
	
	public static BufferedImage apple = Game.objects.getSprite(0, 32, 16, 16);
	public static BufferedImage weaponOne = Game.objects.getSprite(16, 0, 16, 16);	
	public static BufferedImage weaponTwo = Game.objects.getSprite(32, 0, 16, 16);
	public static BufferedImage weaponThree = Game.objects.getSprite(48, 0, 16, 16);
	public static BufferedImage enemyBlue = Game.enemyOne.getSprite(0, 0, 16, 16);
	public static BufferedImage enemyBrown = Game.enemyTwo.getSprite(0, 0, 16, 16);
	
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	private int maskx, masky, mwidth, mheight;
	
	private BufferedImage sprite;
	
	public Entity(double x, double y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
	}
	
	public void setMask(int maskx, int masky, int mwidth, int mheight) {
		
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
		
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}

	public void tick() {
		
	}
	
	public static boolean isColliding(Entity e1, Entity e2) {
		Rectangle playerAtual = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.mwidth, e1.mheight);
		Rectangle healAtual = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.mwidth, e2.mheight);
		
		return playerAtual.intersects(healAtual);
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
		
//		g.setColor(Color.red);
//		g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, mwidth, mheight);
		
	}
	
}
