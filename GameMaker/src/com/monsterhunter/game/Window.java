package com.monsterhunter.game;

import javax.swing.JFrame;

public class Window extends JFrame {
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    public Window() {
        this(WIDTH, HEIGHT);
    }

    public Window(int width, int height) {
        setTitle("Monster Hunter");
        setSize(width, height);
        setContentPane(new GamePanel(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}