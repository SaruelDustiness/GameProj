package com.newbie.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.newbie.entities.Enemy;
import com.newbie.entities.Player;
import com.newbie.main.Game;

public class Ui {
	
	public void render(Graphics g, Graphics s) {
		s.setColor(Color.white);
		s.setFont(new Font("arial", Font.BOLD, 16));
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 16));
		if(!Game.start) {
			
			g.drawString("Pressione Espaço para iniciar!", 60, 120);
			
		}else if((Game.start) && (Game.pause) && (!Game.dead)) {
			
			g.drawString("Jogo Pausado!", 114, 120);
			s.drawString("Pressione ESC para continuar!", 64, 140);
			
		}else if((Game.start) && (!Game.pause) && (!Game.dead)){
			
			g.drawString("", 0, 0);
			s.drawString("", 0, 0);
			
		}else if((Game.start) && (!Game.pause) && (Game.dead)) {
			
			g.drawString("E morreu!!1onze!", 114, 120);
			s.drawString("Pressione R para reiniciar ou ESC para sair!", 4, 140);
			
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
		g.setFont(new Font("arial", Font.BOLD, 12));
		g.drawString("Inimigos: "+Enemy.enemyCount, 270, 11);
		
	}

}
