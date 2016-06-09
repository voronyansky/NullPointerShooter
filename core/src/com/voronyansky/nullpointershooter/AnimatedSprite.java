package com.voronyansky.nullpointershooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Andrey on 07.06.2016.
 */
public class AnimatedSprite {

    private static final int FRAMES_ROW = 2;
    private static final int FRAMES_COL = 2;
    private static final int SPACESHIP_SPEED = 300;

    private Sprite sprite;
    private Animation animation;
    private TextureRegion[] frames;
    private TextureRegion currentFrame;
    private float stateTime;
    private Vector2 velocity;


    public AnimatedSprite(Sprite sprite)
    {
        this.sprite = sprite;
        Texture texture = sprite.getTexture();
        TextureRegion[][] temp = TextureRegion.split(texture, (int)getSpriteWidth() ,  texture.getHeight() / FRAMES_ROW );
        frames = new TextureRegion[FRAMES_ROW * FRAMES_COL];

        int index = 0;

        for(int i = 0; i<FRAMES_ROW; i++)
        {
            for(int j = 0; j < FRAMES_COL; j++)
            {
                frames[index++] = temp[i][j];
            }
        }

        velocity = new Vector2(0,0);
        animation = new Animation(0.5f, frames);
        stateTime = 0f;

    }

    public void draw(SpriteBatch batch)
    {
       stateTime += Gdx.graphics.getDeltaTime();
       currentFrame =  animation.getKeyFrame(stateTime,true);

       batch.draw(currentFrame, sprite.getX(), sprite.getY());
    }

    public void setPosition(float x, float y)
    {
        sprite.setPosition(x - getSpriteCenterOffset() / 2, y);
    }

    public float getSpriteWidth()
    {
        return (int) sprite.getWidth() / FRAMES_COL;
    }
    public int getSpriteHeight() {return (int) sprite.getHeight() / FRAMES_ROW; }

    private float getSpriteCenterOffset()
    {
        return getSpriteWidth()/2;
    }

    public void moveRight()
    {
        velocity = new Vector2(SPACESHIP_SPEED,0);
    }
    public void moveLeft()
    {
        velocity = new Vector2(-SPACESHIP_SPEED, 0);
    }
    public int getX()
    {
        return (int)(sprite.getX() + getSpriteCenterOffset());
    }
    public int getY() {return (int)(sprite.getY());}

    public void move()
    {
        float width = Gdx.graphics.getWidth();
        int moveX = (int)(velocity.x * Gdx.graphics.getDeltaTime());
        int moveY = (int)(velocity.y * Gdx.graphics.getDeltaTime());
        sprite.setPosition(sprite.getX() + moveX, sprite.getY() + moveY);

        if(sprite.getX() < 0)
        {
            sprite.setX(0);
            velocity.x = 0;
        }

        if(sprite.getX() + getSpriteWidth() > width)
        {
            sprite.setX(width - getSpriteWidth());
            velocity.x = 0;
        }

    }

    public void setVelocity(Vector2 vector)
    {
        this.velocity = vector;
    }

    public void changeVelocity()
    {
        this.velocity.x = -this.velocity.x;
    }






}
