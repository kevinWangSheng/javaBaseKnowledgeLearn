package com.Class_.Swing;

import javax.swing.*;

/**
 * @author wang
 * @create 2022-2022-30-13:49
 */
public class DrawTank extends JFrame {
    private TankPanel tankPanel;


    public static void main(String[] args) {
        new DrawTank();
    }

    public DrawTank(){
        tankPanel = new TankPanel();

        this.add(tankPanel);
        this.setSize(1000,1000);

        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
