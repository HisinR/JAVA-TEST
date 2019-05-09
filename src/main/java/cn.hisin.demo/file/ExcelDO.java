package cn.hisin.demo.file;

import java.util.Date;

/**
 * 测试
 *
 */
public class ExcelDO {

    private Date date;

    private String string;

    private Integer integer;

    private Double doub;
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Double getDoub() {
        return doub;
    }

    public void setDoub(Double doub) {
        this.doub = doub;
    }

    @Override
    public String toString() {
        return "ExcelDO{" +
                "date=" + date +
                ", string='" + string + '\'' +
                ", integer=" + integer +
                ", doub=" + doub +
                '}';
    }
}
