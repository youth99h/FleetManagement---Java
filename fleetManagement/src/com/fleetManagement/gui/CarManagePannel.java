package com.fleetManagement.gui;

import com.fleetManagement.dao.CarDao;
import com.fleetManagement.dao.DriverDao;
import com.fleetManagement.gui.extend.CalendarPanel;
import com.fleetManagement.gui.extend.ExtTable;
import com.fleetManagement.pojo.Car;
import com.fleetManagement.pojo.Driver;
import com.fleetManagement.util.TimeUtils;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

//车辆档案处理面板
 
public class CarManagePannel {
	public static int lastRow = 0;

    /**
     * @param panel
     * @param predicate 筛选条件
     */
    public static void placeComponents(JPanel panel, Predicate<? super Car> predicate) {
        panel.removeAll();

        panel.setLayout(new BorderLayout(10, 5));
        
        String[] columns={"车号","登记时间","发证时间","类型","车主","发动机号","车驾号","汽车品牌","核定载客数","颜色"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        List<String> c = null;
		try {
			c = CarDao.get();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for(String info:c)
        {
            String[] args=info.split(",");
            tableModel.addRow(args);
        }

        //表格
        JPanel qP = new JPanel(new BorderLayout(10, 0));
        //表单录入司机信息
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
            if (jTabbedPane.getSelectedIndex() != 3 && jTabbedPane.getTabCount() >3) {
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
            //删除选定行再持久化剩下的司机信息
            try {
    			Car cr = CarDao.getAll().get(selectedRow);
    			CarDao.delete(cr);
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
            //刷新页面
            CarManagePannel.placeComponents(panel, null);
            return nil;
        });
        mMap.put(qMitem, (nil) -> {
            String str = JOptionPane.showInputDialog(panel, "车号", "筛选", JOptionPane.PLAIN_MESSAGE);
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
    			dri2 = CarDao.get();
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
            CarManagePannel.placeComponents(panel, null);
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
				buildForm(panel, eP, CarDao.getAll(), selectedRow);
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
        PrintPanel.print(c, panel, pP);

        panel.updateUI();
        panel.repaint();

    }


    private static void buildForm(JPanel mainPanel, JPanel panel, List<Car> cars, int rows) {
        int lw = 200, tw = 200, lh = 25, th = 25, x = 100, y = 20, tx = lw + x;

        JLabel lTitle = new JLabel("车辆档案录入", JLabel.RIGHT);
        lTitle.setBounds(x, y, lw, lh);
        panel.add(lTitle);

        y += 30;
        JLabel lNo = new JLabel("车号：", JLabel.RIGHT);
        lNo.setBounds(x, y, lw, lh);
        JTextField tNo = new JTextField(20);
        tNo.setBounds(tx, y, tw, th);
        panel.add(lNo);
        panel.add(tNo);

        y += 30;
        JLabel lCheckTime = new JLabel("登记时间：", JLabel.RIGHT);
        lCheckTime.setBounds(x, y, lw, lh);
        JTextField tCheckTime = new JTextField(20);
        tCheckTime.setBounds(tx, y, tw, th);
        CalendarPanel p = new CalendarPanel(tCheckTime, "yyyy-MM-dd");
        p.initCalendarPanel();
        panel.add(p);
        panel.add(lCheckTime);
        panel.add(tCheckTime);

        y += 30;
        JLabel lSuccessTime = new JLabel("发证时间：", JLabel.RIGHT);
        lSuccessTime.setBounds(x, y, lw, lh);
        JTextField tSuccessTime = new JTextField(20);
        tSuccessTime.setBounds(tx, y, tw, th);
        CalendarPanel p1 = new CalendarPanel(tSuccessTime, "yyyy-MM-dd");
        p1.initCalendarPanel();
        panel.add(p1);
        panel.add(lSuccessTime);
        panel.add(tSuccessTime);


        y += 30;
        JLabel lType = new JLabel("类型：", JLabel.RIGHT);
        lType.setBounds(x, y, lw, lh);
        JTextField tType = new JTextField(20);
        tType.setBounds(tx, y, tw, th);
        panel.add(lType);
        panel.add(tType);

        y += 30;
        JLabel lOwnerse = new JLabel("车主：", JLabel.RIGHT);
        lOwnerse.setBounds(x, y, lw, lh);
        JTextField tOwners = new JTextField(20);
        tOwners.setBounds(tx, y, tw, th);
        panel.add(lOwnerse);
        panel.add(tOwners);

        y += 30;
        JLabel lEngineNo = new JLabel("发动机号：", JLabel.RIGHT);
        lEngineNo.setBounds(x, y, lw, lh);
        JTextField tEngineNo = new JTextField(20);
        tEngineNo.setBounds(tx, y, tw, th);
        panel.add(lEngineNo);
        panel.add(tEngineNo);

        y += 30;
        JLabel lDrivingNo = new JLabel("车驾号：", JLabel.RIGHT);
        lDrivingNo.setBounds(x, y, lw, lh);
        JTextField tDrivingNo = new JTextField(20);
        tDrivingNo.setBounds(tx, y, tw, th);
        panel.add(lDrivingNo);
        panel.add(tDrivingNo);

        y += 30;
        JLabel lBand = new JLabel("汽车品牌：", JLabel.RIGHT);
        lBand.setBounds(x, y, lw, lh);
        JTextField tBand = new JTextField(20);
        tBand.setBounds(tx, y, tw, th);
        panel.add(lBand);
        panel.add(tBand);

        y += 30;
        JLabel lCarryLimit = new JLabel("核定载客数：", JLabel.RIGHT);
        lCarryLimit.setBounds(x, y, lw, lh);
        JTextField tCarryLimit = new JTextField(20);
        tCarryLimit.setBounds(tx, y, tw, th);
        panel.add(lCarryLimit);
        panel.add(tCarryLimit);

        y += 30;
        JLabel lColor = new JLabel("颜色：", JLabel.RIGHT);
        lColor.setBounds(x, y, lw, lh);
        JTextField tColor = new JTextField(20);
        tColor.setBounds(tx, y, tw, th);
        panel.add(lColor);
        panel.add(tColor);


        y += 50;
        JButton submitBtn = new JButton("确定");
        submitBtn.setBounds(x + 250, y, 100, th);
        panel.add(submitBtn);

        if (Objects.nonNull(cars) && cars.size() > 0 && rows >= 0) {
            Car temp = cars.get(rows);
            tNo.setText(temp.getNo());
            tCheckTime.setText(temp.getCheckTime().toString());
            tSuccessTime.setText(temp.getSuccessTime().toString());
            tOwners.setText(temp.getOwners());
            tBand.setText(temp.getBand());
            tCarryLimit.setText(temp.getCarryLimit());
            tColor.setText(temp.getColor());
            tType.setText(temp.getType());
            tEngineNo.setText(temp.getEngineNo());
            tDrivingNo.setText(temp.getDrivingNo());
        }

        submitBtn.addActionListener(e -> {
            if (tNo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "车号为空!!!", "检验失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tCheckTime.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "登记时间为空!!!", "检验失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tSuccessTime.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "发证时间为空!!!", "检验失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tType.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "类型为空!!!", "检验失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tOwners.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "车主为空!!!", "检验失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tBand.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "汽车品牌为空!!!", "检验失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tCarryLimit.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "核定载客数为空!!!", "检验失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tColor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "颜色为空!!!", "检验失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Car car = new Car(tNo.getText(), tType.getText(), tOwners.getText(), tEngineNo.getText(), tDrivingNo.getText(), tBand.getText(), tCarryLimit.getText(), tColor.getText(), TimeUtils.str2Date(tCheckTime.getText()), TimeUtils.str2Date(tSuccessTime.getText()));
            // 新加
            if (rows < 0) {
                try {
					CarDao.save(car);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else {
            	Car info = cars.get(rows);
            	
                try {
					CarDao.remove(car, info);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
                
            }
            //刷新
            CarManagePannel.placeComponents(mainPanel, null);

        });

    }
}
