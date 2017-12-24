package vo;

public class GoodsSaleVO {
    /**
     * id
     */
    public int id;


    /**
     * 对应售出的商品
     */
    public GoodsVO vo;

    /**
     * 对应售出的商品数量
     */
    public int saleNumber;

    /**
     * 对应售出商品价格
     */
    public double price;

    /**
     * 备注
     */
    String remark;

    /**
     * 总额
     */
    double totalPrice = price * saleNumber;

    public GoodsSaleVO() {
    }

    public GoodsSaleVO(int id, GoodsVO vo, int saleNumber, double price, String remark, double totalPrice) {
        this.id = id;
        this.vo = vo;
        this.saleNumber = saleNumber;
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

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
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
