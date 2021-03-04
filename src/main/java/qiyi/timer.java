package qiyi;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class timer extends JFrame {
    // 程序入口
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

         timerService frame = new timerService("计时器");
        frame.pack();
        frame.setVisible(true);


        timerService frame2 = new timerService("计时器2");
        frame2.pack();
        frame2.setVisible(true);

        timerService frame3 = new timerService("计时器3");
        frame3.pack();
        frame3.setVisible(true);
    }
}