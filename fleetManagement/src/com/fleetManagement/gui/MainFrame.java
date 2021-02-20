package com.fleetManagement.gui;

import javax.swing.*;

import com.fleetManagement.util.PathUtils;
import com.fleetManagement.util.ScreenUtils;

//程序主界面

public class MainFrame extends JFrame {

    static MainFrame self;

    public MainFrame(int w, int h) {
        this.setSize(w, h);

        this.setTitle("物流公司车辆管理系统");

        self = this;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(ScreenUtils.getMarginWidth(w), ScreenUtils.getMarginHeight(h), w, h);

        this.setIconImage(new ImageIcon(PathUtils.getIconImgPath()).getImage());

        //创建面板
        JPanel panel = new JPanel();
        // 添加面板
        this.add(panel);

        LoginPannel.placeComponents(panel);

    }

    public void doShow() {
        // 设置界面可见
        this.setVisible(true);
    }

    public static void setT(String t) {
        self.setTitle(String.format("%s-%s", self.getTitle().split("-")[0], t));
    }
}
