package com.fleetManagement.pojo;

import java.time.LocalDate;
import java.util.UUID;

import com.fleetManagement.pojo.anno.TableFiled;

// 油卡

public class OilCard {
    @TableFiled(index = 0, name = "油卡编号")
    private String no;
    @TableFiled(index = 1, name = "石油品牌")
    private String band;
    private double quota;
    @TableFiled(index =2, name = "消费编号")
    private String consumeNo;
    @TableFiled(index = 3, name = "消费车号")
    private String consumeCarNo;
    @TableFiled(index = 4, name = "消费金额（元）")
    private double consume;
    @TableFiled(index = 5, name = "充值编号")
    private String rechargeNo;
    @TableFiled(index = 6, name = "充值金额（元）")
    private double recharge;
    @TableFiled(index = 7, name = "记录时间")
    private LocalDate updateTime;
    @TableFiled(index = 8, name = "备注")
    private String memo;

    public OilCard(String no, String band, double consume,String consumeCarNo, double recharge, LocalDate updateTime, String memo) {
        this.no = no;
        this.band = band;
        this.consume = consume;
        this.consumeCarNo = consumeCarNo;
        this.recharge = recharge;
        this.updateTime = updateTime;
        this.memo = memo;
        if (this.consume>0) {
            this.consumeNo =  UUID.randomUUID().toString();
        }
        if (this.recharge>0) {
            this.rechargeNo = UUID.randomUUID().toString();
        }
    }

    public OilCard() {
		// TODO Auto-generated constructor stub
	}

	public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public double getQuota() {
        return quota;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }

    public String getConsumeNo() {
        return consumeNo;
    }

    public void setConsumeNo(String consumeNo) {
        this.consumeNo = consumeNo;
    }

    public String getConsumeCarNo() {
        return consumeCarNo;
    }

    public void setConsumeCarNo(String consumeCarNo) {
        this.consumeCarNo = consumeCarNo;
    }

    public double getConsume() {
        return consume;
    }

    public void setConsume(double consume) {
        this.consume = consume;
    }

    public String getRechargeNo() {
        return rechargeNo;
    }

    public void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    public double getRecharge() {
        return recharge;
    }

    public void setRecharge(double recharge) {
        this.recharge = recharge;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "{\r\n" +
                "no='" + no + '\'' +
                ", \r\n  band='" + band + '\'' +
                ", \r\n  quota=" + quota +
                ", \r\n  consumeNo='" + consumeNo + '\'' +
                ", \r\n  consumeCarNo='" + consumeCarNo + '\'' +
                ", \r\n  consume=" + consume +
                ", \r\n  rechargeNo='" + rechargeNo + '\'' +
                ", \r\n  recharge=" + recharge +
                ", \r\n  updateTime=" + updateTime +
                ", \r\n  memo='" + memo + '\'' +
                "\r\n}";
    }
}
