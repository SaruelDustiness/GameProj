package com.newbie.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.newbie.graficos.Spritesheet;
import com.newbie.main.GameExe;
import com.newbie.main.AtkFunc;
import com.newbie.main.Game;
import com.newbie.world.Camera;
import com.newbie.world.World;

public class Player extends Entity{
	
	public boolean right, up, left, down, atk;
	public double speed = 1.7;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	
	private int framesIdle = 0, framesRun = 0, framesAtk = 0, maxFramesAtk = 5, maxFramesIdle = 20, maxFramesRun = 4, index = 0, maxIndex = 7, index2 = 0, maxIndex2 = 1;
	private int framesDie = 0, maxFramesDie = 6, die = 0, maxDie = 3;
	private int dmgFrames = 0;
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
	private BufferedImage[] rightDmg;
	private BufferedImage[] leftDmg;
	private BufferedImage[] rightSword;
	private BufferedImage[] leftSword;
	private BufferedImage[] rightBuff;
	private BufferedImage[] leftBuff;
	
	private double life = 100, maxLife = 100;
	public int buff = 0;
	public int dano;
	public int danoCrit = 10;
	private int enemyCount = 0;
	public Entity enAt;
	private int entityCount = 0;
	public boolean atkDmg = false;
	public boolean critDmg = false;
	private boolean stopp = false;
	private boolean dead = false;
	private boolean atked = false;
	private boolean equip = false;
	private boolean isBuff = false;
	
	
	public boolean isEquip() {
		return equip;
	}
	
	public void setEquip(boolean equip) {
		this.equip = equip;
	}
	
	public boolean getAtked() {
		return atked;
	}
	
	public void setAtked(boolean atked) {
		this.atked = atked;
	}
	
	public int getEntityCount() {
		return entityCount;
	}

	public void setEntityCount(int entityCount) {
		this.entityCount += entityCount;
	}
	
	public Entity getEnAt() {
		return enAt;
	}

	public void setEnAt(Entity enAt) {
		this.enAt = enAt;
	}
	
	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		if(life == 0) {
			this.life = 0;
		}else {
			this.life += life;
		}
	}

	public double getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(double maxLife) {
		this.maxLife = maxLife;
	}

	public int getBuff() {
		return buff;
	}

	public void setBuff(int buff) {
		this.buff = buff;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public int getDanoCrit() {
		return danoCrit;
	}

	public void setDanoCrit(int danoCrit) {
		this.danoCrit = danoCrit;
	}

	public boolean isAtkDmg() {
		return atkDmg;
	}

	public void setAtkDmg(boolean atkDmg) {
		this.atkDmg = atkDmg;
	}

	public boolean isCritDmg() {
		return critDmg;
	}

	public void setCritDmg(boolean critDmg) {
		this.critDmg = critDmg;
	}

	public boolean isStopp() {
		return stopp;
	}

	public void setStopp(boolean stopp) {
		this.stopp = stopp;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[8];
		leftPlayer = new BufferedImage[8];
		sRightPlayer = new BufferedImage[2];
		sLeftPlayer = new BufferedImage[2];
		leftAtk = new BufferedImage[5];
		rightAtk = new BufferedImage[5];
		leftDead = new BufferedImage[4];
		rightDead = new BufferedImage[4];
		rightDmg = new BufferedImage[1];
		leftDmg = new BufferedImage[1];
		rightSword = new BufferedImage[5];
		leftSword = new BufferedImage[5];
		rightBuff = new BufferedImage[5];
		leftBuff = new BufferedImage[5];
		
		rightDmg[0] = Game.charAnim.getSprite(96, 0, 24, 24);
		leftDmg[0] = Game.charAnim.getSprite(120, 0, 24, 24);
		
		
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
		for(int i = 0; i < 5; i++) {
			rightSword[i] = Game.sword.getSprite(0 + (i*24), 24, 24, 24);
		}
		for(int i = 0; i < 5; i++) {
			leftSword[i] = Game.sword.getSprite(0 + (i*24), 0, 24, 24);
		}
		for(int i = 0; i < 5; i++) {
			rightBuff[i] = Game.buff.getSprite(0 + (i*24), 24, 24, 24);
		}
		for(int i = 0; i < 5; i++) {
			leftBuff[i] = Game.buff.getSprite(0 + (i*24), 0, 24, 24);
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
		
		if((right) && (World.isFree((int)(x+speed),this.getY())) && GameExe.isPlaying()) {
			moved = true;
			dir = right_dir;
			x+=speed;
		}
		else if((left) && (World.isFree((int)(x-speed),this.getY())) && (GameExe.isPlaying())) {
			moved = true;
			dir = left_dir;
			x-=speed;
		}
		if((up) && (World.isFree(this.getX(),(int)(y-speed))) && (GameExe.isPlaying())) {
			moved = true;
			dir = up_dir;
			y-=speed;
		}
		else if((down) && (World.isFree(this.getX(),(int)(y+speed))) && (GameExe.isPlaying())) {
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
		if(atk && !dead && equip) {
			framesAtk++;
			if(framesAtk == maxFramesAtk) {
				framesAtk = 0;
				iAtk++;
				if(iAtk == 2) {	//Ao realizar o terceiro frame do ataque, o jogador 
					AtkFunc.atack();
					System.out.println(isBuff);
				}
				if(iAtk > maxIAtk) {
					iAtk = 0;
					atkDmg = false;
					critDmg = false;
					atk = false;
					if(buff==0) {
						isBuff = false;
					}
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
		
		if(atked) {
			this.dmgFrames++;
			if(this.dmgFrames == 8) {
				this.dmgFrames = 0;
				atked = false;
			}
		}
		
		if(GameExe.getRestart()) {
			for(int i = Game.livingEntities.size()-1; i >= 0; i--) {
				Game.livingEntities.remove(i);
			}
			for(int i = Game.staticEntities.size()-1; i >= 0; i--) {
				Game.staticEntities.remove(i);
			}
			for(int i = Game.blueEnemies.size()-1; i >= 0; i--){
				Game.blueEnemies.remove(i);
			}
			for(int i = Game.brownEnemies.size()-1; i >= 0; i--){
				Game.brownEnemies.remove(i);
			}
			Game.livingEntities.remove(this);
			GameExe.setStart(false);
			GameExe.setPause(false);
			GameExe.setDead(false);
			GameExe.setWin(false);
			GameExe.setRestart(false);
			atkDmg = false;
			critDmg = false;
			stopp = false;
			dead = false;
			Game.objects = new Spritesheet("/objects.png");
			Game.enemyOne = new Spritesheet("/bluemush.png");
			Game.enemyTwo = new Spritesheet("/brownmush.png");
			Game.charAnim = new Spritesheet("/charAnim.png");
			Game.atkDie = new Spritesheet("/atkdie.png");
			Game.player = new Player(0, 0, 16, 16, Game.charAnim.getSprite(70, 0, 24, 24));
			Game.livingEntities.add(Game.player);
			Game.summerMap = new Spritesheet("/mapSummer.png");
			Game.inside = new Spritesheet("/inside.png");
			Game.items = new Spritesheet("/sumitems.png");
			Game.tileMap = new Spritesheet("/tilemap.png");
			Game.world = new World("/newMap2.png");
			return;
		}
		
		this.checkHealCollision();
		this.checkBuffCollision();
		this.checkWeaponCollision();
		
		//Camera
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
	}
	
	public void checkHealCollision() {
		
		for(int i = 0; i < Game.staticEntities.size(); i++) {
			Entity atual = Game.staticEntities.get(i);
			if(atual instanceof Heal) {
				if(Entity.isCollidingHeal(this, atual)){
					life+=10;
					
					if(life >= 100) {
						life = 100;
					}
					
					Game.staticEntities.remove(i);
				}
			}
		}
		
	}
	
	public void checkBuffCollision() {
		
		for(int i = 0; i < Game.staticEntities.size(); i++) {
			Entity atual = Game.staticEntities.get(i);
			if(atual instanceof Buff) {
				if(Entity.isCollidingBuff(this, atual)){
					buff+=5;
					isBuff = true;
					
					Game.staticEntities.remove(i);
				}
			}
		}
		
	}
	
	public void checkWeaponCollision() {
		
		for(int i = 0; i < Game.staticEntities.size(); i++) {
			Entity atual = Game.staticEntities.get(i);
			if(atual instanceof Weapon) {
				if(Entity.isCollidingBuff(this, atual)){
					equip = true;
					
					Game.staticEntities.remove(i);
				}
			}
		}
		
	}
	
	public void render(Graphics g) {
		
		if(dead && GameExe.isLast()) {
			g.drawImage(rightDead[die], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		else if(dead && !GameExe.isLast()) {
			g.drawImage(leftDead[die], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		if(!atked) {
			
			if((dir == right_dir) && (moved)) {
				g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if(atk && equip) {
					if(isBuff) {
						g.drawImage(rightBuff[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}else if(!isBuff){
						g.drawImage(rightSword[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
				}
			}
			else if((dir == left_dir) && (moved)) {
				g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if(atk && equip) {
					if(isBuff) {
						g.drawImage(leftBuff[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}else if(!isBuff){
						g.drawImage(leftSword[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
				}
			}
	
			if((dir == up_dir) && (GameExe.isLast() && moved)) {
				g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if(atk && equip) {
					if(isBuff) {
						g.drawImage(rightBuff[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}else if(!isBuff){
						g.drawImage(rightSword[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
				}
			}
			else if((dir == down_dir) && (!GameExe.isLast() && moved)) {
				g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if(atk && equip) {
					if(isBuff) {
						g.drawImage(leftBuff[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}else if(!isBuff){
						g.drawImage(leftSword[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
				}
			}
			
			if((dir == down_dir) && (GameExe.isLast() && moved)) {
				g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if(atk && equip) {
					if(isBuff) {
						g.drawImage(rightBuff[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}else if(!isBuff){
						g.drawImage(rightSword[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
				}
			}
			else if((dir == up_dir) && (!GameExe.isLast() && moved)) {
				g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if(atk && equip) {
					if(isBuff) {
						g.drawImage(leftBuff[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}else if(!isBuff){
						g.drawImage(leftSword[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
				}
			}
			
			if(GameExe.isLast() && !moved && !dead) {
				if(atk && equip) {
					g.drawImage(rightAtk[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					if(isBuff) {
						g.drawImage(rightBuff[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}else if(!isBuff){
						g.drawImage(rightSword[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
				}
				else {
					g.drawImage(sRightPlayer[index2], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
			}
			else if(!GameExe.isLast() && !moved && !dead) {
				if(atk && equip) {
					g.drawImage(leftAtk[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					if(isBuff) {
						g.drawImage(leftBuff[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}else if(!isBuff){
						g.drawImage(leftSword[iAtk], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
				}
				else {
					g.drawImage(sLeftPlayer[index2], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
			}
			
		}else {
			if(GameExe.isLast()) {
				g.drawImage(rightDmg[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else {
				g.drawImage(leftDmg[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
	}

	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount += enemyCount;
	}
	
}
