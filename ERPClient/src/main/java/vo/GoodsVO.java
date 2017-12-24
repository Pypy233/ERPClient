package vo;

/**
 * Created by py on 2017/10/19.
 */
public class GoodsVO {
    /**
     * 商品编号
     */
    private int number;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品类型
     */
    private String type;

    /**
     * 库存数量
     */
    private int commodityNum;

    /**
     * 进价
     */
    private double purchasePrice;

    /**
     * 零售价
     */
    private double retailPrice;

    /**
     * 最近进价
     */
    private double recentPurPrice;

    /**
     * *最近零售价
     */
    private double recentRetPrice;

    public GoodsVO() {
    }

    /**
     *
     * @param number
     * @param name
     * @param type
     * @param commodityNum
     * @param purchasePrice
     * @param retailPrice
     * @param recentPurPrice
     * @param recentRetPrice
     */
    public GoodsVO(int number, String name, String type, int commodityNum,
                   double purchasePrice, double retailPrice, double recentPurPrice, double recentRetPrice) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.commodityNum = commodityNum;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.recentPurPrice = recentPurPrice;
        this.recentRetPrice = recentRetPrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(int commodityNum) {
        this.commodityNum = commodityNum;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getRecentPurPrice() {
        return recentPurPrice;
    }

    public void setRecentPurPrice(double recentPurPrice) {
        this.recentPurPrice = recentPurPrice;
    }

    public double getRecentRetPrice() {
        return recentRetPrice;
    }

    public void setRecentRetPrice(double recentRetPrice) {
        this.recentRetPrice = recentRetPrice;
    }
}