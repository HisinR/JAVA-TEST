package cn.hisin.demo.file;


import java.util.Objects;

public class ExcelVO {

    private String college;

    private String waterAmount;

    private String waterFee;

    private String electricityFee;

    private String hotWaterAmount;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExcelVO excelVO = (ExcelVO) o;
        return college.equals(excelVO.college) &&
                waterAmount.equals(excelVO.waterAmount) &&
                waterFee.equals(excelVO.waterFee) &&
                electricityFee.equals(excelVO.electricityFee) &&
                hotWaterAmount.equals(excelVO.hotWaterAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(college, waterAmount, waterFee, electricityFee, hotWaterAmount);
    }
}
