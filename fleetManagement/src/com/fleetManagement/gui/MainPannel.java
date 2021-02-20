package com.fleetManagement.gui;

import javax.swing.*;

import com.fleetManagement.gui.extend.VFlowLayout;

import java.awt.*;

//主面板

public class MainPannel {

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
        JButton GIBtn = new JButton("个人信息处理");
        GIBtn.addActionListener(e -> {
            MainFrame.setT("个人信息处理");
            UserInfoManagePanel.placeComponents(center, null);
        });
        
        JButton dMBtn = new JButton("司机档案处理");
        dMBtn.addActionListener(e -> {
            MainFrame.setT("司机档案处理");
            DriverManagePannel.placeComponents(center, null);
        });

        JButton cMBtn = new JButton("车辆档案处理");
        cMBtn.addActionListener(e -> {
            MainFrame.setT("车辆档案处理");
            CarManagePannel.placeComponents(center, null);
        });

        //car cost
        JButton cCBtn = new JButton("车辆日常费用管理");
        cCBtn.addActionListener(e -> {
            MainFrame.setT("车辆日常费用管理");
            CarCostManagePannel.placeComponents(center, null);
        });

        //Oil card
        JButton ocBtn = new JButton("油卡管理");
        ocBtn.addActionListener(e -> {
            MainFrame.setT("油卡管理");
            OilCardManagePannel.placeComponents(center, null);
        });

        //Dispatch van
        JButton dvBtn = new JButton("出（派）车管理");
        dvBtn.addActionListener(e -> {
            MainFrame.setT("出（派）车管理");
            DispatchVanManagePannel.placeComponents(center, null);
        });

        //Cost statistics
        JButton csBtn = new JButton("费用统计");
        csBtn.addActionListener(e -> {
            MainFrame.setT("费用统计");
            CostStatisticsManagePannel.placeComponents(center, null, null);
        });
        
        JButton dbBtn = new JButton("建立司机账户");
        dbBtn.addActionListener(e -> {
            MainFrame.setT("建立司机账户");
            DriverLoginManagePanel.placeComponents(center, null);
        });

        JButton exit = new JButton("退出登录");
        exit.addActionListener(e -> {
            panel.removeAll();
            LoginPannel.placeComponents(panel);
            panel.repaint();
        });
        
        nav.add(GIBtn);
        nav.add(dMBtn);
        nav.add(cMBtn);
        nav.add(cCBtn);
        nav.add(ocBtn);
        nav.add(dvBtn);
        nav.add(csBtn);
        nav.add(dbBtn);
        nav.add(exit);
        panel.updateUI();

        panel.repaint();
    }
}
