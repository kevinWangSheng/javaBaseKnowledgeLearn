package com.Class_.Swing;

import javax.swing.*;

/**
 * @author wang
 * @create 2022-2022-30-16:37
 */
public class BallEvent extends JFrame {
    private MyPanelEvent myPanelEvent = null;
    public static void main(String[] args) {
        new BallEvent();
    }

    public BallEvent(){
        myPanelEvent = new MyPanelEvent();
        this.add(myPanelEvent);

//        System.out.println("wahah");

        this.setVisible(true);
        this.setSize(300,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(myPanelEvent);
    }
}


