package com.Class_.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author wang
 * @create 2022-2022-30-17:21
 */

public class Demo10 extends JFrame {
    MyJpanel1 myjpanel = new MyJpanel1();
    public Demo10(){
        this.add(myjpanel);//将自定义的面板放入JFrame窗体中
        this.setVisible(true);
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 500);
        this.addKeyListener(myjpanel);
    }

    public static void main(String[] args) {
        Demo10 de = new Demo10();
    }
}

class MyJpanel1 extends JPanel implements KeyListener {
    int x = 10;
    int y = 10;
    public void paint(Graphics g){
        super.paint(g);
        g.drawOval(x, y, 10, 10);
    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            y+=5;
        }else if(e.getKeyCode()==KeyEvent.VK_UP){
            y-=5;
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            x-=5;
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            x+=5;
        }
        this.repaint();//起到重新调用paint（）的作用
    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}


