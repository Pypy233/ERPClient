package ui.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class SaleDetailResultModel {
    public SimpleStringProperty date;
    public SimpleStringProperty goodsName;
    public SimpleStringProperty type;
    public SimpleStringProperty ammount;
    public SimpleStringProperty price;
    public SimpleStringProperty sum;

    public SaleDetailResultModel(String d,String g,String t,String a,String p,String s ){
        this.date=new SimpleStringProperty(d);
        this.goodsName=new SimpleStringProperty(g);
        this.type=new SimpleStringProperty(t);
        this.ammount=new SimpleStringProperty(a);
        this.price=new SimpleStringProperty(p);
        this.sum=new SimpleStringProperty(s);
        }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getGoodsName() {
        return goodsName.get();
    }

    public SimpleStringProperty goodsNameProperty() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName.set(goodsName);
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

    public String getAmmount() {
        return ammount.get();
    }

    public SimpleStringProperty ammountProperty() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount.set(ammount);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getSum() {
        return sum.get();
    }

    public SimpleStringProperty sumProperty() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum.set(sum);
    }
}
