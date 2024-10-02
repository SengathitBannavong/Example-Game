package com.monsterhunter.game.tiles.blocks;

import com.monsterhunter.game.util.AABB;
import com.monsterhunter.game.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HoleBlock extends Block {
    public HoleBlock(BufferedImage image, Vector2D position, int width, int height) {
        super(image, position, width, height);
    }

    @Override
    public boolean update(AABB p) {
        return false;
    }

    public void render(java.awt.Graphics2D g) {
        super.render(g);
        g.setColor(Color.GREEN);
        g.drawRect((int) position.getWorldVar().x, (int) position.getWorldVar().y, width, height);
    }
}
