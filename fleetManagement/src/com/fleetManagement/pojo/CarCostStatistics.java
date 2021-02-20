package com.fleetManagement.pojo;

import com.fleetManagement.pojo.anno.TableFiled;

//汽车费用统计

public class CarCostStatistics {

  @TableFiled(index = 0, name = "车牌号")
  private String no;
  @TableFiled(index = 1, name = "维修总费用(元)")
  private double fixCost;
  @TableFiled(index = 2, name = "备注")
  private String memo;

  public CarCostStatistics(String no, double fixCost, String memo) {
      this.no = no;
      this.fixCost = fixCost;
      this.memo = memo;
  }

  public String getNo() {
      return no;
  }

  public void setNo(String no) {
      this.no = no;
  }

  public double getFixCost() {
      return fixCost;
  }

  public void setFixCost(double fixCost) {
      this.fixCost = fixCost;
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
              ", \r\n fixCost=" + fixCost +
              ", \r\n memo='" + memo + '\'' +
              "\r\n}";
  }
}
