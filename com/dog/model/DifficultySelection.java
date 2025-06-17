package com.dog.model;
import com.dog.view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
public class DifficultySelection extends JFrame {
    private final Start.GameStarter gameStarter;
    public DifficultySelection() {    //初始化窗口
        setTitle("选择难度");
        setSize(300, 200);
        setLocationRelativeTo(null);    //窗口在屏幕中央显示
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //用户关闭窗口时，程序将退出
        setLayout(new GridLayout(3, 1, 10, 10));  // 设置布局为网格布局：3行1列，按钮间距10像素
        gameStarter = new Start.GameStarter();   //初始化游戏启动器（Start的内部类）

        // 创建三个难度按钮并添加到窗口
        JButton easyBtn = createDifficultyButton("简单模式", 1);
        JButton normalBtn = createDifficultyButton("普通模式", 2);
        JButton hardBtn = createDifficultyButton("困难模式", 3);
        add(easyBtn);
        add(normalBtn);
        add(hardBtn);
    }
    private JButton createDifficultyButton(String text, int difficulty) {
        JButton button = new JButton(text);
        button.setFont(new Font("微软雅黑", Font.BOLD, 16));  // 设置按钮字体（微软雅黑，粗体，16号）
        button.addActionListener((ActionEvent e) -> {    //为按钮添加一个动作监听器，当按钮被点击时，执行以下操作
            gameStarter.startGame(difficulty);
            dispose(); // 关闭选择窗口
        });
        return button;
    }
}