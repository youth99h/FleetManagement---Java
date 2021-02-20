package com.fleetManagement.dao;

import com.fleetManagement.pojo.Car;
import com.fleetManagement.pojo.Driver;
import com.fleetManagement.pojo.User;
import com.fleetManagement.util.DBManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CarDao {
	public static void save(Car car) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "insert into car (CarNum, RegisterTime, CertificateTime, Type, CarOwner, EngineNum, Num, CarBrand, CarPeople, CarColor) values('" +
		car.getNo() + "','" + car.getCheckTime() + "','" + car.getSuccessTime() + "','" + car.getType() + "','" 
		+ car.getOwners() + "','" + car.getEngineNo() + "','" + car.getDrivingNo() + "','" + car.getBand() + "','" + car.getCarryLimit() + "','"
		+ car.getColor() + "')";
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
		sql = "select * from car";
		try {
			rs = db.executeQuery(sql);
			while(rs.next()){
				String CarNum = rs.getString(1);
				String RegisterTime = rs.getString(2);
				String CertificateTime = rs.getString(3);
				String Type = rs.getString(4);
				String CarOwner = rs.getString(5);
				String EngineNum = rs.getString(6);
				String Num = rs.getString(7);
				String CarBrand = rs.getString(8);
				String CarPeople = rs.getString(9);
				String CarColor = rs.getString(10);
				String Carinfs = CarNum + "," + RegisterTime + "," + CertificateTime + "," + Type +
						"," + CarOwner + "," + EngineNum + "," + Num + "," + CarBrand + "," + CarPeople
						+ "," + CarColor;
				list.add(Carinfs);				
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
	
	public static Object[] getCarNos() throws Exception {
		List<Car> cars = new ArrayList<Car>();
		String sql1;
		DBManager db1 = new DBManager();
		ResultSet rs1 = null;
		Car car; 
		sql1 = " select CarNum from car ";
		try {
			rs1 = db1.executeQuery(sql1);
			while(rs1.next())
			{
				car = new Car();
				car.setNo(rs1.getString(1));
				//cars = rs1.getString(1);
				cars.add(car);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db1.close();
		}
		
		return cars.stream().map(Car::getNo).collect(Collectors.toList()).toArray();
	}
	
public static List<Car> getAll() throws Exception {
		
		List<Car> ca = new ArrayList<Car>();
		String sql1;
		DBManager db1 = new DBManager();
		ResultSet rs1 = null;
		Car car;
		sql1 = " select * from car ";
		try {
			rs1 = db1.executeQuery(sql1);
			while(rs1.next())
			{
				car = new Car();
				car.setNo(rs1.getString(1));
				car.setCheckTime(LocalDate.parse(rs1.getString(2)));
				car.setSuccessTime(LocalDate.parse(rs1.getString(3)));
				car.setType(rs1.getString(4));
				car.setOwners(rs1.getString(5));
				car.setEngineNo(rs1.getString(6));
				car.setDrivingNo(rs1.getString(7));
				car.setBand(rs1.getString(8));
				car.setCarryLimit(rs1.getString(9));
				car.setColor(rs1.getString(10));
				
				ca.add(car);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db1.close();
		}
		
		return ca;
		
	}
	
	
	public static void remove(Car car, Car info) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "update car set CarNum = '" + car.getNo() + "' ,RegisterTime = '" + car.getCheckTime() + "', CertificateTime ='" +
		car.getSuccessTime() + "', Type = '" + car.getType() + "', CarOwner = '" + car.getOwners() + "', EngineNum = '" + car.getEngineNo() +
		"', Num = '" + car.getDrivingNo() + "', CarBrand = '" + car.getBand() + "', CarPeople = '" + car.getCarryLimit() + "', CarColor ='" + 
		car.getColor() +"' where CarNum = '" + info.getNo() + "'AND RegisterTime ='" + info.getCheckTime() + "'AND CertificateTime = '" +
				info.getSuccessTime() + "'AND Type = '" + info.getType() + "' AND CarOwner ='" + info.getOwners() + "' AND EngineNum ='" + info.getEngineNo() +
				"'AND Num ='" + info.getDrivingNo() + "' AND CarBrand ='" + info.getBand() + "' AND CarPeople ='" + info.getCarryLimit() + "' AND CarColor ='" +
				info.getColor() + "'";
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


	public static void delete(Car cr) throws Exception{
		
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "delete from car where CarNum = '" + cr.getNo() + "'AND RegisterTime ='" + cr.getCheckTime() + "'AND CertificateTime = '" +
		cr.getSuccessTime() + "'AND Type = '" + cr.getType() + "' AND CarOwner ='" + cr.getOwners() + "' AND EngineNum ='" + cr.getEngineNo() +
		"'AND Num ='" + cr.getDrivingNo() + "' AND CarBrand ='" + cr.getBand() + "' AND CarPeople ='" + cr.getCarryLimit() + "' AND CarColor ='" +
		cr.getColor() + "'";
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
