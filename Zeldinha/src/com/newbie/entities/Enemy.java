package com.newbie.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.newbie.main.Game;
import com.newbie.world.Camera;
import com.newbie.world.World;

public class Enemy extends Entity{
	
	private int framesDmg = 0, framesRun = 0, framesAtk = 0, framesDie = 0;
	private int maxFramesDmg = 6, maxFramesRun = 7, maxFramesAtk = 6, maxFramesDie = 7;
	private int dmg = 0, maxDmg = 5, run = 0, maxRun = 6, atk = 0, maxAtk = 5, die = 0, maxDie = 6;
	private boolean last = true;
	
	private double speed = 1;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir;
	private Boolean blue;

	private BufferedImage[] rightBlueRun;
	private BufferedImage[] leftBlueRun;
	private BufferedImage[] rightBlueAtk;
	private BufferedImage[] leftBlueAtk;
	private BufferedImage[] rightBlueDie;
	private BufferedImage[] leftBlueDie;
	private BufferedImage[] rightBlueDmg;
	private BufferedImage[] leftBlueDmg;
	
	private BufferedImage[] rightBrownRun;
	private BufferedImage[] leftBrownRun;
	private BufferedImage[] rightBrownAtk;
	private BufferedImage[] leftBrownAtk;
	private BufferedImage[] rightBrownDie;
	private BufferedImage[] leftBrownDie;
	private BufferedImage[] rightBrownDmg;
	private BufferedImage[] leftBrownDmg;
	
//	private int maskx = 8, masky = 8, maskw = 5, maskh = 5;

	public Enemy(double x, double y, int width, int height, BufferedImage sprite, Boolean blue) {
		super(x, y, width, height, null);
		this.blue = blue;
		
		//Cogumelo Azul
		rightBlueAtk = new BufferedImage[6];
		rightBlueRun = new BufferedImage[7];
		rightBlueDmg = new BufferedImage[6];
		rightBlueDie = new BufferedImage[7];
		leftBlueAtk = new BufferedImage[6];
		leftBlueRun = new BufferedImage[7];
		leftBlueDmg = new BufferedImage[6];
		leftBlueDie = new BufferedImage[7];
		
		for(int i = 0; i < 6; i++) {
			rightBlueAtk[i] = Game.enemyOne.getSprite(0 + (i*16), 0, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			rightBlueRun[i] = Game.enemyOne.getSprite(0 + (i*16), 16, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			rightBlueDmg[i] = Game.enemyOne.getSprite(0 + (i*16), 32, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			rightBlueDie[i] = Game.enemyOne.getSprite(0 + (i*16), 48, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			leftBlueAtk[i] = Game.enemyOne.getSprite(0 + (i*16), 64, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			leftBlueRun[i] = Game.enemyOne.getSprite(0 + (i*16), 80, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			leftBlueDmg[i] = Game.enemyOne.getSprite(0 + (i*16), 96, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			leftBlueDie[i] = Game.enemyOne.getSprite(0 + (i*16), 112, 16, 16);
		}
		
		//Cogumelo Marrom
		rightBrownAtk = new BufferedImage[6];
		rightBrownRun = new BufferedImage[7];
		rightBrownDmg = new BufferedImage[6];
		rightBrownDie = new BufferedImage[7];
		leftBrownAtk = new BufferedImage[6];
		leftBrownRun = new BufferedImage[7];
		leftBrownDmg = new BufferedImage[6];
		leftBrownDie = new BufferedImage[7];
		
		for(int i = 0; i < 6; i++) {
			rightBrownAtk[i] = Game.enemyTwo.getSprite(0 + (i*16), 0, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			rightBrownRun[i] = Game.enemyTwo.getSprite(0 + (i*16), 16, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			rightBrownDmg[i] = Game.enemyTwo.getSprite(0 + (i*16), 32, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			rightBrownDie[i] = Game.enemyTwo.getSprite(0 + (i*16), 48, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			leftBrownAtk[i] = Game.enemyTwo.getSprite(0 + (i*16), 64, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			leftBrownRun[i] = Game.enemyTwo.getSprite(0 + (i*16), 80, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			leftBrownDmg[i] = Game.enemyTwo.getSprite(0 + (i*16), 96, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			leftBrownDie[i] = Game.enemyTwo.getSprite(0 + (i*16), 112, 16, 16);
		}		
		
	}
	
	public void tick() {
		
		framesRun++;
		if(framesRun == maxFramesRun) {
			framesRun = 0;
			run++;
			if(run > maxRun) {
				run = 0;
			}
		}
		
		if(Game.rand.nextInt(100) < 20) {
			
			if(((int)x < Game.player.getX()) && (World.isFree((int)(this.getX()+speed), this.getY())) 
					&& !isColidding((int)(this.getX()+speed), this.getY())) {
				x+=speed;
				dir = right_dir;
				last = true;
			}else if(((int)x > Game.player.getX()) && (World.isFree((int)(this.getX()-speed), this.getY()))
					&& !isColidding((int)(this.getX()-speed), this.getY())) {
				x-=speed;
				dir = left_dir;
				last = false;
			}
			
			if(((int)y < Game.player.getY()) && (World.isFree(this.getX(), (int)(this.getY()+speed)))
					&& !isColidding(this.getX(), (int)(this.getY()+speed))) {
				y+=speed;
				dir = up_dir;
			}else if(((int)y > Game.player.getY()) && (World.isFree(this.getX(), (int)(this.getY()-speed)))
					&& !isColidding(this.getX(), (int)(this.getY()-speed)))	{
				y-=speed;
				dir = down_dir;
			}
			
		}
		
	}
	
	public boolean isColidding(int xNext, int yNext) {
		
		Rectangle enemyCurrent = new Rectangle(xNext, yNext, World.TILE_SIZE, World.TILE_SIZE);
		
		for(int i = 0; i< Game.enemies.size(); i++) {
			
			Enemy e = Game.enemies.get(i);
			
			if(e == this) {
				
				continue;
				
			}
			
			Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), World.TILE_SIZE+1, World.TILE_SIZE+1);
			
			if(enemyCurrent.intersects(targetEnemy)) {
				
				return true;
				
			}
		}
		
		return false;
	}
	
	public void render(Graphics g) {
		
		if(blue) {
			
			if(dir == right_dir) {
				g.drawImage(rightBlueRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else if(dir == left_dir) {
				g.drawImage(leftBlueRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if((dir == up_dir) && last) {
				g.drawImage(rightBlueRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else if((dir == down_dir) && !last) {
				g.drawImage(leftBlueRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if((dir == up_dir) && !last) {
				g.drawImage(leftBlueRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else if((dir == down_dir) && last) {
				g.drawImage(rightBlueRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			
		}else {
			if(dir == right_dir) {
				g.drawImage(rightBrownRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else if(dir == left_dir) {
				g.drawImage(leftBrownRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if((dir == up_dir) && last) {
				g.drawImage(rightBrownRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else if((dir == down_dir) && !last) {
				g.drawImage(leftBrownRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			if((dir == up_dir) && !last) {
				g.drawImage(leftBrownRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else if((dir == down_dir) && last) {
				g.drawImage(rightBrownRun[run], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}

//		super.render(g);
//		g.setColor(Color.blue);
//		g.fillRect(this.getX() - Camera.x, this.getY() - Camera.y, 16, 16);
		
		
	}

}
