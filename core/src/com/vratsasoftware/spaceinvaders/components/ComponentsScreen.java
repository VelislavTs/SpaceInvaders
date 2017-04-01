package com.vratsasoftware.spaceinvaders.components;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ComponentsScreen implements Screen {

	float currentShipXPosition;
	Ship ship;
	Laser laser;
	SpriteBatch batch;
	public ArrayList<Laser> lasersShot;
	Aliens alien;
	Wall wall;
	Background background;
	boolean superShot;
	boolean areAliensGoingRight = true;

	public boolean areAliensGoingRight() {
		return areAliensGoingRight;
	}

	public void setAreAliensGoingRight(boolean areAliensGoingRight) {
		this.areAliensGoingRight = areAliensGoingRight;
	}

	@Override
	public void show() {
		ship = new Ship();
		batch = new SpriteBatch();
		laser = new Laser(ship.getPlayerX());
		System.out.println(currentShipXPosition);
		lasersShot = new ArrayList<Laser>();
		alien = new Aliens();
		alien.createNewAliens();

		wall = new Wall();
		background = new Background();
		superShot = true;
		areAliensGoingRight = true;
	}

	@Override
	public void render(float delta) {
		// System.out.println(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(this.batch);

	}

	private void update(SpriteBatch batch2) {
		batch.begin();
		background.showBackground(batch);
		batch.draw(ship.getShipTexture(), ship.getPlayerX(), ship.getPlayerY(), 50, 50);
		laser.shootNewLaser(this.lasersShot, currentShipXPosition, this.ship);
		// System.out.println(superShot);
		superShot();
		laser.displayLasersShot(this.lasersShot, this.batch);
		// alien.testShow(this.batch);
		// alien.showX();
		alien.showAliens(this.batch);
		wall.display(batch);
		currentShipXPosition = ship.getPlayerX();
		checkForCollision();
		ship.update(Gdx.graphics.getDeltaTime());
		batch.end();

	}

	private void superShot() {
		if (this.superShot) {
			if (laser.shootSuperLaser(this.lasersShot, currentShipXPosition, this.ship)) {
				//this.superShot = false;
			}

		}
	}

	protected int checkTheLaserCoordinatesY(ArrayList<Laser> lasersShot, SpriteBatch batch, int x) {
		if (lasersShot.size() > 0) {
			return lasersShot.get(x).getLaserY();
		}
		return 0;
	}

	protected int checkTheLaserCoordinatesX(ArrayList<Laser> lasersShot, SpriteBatch batch, int x) {
		if (lasersShot.size() > 0) {
			return lasersShot.get(x).getLaserX();
		}
		return 0;

	}

	private boolean checkForCollision() {
		int laserX = 0;
		int laserY = 0;
		// System.out.println(lasersShot.size());
		boolean killed = false;
		if (lasersShot.size() > 0) {
			for (int x = 0; x < lasersShot.size(); x++) {
				System.out.println("X: " + x);
				laserX = checkTheLaserCoordinatesX(lasersShot, batch, x);
				laserY = checkTheLaserCoordinatesY(lasersShot, batch, x);
				// System.out.println(x+" "+laserY );
				int index = 0;
				for (int i = 0; i < alien.aliensCoordinatesX.length; i++) {
					for (int j = 0; j < alien.aliensCoordinatesX[0].length; j++) {

						// System.out.printf("Alien at coordinates [%d][%d] =
						// [%d]
						// \n", i, j,
						// alien.getAliensCoordinatesY(i, j));
						// System.out.println("Laser coordinates Y: " + laserY);
						int alienX = alien.getAliensCoordinatesX(i, j);
						int alienY = alien.getAliensCoordinatesY(i, j);
						if(i == 0) { 
							index = 10;
						} else { 
							index = 30;
						}
						for (int alienSize = 5; alienSize <= alien.getAlien().getHeight() / 10; alienSize++) {
							if ((laserY == (alienY - alien.getAlien().getHeight() + 100 + alienSize) ) && (laserX >= alienX - index)
									&& (laserX <= (alienX + index)) && alien.isAlienAlive(i, j)) {
								alien.killAlien(i, j);
								killed = true;
								if (lasersShot.size() == 1) {
									lasersShot.clear();
								} else {
									
									lasersShot.remove(x);

								}
							}
						}
						if (killed) {
							killed = false;
							break;
						}
					}
				}
			}
		}
		return false;

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

	public SpriteBatch getBatch() {
		return batch;
	}
}