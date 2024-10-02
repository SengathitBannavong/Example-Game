package com.monsterhunter.game.tiles;

import com.monsterhunter.game.graphic.Sprite;
import com.monsterhunter.game.tiles.blocks.Block;
import com.monsterhunter.game.tiles.blocks.HoleBlock;
import com.monsterhunter.game.tiles.blocks.ObjBlock;
import com.monsterhunter.game.util.Vector2D;

import java.awt.Graphics2D;
import java.util.HashMap;


public class TileMapObj extends TileMap {

    public static HashMap<String, Block> tileMapObj;

    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        Block tempBlock;
        tileMapObj = new HashMap<String, Block>();

        String[] block = data.split(",");
        for (int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
            if (temp != 0) {
                if(temp == 172){
                    tempBlock =     new HoleBlock(sprite.getSprite((int)(temp - 1) % tileColumns, (int)(temp - 1) / tileColumns),
                                    new Vector2D((float) (i % width) * tileWidth,
                                    (float) (i / height) * tileHeight),tileWidth, tileHeight);
                }else{
                    tempBlock =     new ObjBlock(sprite.getSprite((int)(temp - 1) % tileColumns, (int)(temp - 1) / tileColumns),
                                    new Vector2D((float) (i % width) * tileWidth,
                                    (float) (i / height) * tileHeight),tileWidth, tileHeight);
                }
                tileMapObj.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int)(i / height)), tempBlock);
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        for (Block block : tileMapObj.values()) {
            block.render(g);
        }
    }
}
