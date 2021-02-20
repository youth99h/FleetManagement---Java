package com.fleetManagement.dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fleetManagement.pojo.DispatchVan;
import com.fleetManagement.pojo.OilCard;
import com.fleetManagement.util.DBManager;

public class DispatchVanDao {
	public static void save(DispatchVan dispatchvan) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "insert into delivery (DeliveryNum, DeliveryName, GoOff, Start, Bourn) values('" +
				dispatchvan.getNo() + "','" + dispatchvan.getDriverName() + "','" + dispatchvan.getStartTime() + "','" 
		+ dispatchvan.getTo() + "','"  + dispatchvan.getFrom() + "')";
		try {
			rs = db.executeUpdate(sql);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db.close();
		}
	}
	
	public static List<String> get() throws Exception {
		List<String> list = new ArrayList<String>();
		String sql;
		DBManager db = new DBManager();
		ResultSet rs = null;
		sql = "select * from delivery";
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
	
	public static List<DispatchVan> getAll() throws Exception {
		
		List<DispatchVan> dv = new ArrayList<DispatchVan>();
		String sql1;
		DBManager db1 = new DBManager();
		ResultSet rs1 = null;
		DispatchVan dvan; 
		sql1 = " select * from delivery ";
		try {
			rs1 = db1.executeQuery(sql1);
			while(rs1.next())
			{
				dvan = new DispatchVan();
				dvan.setNo(rs1.getString(1));
				dvan.setDriverName(rs1.getString(2));
				dvan.setStartTime(LocalDate.parse(rs1.getString(3)));
				dvan.setTo(rs1.getString(4));
				dvan.setFrom(rs1.getString(5));
				//cars = rs1.getString(1);
				dv.add(dvan);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db1.close();
		}
		
		return dv;
		
	}
	
	
	public static void remove(DispatchVan car, DispatchVan info) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "update delivery set DeliveryNum = '" + car.getNo() + "' ,DeliveryName = '" + car.getDriverName() + "', GoOff ='" +
				car.getStartTime() + "', Start = '" + car.getTo() + "', Bourn = '" + car.getFrom() + 
				"' where DeliveryNum = '" + info.getNo() + "'AND DeliveryName = '" + info.getDriverName() + "'AND GoOff ='"+
				info.getStartTime() + "'AND Start ='" + info.getTo() + "'AND Bourn ='" + info.getFrom() + "'";
		try {
			rs = db.executeUpdate(sql);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db.close();
		}
	}
	
	public static void delete(DispatchVan dvd) throws Exception{
		
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "delete from delivery where DeliveryName = '" + dvd.getDriverName() + "'AND DeliveryNum = '" + dvd.getNo() + "'AND GoOff = '" +
		dvd.getStartTime() + "'AND Start ='" + dvd.getTo() + "'AND Bourn ='" + dvd.getFrom() + "'";
		try {
			rs = db.executeUpdate(sql);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		
	}
}
