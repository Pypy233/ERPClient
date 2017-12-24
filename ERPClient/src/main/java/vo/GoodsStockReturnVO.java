package vo;

public class GoodsStockReturnVO {
    /**
     * id
     */
    public int id;


    /**
     * 对应进货退货的商品
     */
    public GoodsVO vo;

    /**
     * 对应进货退货的商品数量
     */
    public int stockReturnNumber;

    /**
     * 备注
     */
    String remark;

    /**
     * 总额
     */
    double totalPrice = stockReturnNumber * vo.getPurchasePrice();

    public GoodsStockReturnVO() {
    }

    public GoodsStockReturnVO(int id, GoodsVO vo, int stockReturnNumber, String remark, double totalPrice) {
        this.id = id;
        this.vo = vo;
        this.stockReturnNumber = stockReturnNumber;
        this.remark = remark;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GoodsVO getVo() {
        return vo;
    }

    public void setVo(GoodsVO vo) {
        this.vo = vo;
    }

    public int getStockReturnNumber() {
        return stockReturnNumber;
    }

    public void setStockReturnNumber(int stockReturnNumber) {
        this.stockReturnNumber = stockReturnNumber;
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
}
