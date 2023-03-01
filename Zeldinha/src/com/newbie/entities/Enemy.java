package com.newbie.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.newbie.main.Game;
import com.newbie.world.Camera;
import com.newbie.world.World;

public class Enemy extends Entity{
	
	private int framesAtk = 0, framesRun = 0, framesIdle = 0, framesDie = 0;
	private int maxFramesAtk = 10, maxFramesRun = 7, maxFramesIdle = 6, maxFramesDie = 7;
	private int atk = 0, maxAtk = 5, run = 0, maxRun = 6, idle = 0, maxIdle = 5, die = 0, maxDie = 6;
	private boolean last = true;
	
	private double speed = 1;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir;
	private Boolean blue;
	public boolean dead = false, stopp = false;
	public double life = 10, dmgTkn;

	private BufferedImage[] rightBlueRun;
	private BufferedImage[] leftBlueRun;
	private BufferedImage[] rightBlueIdle;
	private BufferedImage[] leftBlueIdle;
	private BufferedImage[] rightBlueDie;
	private BufferedImage[] leftBlueDie;
	private BufferedImage[] rightBlueAtk;
	private BufferedImage[] leftBlueAtk;
	
	private BufferedImage[] rightBrownRun;
	private BufferedImage[] leftBrownRun;
	private BufferedImage[] rightBrownIdle;
	private BufferedImage[] leftBrownIdle;
	private BufferedImage[] rightBrownDie;
	private BufferedImage[] leftBrownDie;
	private BufferedImage[] rightBrownAtk;
	private BufferedImage[] leftBrownAtk;
	
//	private int maskx = 8, masky = 8, maskw = 5, maskh = 5;

	public Enemy(double x, double y, int width, int height, BufferedImage sprite, Boolean blue) {
		super(x, y, width, height, null);
		this.blue = blue;
		
		//Cogumelo Azul
		rightBlueIdle = new BufferedImage[6];
		rightBlueRun = new BufferedImage[7];
		rightBlueAtk = new BufferedImage[6];
		rightBlueDie = new BufferedImage[7];
		leftBlueIdle = new BufferedImage[6];
		leftBlueRun = new BufferedImage[7];
		leftBlueAtk = new BufferedImage[6];
		leftBlueDie = new BufferedImage[7];
		
		for(int i = 0; i < 6; i++) {
			rightBlueIdle[i] = Game.enemyOne.getSprite(0 + (i*16), 0, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			rightBlueRun[i] = Game.enemyOne.getSprite(0 + (i*16), 16, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			rightBlueAtk[i] = Game.enemyOne.getSprite(0 + (i*16), 32, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			rightBlueDie[i] = Game.enemyOne.getSprite(0 + (i*16), 48, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			leftBlueIdle[i] = Game.enemyOne.getSprite(0 + (i*16), 64, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			leftBlueRun[i] = Game.enemyOne.getSprite(0 + (i*16), 80, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			leftBlueAtk[i] = Game.enemyOne.getSprite(0 + (i*16), 96, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			leftBlueDie[i] = Game.enemyOne.getSprite(0 + (i*16), 112, 16, 16);
		}
		
		//Cogumelo Marrom
		rightBrownIdle = new BufferedImage[6];
		rightBrownRun = new BufferedImage[7];
		rightBrownAtk = new BufferedImage[6];
		rightBrownDie = new BufferedImage[7];
		leftBrownIdle = new BufferedImage[6];
		leftBrownRun = new BufferedImage[7];
		leftBrownAtk = new BufferedImage[6];
		leftBrownDie = new BufferedImage[7];
		
		for(int i = 0; i < 6; i++) {
			rightBrownIdle[i] = Game.enemyTwo.getSprite(0 + (i*16), 0, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			rightBrownRun[i] = Game.enemyTwo.getSprite(0 + (i*16), 16, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			rightBrownAtk[i] = Game.enemyTwo.getSprite(0 + (i*16), 32, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			rightBrownDie[i] = Game.enemyTwo.getSprite(0 + (i*16), 48, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			leftBrownIdle[i] = Game.enemyTwo.getSprite(0 + (i*16), 64, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			leftBrownRun[i] = Game.enemyTwo.getSprite(0 + (i*16), 80, 16, 16);
		}
		for(int i = 0; i < 6; i++) {
			leftBrownAtk[i] = Game.enemyTwo.getSprite(0 + (i*16), 96, 16, 16);
		}
		for(int i = 0; i < 7; i++) {
			leftBrownDie[i] = Game.enemyTwo.getSprite(0 + (i*16), 112, 16, 16);
		}		
		
	}
	
	public void tick() {
		
		if((!dead) && (Game.start) && (!Game.pause) && (!Game.dead)) {
			//Se o jogo for iniciado, estiver despausado e o jogador não estiver morto,
			//o inimigo corre até ele.
			framesRun++;
			if(framesRun == maxFramesRun) {
				framesRun = 0;
				run++;
				if(run > maxRun) {
					run = 0;
				}
			}
			
			if(isCollidingWithPlayer()) {//Se o inimigo estiver colidindo com o jogador, é 
				//realizada a animação de ataque.
				
				if(!dead) {
					framesAtk++;
					if(framesAtk == maxFramesAtk) {
						framesAtk = 0;
						atk++;
						if(atk == 3) {	//Ao realizar o terceiro frame do ataque, o jogador 
							//recebe dano aleatório entre 0 e 9.
							Game.player.setLife(-(Game.rand.nextInt(10) + 1));
							Game.player.setAtked(true);
						}
						if(atk > maxAtk) {
							atk = 0;
						}
					}else {
						if(Game.player.getLife() <= 0) {//Se a vida zerar, o jogo exibe a tela final e 
							//o jogador é morto.
							Game.dead = true;
							Game.player.setDead(true);
						}
						
					}
				}
				
			}
			
			if(!isCollidingWithPlayer() && !dead) {// Se o jogador não estiver colidindo com o inimigo, 
				//o inimigo irá se mover em sua direção.
				atk = 0;
				
				if(Game.rand.nextInt(100) < 20) { //A velocidade de movimento do inimigo se 
					//relaciona à chance de um número aleatório entre 0 e 99 ser menor que 20. 
					
					if(((int)x < Game.player.getX()) && (World.isFree((int)(this.getX()+speed), this.getY())) 
							&& !isColliding((int)(this.getX()+speed), this.getY())) {
						//Se o jogador estiver na direita, o inimigo se move para a direita.
						x+=speed;
						dir = right_dir;
						last = true;
					}else if(((int)x > Game.player.getX()) && (World.isFree((int)(this.getX()-speed), this.getY()))
							&& !isColliding((int)(this.getX()-speed), this.getY())) {
						//Se o jogador não estiver na direita, mas na esquerda, o inimigo se 
						//move para a esquerda.
						x-=speed;
						dir = left_dir;
						last = false;
					}
					
					if(((int)y < Game.player.getY()) && (World.isFree(this.getX(), (int)(this.getY()+speed)))
							&& !isColliding(this.getX(), (int)(this.getY()+speed))) {
						//Se o jogador estiver acima do inimigo, o mesmo vai para cima.
						y+=speed;
						dir = up_dir;
					}else if(((int)y > Game.player.getY()) && (World.isFree(this.getX(), (int)(this.getY()-speed)))
							&& !isColliding(this.getX(), (int)(this.getY()-speed)))	{
						//Se o jogador não estiver acima do inimigo, mas embaixo, o inimigo se 
						//move para baixo.
						y-=speed;
						dir = down_dir;
					}
					
				}
				
			}
			
		}else if (((!dead) && (Game.start) && (Game.pause) && (!Game.dead))||
				((!dead) && (!Game.start) && (!Game.pause) && (!Game.dead))||
				((!dead) && (Game.start) && (!Game.pause) && (Game.dead))){
			//Caso contrário, o inimigo ficará paradinho.
			if(!stopp) {
				framesIdle++;
				if(framesIdle == maxFramesIdle) {
					framesIdle = 0;
					idle++;
					if(idle > maxIdle) {
						idle = 0;
					}
				}
				
			}
			
		}else if(((dead) && (!stopp) && (Game.start) && (!Game.pause) && (!Game.dead)) ||
			((dead) && (!stopp) && (Game.start) && (Game.pause) && (!Game.dead))) {
			
			framesDie++;
			if(framesDie == maxFramesDie) {
				framesDie = 0;
				die++;
				if(Game.pause) {
					die--;
				}
				if(die > maxDie) {
					die = 6;
					stopp = true;
					Game.livingEntities.remove(this);
					if(blue) {
						Game.blueEnemies.remove(this);
					}else {
						Game.brownEnemies.remove(this);
					}
					Game.player.setEnemyCount(-1);
				}
			}
		}

		if((Game.player.getEnemyCount() == 0) && (Game.start)) {
			Game.win = true;
		}
		
	}
	
	public boolean isCollidingWithPlayer() {
		//Verifica se o inimigo está colidindo com o jogador.
		
		Rectangle enemyCurrent = new Rectangle(this.getX(), this.getY(), World.TILE_SIZE, World.TILE_SIZE);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 24, 24);
		if(!dead) {
			return enemyCurrent.intersects(player);
		}else {
			return !enemyCurrent.intersects(player);
		}
		
	}
	
	public boolean isColliding(int xNext, int yNext) {
		//Verifica se o inimigo está colidindo com o cenário ou com outros inimigos.
		
		Rectangle enemyCurrent = new Rectangle(xNext, yNext, World.TILE_SIZE, World.TILE_SIZE);
		
		for(int i = 0; i< Game.blueEnemies.size(); i++) {
			
			Enemy e = Game.blueEnemies.get(i);
			
			if(e == this && !dead) {
				
				continue;
				
			}
			
			Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), World.TILE_SIZE+1, World.TILE_SIZE+1);
			
			if(enemyCurrent.intersects(targetEnemy) && !dead) {
				
				return true;
				
			}
		}
		
		for(int i = 0; i< Game.brownEnemies.size(); i++) {
			
			Enemy e = Game.brownEnemies.get(i);
			
			if(e == this && !dead) {
				
				continue;
				
			}
			
			Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), World.TILE_SIZE+1, World.TILE_SIZE+1);
			
			if(enemyCurrent.intersects(targetEnemy) && !dead) {
				
				return true;
				
			}
		}
		
		return false;
	}
	
	public void render(Graphics g) {
		
		if((Game.start) && (!Game.pause) && (!Game.dead) && (!dead)) {
		
			if(!isCollidingWithPlayer()) { // Inimigo Correndo
			
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
				
			}else { // Inimigo atacando!
				
				if(blue) {
					
					if(last) {
						g.drawImage(rightBlueAtk[atk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
					else if(!last) {
						g.drawImage(leftBlueAtk[atk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
				
				}else {
					if(last) {
						g.drawImage(rightBrownAtk[atk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
					else if(!last) {
						g.drawImage(leftBrownAtk[atk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
				
				}
					
			}
			
		}else if (((Game.start) && (!Game.pause) && (!Game.dead) && (!dead)) || 
				((Game.start) && (Game.pause) && (!Game.dead) && (!dead)) ||
				((!Game.start) && (!Game.pause) && (!Game.dead) && (!dead)) ||
				((Game.start) && (!Game.pause) && (Game.dead) && (!dead))){ //Inimigo parado
			
			if(blue) {
				
				if(last) {
					g.drawImage(rightBlueIdle[idle], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				else if(!last) {
					g.drawImage(leftBlueIdle[idle], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
			
			}else {
				if(last) {
					g.drawImage(rightBrownIdle[idle], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
				else if(!last) {
					g.drawImage(leftBrownIdle[idle], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
			
			}
			
		} else if (((Game.start) && (!Game.pause) && (!Game.dead) && (dead)) ||
				((Game.start) && (Game.pause) && (!Game.dead) && (dead))) {
			if(blue) {
				
				if(last) {
					g.drawImage(rightBlueDie[die], this.getX() - Camera.x, this.getY() - Camera.y, null);
					if(die == 6) {
						g.drawImage(null, 0, 0, null);
					}
				}
				else if(!last) {
					g.drawImage(leftBlueDie[die], this.getX() - Camera.x, this.getY() - Camera.y, null);
					if(die == 6) {
						g.drawImage(null, 0, 0, null);
					}
				}
			
			}else {
				if(last) {
					g.drawImage(rightBrownDie[die], this.getX() - Camera.x, this.getY() - Camera.y, null);
					if(die == 6) {
						g.drawImage(null, 0, 0, null);
					}
				}
				else if(!last) {
					g.drawImage(leftBrownDie[die], this.getX() - Camera.x, this.getY() - Camera.y, null);
					if(die == 6) {
						g.drawImage(null, 0, 0, null);
					}
				}
			
			}
		}

//		super.render(g);
//		g.setColor(Color.blue);
//		g.fillRect(this.getX() - Camera.x, this.getY() - Camera.y, 16, 16);
		
		
	}

}
