package com.newbie.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.newbie.entities.Enemy;
import com.newbie.entities.Entity;
import com.newbie.entities.Player;
import com.newbie.main.Game;
import com.newbie.world.Camera;

public class Ui {
	
	public void render(Graphics g, Graphics s) {
		s.setColor(Color.white);
		s.setFont(new Font("arial", Font.BOLD, 14));
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 14));
		if(!Game.start) {
			
			g.drawString("Pressione Espaço para iniciar!", 62, 124);
			
		}else if((Game.start) && (Game.pause) && (!Game.dead) && (!Game.win)) {
			
			g.drawString("Jogo Pausado!", 119, 114);
			s.drawString("Pressione ESC para continuar!", 61, 138);
			
		}else if((Game.start) && (!Game.pause) && (!Game.dead) && (!Game.win)){
			
			g.drawString("", 0, 0);
			s.drawString("", 0, 0);
			
		}else if((Game.start) && (!Game.pause) && (Game.dead) && (!Game.win)) {
			
			g.drawString("E morreu!!1onze!", 111, 114);
			s.drawString("Pressione R para reiniciar ou ESC para sair!", 20, 138);
			
		}else if((Game.start) && (!Game.pause) && (!Game.dead) && (Game.win)) {

			g.drawString("Parabéns! Você ganhou!", 90, 114);
			s.drawString("Pressione R para reiniciar ou ESC para sair!", 20, 138);
			
		}
		
		
		s.setColor(Color.cyan); // crit
		s.setFont(new Font("arial", Font.BOLD, 16));
		g.setColor(Color.red); // dano
		g.setFont(new Font("arial", Font.BOLD, 16));
		if(Player.atkDmg && !Player.critDmg) {
			Entity atual = Player.enAt;
			if(atual instanceof Enemy) {
				g.drawString((int)((Enemy) atual).dmgTkn+"", ((Enemy) atual).getX() - Camera.x, (((Enemy) atual).getY() - Camera.y) - 6);
			}
		}else if(Player.critDmg && !Player.atkDmg) {
			Entity atual = Player.enAt;
			if(atual instanceof Enemy) {
				s.drawString((int)((Enemy) atual).dmgTkn+"", ((Enemy) atual).getX() - Camera.x, (((Enemy) atual).getY() - Camera.y) - 6);
			}
		}else if(!Player.atkDmg && !Player.critDmg) {
			g.drawString("", 0, 0);
			s.drawString("", 0, 0);
		}
		
		g.setColor(Color.red);
		g.fillRect(8, 4, 50, 8);
		
		g.setColor(Color.green);
		g.fillRect(8, 4, (int)((Player.life/Player.maxLife)*50), 8);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 8));
		if(Player.life <= 0) {
			Player.life = 0;
			g.drawString((int)Player.life+"/"+(int)Player.maxLife, 60, 11);
		}else {
			g.drawString((int)Player.life+"/"+(int)Player.maxLife, 60, 11);
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("Inimigos: "+Enemy.enemyCount, 278, 11);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("Crit 100%: "+Player.buff, 278, 236);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("FPS: "+Game.frames, 8, 236);
		
	}

}
