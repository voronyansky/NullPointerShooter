package com.voronyansky.nullpointershooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrey on 08.06.2016.
 */
public class ShotManager {

    private final static int SHOT_Y_OFFSET = 90;
    private final static int SHOT_SPEED = 300;
    public static final float ENEMY_SHOT_Y_OFFSET = 400;
    private Texture shotTexture;
    private Texture enemyShotTexture;
    private List<AnimatedSprite> shots = new ArrayList<AnimatedSprite>();
    private List<AnimatedSprite> enemyShots = new ArrayList<AnimatedSprite>();
    private static final float MIN_TIME_BETWEEN_SHOTS = 0.5f;
    private float timeSinceLastShot = 0f;
    private Sound laser = Gdx.audio.newSound(Gdx.files.internal("shipShot.mp3"));

    public ShotManager(Texture texture, Texture enemyShotTexture)
    {
        this.shotTexture = texture;
        this.enemyShotTexture = enemyShotTexture;
    }

    public void firePlayerShot(int shipCenterLocation)
    {
        if(canShot())
        {
            Sprite shot = new Sprite(shotTexture);
            AnimatedSprite animatedShot = new AnimatedSprite(shot);
            animatedShot.setPosition(shipCenterLocation, SHOT_Y_OFFSET);
            animatedShot.setVelocity(new Vector2(0,SHOT_SPEED));
            shots.add(animatedShot);
            laser.play();
            timeSinceLastShot = 0f;
        }
    }

    public void fireEnemyShot(int enemyCenter)
    {
        Sprite shot = new Sprite(enemyShotTexture);
        AnimatedSprite animatedShot = new AnimatedSprite(shot);
        animatedShot.setPosition(enemyCenter, ENEMY_SHOT_Y_OFFSET);
        animatedShot.setVelocity(new Vector2(0,-SHOT_SPEED));
        enemyShots.add(animatedShot);
    }


    private boolean canShot()
    {
        return timeSinceLastShot > MIN_TIME_BETWEEN_SHOTS;
    }

    public void update()
    {
        Iterator<AnimatedSprite> iterator = shots.iterator();
        while(iterator.hasNext())
        {
            AnimatedSprite sprite = iterator.next();
            sprite.move();
            if(sprite.getY() > Gdx.graphics.getHeight())
                iterator.remove();
        }

        Iterator<AnimatedSprite> enemyIterator = enemyShots.iterator();
        while(enemyIterator.hasNext())
        {
            AnimatedSprite enemy = enemyIterator.next();
            enemy.move();
            if(enemy.getY() < 0)
                enemyIterator.remove();
        }
        timeSinceLastShot += Gdx.graphics.getDeltaTime();

    }

    public void draw(SpriteBatch batch)
    {
        for(AnimatedSprite shot : shots)
            shot.draw(batch);

        for(AnimatedSprite enemyShot : enemyShots)
            enemyShot.draw(batch);
    }



}
