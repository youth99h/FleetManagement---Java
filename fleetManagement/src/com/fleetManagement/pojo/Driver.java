package com.fleetManagement.pojo;

import java.time.LocalDate;

import com.fleetManagement.pojo.anno.TableFiled;

//司机

public class Driver {

    @TableFiled(index = 0, name = "姓名")
    private String name;
    @TableFiled(index = 1, name = "性别")
    private char sex;
    @TableFiled(index = 2, name = "出生日期")
    private LocalDate birthday;
    @TableFiled(index = 3, name = "地址")
    private String address;
    // 初次领证时间
    @TableFiled(index = 4, name = "初次领证时间")
    private LocalDate certificateTime;
    // 执照号码
    @TableFiled(index = 5, name = "执照号码")
    private String licenseNumber;
    // 准驾车型
    @TableFiled(index = 6, name = "准驾车型")
    private String drivingLicense;
    // 有效起始时间
    @TableFiled(index = 7, name = "有效起始时间")
    private LocalDate startTime;
    // 有效结束时间
    @TableFiled(index = 8, name = "有效结束时间")
    private LocalDate endTime;

    public Driver(){}

    public Driver(String name, char sex, LocalDate birthday, String address, LocalDate certificateTime, String licenseNumber, String drivingLicense, LocalDate startTime, LocalDate endTime) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.certificateTime = certificateTime;
        this.licenseNumber = licenseNumber;
        this.drivingLicense = drivingLicense;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCertificateTime() {
        return certificateTime;
    }

    public void setCertificateTime(LocalDate certificateTime) {
        this.certificateTime = certificateTime;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "{\r\n" +
                "name='" + name + '\'' +
                ", \r\n sex=" + sex +
                ", \r\n birthday=" + birthday +
                ", \r\n address='" + address + '\'' +
                ", \r\n certificateTime=" + certificateTime +
                ", \r\n licenseNumber='" + licenseNumber + '\'' +
                ", \r\n drivingLicense='" + drivingLicense + '\'' +
                ", \r\n startTime=" + startTime +
                ", \r\n endTime=" + endTime +
                "\r\n}";
    }
}
