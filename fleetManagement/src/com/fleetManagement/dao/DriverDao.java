package com.fleetManagement.dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fleetManagement.pojo.Car;
import com.fleetManagement.pojo.Driver;
import com.fleetManagement.util.DBManager;

public class DriverDao {
	
	public static void save(Driver driver) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "insert into driver (Name, Sex, Day, FirstDay, BeginDay, OverDay, Place, PermitNum, CarType) values('" +
		driver.getName() + "','" + driver.getSex() + "','" + driver.getBirthday() + "','" + driver.getCertificateTime() + "','" 
		+ driver.getStartTime() + "','" + driver.getEndTime() + "','" + driver.getAddress() + "','" + driver.getLicenseNumber() + "','"
		+ driver.getDrivingLicense() + "')";
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
		List<String>list=new ArrayList<String>();
		String sql;
		DBManager db = new DBManager();
		ResultSet rs = null;
		sql = "select * from driver";
		try {
			rs = db.executeQuery(sql);
			while(rs.next()){
				String Name = rs.getString(1);
				String Sex = rs.getString(2);
				String Day = rs.getString(3);
				String FirstDay = rs.getString(4);
				String BeginDay = rs.getString(5);
				String OverDay = rs.getString(6);
				String Place = rs.getString(7);
				String PermitNum = rs.getString(8);
				String CarType = rs.getString(9);
				String Driverinfs = Name + "," + Sex + "," + Day + "," + FirstDay +
						"," + BeginDay + "," + OverDay + "," + Place + "," + PermitNum + "," + CarType;
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
	
	
	public static Object[] getDriverNames() throws Exception {
		List<Driver> drivers = new ArrayList<Driver>();
		String sql1;
		DBManager db1 = new DBManager();
		ResultSet rs1 = null;
		Driver driver; 
		sql1 = " select Name from driver ";
		try {
			rs1 = db1.executeQuery(sql1);
			while(rs1.next())
			{
				driver = new Driver();
				driver.setName(rs1.getString(1));
				//cars = rs1.getString(1);
				drivers.add(driver);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db1.close();
		}
		
		return drivers.stream().map(Driver::getName).collect(Collectors.toList()).toArray();
	}
	
	public static List<String> seek(String name) throws Exception {
		List<String> list = new ArrayList<String>();
		String sql;
		DBManager db = new DBManager();
		ResultSet rs = null;
		sql = "select * from driver where name ='" + name + "'";
		try {
			rs = db.executeQuery(sql);
			while(rs.next()){
				String Name = rs.getString(1);
				String Sex = rs.getString(2);
				String Day = rs.getString(3);
				String FirstDay = rs.getString(4);
				String BeginDay = rs.getString(5);
				String OverDay = rs.getString(6);
				String Place = rs.getString(7);
				String PermitNum = rs.getString(8);
				String CarType = rs.getString(9);
				String Driverinfs = Name + "," + Sex + "," + Day + "," + FirstDay +
						"," + BeginDay + "," + OverDay + "," + Place + "," + PermitNum + "," + CarType;
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
	
	public static List<Driver> getAll() throws Exception {
		
		List<Driver> driv = new ArrayList<Driver>();
		String sql1;
		DBManager db1 = new DBManager();
		ResultSet rs1 = null;
		Driver driver; 
		sql1 = " select * from driver ";
		try {
			rs1 = db1.executeQuery(sql1);
			while(rs1.next())
			{
				driver = new Driver();
				driver.setName(rs1.getString(1));
				driver.setSex(rs1.getString(2).charAt(0));
				driver.setBirthday(LocalDate.parse(rs1.getString(3)));
				driver.setCertificateTime(LocalDate.parse(rs1.getString(4)));
				driver.setStartTime(LocalDate.parse(rs1.getString(5)));
				driver.setEndTime(LocalDate.parse(rs1.getString(6)));
				driver.setAddress(rs1.getString(7));
				driver.setLicenseNumber(rs1.getString(8));
				driver.setDrivingLicense(rs1.getString(9));
				//cars = rs1.getString(1);
				driv.add(driver);
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
	
	
	public static void remove(Driver driver, Driver info) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "update driver set Name = '" + driver.getName() + "' ,Sex = '" + driver.getSex() + "', Day ='" +
				driver.getBirthday() + "', FirstDay = '" + driver.getCertificateTime() + "', BeginDay = '" + driver.getStartTime() + "', OverDay = '" + driver.getEndTime() +
				"', Place = '" + driver.getAddress() + "', PermitNum = '" + driver.getLicenseNumber() + "', CarType  = '" + driver.getDrivingLicense()  +
				"' where Name = '" + info.getName() + "'AND Sex ='" + info.getSex() +"'AND Day = '" + info.getBirthday() +
				"' AND FirstDay = '" + info.getCertificateTime() + "' AND BeginDay ='" + info.getStartTime() +"' AND OverDay ='" + info.getEndTime() + 
				"' AND Place ='" + info.getAddress()  + "' AND PermitNum ='" + info.getLicenseNumber() + "' AND CarType ='" + info.getDrivingLicense() + "'"; 
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
	
	
	public static void delete(Driver de) throws Exception{
		
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "delete from driver where Name = '" + de.getName() + "'AND Sex ='" + de.getSex() +"'AND Day = '" + de.getBirthday() +
				"' AND FirstDay = '" + de.getCertificateTime() + "' AND BeginDay ='" + de.getStartTime() +"' AND OverDay ='" + de.getEndTime() + 
				"' AND Place ='" + de.getAddress()  + "' AND PermitNum ='" + de.getLicenseNumber() + "' AND CarType ='" + de.getDrivingLicense() + "'"; 
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

