package com.monsterhunter.game.graphic;

import com.monsterhunter.game.util.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Sprite {

    private BufferedImage SPRITE_SHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int width;
    public int height;
    private int wSprite;
    private int hSprite;

    private String source;

    // Constructor for default TILE_SIZE
    public Sprite(String file) {
        source = file;
        this.width = TILE_SIZE;
        this.height = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        SPRITE_SHEET = loadSprite(file);

        if (SPRITE_SHEET != null) {
            this.wSprite = SPRITE_SHEET.getWidth() / width;
            this.hSprite = SPRITE_SHEET.getHeight() / height;
            loadSpriteArray(); // Only load if sprite sheet is valid
        } else {
            System.err.println("Error: Sprite sheet could not be loaded.");
        }
    }

    // Constructor for custom sprite sizes
    public Sprite(String file, int wSprite, int hSprite) {
        source = file;
        this.width = wSprite;
        this.height = hSprite;

        System.out.println("Loading: " + file + "...");
        SPRITE_SHEET = loadSprite(file);

        if (SPRITE_SHEET != null) {
            this.wSprite = SPRITE_SHEET.getWidth() / width;
            this.hSprite = SPRITE_SHEET.getHeight() / height;
            System.out.println(hSprite + " " + wSprite + "why");
            System.out.println(this.hSprite + " " + this.wSprite + "this why");
            loadSpriteArray(); // Only load if sprite sheet is valid
        } else {
            System.err.println("Error: Sprite sheet could not be loaded.");
        }
    }

    // Set size and reload sprite array
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        if (SPRITE_SHEET != null) {
            wSprite = SPRITE_SHEET.getWidth() / width;
            hSprite = SPRITE_SHEET.getHeight() / height;
            loadSpriteArray();
        } else {
            System.err.println("Error: Sprite sheet is not loaded.");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Load sprite sheet from file
    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(file)) {
            if (is == null) {
                throw new IOException("Resource not found: " + file);
            }
            sprite = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("ERROR: Could not load file: " + file);
            e.printStackTrace();
        }
        return sprite;
    }

    // Load sprite array (ensure sprite sheet is valid)
    private void loadSpriteArray() {
        if (SPRITE_SHEET == null) {
            System.err.println("Error: Sprite sheet is null. Cannot load sprite array.");
            return;
        }
        System.out.println(SPRITE_SHEET.getHeight() + " " + SPRITE_SHEET.getWidth() + " original will do");
        System.out.println(hSprite + " " + wSprite + " original");
        System.out.println(height*hSprite + " " + width*wSprite + " original should");
        spriteArray = new BufferedImage[hSprite][wSprite];
        for (int y = 0; y < hSprite; y++) {
            for (int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
        System.out.println("Sprite array loaded successfully by "+ source);
    }

    // Get individual sprite from sheet
    public BufferedImage getSpriteSheet() {
        return SPRITE_SHEET;
    }

   public BufferedImage getSprite(int x, int y) {
    if (SPRITE_SHEET == null) {
        System.err.println("Error: Sprite sheet is not loaded.");
        return null;
    }
    if (x < 0 || x >= wSprite || y < 0 || y >= hSprite) {
        System.err.println("Error: Sprite coordinates out of bounds.");
        return null;
    }
    return SPRITE_SHEET.getSubimage(x * width, y * height, width, height);
}

    public BufferedImage[] getSpriteArray(int i) {
        return spriteArray[i];
    }

    public BufferedImage[][] getSpriteArray2() {
        return spriteArray;
    }

    // Draw an array of images
    public static void drawArray(Graphics2D g, ArrayList<Image> img, Vector2D pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;
        for (int i = 0; i < img.size(); i++) {
            if (img.get(i) != null) {
                g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
            }
            x += xOffset;
            y += yOffset;
        }
    }

    // Draw a string of characters using a font and offset positions
    public static void drawArray(Graphics2D g, Font f, String word, Vector2D pos, int width, int height, int xOffset, int yOffset) {
        final char SPACE_CHAR = 32;
        float x = pos.x;
        float y = pos.y;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c != SPACE_CHAR) {
                g.drawImage(f.getFont(c), (int) x, (int) y, width, height, null);
            }
            x += xOffset;
            y += yOffset;
        }
    }
}
