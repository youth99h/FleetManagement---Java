package com.fleetManagement.gui;

import com.fleetManagement.dao.DriverTaskDao;
import com.fleetManagement.gui.extend.CalendarPanel;
import com.fleetManagement.gui.extend.ExtTable;
import com.fleetManagement.pojo.DispatchVan;
import com.fleetManagement.util.TimeUtils;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

 
public class DriverTaskPanel {
	public static int lastRow = 0;

    public static void placeComponents(JPanel panel, Predicate<? super DispatchVan> predicate) {
        panel.removeAll();

        panel.setLayout(new BorderLayout(10, 5));
    
        String[] columns={"车号","司机姓名","出发时间","出发地","目的地"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        List<String> dis = null;
		try {
			dis = DriverTaskDao.get();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for(String info:dis)
        {
            String[] args=info.split(",");
            tableModel.addRow(args);
        }
        
        //表格
        JPanel qP = new JPanel(new BorderLayout(10, 0));
        //表单录入出派车辆信息
        JPanel pP = new JPanel(null);
        JPanel eP = new JPanel(null);
        Map<String, Component> panelsMap = new HashMap<>();
        panelsMap.put("主面板", qP);
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
        //功能
        HashMap<JMenuItem, Function> mMap = new HashMap<>();
        JMenuItem qMitem = new JMenuItem("筛选");
        JMenuItem rMitem = new JMenuItem("刷新");
        
        mMap.put(qMitem, (nil) -> {
            String str = JOptionPane.showInputDialog(panel, "出发时间", "筛选", JOptionPane.PLAIN_MESSAGE);
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
    			dri2 = DriverTaskDao.get();
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
       	 
            DriverTaskPanel.placeComponents(panel, null);
           
            return nil;
        });
        
        MouseInputListener mouseInputListener = ExtTable.buildMouseInputMenuListener(mMap);
        table.addMouseListener(mouseInputListener);
        
        //打印
        PrintPanel.print(dis, panel, pP);
        panel.updateUI();
        panel.repaint();

    }


}

