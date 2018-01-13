package ui.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import vo.GoodsVO;


public class GoodsInfoSearchModel {
    CheckBox box;
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
    private final SimpleStringProperty type;

    /**
     * 库存数量
     */
    private final SimpleStringProperty commodityNum;

    /**
     * 进价
     */
    private final SimpleStringProperty purchasePrice;

    /**
     * 零售价
     */
    private final SimpleStringProperty retailPrice;


    public GoodsInfoSearchModel(GoodsVO vo) {
        box=new CheckBox();
        number = new SimpleStringProperty(vo.getNumber()+"");
        name = new SimpleStringProperty(vo.getName());
        type = new SimpleStringProperty(vo.getType());
        commodityNum = new SimpleStringProperty(vo.getCommodityNum()+"" );
        purchasePrice = new SimpleStringProperty(vo.getPurchasePrice()+"");
        retailPrice = new SimpleStringProperty(vo.getRetailPrice()+"");
    }

    public CheckBox getBox() {
        return box;
    }

    public void setBox(CheckBox box) {
        this.box = box;
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
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

    public String getCommodityNum() {
        return commodityNum.get();
    }

    public SimpleStringProperty commodityNumProperty() {
        return commodityNum;
    }

    public void setCommodityNum(String commodityNum) {
        this.commodityNum.set(commodityNum);
    }

    public String getPurchasePrice() {
        return purchasePrice.get();
    }

    public SimpleStringProperty purchasePriceProperty() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice.set(purchasePrice);
    }

    public String getRetailPrice() {
        return retailPrice.get();
    }

    public SimpleStringProperty retailPriceProperty() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice.set(retailPrice);
    }
}
