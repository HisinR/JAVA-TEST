package cn.hisin.demo.file;


import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Objects;

public class ExcelVO {

    private String college;

    private String waterAmount;

    private String managerFee;

    private String waterElecCosts;

    private String waterFee;

    private String electricityFee;

    private String hotWaterAmount;

    private String electricityAmount;

    private String recharge;

    private Date date;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(String waterAmount) {
        this.waterAmount = waterAmount;
    }

    public String getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(String waterFee) {
        this.waterFee = waterFee;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getHotWaterAmount() {
        return hotWaterAmount;
    }

    public void setHotWaterAmount(String hotWaterAmount) {
        this.hotWaterAmount = hotWaterAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getManagerFee() {
        return managerFee;
    }

    public void setManagerFee(String managerFee) {
        this.managerFee = managerFee;
    }

    public String getWaterElecCosts() {
        return waterElecCosts;
    }

    public void setWaterElecCosts(String waterElecCosts) {
        this.waterElecCosts = waterElecCosts;
    }

    public String getElectricityAmount() {
        return electricityAmount;
    }

    public void setElectricityAmount(String electricityAmount) {
        this.electricityAmount = electricityAmount;
    }

    public String getRecharge() {
        return recharge;
    }

    public void setRecharge(String recharge) {
        this.recharge = recharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExcelVO excelVO = (ExcelVO) o;
        return Objects.equals(college, excelVO.college) &&
                Objects.equals(waterAmount, excelVO.waterAmount) &&
                Objects.equals(managerFee, excelVO.managerFee) &&
                Objects.equals(waterElecCosts, excelVO.waterElecCosts) &&
                Objects.equals(waterFee, excelVO.waterFee) &&
                Objects.equals(electricityFee, excelVO.electricityFee) &&
                Objects.equals(hotWaterAmount, excelVO.hotWaterAmount) &&
                Objects.equals(electricityAmount, excelVO.electricityAmount) &&
                Objects.equals(recharge, excelVO.recharge) &&
                Objects.equals(date, excelVO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(college, waterAmount, managerFee, waterElecCosts, waterFee, electricityFee, hotWaterAmount, electricityAmount, recharge, date);
    }

    @Override
    public String toString() {
        return "ExcelVO{" +
                "college='" + college + '\'' +
                ", waterAmount='" + waterAmount + '\'' +
                ", managerFee='" + managerFee + '\'' +
                ", waterElecCosts='" + waterElecCosts + '\'' +
                ", waterFee='" + waterFee + '\'' +
                ", electricityFee='" + electricityFee + '\'' +
                ", hotWaterAmount='" + hotWaterAmount + '\'' +
                ", electricityAmount='" + electricityAmount + '\'' +
                ", recharge='" + recharge + '\'' +
                ", date=" + date +
                '}';
    }
}
