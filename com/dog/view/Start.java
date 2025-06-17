package com.dog.view;

import com.dog.model.*;
import com.dog.test.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Start extends JFrame {


    private Integer  width = 450;
    private Integer  height =800;


    private Brand bj =new Brand("游戏机背景");
    private Brand xc = new Brand("消除区域");

    public Start() throws HeadlessException {

    }

    //窗口绘制
    public  void init(){
        this.setVisible(true);
        this.setSize(width,height);
        this.setTitle("狗了个狗");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        this.setBounds(0,0,450,800);
        this.setLocationRelativeTo(null);
    }

    public void renderMap(){
        List<Layer>  list=map.getLayers();
        for (int i = 0; i < list.size(); i++) {
            renderLayer(this.getContentPane(),list.get(i));
        }

        map.grayDecide();  // 置灰判定

    }


    private void renderLayer(Container container, Layer layer) {
        if (layer.getCells() == null) return; // 如果图层单元格为空，直接返回

        Cell[][] cells = layer.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];

                // 检查 cell 是否为 null
                if (cell == null || cell.getState() != 2) {
                    continue; // 如果单元格为空或状态不符合要求，跳过
                }

                Brand brand = cell.getBrand();
                if (brand == null) continue; // 如果牌对象为空，跳过

                int brandx = j * 50 + layer.getOffset();
                int brandy = i * 50 + layer.getOffset();

                brand.setBounds(brandx, brandy, 50, 50);
                this.getContentPane().add(brand);
            }
        }
    }

    public void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    repaint();
                }

            }
        }).start();
    }
    //用于接收难度以启动游戏
    public static class GameStarter {
        public void startGame(int difficulty) {
            SwingUtilities.invokeLater(() -> {
                Start start = new Start(difficulty);
                start.setVisible(true);
            });
        }
    }

    // 修改构造函数接收难度参数
    private int gameDifficulty;

    public Start(int difficulty) throws HeadlessException {
        this.gameDifficulty = difficulty;
        initComponents();
    }

    private void initComponents() {
        init();
        loadMapByDifficulty(); // 先加载地图数据
        if (map != null) {
            renderMap();
        } else {
            System.err.println("地图数据加载失败！");
            return;
        }

        // 消除区域和背景绘制
        bj.setBounds(0, 0, 450, 800);
        this.getContentPane().add(bj);

        xc.setBounds(0, 575, 450, 800);
        this.getContentPane().add(xc);

        refresh();


    }

    // 修改地图初始化逻辑
    public static Map map;

    static {
        // 默认初始化（会被覆盖）
        map = TestMap.buildMap(1);
    }

    // 新增根据难度加载地图的方法
    private void loadMapByDifficulty() {
        switch (gameDifficulty) {
            case 1:
                map = TestMap.buildMap(1);
                break;
            case 2:
                map = TestMap.buildMap(2);
                break;
            case 3:
                map = TestMap.buildMap(3);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DifficultySelection().setVisible(true);
        });
        new Start();
    }
}
