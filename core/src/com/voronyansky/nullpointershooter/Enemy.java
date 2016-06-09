package com.voronyansky.nullpointershooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Andrey on 08.06.2016.
 */
public class Enemy {

    private Texture texture;
    private AnimatedSprite animatedSprite;
    private static final Random RN = new Random();
    private static final int ENEMY_SPEED = 250;
    private ShotManager shotManager;

    public Enemy(Texture texture, ShotManager shotManager)
    {
        this.texture = texture;
        this.shotManager = shotManager;
        spawn();
    }

    private void spawn() {
        Sprite enemySprite = new Sprite(texture);
        animatedSprite = new AnimatedSprite(enemySprite);
        int xPosition = createRandomPosition();
        animatedSprite.setPosition(xPosition, Gdx.graphics.getHeight() - animatedSprite.getSpriteHeight());
        animatedSprite.setVelocity(new Vector2(ENEMY_SPEED,0));
    }

    private int createRandomPosition() {
        return RN.nextInt((int)(Gdx.graphics.getWidth() - animatedSprite.getSpriteWidth())) + (int)animatedSprite.getSpriteWidth() / 2;
    }

    public void draw(SpriteBatch batch)
    {
        animatedSprite.draw(batch);
    }

    public void update()
    {
        if(shouldChangeDirection())
            animatedSprite.changeVelocity();

        if(shouldFire())
            shotManager.fireEnemyShot(animatedSprite.getX());
        animatedSprite.move();
    }

    public int getX()
    {
        return animatedSprite.getX();
    }

    private boolean shouldFire() {
        return RN.nextInt(Integer.MAX_VALUE) == 0;
    }

    private boolean shouldChangeDirection()
    {
        return RN.nextInt(51) == 0;
    }

}
