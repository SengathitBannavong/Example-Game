package com.monsterhunter.game.tiles.blocks;

import com.monsterhunter.game.util.AABB;
import com.monsterhunter.game.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {
    protected int width;
    protected int height;

    protected BufferedImage image;
    protected Vector2D position;

    public Block(BufferedImage image, Vector2D position, int width, int height) {
        this.image = image;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public abstract boolean update(AABB p);

    public void render(Graphics2D g){
        g.drawImage(image, (int) position.x, (int) position.y, width, height, null);
    }
}
