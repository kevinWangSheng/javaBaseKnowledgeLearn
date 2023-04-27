package com.Class_.Swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author wang
 * @create 2022-2022-30-11:10
 */
public class DrawCricle extends JFrame {
//    private Graphics graphics;
    private MyPanel myPanel;
    public static void main(String[] args) {
        new DrawCricle();
        System.out.println("1");
    }

    public DrawCricle(){
        myPanel = new MyPanel();
        this.add(myPanel);

        this.setSize(300,400);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }

}

class MyPanel extends JPanel{
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("wwww");
//        g.drawOval(10,10,100,100);

        Image image = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/baoguo.png"));

        g.drawImage(image,20,20,100,100,this);
    }
}
