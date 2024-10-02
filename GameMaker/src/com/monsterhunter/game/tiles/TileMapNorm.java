package com.monsterhunter.game.tiles;

import com.monsterhunter.game.graphic.Sprite;
import com.monsterhunter.game.tiles.blocks.Block;
import com.monsterhunter.game.tiles.blocks.NormBlock;
import com.monsterhunter.game.util.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class TileMapNorm extends TileMap {

    private ArrayList<Block> blocks;

    public TileMapNorm(String data, Sprite sprite,int width,int height,int tileWidth,int tileHeight,int tileColumns) {
        blocks = new ArrayList<Block>();
        String[] block = data.split(",");
        for(int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
            if(temp != 0) {
                blocks.add(new NormBlock(sprite.getSprite((int)(temp - 1) % tileColumns, (int)(temp-1) / tileColumns),new Vector2D((float) (i % width) * tileWidth,(float) (i / height) * tileHeight),tileWidth, tileHeight));
            }
        }

    }

    @Override
    public void render(Graphics2D g) {
        for(int i = 0; i < blocks.size(); i++) {
            blocks.get(i).render(g);
        }
    }
}
