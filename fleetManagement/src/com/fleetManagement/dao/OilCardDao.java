package com.fleetManagement.dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fleetManagement.pojo.CarCost;
import com.fleetManagement.pojo.OilCard;
import com.fleetManagement.util.DBManager;

public class OilCardDao {
	public static void save(OilCard oilcard) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "insert into oilcard (CardNum, OilBrand, PayNo, PayCarNo, PayMoney, InNo, InMoney, InputTime, OilRemark) values('" +
		oilcard.getNo() + "','" + oilcard.getBand() + "','" + oilcard.getConsumeNo() + "','" + oilcard.getConsumeCarNo() + "','" 
		+ oilcard.getConsume() + "','" + oilcard.getRechargeNo() + "','" + oilcard.getRecharge() + "','" + oilcard.getUpdateTime() + "','"
		+ oilcard.getMemo() + "')";
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
		sql = "select * from oilcard";
		try {
			rs = db.executeQuery(sql);
			while(rs.next()){
				String CardNum = rs.getString(1);
				String OilBrand = rs.getString(2);
				String PayNo = rs.getString(3);
				String PayCarNo  = rs.getString(4);
				String PayMoney = rs.getString(5);
				String InNo = rs.getString(6);
				String InMoney = rs.getString(7);
				String InputTime = rs.getString(8);
				String OilRemark = rs.getString(9);
				String Oilinfs = CardNum + "," + OilBrand + "," + PayNo + "," + PayCarNo +
						"," + PayMoney + "," + InNo + "," + InMoney + "," + InputTime + "," + OilRemark; 
				list.add(Oilinfs);				
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
	
	public static List<OilCard> getAll() throws Exception {
		
		List<OilCard> ocd = new ArrayList<OilCard>();
		String sql1;
		DBManager db1 = new DBManager();
		ResultSet rs1 = null;
		OilCard oc; 
		sql1 = " select * from oilcard ";
		try {
			rs1 = db1.executeQuery(sql1);
			while(rs1.next())
			{
				oc = new OilCard();
				oc.setNo(rs1.getString(1));
				oc.setBand(rs1.getString(2));
				oc.setConsumeNo(rs1.getString(3));
				oc.setConsumeCarNo(rs1.getString(4));
				oc.setConsume(rs1.getDouble(5));
				oc.setRechargeNo(rs1.getString(6));
				oc.setRecharge(rs1.getDouble(7));
				oc.setUpdateTime(LocalDate.parse(rs1.getString(8)));
				oc.setMemo(rs1.getString(9));
				//cars = rs1.getString(1);
				ocd.add(oc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			db1.close();
		}
		
		return ocd;
		
	}
	
	
	public static void remove(OilCard car, OilCard info) throws Exception {
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "update oilcard set CardNum = '" + car.getNo() + "' ,OilBrand = '" + car.getBand() + "', PayNo ='" +
				car.getConsumeNo() + "', PayCarNo = '" + car.getConsumeCarNo() + "', PayMoney = '" + car.getConsume() + 
				"', InNo = '" + car.getRechargeNo() + "', InMoney = '" + car.getRecharge() +"', InputTime ='" + car.getUpdateTime() +
				"', OilRemark = '" + car.getMemo() + "' where CardNum = '" + info.getNo() + "'AND OilRemark = '" + info.getMemo() + "'AND OilBrand='"+
				info.getBand() +"'AND PayNo ='" + info.getConsumeNo() + "'AND PayCarNo ='" + info.getConsumeCarNo() + "'AND PayMoney='" + info.getConsume() +
				"'AND InNo ='" + info.getRechargeNo() + "'AND InMoney ='" + info.getRecharge()+"'AND InputTime ='" + info.getUpdateTime() +"'";
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
	
	public static void delete(OilCard oid) throws Exception{
		
		String sql;
		DBManager db = new DBManager();
		int rs;
		sql = "delete from oilcard where OilRemark = '" + oid.getMemo() + "'AND CardNum = '" + oid.getNo() + "'AND OilBrand='"+
				oid.getBand() +"'AND PayNo ='" + oid.getConsumeNo() + "'AND PayCarNo ='" + oid.getConsumeCarNo() + "'AND PayMoney='" + oid.getConsume() +
				"'AND InNo ='" + oid.getRechargeNo() + "'AND InMoney ='" + oid.getRecharge()+"'AND InputTime ='" + oid.getUpdateTime() +"'";
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
