package com.monsterhunter.game.state;

import com.monsterhunter.game.GamePanel;
import com.monsterhunter.game.entity.Player;
import com.monsterhunter.game.graphic.Font;
import com.monsterhunter.game.graphic.Sprite;
import com.monsterhunter.game.tiles.TileManager;
import com.monsterhunter.game.util.KeyHandler;
import com.monsterhunter.game.util.MouseHandler;
import com.monsterhunter.game.util.Vector2D;

import java.awt.Graphics2D;

public class PlayState extends GameState {

    private Font font;
    private Player player;
    private TileManager tileManager;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        tileManager = new TileManager("tile/tilemap.xml");
        font = new Font("font/font.png", 10, 10);
        player = new Player(new Sprite("entity/player.png"), new Vector2D(300,300), 64);
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
       player.input(key, mouse);
    }

    @Override
    public void render(Graphics2D g) {
        tileManager.render(g);
        Sprite.drawArray(g,font,GamePanel.oldFrameCount + " FPS", new Vector2D(GamePanel.width - 500,0), 32,32, 16,0);
        player.render(g);
    }

}
