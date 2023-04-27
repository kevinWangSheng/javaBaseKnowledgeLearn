package com.Class_.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author wang
 * @create 2022-2022-30-16:38
 */
public class MyPanelEvent extends JPanel implements KeyListener {
    private int x = 10;
    private int y = 10;

    public MyPanelEvent(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public MyPanelEvent() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.fillOval(x,y,20,20);
//        g.fillRect(20,20,20,20);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("111");
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            y++;

        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            y--;

        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            x--;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            x++;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
