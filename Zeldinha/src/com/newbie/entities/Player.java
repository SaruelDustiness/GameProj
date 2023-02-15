package com.newbie.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.newbie.main.Game;
import com.newbie.world.Camera;
import com.newbie.world.World;

public class Player extends Entity{
	
	public boolean right, up, left, down, atk;
	public static boolean dead;
	public boolean stopp = false;
	public double speed = 1.7;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	
	private int framesIdle = 0, framesRun = 0, framesAtk = 0, maxFramesAtk = 5, maxFramesIdle = 20, maxFramesRun = 4, index = 0, maxIndex = 7, index2 = 0, maxIndex2 = 1;
	private int framesDie = 0, maxFramesDie = 6, die = 0, maxDie = 3;
	private int iAtk = 0, maxIAtk = 4;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] sRightPlayer;
	private BufferedImage[] sLeftPlayer;
	private BufferedImage[] rightAtk;
	private BufferedImage[] leftAtk;
	private BufferedImage[] rightDead;
	private BufferedImage[] leftDead;
	public static double life = 100, maxLife = 100;
	public static int buff = 0;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		
		rightPlayer = new BufferedImage[8];
		leftPlayer = new BufferedImage[8];
		sRightPlayer = new BufferedImage[2];
		sLeftPlayer = new BufferedImage[2];
		leftAtk = new BufferedImage[5];
		rightAtk = new BufferedImage[5];
		leftDead = new BufferedImage[4];
		rightDead = new BufferedImage[4];
		
		for(int i = 0; i < 8; i++) {
			rightPlayer[i] = Game.charAnim.getSprite(0 + (i*24), 48, 24, 24);
		}
		for(int i = 0; i < 8; i++) {
			leftPlayer[i] = Game.charAnim.getSprite(0 + (i*24), 24, 24, 24);
		}
		for(int i = 0; i < 2; i++) {
			sRightPlayer[i] = Game.charAnim.getSprite(48 + (i*24), 0, 24, 24);
		}
		for(int i = 0; i < 2; i++) {
			sLeftPlayer[i] = Game.charAnim.getSprite(0 + (i*24), 0, 24, 24);
		}
		for(int i = 0; i < 5; i++) {
			rightAtk[i] = Game.atkDie.getSprite(0 + (i*24), 48, 24, 24);
		}
		for(int i = 0; i < 5; i++) {
			leftAtk[i] = Game.atkDie.getSprite(0 + (i*24), 0, 24, 24);
		}
		for(int i = 0; i < 4; i++) {
			rightDead[i] = Game.atkDie.getSprite(0 + (i*24), 72, 24, 24);
		}
		for(int i = 0; i < 4; i++) {
			leftDead[i] = Game.atkDie.getSprite(0 + (i*24), 24, 24, 24);
		}
		
	}

	public void tick() {
		
		moved = false;
		
		if((right) && (World.isFree((int)(x+speed),this.getY())) && (!Game.dead) && (!Game.win)) {
			moved = true;
			dir = right_dir;
			x+=speed;
		}
		else if((left) && (World.isFree((int)(x-speed),this.getY())) && (!Game.dead) && (!Game.win)) {
			moved = true;
			dir = left_dir;
			x-=speed;
		}
		if((up) && (World.isFree(this.getX(),(int)(y-speed))) && (!Game.dead) && (!Game.win)) {
			moved = true;
			dir = up_dir;
			y-=speed;
		}
		else if((down) && (World.isFree(this.getX(),(int)(y+speed))) && (!Game.dead) && (!Game.win)) {
			moved = true;
			dir = down_dir;
			y+=speed;
		}
		
		//Animação de andar
		if(moved && !dead) {
			framesRun++;
			if(framesRun == maxFramesRun) {
				framesRun = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
		//Animação de parado
		if(!moved && !dead) {
			framesIdle++;
			if(framesIdle == maxFramesIdle) {
				framesIdle = 0;
				index2++;
				if(index2 > maxIndex2) {
					index2 = 0;
				}
			}
		}
		//Animação de ataque
		if(atk && !dead) {
			framesAtk++;
			if(framesAtk == maxFramesAtk) {
				framesAtk = 0;
				iAtk++;
				if(iAtk == 2) {	//Ao realizar o terceiro frame do ataque, o jogador 
					for(int i = 0; i < Game.entities.size(); i++) {
						Entity atual = Game.entities.get(i);
						if(atual instanceof Enemy) {
							if(((Enemy) atual).isCollidingWithPlayer()){
								int dano = Game.rand.nextInt(7)+1;
								int chance = Game.rand.nextInt(100)+1;
								if((chance < 10) && (buff == 0)){
									((Enemy) atual).life += -10;
									System.out.println("Inimigo "+i+": "+((Enemy) atual).life);
								}else if((chance > 10) && (buff == 0)){
									((Enemy) atual).life += -dano;
									System.out.println("Inimigo "+i+": "+((Enemy) atual).life);
								}else if((buff > 0)) {
									((Enemy) atual).life += -10;
									buff--;
									System.out.println("Inimigo "+i+": "+((Enemy) atual).life);
								}
								if(((Enemy) atual).life <= 0) {
									((Enemy) atual).dead = true;
								}
							}
						}
					}
				}
				if(iAtk > maxIAtk) {
					iAtk = 0;
					atk = false;
				}
			}
		}
		//Animação de morte
		if(dead && !stopp) {
			framesDie++;
			if(framesDie == maxFramesDie) {
				framesDie = 0;
				die++;
				if(die > maxDie) {
					die = 3;
					stopp = true;
				}
			}
		}
		
		this.checkHealCollision();
		this.checkBuffCollision();
		
		//Camera
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
	}
	
	public void checkHealCollision() {
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Heal) {
				if(Entity.isCollidingHeal(this, atual)){
					life+=10;
					
					if(life >= 100) {
						life = 100;
					}
					
					Game.entities.remove(i);
				}
			}
		}
		
	}
	
	public void checkBuffCollision() {
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Buff) {
				if(Entity.isCollidingBuff(this, atual)){
					
					buff+=5;
					
					Game.entities.remove(i);
				}
			}
		}
		
	}
	
	public void render(Graphics g) {
		
		if(dead && Game.last) {
			g.drawImage(rightDead[die], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		else if(dead && !Game.last) {
			g.drawImage(leftDead[die], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		if((dir == right_dir) && (moved)) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		else if((dir == left_dir) && (moved)) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}

		if((dir == up_dir) && (Game.last && moved)) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		else if((dir == down_dir) && (!Game.last && moved)) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		if((dir == down_dir) && (Game.last && moved)) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		else if((dir == up_dir) && (!Game.last && moved)) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		if(Game.last && !moved && !dead) {
			if(atk) {
				g.drawImage(rightAtk[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else {
				g.drawImage(sRightPlayer[index2], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		else if(!Game.last && !moved && !dead) {
			if(atk) {
				g.drawImage(leftAtk[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else {
				g.drawImage(sLeftPlayer[index2], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
	}
	
}
