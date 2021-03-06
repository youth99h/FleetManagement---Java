package com.fleetManagement.dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fleetManagement.gui.LoginPannel;
import com.fleetManagement.pojo.DriverLogin;
import com.fleetManagement.util.DBManager;

public class DriverLoginDao {
	public static String queryUserByName(String user) throws Exception {
		List<DriverLogin> teList = new ArrayList<DriverLogin>();
		String password = null;
		String sql;
		DBManager db = new DBManager();
		ResultSet rs = null;
		sql = "select Password from driverlogin where Name = '" + user + "'";
		try {
			rs = db.executeQuery(sql);
			while(rs.next())
			{
				password = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		
		return password;
	}
	
	public static List<String> get() throws Exception {
		List<String>list = new ArrayList<String>();
		String sql;
		DBManager db = new DBManager();
		ResultSet rs = null;
		sql = "select * from driverlogin";
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
	
	public static List<DriverLogin> getAll() throws Exception {
		
		List<DriverLogin> driv = new ArrayList<DriverLogin>();
		String sql1;
		DBManager db1 = new DBManager();
		ResultSet rs1 = null;
		DriverLogin user; 
		sql1 = " select * from driverlogin";
		try {
			rs1 = db1.executeQuery(sql1);
			while(rs1.next())
			{
				user = new DriverLogin();
				user.setName(rs1.getString(1));
				user.setPassworld(rs1.getString(2));
				
				//cars = rs1.getString(1);
				driv.add(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db1.close();
		}
		
		return driv;
		
	}
	
	public static List<DriverLogin> getInfo() throws Exception {
		
		List<DriverLogin> driv = new ArrayList<DriverLogin>();
		String sql1;
		DBManager db1 = new DBManager();
		ResultSet rs1 = null;
		DriverLogin user; 
		sql1 = " select * from driverlogin where Name = '" + LoginPannel.getUsers()[0] + "'";
		try {
			rs1 = db1.executeQuery(sql1);
			while(rs1.next())
			{
				user = new DriverLogin();
				user.setName(rs1.getString(1));
				user.setPassworld(rs1.getString(2));
				
				//cars = rs1.getString(1);
				driv.add(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db1.close();
		}
		
		return driv;
		
	}
	
	public static void delete(DriverLogin de) throws Exception{
		
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "delete from driverlogin where Name = '" + de.getName() + "'AND Password ='" + de.getPassworld() + "'"; 
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
	
	public static void save(DriverLogin user) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "insert into driverlogin (Name, Password) values('" + user.getName() + "','" + user.getPassworld() + "')";
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
	
	public static void remove(DriverLogin user, DriverLogin info) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "update driverlogin set Name = '" + user.getName() + "' ,Password = '" + user.getPassworld() + 
				"' where Name ='" + info.getName() + "' AND Password ='" + info.getPassworld() + "'"; 
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
