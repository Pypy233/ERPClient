package vo;

/**
 * Created by py on 2017/10/19.
 */
public class CommodityVO {
    /**
     * id
     */
    private int id;

    /**
     * 预设时间
     */
    public String presetTime;

    /**
     * 日期
     */
    public String date;

    /**
     * 出库数量
     */
    private int deliveryAmount;

    /**
     * 出库金额
     */
    private double deliveryMoney;

    /**
     * 入库数量
     */
    private int storageAmount;

    /**
     *  入库金额
     */
    private double storageMoney;

    /**
     * 进货数量
     */
    private int stockAmount;

    /**
     *  进货金额
     */
    private double stockMoney;

    /**
     * 销售数量
     */
    private int saleAmount;

    /**
     * 销售金额
     */
    private double saleMoney;


    public CommodityVO() {
    }


    public CommodityVO(int id, String presetTime, String date, int deliveryAmount,
                       double deliveryMoney, int storageAmount, double storageMoney,
                       int stockAmount, double stockMoney, int saleAmount, double saleMoney) {
        this.id = id;
        this.presetTime = presetTime;
        this.date = date;
        this.deliveryAmount = deliveryAmount;
        this.deliveryMoney = deliveryMoney;
        this.storageAmount = storageAmount;
        this.storageMoney = storageMoney;
        this.stockAmount = stockAmount;
        this.stockMoney = stockMoney;
        this.saleAmount = saleAmount;
        this.saleMoney = saleMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPresetTime() {
        return presetTime;
    }

    public void setPresetTime(String presetTime) {
        this.presetTime = presetTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(int deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public double getDeliveryMoney() {
        return deliveryMoney;
    }

    public void setDeliveryMoney(double deliveryMoney) {
        this.deliveryMoney = deliveryMoney;
    }

    public int getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(int storageAmount) {
        this.storageAmount = storageAmount;
    }

    public double getStorageMoney() {
        return storageMoney;
    }

    public void setStorageMoney(double storageMoney) {
        this.storageMoney = storageMoney;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public double getStockMoney() {
        return stockMoney;
    }

    public void setStockMoney(double stockMoney) {
        this.stockMoney = stockMoney;
    }

    public int getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(int saleAmount) {
        this.saleAmount = saleAmount;
    }

    public double getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(double saleMoney) {
        this.saleMoney = saleMoney;
    }
}
