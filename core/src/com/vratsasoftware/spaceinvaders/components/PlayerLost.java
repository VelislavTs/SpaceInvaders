package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.GameOverScreen;

public class PlayerLost extends ComponentsScreen {
	
	public PlayerLost(Ship ship ,boolean isPlayerAlieve  ,int explosionIndex,long timerForExpolosions,SpriteBatch batch){
		this.ship=ship;
		this.isPlayerAlieve=isPlayerAlieve;
		this.explosionIndex=explosionIndex;
		this.timerForAliensShot=timerForExpolosions;
		this.batch=batch;
		
	}
	ComponentsScreen cs= new ComponentsScreen();
	protected void checkIfPlayerLose() {
		System.out.println("EXp+"+cs.getExplosionIndex());
		if (ship.checkIfLose(ship.getLives())) {
			System.out.println("valzam");
			this.isPlayerAlieve = false;
		setPlayerAlieve(false);
			if (cs.getExplosionIndex()== 0) {
				ship.explosion(batch, -1, Gdx.graphics.getDeltaTime());
				cs.setTimerForExpolosions(System.currentTimeMillis());
				cs.riseExplosionIndex();
			}

			int start = (int) (getTimerForExpolosions() / 1000) % 60;
			int end = (int) (System.currentTimeMillis() / 1000) % 60;
			// System.out.println("START" + start);
			// System.out.println("END" + end);
			if (end - start == 1) {
				cs.riseExplosionIndex();
//				timerForExpolosions = System.currentTimeMillis();
				cs.setTimerForExpolosions(System.currentTimeMillis());
			} else {
				if ((60 - start) + end == 1 || (60 - start) + end == 0) {
					cs.riseExplosionIndex();
				//	timerForExpolosions = System.currentTimeMillis();
					cs.setTimerForExpolosions(System.currentTimeMillis());
				}

				if (cs.getExplosionIndex() == 5) {
					game.setScreen(new GameOverScreen(game, playerPoints, aliensKilled));
				} else {
					ship.explosion(batch, cs.getExplosionIndex(), Gdx.graphics.getDeltaTime());
				}

			}
		}
	}
}
