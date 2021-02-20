package com.fleetManagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBManager
{

	private Connection con;
	private Statement stm;
	private ResultSet rs;

	public DBManager()
	{
		try {
			// 1.加载驱动
			String driverName = "com.mysql.cj.jdbc.Driver"; // mysql
			System.out.println("---"+driverName);	// jdbc驱动描述符,实际上就是driver类在包中的完整路径
			Class.forName(driverName);  

			// 2.建立与数据库的连接
			String url = "jdbc:mysql://localhost:3306/fleetManagement?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false"; // 数据库连接字符串，一般使用统一资源定位符（url）的形式

			String user = "root"; // 连接数据库时的用户
			String password = "hbw12178"; // 连接数据库时的密码
			System.out.println("user="+user+" password="+password);
			con = DriverManager.getConnection(url, user, password);
			
/*			Context ctx=new InitialContext();
			DataSource ds=(DataSource) ctx.lookup("java:comp/env/jdbc/studentmanager");//java:comp/env/为前缀,jdbc/studentmanager为名字
			con=ds.getConnection();*/
			//3.创建语句对象
			stm = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public ResultSet executeQuery(String sql)
		throws Exception
	{
		stm = con.createStatement(2004, 1007);
		rs = stm.executeQuery(sql);
		return rs;
	}

	public int executeUpdate(String sql)
		throws Exception
	{
		int ret = 0;
		stm = con.createStatement();
		ret = stm.executeUpdate(sql);
		return ret;
	}


	
	
	

	public void close()
		throws Exception
	{
		if (rs != null)
			rs.close();
		if (stm != null)
			stm.close();
		if (con != null)
			con.close();
	}

	
}