package vo;

public class GoodsSaleReturnVO {
    /**
     * id
     */
    private int id;

    /**
     * 对应销售退货的商品
     */
    private GoodsVO vo;

    /**
     * 对应销售退货的商品数量
     */
    private int saleReturnNumber;

    /**
     * 对应销售退货商品价格
     */
    public double price;

    /**
     * 备注
     */
    String remark;

    /**
     * 总额
     */
    double totalPrice = price * saleReturnNumber;

    public GoodsSaleReturnVO() {
    }

    public GoodsSaleReturnVO(int id, GoodsVO vo, int saleReturnNumber, double price, String remark, double totalPrice) {
        this.id = id;
        this.vo = vo;
        this.saleReturnNumber = saleReturnNumber;
        this.price = price;
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

    public int getSaleReturnNumber() {
        return saleReturnNumber;
    }

    public void setSaleReturnNumber(int saleReturnNumber) {
        this.saleReturnNumber = saleReturnNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
