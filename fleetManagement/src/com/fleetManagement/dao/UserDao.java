package com.fleetManagement.dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fleetManagement.gui.LoginPannel;
import com.fleetManagement.pojo.User;
import com.fleetManagement.util.DBManager;

public class UserDao {
	public static String queryUserByName(String user) throws Exception {
		List<User> teList = new ArrayList<User>();
		String password = null;
		String sql;
		DBManager db = new DBManager();
		ResultSet rs = null;
		sql = "select Password from user where User = '" + user + "'";
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
		sql = "select * from user";
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
	
	public static List<User> getAll() throws Exception {
		
		List<User> driv = new ArrayList<User>();
		String sql1;
		DBManager db1 = new DBManager();
		ResultSet rs1 = null;
		User user; 
		sql1 = " select * from user";
		try {
			rs1 = db1.executeQuery(sql1);
			while(rs1.next())
			{
				user = new User();
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
	
	public static List<User> getInfo() throws Exception {
		
		List<User> driv = new ArrayList<User>();
		String sql1;
		DBManager db1 = new DBManager();
		ResultSet rs1 = null;
		User user; 
		sql1 = " select * from user where User = '" + LoginPannel.getUsers()[1] + "'";
		try {
			rs1 = db1.executeQuery(sql1);
			while(rs1.next())
			{
				user = new User();
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
	
	public static void delete(User de) throws Exception{
		
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "delete from user where User = '" + de.getName() + "'AND Password ='" + de.getPassworld() + "'"; 
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
	
	public static void save(User user) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "insert into user (User, Password) values('" + user.getName() + "','" + user.getPassworld() + "')";
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
	
	public static void remove(User user, User info) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "update user set User = '" + user.getName() + "' ,Password = '" + user.getPassworld() + 
				"' where User ='" + info.getName() + "' AND Password ='" + info.getPassworld() + "'"; 
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
	
	public static List<String> getName() throws Exception {
		List<String>list = new ArrayList<String>();
		String sql;
		DBManager db = new DBManager();
		ResultSet rs = null;
		sql = "select * from user  where User = '" + LoginPannel.getUsers()[1] + "'";
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
