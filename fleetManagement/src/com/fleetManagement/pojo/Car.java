package com.fleetManagement.pojo;

import java.time.LocalDate;

import com.fleetManagement.pojo.anno.TableFiled;

//汽车信息管理

public class Car {
    @TableFiled(index = 0, name = "车号")
    private String no;
    @TableFiled(index = 1, name = "类型")
    private String type;
    @TableFiled(index = 2, name = "车主")
    private String owners;
    @TableFiled(index = 3, name = "发动机号")
    private String engineNo;
    @TableFiled(index = 4, name = "车驾号")
    private String drivingNo;
    @TableFiled(index = 5, name = "汽车品牌")
    private String band;
    @TableFiled(index = 6, name = "核定载客数")
    private String carryLimit;
    @TableFiled(index = 7, name = "颜色")
    private String color;
    @TableFiled(index = 8, name = "登记时间")
    private LocalDate checkTime;
    @TableFiled(index = 9, name = "发证时间")
    private LocalDate successTime;

    public Car(String no, String type, String owners, String engineNo, String drivingNo, String band, String carryLimit, String color, LocalDate checkTime, LocalDate successTime) {
        this.no = no;
        this.type = type;
        this.owners = owners;
        this.engineNo = engineNo;
        this.drivingNo = drivingNo;
        this.band = band;
        this.carryLimit = carryLimit;
        this.color = color;
        this.checkTime = checkTime;
        this.successTime = successTime;
    }

    public Car() {
		// TODO Auto-generated constructor stub
	}

	public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getDrivingNo() {
        return drivingNo;
    }

    public void setDrivingNo(String drivingNo) {
        this.drivingNo = drivingNo;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getCarryLimit() {
        return carryLimit;
    }

    public void setCarryLimit(String carryLimit) {
        this.carryLimit = carryLimit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDate checkTime) {
        this.checkTime = checkTime;
    }

    public LocalDate getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(LocalDate successTime) {
        this.successTime = successTime;
    }

    @Override
    public String toString() {
        return "{\r\n"  +
                "no='" + no + '\'' +
                ", \r\ntype='" + type + '\'' +
                ", \r\nowners='" + owners + '\'' +
                ", \r\nengineNo='" + engineNo + '\'' +
                ", \r\ndrivingNo='" + drivingNo + '\'' +
                ", \r\nband='" + band + '\'' +
                ", \r\ncarryLimit='" + carryLimit + '\'' +
                ", \r\ncolor='" + color + '\'' +
                ", \r\ncheckTime=" + checkTime +
                ", \r\nsuccessTime=" + successTime +
                "\r\n}";
    }
}