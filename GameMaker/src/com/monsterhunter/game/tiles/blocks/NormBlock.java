package com.monsterhunter.game.tiles.blocks;

import com.monsterhunter.game.util.AABB;
import com.monsterhunter.game.util.Vector2D;

import java.awt.image.BufferedImage;

public class NormBlock extends Block {

    public NormBlock(BufferedImage image, Vector2D position, int width, int height) {
        super(image, position, width, height);
    }

    @Override
    public boolean update(AABB p) {
        return false;
    }

    public void render(java.awt.Graphics2D g) {
        super.render(g);
    }
}
