package ui.model;

import javafx.beans.property.SimpleStringProperty;
import vo.GoodsVO;

public class GoodsModel {

    /**
     * 商品编号
     */
    private final SimpleStringProperty number;

    /**
     * 商品名称
     */
    private final SimpleStringProperty name;

    /**
     * 商品类型
     */
    private  final SimpleStringProperty  type;

    /**
     * 库存数量
     */
    private  final SimpleStringProperty commodityNum;

    /**
     * 进价
     */
    private  final SimpleStringProperty  purchasePrice;

    /**
     * 零售价
     */
    private  final SimpleStringProperty retailPrice;

    /**
     * 最近进价
     */
    private final SimpleStringProperty  recentPurPrice;

    /**
     * *最近零售价
     */
    private final SimpleStringProperty  recentRetPrice;

    public GoodsModel(GoodsVO vo){
        if(vo == null){
            number = new SimpleStringProperty();
            name = new SimpleStringProperty();
            type = new SimpleStringProperty();
            commodityNum = new SimpleStringProperty();
            purchasePrice = new SimpleStringProperty();
            retailPrice = new SimpleStringProperty();
            recentPurPrice = new SimpleStringProperty();
            recentRetPrice = new SimpleStringProperty();
        }
        else{
            number = new SimpleStringProperty(vo.getNumber() + "");
            name = new SimpleStringProperty(vo.getName());
            type = new SimpleStringProperty(vo.getType());
            commodityNum = new SimpleStringProperty(vo.getCommodityNum() + "");
            purchasePrice = new SimpleStringProperty(vo.getPurchasePrice() + "");
            retailPrice  = new SimpleStringProperty(vo.getRetailPrice() + "");
            recentPurPrice = new SimpleStringProperty(vo.getRecentPurPrice() + "");
            recentRetPrice = new SimpleStringProperty(vo.getRecentRetPrice() + "");
        }
    }


    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number + "");
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getCommodityNum() {
        return commodityNum.get();
    }

    public SimpleStringProperty commodityNumProperty() {
        return commodityNum;
    }

    public void setCommodityNum(int commodityNum) {
        this.commodityNum.set(commodityNum + "");
    }

    public String getPurchasePrice() {
        return purchasePrice.get();
    }

    public SimpleStringProperty purchasePriceProperty() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice.set(purchasePrice + "");
    }

    public String getRetailPrice() {
        return retailPrice.get();
    }

    public SimpleStringProperty retailPriceProperty() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice.set(retailPrice + "");
    }

    public String getRecentPurPrice() {
        return recentPurPrice.get();
    }

    public SimpleStringProperty recentPurPriceProperty() {
        return recentPurPrice;
    }

    public void setRecentPurPrice(double recentPurPrice) {
        this.recentPurPrice.set(recentPurPrice + "");
    }

    public String getRecentRetPrice() {
        return recentRetPrice.get();
    }

    public SimpleStringProperty recentRetPriceProperty() {
        return recentRetPrice;
    }

    public void setRecentRetPrice(double recentRetPrice) {
        this.recentRetPrice.set(recentRetPrice + "");
    }
}
