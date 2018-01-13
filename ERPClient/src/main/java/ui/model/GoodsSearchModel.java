package ui.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import vo.GoodsVO;

public class GoodsSearchModel {
    /**
     * 商品编号
     */
    private final SimpleIntegerProperty number;

    /**
     * 商品名称
     */
    private final SimpleStringProperty name;

    /**
     * 商品类型
     */
    private final SimpleStringProperty type;

    /**
     * 库存数量
     */
    private final SimpleIntegerProperty commodityNum;

    /**
     * 进价
     */
    private final SimpleDoubleProperty purchasePrice;

    /**
     * 零售价
     */
    private final SimpleDoubleProperty retailPrice;

    CheckBox box=new CheckBox();


    public GoodsSearchModel(GoodsVO vo) {
        number = new SimpleIntegerProperty(vo.getNumber());
        name = new SimpleStringProperty(vo.getName());
        type = new SimpleStringProperty(vo.getType());
        commodityNum = new SimpleIntegerProperty(vo.getCommodityNum() );
        purchasePrice = new SimpleDoubleProperty(vo.getPurchasePrice());
        retailPrice = new SimpleDoubleProperty(vo.getRetailPrice());
    }


    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
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

    public int getCommodityNum() {
        return commodityNum.get();
    }

    public SimpleIntegerProperty commodityNumProperty() {
        return commodityNum;
    }

    public void setCommodityNum(int commodityNum) {
        this.commodityNum.set(commodityNum);
    }

    public double getPurchasePrice() {
        return purchasePrice.get();
    }

    public SimpleDoubleProperty purchasePriceProperty() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice.set(purchasePrice);
    }

    public double getRetailPrice() {
        return retailPrice.get();
    }

    public SimpleDoubleProperty retailPriceProperty() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice.set(retailPrice);
    }
}
