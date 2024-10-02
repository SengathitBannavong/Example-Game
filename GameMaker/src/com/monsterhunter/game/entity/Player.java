package com.monsterhunter.game.entity;

import com.monsterhunter.game.graphic.Sprite;
import com.monsterhunter.game.util.KeyHandler;
import com.monsterhunter.game.util.MouseHandler;
import com.monsterhunter.game.util.Vector2D;

import java.awt.Graphics2D;

public class Player extends Entity{

    
    public Player(Sprite sprite, Vector2D origin, int size) {
        super(sprite, origin, size);
    }

    public void movement(){
        if(movement[F_Movement.UP.ordinal()]){
            dy -= acc;
            if(dy < -maxSpeed){
                dy = -maxSpeed;
            }
        }else{
            if(dy < 0){
                dy += deacc;
                if(dy > 0){
                    dy = 0;
                }
            }
        }

        if(movement[F_Movement.DOWN.ordinal()]) {
            dy += acc;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        }else{
            if(dy > 0){
                dy -= deacc;
                if(dy < 0){
                    dy = 0;
                }
            }
        }

        if(movement[F_Movement.LEFT.ordinal()]){
            dx -= acc;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }else{
            if(dx < 0){
                dx += deacc;
                if(dx > 0){
                    dx = 0;
                }
            }
        }

        if(movement[F_Movement.RIGHT.ordinal()]) {
            dx += acc;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        }else{
            if(dx > 0){
                dx -= deacc;
                if(dx < 0){
                    dx = 0;
                }
            }
        }
    }

    public void update() {
        super.update();
        movement();
        Vector2D set = new Vector2D();
        set.setVector(dx, dy);
        set = set.normalize().multiply(acc);
        origin = origin.add(set);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(ani.getImage(), (int) (origin.x), (int) (origin.y), size, size, null);
    }

    public void input(KeyHandler key, MouseHandler mouse) {

        if(mouse.getButtom() == 1){
            System.out.println("Player:" + origin.x + " " + origin.y);
        }

        if(key.up.down){
            movement[F_Movement.UP.ordinal()] = true;
        }else{
            movement[F_Movement.UP.ordinal()] = false;
        }

        if(key.down.down){
            movement[F_Movement.DOWN.ordinal()] = true;
        }else{
            movement[F_Movement.DOWN.ordinal()] = false;
        }

        if(key.left.down) {
            movement[F_Movement.LEFT.ordinal()] = true;
        }else{
            movement[F_Movement.LEFT.ordinal()] = false;
        }

        if(key.right.down) {
            movement[F_Movement.RIGHT.ordinal()] = true;
        }else{
            movement[F_Movement.RIGHT.ordinal()] = false;
        }

        if(key.attack.down){
            movement[F_Movement.ATTACK.ordinal()] = true;
        }else{
            movement[F_Movement.ATTACK.ordinal()] = false;
        }
    }
}
