package com.voronyansky.nullpointershooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Write docs
 */
public class NullPointerShooter extends ApplicationAdapter {
	SpriteBatch batch;
	private OrthographicCamera camera;
	private Texture background;
	private AnimatedSprite spaceship;
	private ShotManager shotManager;
	private Music backgroundMusic;
	private Enemy enemy;

	/**
	 * Write docs
	 */
	@Override
	public void create () {

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);

		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("space-background.png"));

		Texture spaceShipTexture = new Texture(Gdx.files.internal("animated-spaceship.png"));
		Sprite spaceShipSprite = new Sprite(spaceShipTexture);
		spaceship = new AnimatedSprite(spaceShipSprite);
		spaceship.setPosition(width/2, 0);

		Texture shotTexture = new Texture(Gdx.files.internal("shot.png"));
		Texture enemyShotTexture = new Texture(Gdx.files.internal("shot.png"));
		shotManager = new ShotManager(shotTexture, enemyShotTexture);
		Texture enemyTexture = new Texture(Gdx.files.internal("animated-spaceship.png"));
		enemy = new Enemy(enemyTexture, shotManager);

		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
		backgroundMusic.setVolume(1.5f);
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
	}

	/**
	 * Write docs
	 */
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background,0,0);
		spaceship.draw(batch);
		shotManager.draw(batch);
		enemy.draw(batch);
		batch.end();

		handleInput();

		spaceship.move();
		enemy.update();
		shotManager.update();


	}

	private void handleInput()
	{
		Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touch);

		if(Gdx.input.isTouched())
		{
			if(touch.x > spaceship.getX())
			{
				spaceship.moveRight();
			}
			else
			{
				spaceship.moveLeft();
			}

			shotManager.firePlayerShot(spaceship.getX());
			shotManager.fireEnemyShot(enemy.getX());
		}


	}


	/**
	 * Write docs
	 */
	@Override
	public void dispose()
	{
		batch.dispose();
	}
}
