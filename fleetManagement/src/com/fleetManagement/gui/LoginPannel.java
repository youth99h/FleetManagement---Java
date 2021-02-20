package com.fleetManagement.gui;

import com.fleetManagement.dao.DriverLoginDao;
import com.fleetManagement.dao.UserDao;
import com.fleetManagement.pojo.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//登录面板
 
public class LoginPannel {
	public static String[] users = new String[2];

    public static void placeComponents(JPanel panel) {

        MainFrame.setT("登录");

        /*
         * 设置布局null
         */
        panel.setLayout(null);

        int labelX = 80;
        int inputX = 165;
        int marginlabelX = 50;
        int x = (Config.getWidth() - labelX - inputX) / 2;
        int y = Config.getHeight() / 4;

        // 创建 JLabel
        JLabel userLabel = new JLabel("账户:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        userLabel.setBounds(x, y, labelX, 25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(x + marginlabelX, y, inputX, 25);
        panel.add(userText);
       

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(x, y + 50, labelX, 25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(x + marginlabelX, y + 50, inputX, 25);
        panel.add(passwordText);

        // 创建登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(x + 80, y + 100, labelX, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					if (passwordText.getText().equals(UserDao.queryUserByName(userText.getText()))) {
						users[1] = userText.getText();
						MainPannel.placeComponents(panel);
					} else if(passwordText.getText().equals("123456") && userText.getText().equals("root")) {
						RootPanel.placeComponents(panel);
					} else if(passwordText.getText().equals(DriverLoginDao.queryUserByName(userText.getText()))) {
						users[0] = userText.getText();
						DriverLoginPanel.placeComponents(panel);
					} else {
					    JOptionPane.showMessageDialog(panel, "账户名或者密码错误!!!", "登录异常", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
        
        
    }

	public static String[] getUsers() {
		return users;
	}

	public static void setUsers(String[] users) {
		LoginPannel.users = users;
	}
    
    

}