package com.newbie.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.newbie.world.Camera;

public class Ranged extends Entity{

	private int dx, dy;
	private double spd;
	
	public Ranged(double x, double y, int width, int height, BufferedImage sprite, int dx, int dy) {
	
		super(x, y, width, height, sprite);
	
	}
	
	public void tick() {
		
		x += dx*spd;
		y += dy*spd;
	
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.CYAN);
		g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, width, height);
		
	}

}
