package vo;

import java.util.HashSet;
import java.util.Set;

public class SaleReturnVO {
    /**
     * 单据编号（XSTHD-yyyyMMdd-xxxxx）
     */
    String number;

    /**
     * 当天日期
     */
    String date;


    /**
     * 当天单据号码
     */
    int receiptNumber;

    /**
     * 客户（仅显示销售商）
     */
    String retailer;

    /**
     * 业务员
     */
    String salesman;

    /**
     * 操作员（当前登录系统的用户）
     */
    String operator;

    /**
     * 仓库
     */
    int commodityNumber;

    /**
     * 出货商品
     */
    Set<GoodsSaleReturnVO> saleReturnSet = new HashSet<>();

    /**
     * 总额
     */
    private double payPrice;

    /**
     * 备注
     */
    String remark;

    /**
     * 单据状态
     */
    String state;

    public SaleReturnVO() {
    }

    public SaleReturnVO(String number, String date, int receiptNumber, String retailer,
                        String salesman, String operator, int commodityNumber,
                        Set<GoodsSaleReturnVO> saleReturnSet, double payPrice, String remark) {
        this.number = number;
        this.date = date;
        this.receiptNumber = receiptNumber;
        this.retailer = retailer;
        this.salesman = salesman;
        this.operator = operator;
        this.commodityNumber = commodityNumber;
        this.saleReturnSet = saleReturnSet;
        this.payPrice = payPrice;
        this.remark = remark;
    }

    public SaleReturnVO(String number, String date, int receiptNumber, String retailer,
                        String salesman, String operator, int commodityNumber,
                        Set<GoodsSaleReturnVO> saleReturnSet, double payPrice, String remark, String state) {
        this.number = number;
        this.date = date;
        this.receiptNumber = receiptNumber;
        this.retailer = retailer;
        this.salesman = salesman;
        this.operator = operator;
        this.commodityNumber = commodityNumber;
        this.saleReturnSet = saleReturnSet;
        this.payPrice = payPrice;
        this.remark = remark;
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(int commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public Set<GoodsSaleReturnVO> getSaleReturnSet() {
        return saleReturnSet;
    }

    public void setSaleReturnSet(Set<GoodsSaleReturnVO> saleReturnSet) {
        this.saleReturnSet = saleReturnSet;
    }

    public double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
