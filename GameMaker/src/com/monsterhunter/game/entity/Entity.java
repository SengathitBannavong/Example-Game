package com.monsterhunter.game.entity;

import com.monsterhunter.game.graphic.Animation;
import com.monsterhunter.game.graphic.Sprite;
import com.monsterhunter.game.util.AABB;
import com.monsterhunter.game.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected Animation ani;
    protected int currentAnimation;
    protected Vector2D origin;
    protected Sprite sprite;
    protected int size;

    protected boolean[] movement;

    protected int attackSpeed;
    protected int attackDuration;
    protected int attackTimer;

    protected float dx;
    protected float dy;

    protected float maxSpeed = 10f;
    protected float acc = 3f;
    protected float deacc = 3f;

    protected AABB hitBounds;
    protected AABB bounds;


    public Entity(Sprite sprite, Vector2D origin, int size) {
        this.sprite = sprite;
        this.origin = origin;
        this.size = size;


        bounds = new AABB(origin, size, size);
        hitBounds = new AABB(new Vector2D(origin.x + (size / 2), origin.y + (size / 2)), size, size);

        movement = new boolean[F_Movement.SIZE.ordinal()];

        ani = new Animation();
        setAnimation(F_Movement.RIGHT, sprite.getSpriteArray(F_Movement.RIGHT.ordinal()), 10);
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setMaxSpeed(float maxSpeed){
        this.maxSpeed = maxSpeed;
    }

    public void setAcc(float acc){
        this.acc = acc;
    }

    public void setDeacc(float deacc){
        this.deacc = deacc;
    }

    public AABB getBounds(){
        return bounds;
    }

    public AABB getHitBounds(){
        return hitBounds;
    }

    public int getSize() {
        return size;
    }

    public Animation getAnimation() {
        return ani;
    }

    public void setAnimation(F_Movement fMovement, BufferedImage[] spriteArray, int delay){
        currentAnimation = fMovement.ordinal();
        ani.setFrames(spriteArray);
        ani.setDelay(delay);
    }

    public void animate() {
        if (movement[F_Movement.UP.ordinal()]) { // UP
            if (currentAnimation != F_Movement.UP.ordinal() || ani.hasPlayedOnce()) {
                setAnimation(F_Movement.UP, sprite.getSpriteArray(F_Movement.UP.ordinal()), 5);
            }else{
                if(ani.getDelay() == -1){
                    ani.setDelay(5);
                }
            }
        }
        else if (movement[F_Movement.DOWN.ordinal()]) { // DOWN
            if (currentAnimation != F_Movement.DOWN.ordinal() || ani.hasPlayedOnce()) {
                setAnimation(F_Movement.DOWN, sprite.getSpriteArray(F_Movement.DOWN.ordinal()), 5);
            }else{
                if(ani.getDelay() == -1){
                    ani.setDelay(5);
                }
            }
        }
        else if (movement[F_Movement.LEFT.ordinal()]) { // LEFT
            if (currentAnimation != F_Movement.LEFT.ordinal() || ani.hasPlayedOnce()) {
                setAnimation(F_Movement.LEFT, sprite.getSpriteArray(F_Movement.LEFT.ordinal()), 5);
            }else{
                if(ani.getDelay() == -1){
                    ani.setDelay(5);
                }
            }
        }
        else if (movement[F_Movement.RIGHT.ordinal()]) { // RIGHT
            if (currentAnimation != F_Movement.RIGHT.ordinal() || ani.hasPlayedOnce()) {
                setAnimation(F_Movement.RIGHT, sprite.getSpriteArray(F_Movement.RIGHT.ordinal()), 5);
            }else{
                if(ani.getDelay() == -1){
                    ani.setDelay(5);
                }
            }
        }
        else {
            setDefaultAnimation();
        }
    }

    private void setDefaultAnimation() {
        ani.setFrames(sprite.getSpriteArray(currentAnimation));
        ani.setDelay(-1);
    }


    public void setHitBoxDirection(){
        if(movement[F_Movement.UP.ordinal()]) // UP
        {
            hitBounds.setYOffset((float) -size / 2);
            hitBounds.setXOffset((float) -size / 2);
        }
        else if(movement[F_Movement.DOWN.ordinal()]){ // DOWN
            hitBounds.setYOffset((float) size / 2);
            hitBounds.setXOffset((float) -size / 2);
        }
        else if(movement[F_Movement.LEFT.ordinal()]){ // LEFT
            hitBounds.setYOffset(0);
            hitBounds.setXOffset(-size);
        }
        else if(movement[F_Movement.RIGHT.ordinal()]){ // RIGHT
            hitBounds.setYOffset(0);
            hitBounds.setXOffset(0);
        }

    }

    public void update(){
        animate();
        setHitBoxDirection();
        ani.update();
    }

    public abstract void render(Graphics2D g);
}
