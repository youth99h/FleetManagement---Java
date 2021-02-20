package com.fleetManagement.dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fleetManagement.pojo.Car;
import com.fleetManagement.pojo.CarCost;
import com.fleetManagement.pojo.Driver;
import com.fleetManagement.util.DBManager;

public class CarCostDao {
	public static void save(CarCost carcost) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "insert into carcost (CostNum, ServiceTime, ServiceMany, ServiceUtil, ServiceContent, ServiceAccessories, Remark) values('" +
				carcost.getNo() + "','" + carcost.getFixTime() + "','" + carcost.getFixCost() + "','" + carcost.getFixCompany() + "','" 
		+ carcost.getFixContext() + "','" + carcost.getFixParts() + "','"  + carcost.getMemo() + "')";
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
		sql = "select * from carcost";
		try {
			rs = db.executeQuery(sql);
			while(rs.next()){
				String CostNum = rs.getString(1);
				String ServiceTime = rs.getString(2);
				String ServiceMany = rs.getString(3);
				String ServiceUtil = rs.getString(4);
				String ServiceContent = rs.getString(5);
				String ServiceAccessories = rs.getString(6);
				String Remark = rs.getString(7);
				String CarCostinfs = CostNum + "," + ServiceTime + "," + ServiceMany + "," + ServiceUtil +
						"," + ServiceContent + "," + ServiceAccessories + "," + Remark; 
				list.add(CarCostinfs);				
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
	
	public static List<CarCost> getAll() throws Exception {
		
		List<CarCost> co = new ArrayList<CarCost>();
		String sql1;
		DBManager db1 = new DBManager();
		ResultSet rs1 = null;
		CarCost carcost; 
		sql1 = " select * from carcost ";
		try {
			rs1 = db1.executeQuery(sql1);
			while(rs1.next())
			{
				carcost = new CarCost();
				carcost.setNo(rs1.getString(1));
				carcost.setFixTime(rs1.getString(2));
				carcost.setFixCost(rs1.getDouble(3));
				carcost.setFixCompany(rs1.getString(4));
				carcost.setFixContext(rs1.getString(5));
				carcost.setFixParts(rs1.getString(6));
				carcost.setMemo(rs1.getString(7));
				//cars = rs1.getString(1);
				co.add(carcost);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db1.close();
		}
		
		return co;
		
	}
	
	
	public static void remove(CarCost car, CarCost info) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "update carcost set CostNum = '" + car.getNo() + "' ,ServiceTime = '" + car.getFixTime() + "', ServiceMany ='" +
				car.getFixCost() + "', ServiceUtil = '" + car.getFixCompany() + "', ServiceContent = '" + car.getFixContext() + 
				"', ServiceAccessories = '" + car.getFixParts() + "', Remark = '" + car.getMemo() +
				"' where CostNum = '" + info.getNo() + "'AND Remark = '" + info.getMemo() + "'AND ServiceAccessories ='" + info.getFixParts() +
				"'AND ServiceContent ='" + info.getFixContext() + "'AND ServiceUtil ='" + info.getFixCompany() + "'AND ServiceMany ='" + info.getFixCost() +
				"'AND ServiceTime ='" + info.getFixTime() + "'";
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
	
	public static void delete(CarCost cor) throws Exception{
		
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "delete from carcost where Remark = '" + cor.getMemo() + "'AND CostNum = '" + cor.getNo() + "'AND ServiceTime ='" + cor.getFixTime() + 
				"'AND ServiceMany = '" + cor.getFixCost() + "'AND ServiceUtil ='" + cor.getFixCompany() + "'AND ServiceContent = '" + cor.getFixContext() +
				"'AND ServiceAccessories = '" + cor.getFixParts() + "'";
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
