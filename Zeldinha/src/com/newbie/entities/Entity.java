package com.newbie.entities;

import java.awt.Graphics;
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
	
	private BufferedImage sprite;
	
	public Entity(double x, double y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
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
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
}
