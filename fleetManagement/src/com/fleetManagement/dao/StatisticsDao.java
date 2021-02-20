package com.fleetManagement.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fleetManagement.util.DBManager;

public class StatisticsDao {
	
	 private static final String Memo = "系统统计";
	 
	public static List<String> getOil() throws Exception {
		List<String>list = new ArrayList<String>();
		String sql;
		DBManager db = new DBManager();
		ResultSet rs = null;
		sql = "select CardNum, (InMoney - PayMoney) from oilcard group by CardNum";
		try {
			rs = db.executeQuery(sql);
			while(rs.next()){
				String ONum = rs.getString(1);
				String OMany = rs.getString(2);
				
				String Driverinfs = ONum + "," + OMany + "," + Memo; 
				list.add(Driverinfs);				
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
	
	public static List<String> getCar() throws Exception {
		List<String>list = new ArrayList<String>();
		String sql;
		DBManager db = new DBManager();
		ResultSet rs = null;
		sql = "select CostNum, SUM(ServiceMany) from carcost group by CostNum";
		try {
			rs = db.executeQuery(sql);
			while(rs.next()){
				String CNum = rs.getString(1);
				String CMoney = rs.getString(2);
				
				String Driverinfs = CNum + "," + CMoney + "," + Memo;
				list.add(Driverinfs);				
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
