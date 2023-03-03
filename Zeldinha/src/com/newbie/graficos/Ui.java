package com.newbie.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.newbie.entities.Enemy;
import com.newbie.entities.Entity;
import com.newbie.main.Game;
import com.newbie.main.GameExe;
import com.newbie.world.Camera;

public class Ui {
	
	public void render(Graphics g, Graphics s) {
		s.setColor(Color.white);
		s.setFont(new Font("arial", Font.BOLD, 14));
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 14));
		if(GameExe.yetStart()) {
			
			g.drawString("Pressione Espaço para iniciar!", 62, 124);
			
		}else if(GameExe.isPaused()) {
			
			g.drawString("Jogo Pausado!", 119, 114);
			s.drawString("Pressione ESC para continuar!", 61, 138);
			
		}else if(GameExe.isPlaying()){
			
			g.drawString("", 0, 0);
			s.drawString("", 0, 0);
			
		}else if(GameExe.theyDie()) {
			
			g.drawString("E morreu!!1onze!", 111, 114);
			s.drawString("Pressione R para reiniciar ou ESC para sair!", 20, 138);
			
		}else if(GameExe.theyWin()) {

			g.drawString("Parabéns! Você ganhou!", 90, 114);
			s.drawString("Pressione R para reiniciar ou ESC para sair!", 20, 138);
			
		}
		
		
		s.setColor(Color.cyan); // crit
		s.setFont(new Font("arial", Font.BOLD, 16));
		g.setColor(Color.red); // dano
		g.setFont(new Font("arial", Font.BOLD, 16));
		if(Game.player.isAtkDmg() && !Game.player.isCritDmg()) {
			Entity atual = Game.player.getEnAt();
			if(atual instanceof Enemy) {
				g.drawString((int)((Enemy) atual).dmgTkn+"", ((Enemy) atual).getX() - Camera.x, (((Enemy) atual).getY() - Camera.y) - 6);
			}
		}else if(Game.player.isCritDmg() && !Game.player.isAtkDmg()) {
			Entity atual = Game.player.getEnAt();
			if(atual instanceof Enemy) {
				s.drawString((int)((Enemy) atual).dmgTkn+"", ((Enemy) atual).getX() - Camera.x, (((Enemy) atual).getY() - Camera.y) - 6);
			}
		}else if(!Game.player.isAtkDmg() && !Game.player.isCritDmg()) {
			g.drawString("", 0, 0);
			s.drawString("", 0, 0);
		}
		
		g.setColor(Color.red);
		for(int i = 0; i < Game.livingEntities.size(); i++) {
			Entity atual = Game.livingEntities.get(i);
			if(atual instanceof Enemy) {
				g.fillRect(((Enemy) atual).getX() - Camera.x, (((Enemy) atual).getY() - Camera.y) - 6, 16, 2);
			}
		}
		
		g.setColor(Color.green);
		for(int i = 0; i < Game.livingEntities.size(); i++) {
			Entity atual = Game.livingEntities.get(i);
			if(atual instanceof Enemy) {
				g.fillRect(((Enemy) atual).getX() - Camera.x, (((Enemy) atual).getY() - Camera.y) - 6, (int) ((((Enemy) atual).getLife()/((Enemy) atual).getMaxLife())*16), 2);
			}
		}
		
//		g.setColor(Color.red);
//		g.fillRect((Game.player.getX() - Camera.x), (Game.player.getY() - Camera.y) - 6, 24, 3);
//		
//		g.setColor(Color.green);
//		g.fillRect((Game.player.getX() - Camera.x), (Game.player.getY() - Camera.y) - 6, (int)((Game.player.getLife()/Game.player.getMaxLife())*24), 3);

		g.setColor(Color.red);
		g.fillRect(8, 4, 50, 8);
		
		g.setColor(Color.green);
		g.fillRect(8, 4, (int)((Game.player.getLife()/Game.player.getMaxLife())*50), 8);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 10));
		if(GameExe.getRestart()) {
			g.drawString("", 60, 11);
		}else if(Game.player.getLife() <= 0) {
			Game.player.setLife(0);
			g.drawString((int)Game.player.getLife()+" / "+(int)Game.player.getMaxLife(), 60, 11);
		}else {
			g.drawString((int)Game.player.getLife()+" / "+(int)Game.player.getMaxLife(), 60, 11);
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("Inimigos: "+(int)(Game.blueEnemies.size()+Game.brownEnemies.size()), 278, 11);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("Crit 100%: "+Game.player.getBuff(), 278, 236);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("FPS: "+Game.frames, 8, 236);
		
	}

}
