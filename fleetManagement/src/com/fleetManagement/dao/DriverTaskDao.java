package com.fleetManagement.dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fleetManagement.gui.LoginPannel;
import com.fleetManagement.pojo.DispatchVan;
import com.fleetManagement.pojo.OilCard;
import com.fleetManagement.util.DBManager;

public class DriverTaskDao {

	public static List<String> get() throws Exception {
		List<String> list = new ArrayList<String>();
		String sql;
		DBManager db = new DBManager();
		ResultSet rs = null;
		sql = "select * from delivery where DeliveryName = '" + LoginPannel.getUsers()[0] + "'";
	
		try {
			rs = db.executeQuery(sql);
			while(rs.next()){
				String DeliveryNum = rs.getString(1);
				String DeliveryName = rs.getString(2);
				String GoOff = rs.getString(3);
				String Start = rs.getString(4);
				String Bourn = rs.getString(5);
				
				String Disinfs = DeliveryNum + "," + DeliveryName + "," + GoOff + "," + Start +
						"," + Bourn; 
				list.add(Disinfs);				
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db.close();
		}		
		return list;
	}
	
	public static List<String> getName() throws Exception {
		List<String>list = new ArrayList<String>();
		String sql;
		DBManager db = new DBManager();
		ResultSet rs = null;
		sql = "select * from driverlogin where Name = '" + LoginPannel.getUsers()[0] + "'";
		try {
			rs = db.executeQuery(sql);
			while(rs.next()){
				String user = rs.getString(1);
				String password = rs.getString(2);
				
				String Userinfs = user + "," + password;
				list.add(Userinfs);				
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db.close();
		}		
		return list;
	}
	
}
