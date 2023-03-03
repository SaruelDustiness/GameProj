package com.newbie.main;

import com.newbie.entities.Enemy;
import com.newbie.entities.Entity;

public class AtkFunc {
	
	public static void atack() {
		for(int i = 0; i < Game.livingEntities.size(); i++) {
			Entity atual = Game.livingEntities.get(i);
			if(atual instanceof Enemy) {
				if(((Enemy) atual).isCollidingWithPlayer()){
					Game.player.enAt = atual;
					Game.player.dano = Game.rand.nextInt(7)+1;
					int chance = Game.rand.nextInt(100)+1;
					if((chance <= 10) && (Game.player.buff == 0)){
						((Enemy) atual).life += -Game.player.danoCrit;
						((Enemy) atual).dmgTkn = Game.player.danoCrit;
						Game.player.critDmg = true;
						System.out.println(((Enemy) atual).dmgTkn);
						System.out.println("Inimigo "+i+": "+((Enemy) atual).life);
					}else if((chance > 10) && (Game.player.buff == 0)){
						((Enemy) atual).life += -Game.player.dano;
						((Enemy) atual).dmgTkn = Game.player.dano;
						Game.player.atkDmg = true;
						System.out.println("Inimigo "+i+": "+((Enemy) atual).life);
					}else if((Game.player.buff > 0)) {
						((Enemy) atual).life += -Game.player.danoCrit;
						((Enemy) atual).dmgTkn = Game.player.danoCrit;
						Game.player.buff+= -1;
						Game.player.critDmg = true;
						System.out.println("Inimigo "+i+": "+((Enemy) atual).life);
					}
					if(((Enemy) atual).life <= 0) {
						((Enemy) atual).dead = true;
					}
				}
			}
		}
	}
	
}
