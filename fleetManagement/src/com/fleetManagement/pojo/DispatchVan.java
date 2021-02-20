package com.fleetManagement.pojo;

import java.time.LocalDate;

import com.fleetManagement.pojo.anno.TableFiled;

// 出（派）车记录

public class DispatchVan {
    @TableFiled(index = 0, name = "车号")
    private String no;
    @TableFiled(index = 1, name = "出发地")
    private String to;
    @TableFiled(index = 2, name = "目的地")
    private String from;
    @TableFiled(index = 3, name = "发车时间")
    private LocalDate startTime;
    @TableFiled(index = 4, name = "司机姓名")
    private String driverName;

    public DispatchVan(String no, String to, String from, LocalDate startTime, String driverName) {
        this.no = no;
        this.to = to;
        this.from = from;
        this.startTime = startTime;
        this.driverName = driverName;
    }

    public DispatchVan() {
		// TODO Auto-generated constructor stub
	}

	public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return "{\r\n" +
                "no='" + no + '\'' +
                ", \r\n to='" + to + '\'' +
                ", \r\n from='" + from + '\'' +
                ", \r\n startTime=" + startTime +
                ", \r\n driverName='" + driverName + '\'' +
                "\r\n}";
    }
}
