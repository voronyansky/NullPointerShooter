package com.voronyansky.nullpointershooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Write docs
 */
public class NullPointerShooter extends ApplicationAdapter {
	SpriteBatch batch;
	private OrthographicCamera camera;
	private Texture background;
	private Sprite spaceshipSprite;

	/**
	 * Write docs
	 */
	@Override
	public void create () {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);

		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("space-background.png"));
		Texture spaceshipTexture = new Texture(Gdx.files.internal("spaceship.png"));
		spaceshipSprite = new Sprite(spaceshipTexture);
		spaceshipSprite.setPosition((1024 - spaceshipTexture.getHeight()) / 2, 0);

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
		spaceshipSprite.draw(batch);
		batch.end();
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
