package com.Class_.Swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author wang
 * @create 2022-2022-30-13:50
 */
public class TankPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,1000);

        g.setColor(Color.BLUE);
//        two rect
        g.fillRect(50,300,30,200);
        g.fillRect(180,300,30,200);

        g.fillRect(80,330,100,140);
//        draw a circle
        g.setColor(Color.CYAN);

        g.fillOval(80,350,100,100);

        g.setColor(Color.RED);


        g.drawLine(130,200,130,400);


    }
}
