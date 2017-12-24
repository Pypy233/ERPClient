package vo;

import java.util.HashSet;
import java.util.Set;

public class StockReturnVO {
    /**
     * 单据编号
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
     * 供应商
     */
    String provider;

    /**
     * 仓库
     */
    int commodityNumber;

    /**
     * 操作员
     */
    String operator;

    /**
     * 入库商品
     */
    Set<GoodsStockReturnVO> stockSet = new HashSet<>();
    /**
     * 备注
     */
    String remark;

    /**
     * 总额合计
     */
    double totalPrice;

    /**
     * 单据状态
     */
    String state;

    public StockReturnVO() {
    }

    public StockReturnVO(String number, String date, int receiptNumber,
                         String provider, int commodityNumber, String operator,
                         Set<GoodsStockReturnVO> stockSet, String remark, double totalPrice) {
        this.number = number;
        this.date = date;
        this.receiptNumber = receiptNumber;
        this.provider = provider;
        this.commodityNumber = commodityNumber;
        this.operator = operator;
        this.stockSet = stockSet;
        this.remark = remark;
        this.totalPrice = totalPrice;
    }

    public StockReturnVO(String number, String date, int receiptNumber, String provider,
                         int commodityNumber, String operator, Set<GoodsStockReturnVO> stockSet,
                         String remark, double totalPrice, String state) {
        this.number = number;
        this.date = date;
        this.receiptNumber = receiptNumber;
        this.provider = provider;
        this.commodityNumber = commodityNumber;
        this.operator = operator;
        this.stockSet = stockSet;
        this.remark = remark;
        this.totalPrice = totalPrice;
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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(int commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Set<GoodsStockReturnVO> getStockSet() {
        return stockSet;
    }

    public void setStockSet(Set<GoodsStockReturnVO> stockSet) {
        this.stockSet = stockSet;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
