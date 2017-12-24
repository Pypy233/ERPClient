package vo;

public class GoodsStockVO {
    /**
     * id
     */
    public int id;
    /**
     * 对应进货的商品类
     */
    public GoodsVO vo;

    /**
     * 对应进货的商品数量
     */

    public int stockNumber;

    /**
     * 备注
     */
    String remark;

    /**
     * 总额合计
     */
    double totalPrice = vo.getPurchasePrice() * stockNumber;

    public GoodsStockVO() {
    }

    public GoodsStockVO(int id, GoodsVO vo, int stockNumber, String remark, double totalPrice) {
        this.id = id;
        this.vo = vo;
        this.stockNumber = stockNumber;
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

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
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
