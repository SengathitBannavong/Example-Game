package com.monsterhunter.game.util;

import com.monsterhunter.game.entity.Entity;

// this class is used to create a bounding box for collision detection
public class AABB {
    private Vector2D position;
    private float xOffset = 0;
    private float yOffset = 0;
    private float width;
    private float height;
    private float radius;
    private int size;

    private Entity entity;

    public AABB(Vector2D position, float xOffset, float yOffset, float width, float height) {
        this.position = position;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = width;
        this.height = height;

        size = (int) Math.max(width, height);
    }

    public AABB(Vector2D position, int radius, Entity etity) {
        this.position = position;
        this.radius = radius;
        this.entity = entity;
        size = radius;
    }

    public AABB(Vector2D position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;

        size = (int) Math.max(width, height);
    }

    public float getxOffset() {
        return xOffset;
    }

    public float getHeight() {
        return height;
    }

    public float getyOffset() {
        return yOffset;
    }

    public float getWidth() {
        return width;
    }

    public int getSize() {
        return size;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setBox(Vector2D position,int width, int height){
        this.position = position;
        this.width = width;
        this.height = height;

        size = (int) Math.max(width, height);
    }

    public void setCircle(Vector2D position, int radius){
        this.position = position;
        this.radius = radius;

        size = radius;
    }

    public void setWidth(float width){
        this.width = width;
    }

    public void setHeight(float height){
        this.height = height;
    }

    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    // this method checks if two bounding boxes are colliding
    public boolean collides(AABB bBox){
        float ax = ((position.getWorldVar().x + (xOffset)) + (width / 2));
        float ay = ((position.getWorldVar().y + (yOffset)) + (height / 2));
        float bx = ((bBox.position.getWorldVar().x + (bBox.xOffset / 2)) + (width /2));
        float by = ((bBox.position.getWorldVar().y + (bBox.yOffset / 2)) + (height /2));

        if(Math.abs(ax - bx) < (this.width /2) + (bBox.width / 2)){
            return Math.abs(ay - by) < (this.height / 2) + (bBox.height / 2);
        }
        return false;
    }

    // this method checks if a circle and a bounding box are colliding
    public boolean colCircleBox(AABB aBox){
        float cx = (float) (position.getWorldVar().x + radius / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));
        float cy = (float) (position.getWorldVar().y + radius / Math.sqrt(2) - entity.getSize() / Math.sqrt(2));

        float xDelta = cx - Math.max(aBox.position.getWorldVar().x + (aBox.getWidth() / 2), Math.min(cx, aBox.position.getWorldVar().x));
        float yDelta = cy - Math.max(aBox.position.getWorldVar().y + (aBox.getHeight() / 2), Math.min(cy, aBox.position.getWorldVar().y));

        return (xDelta * xDelta + yDelta * yDelta) < ((this.radius / Math.sqrt(2)) * (this.radius / Math.sqrt(2)));
    }

}
