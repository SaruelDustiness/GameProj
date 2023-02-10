//package com.newbie.entities;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Rectangle;
//import java.awt.image.BufferedImage;
//
//import com.newbie.main.Game;
//import com.newbie.world.Camera;
//import com.newbie.world.World;
//
//public class Enemy extends Entity{
//	
//	private double speed = 1;
//	private int maskx = 8, masky = 8, maskw = 10, maskh = 10;
//
//	public Enemy(double x, double y, int width, int height, BufferedImage sprite) {
//		super(x, y, width, height, sprite);
//		
//	}
//	
//	public void tick() {
//		
////		if(Game.rand.nextInt(100) < 30) {
//			
//			if(((int)x < Game.player.getX()) && (World.isFree((int)(this.getX()+speed), this.getY())) 
//					&& !isColidding((int)(this.getX()+speed), this.getY())) {
//				x+=speed;
//			}else if(((int)x > Game.player.getX()) && (World.isFree((int)(this.getX()-speed), this.getY()))
//					&& !isColidding((int)(this.getX()-speed), this.getY())) {
//				x-=speed;
//			}
//			
//			if(((int)y < Game.player.getY()) && (World.isFree(this.getX(), (int)(this.getY()+speed)))
//					&& !isColidding(this.getX(), (int)(this.getY()+speed))) {
//				y+=speed;
//			}else if(((int)y > Game.player.getY()) && (World.isFree(this.getX(), (int)(this.getY()-speed)))
//					&& !isColidding(this.getX(), (int)(this.getY()-speed)))	{
//				y-=speed;
//			}
//			
////		}
//		
//	}
//	
//	public boolean isColidding(int xNext, int yNext) {
//		
//		Rectangle enemyCurrent = new Rectangle(xNext + maskx, yNext + masky, maskw, maskh);
//		
//		for(int i = 0; i< Game.enemies.size(); i++) {
//			
//			Enemy e = Game.enemies.get(i);
//			
//			if(e == this) {
//				
//				continue;
//				
//			}
//			
//			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky, maskw, maskh);
//			
//			if(enemyCurrent.intersects(targetEnemy)) {
//				
//				return true;
//				
//			}
//		}
//		
//		return false;
//	}
//	
//	public void render(Graphics g) {
//		
//		super.render(g);
//		
//		g.setColor(Color.blue);
//		g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, maskw, maskh);
//		
//	}
//
//}
