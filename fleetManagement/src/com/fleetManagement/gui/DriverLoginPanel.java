package com.fleetManagement.gui;

import javax.swing.*;

import com.fleetManagement.gui.extend.VFlowLayout;

import java.awt.*;

//主面板

public class DriverLoginPanel {

    public static void placeComponents(JPanel panel) {
        panel.removeAll();

        MainFrame.setT("主页");

        panel.setLayout(new BorderLayout(10,5));

        //东西南北中，面板
        JPanel nav = new JPanel(new VFlowLayout());
        nav.setBackground(Color.white);
        JPanel center = new JPanel(null);
        JPanel jp3 = new JPanel(new GridLayout(10,2));
        JPanel jp4 = new JPanel(new FlowLayout());
        JPanel jp5 = new JPanel(new FlowLayout());

        //加入主面板
        panel.add(BorderLayout.NORTH,jp3);
        panel.add(BorderLayout.SOUTH,jp3);
        panel.add(BorderLayout.CENTER,center);
        panel.add(BorderLayout.EAST,jp3);
        panel.add(BorderLayout.WEST,nav);


        //左边导航
        JButton DITtn = new JButton("个人信息");
        DITtn.addActionListener(e -> {
            MainFrame.setT("个人信息");
            DriverInfoManagePanel.placeComponents(center, null);
        });

        
        JButton dRTtn = new JButton("查看任务");
        dRTtn.addActionListener(e -> {
            MainFrame.setT("查看任务");
            DriverTaskPanel.placeComponents(center, null);
        });

        JButton exit = new JButton("退出登录");
        exit.addActionListener(e -> {
            panel.removeAll();
            LoginPannel.placeComponents(panel);
            panel.repaint();
        });
        
        nav.add(DITtn);
        nav.add(dRTtn);
        nav.add(exit);
        panel.updateUI();

        panel.repaint();
    }
}

