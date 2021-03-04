package qiyi;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class util extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String INITIAL_LABEL_TEXT = "00:00:00 000";

    // 记录程序开始时间
    private long programStart = System.currentTimeMillis();

    // 程序一开始就是暂停的
    private long pauseStart = programStart;

    // 程序暂停的总时间
    private long pauseCount = 0;

    private JLabel label = new JLabel(INITIAL_LABEL_TEXT);

    private JButton startPauseButton = new JButton("开始");

    private JButton resetButton = new JButton("清零");

    // 为窗体面板添加边框
    public void setupBorder() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
    }

    // 配置按钮
    public void setupButtonsPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(startPauseButton);
        panel.add(resetButton);
        add(panel, BorderLayout.SOUTH);
    }

    // 配置标签
    public void setupLabel() {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 40));
        this.add(label, BorderLayout.CENTER);
    }
}