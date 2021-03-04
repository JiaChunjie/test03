package qiyi;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import qiyi.util;

public class timerService extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String INITIAL_LABEL_TEXT = "00:00:00 000";

    // 计数线程
    private CountingThread thread = new CountingThread();

    // 记录程序开始时间
    private long programStart = System.currentTimeMillis();

    // 程序一开始就是暂停的
    private long pauseStart = programStart;

    // 程序暂停的总时间
    private long pauseCount = 0;

    private JLabel label = new JLabel(INITIAL_LABEL_TEXT);

    private JButton startPauseButton = new JButton("开始");

    private JButton resetButton = new JButton("清零");

    private ActionListener startPauseButtonListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (thread.stopped) {
                pauseCount += (System.currentTimeMillis() - pauseStart);
                thread.stopped = false;
                startPauseButton.setText("暂停");
            } else {
                pauseStart = System.currentTimeMillis();
                thread.stopped = true;
                startPauseButton.setText("继续");
            }
        }
    };

    private ActionListener resetButtonListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            pauseStart = programStart;
            pauseCount = 0;
            thread.stopped = true;
            label.setText(INITIAL_LABEL_TEXT);
            startPauseButton.setText("开始");
        }
    };

    public timerService(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300, 300);
        setResizable(false);

        util utilI = new util();

        utilI.setupBorder();
        utilI.setupLabel();
        utilI.setupButtonsPanel();

        startPauseButton.addActionListener(startPauseButtonListener);
        resetButton.addActionListener(resetButtonListener);

        thread.start(); // 计数线程一直就运行着
    }

    private class CountingThread extends Thread {

        public boolean stopped = true;

        private CountingThread() {
            setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                if (!stopped) {
                    long elapsed = System.currentTimeMillis() - programStart - pauseCount;
                    label.setText(format(elapsed));
                }

                try {
                    sleep(1);  // 1毫秒更新一次显示
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

        // 将毫秒数格式化
        private String format(long elapsed) {
            int hour, minute, second, milli;

            milli = (int) (elapsed % 1000);
            elapsed = elapsed / 1000;

            second = (int) (elapsed % 60);
            elapsed = elapsed / 60;

            minute = (int) (elapsed % 60);
            elapsed = elapsed / 60;

            hour = (int) (elapsed % 60);

            return String.format("%02d:%02d:%02d %03d", hour, minute, second, milli);
        }
    }
}