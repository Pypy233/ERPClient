package vo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by py on 2017/10/20.
 */
public class SaleVO {
    /**
     * 单据编号（XSD-yyyyMMdd-xxxxx）
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
    Set<GoodsSaleVO> saleSet = new HashSet<>();

    /**
     * 折让前总额
     */
    private double totalPrice;

    /**
     * 折让金额
     */
    private double discount;

    /**
     * 代金券金额
     */
    private double voucher;

    /**
     * 折让后总额
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

    public SaleVO() {
    }

    /**
     *
     * @param number
     * @param date
     * @param receiptNumber
     * @param retailer
     * @param salesman
     * @param operator
     * @param commodityNumber
     * @param saleSet
     * @param totalPrice
     * @param discount
     * @param voucher
     * @param payPrice
     * @param remark
     */
    public SaleVO(String number, String date, int receiptNumber, String retailer,
                  String salesman, String operator, int commodityNumber, Set<GoodsSaleVO> saleSet,
                  double totalPrice, double discount, double voucher, double payPrice, String remark) {
        this.number = number;
        this.date = date;
        this.receiptNumber = receiptNumber;
        this.retailer = retailer;
        this.salesman = salesman;
        this.operator = operator;
        this.commodityNumber = commodityNumber;
        this.saleSet = saleSet;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.voucher = voucher;
        this.payPrice = payPrice;
        this.remark = remark;
    }

    public SaleVO(String number, String date, int receiptNumber, String retailer, String salesman,
                  String operator, int commodityNumber, Set<GoodsSaleVO> saleSet, double totalPrice,
                  double discount, double voucher, double payPrice, String remark, String state) {
        this.number = number;
        this.date = date;
        this.receiptNumber = receiptNumber;
        this.retailer = retailer;
        this.salesman = salesman;
        this.operator = operator;
        this.commodityNumber = commodityNumber;
        this.saleSet = saleSet;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.voucher = voucher;
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

    public Set<GoodsSaleVO> getSaleSet() {
        return saleSet;
    }

    public void setSaleSet(Set<GoodsSaleVO> saleSet) {
        this.saleSet = saleSet;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getVoucher() {
        return voucher;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
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
