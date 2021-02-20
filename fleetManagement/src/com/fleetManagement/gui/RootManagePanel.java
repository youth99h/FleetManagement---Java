package com.fleetManagement.gui;

import com.fleetManagement.dao.UserDao;
import com.fleetManagement.pojo.User;
import com.fleetManagement.gui.extend.CalendarPanel;
import com.fleetManagement.gui.extend.ExtTable;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class RootManagePanel {
	
	public static int lastRow = 0;
    /**
     * @param panel
     * @param predicate 筛选条件
     */
    public static void placeComponents(JPanel panel, Predicate<? super User> predicate) {
        panel.removeAll();

        panel.setLayout(new BorderLayout(10, 5));

        String[] columns={"用户名", "密码"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        List<String> dri = null;
		try {
			dri = UserDao.get();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for(String info:dri)
        {
            String[] args=info.split(",");
            tableModel.addRow(args);
        }

        //表格
        JPanel qP = new JPanel(new BorderLayout(10, 0));

        JPanel aP = new JPanel(null);
        JPanel eP = new JPanel(null);
        JPanel pP = new JPanel(null);
        Map<String, Component> panelsMap = new HashMap<>();
        panelsMap.put("主面板", qP);
        panelsMap.put("录入", aP);
        panelsMap.put("打印", pP);

        JTabbedPane jTabbedPane = ExtTable.buildJTabbedPane(panelsMap);
        panel.add(jTabbedPane, BorderLayout.CENTER);

        jTabbedPane.addChangeListener(e -> {
            if (jTabbedPane.getSelectedIndex() != 3 && jTabbedPane.getTabCount() > 3) {
                jTabbedPane.remove(3);
            }
        });


        JTable table = ExtTable.getDefaultTable(null);
        table.setModel(tableModel);
        qP.add(table.getTableHeader(), BorderLayout.NORTH);
        qP.add(new JScrollPane(table), BorderLayout.CENTER);

        //删除行数据
        HashMap<JMenuItem, Function> mMap = new HashMap<>();
        JMenuItem dMitem = new JMenuItem("删除选定行");
        JMenuItem qMitem = new JMenuItem("筛选");
        JMenuItem rMitem = new JMenuItem("刷新");
        JMenuItem eMitem = new JMenuItem("编辑");
        
        mMap.put(dMitem, (nil) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(panel, "没有选择要删除的行!!!", "操作有误", JOptionPane.WARNING_MESSAGE);
                return nil;
            }
       
           try {
			User de = UserDao.getAll().get(selectedRow);
			UserDao.delete(de);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
            //刷新页面
            RootManagePanel.placeComponents(panel, null);
            return nil;
        });
        
        mMap.put(qMitem, (nil) -> {
            String str = JOptionPane.showInputDialog(panel, "姓名", "筛选", JOptionPane.PLAIN_MESSAGE);
            if (str.isEmpty()) {
                return nil;
            }
            

            //按名字做条件筛选
            int count=table.getRowCount();
            if (count<=0) {
             return 0;
            }else {
             for (int i = count -1 ; i >= 0; i--) {
              tableModel.removeRow(table.getRowCount() - 1);
             }
            }
            
            List<String> dri2 = null;
    		try {
    			dri2 = UserDao.get();
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
            for(String info:dri2)
            {
            	if(info.contains(str)) {
            		String[] args2=info.split(",");
            		tableModel.addRow(args2);
            	}        
            }
            
            return nil;
        });
        
        mMap.put(rMitem, (nil) -> {
        	 
            RootManagePanel.placeComponents(panel, null);
           
            return nil;
        });
        //编辑
        mMap.put(eMitem, (nil) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(panel, "没有编辑的行!!!", "操作有误", JOptionPane.WARNING_MESSAGE);
                return nil;
            }
            try {
				buildForm(panel, eP, UserDao.getAll(), selectedRow);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            jTabbedPane.add("编辑", eP);
            jTabbedPane.setSelectedIndex(3);
            return nil;
        });

        MouseInputListener mouseInputListener = ExtTable.buildMouseInputMenuListener(mMap);
        table.addMouseListener(mouseInputListener);

        //表单
        buildForm(panel, aP, null, -1);

        //打印
        PrintPanel.print(dri, panel, pP);

        panel.updateUI();
        panel.repaint();

    }


    private static void buildForm(JPanel mainPanel, JPanel panel, List<User> users, int rows) {
        int lw = 200, tw = 200, lh = 25, th = 25, x = 100, y = 20, tx = lw + x;

        JLabel lTitle = new JLabel("管理员档案录入", JLabel.RIGHT);
        lTitle.setBounds(x, y, lw, lh);
        panel.add(lTitle);

        y += 30;
        JLabel lName = new JLabel("姓名：", JLabel.RIGHT);
        lName.setBounds(x, y, lw, lh);
        JTextField tName = new JTextField(20);
        tName.setBounds(tx, y, tw, th);
        panel.add(lName);
        panel.add(tName);

        y += 30;
        JLabel lPassword = new JLabel("密码：", JLabel.RIGHT);
        lPassword.setBounds(x, y, lw, lh);
        JTextField tPassword = new JTextField(20);
        tPassword.setBounds(tx, y, tw, th);
        panel.add(lPassword);
        panel.add(tPassword);

        y += 50;
        JButton submitBtn = new JButton("确定");
        submitBtn.setBounds(x + 250, y, 100, th);
        panel.add(submitBtn);

        if (Objects.nonNull(users) && users.size() > 0 && rows >= 0) {
            User temp = users.get(rows);
            tName.setText(temp.getName());
            tPassword.setText(temp.getPassworld());
        }

        submitBtn.addActionListener(e -> {
            String name = tName.getText();
            if (Objects.isNull(name) || name.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "用户名不能为空!!!", "检验失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
           
            String password = tPassword.getText();
            if (Objects.isNull(password) || password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "密码不能为空!!!", "检验失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
           
            User user = new User(name, password);
            // 新加
            if (rows < 0) {
                try {
					UserDao.save(user);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            // 编辑
            else {
            	User info = users.get(rows);
            	
                try {
					UserDao.remove(user, info);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
                
            }
            //刷新
            RootManagePanel.placeComponents(mainPanel, null);

        });

    }
}

