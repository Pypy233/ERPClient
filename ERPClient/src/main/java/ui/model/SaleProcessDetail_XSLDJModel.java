package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class SaleProcessDetail_XSLDJModel {
    private final SimpleStringProperty number;
    private final SimpleStringProperty name;
    private final SimpleStringProperty type;
    private final SimpleStringProperty ammuont;
    private final SimpleStringProperty price;
    private final SimpleStringProperty sum;
    private final SimpleStringProperty remark;

    public SaleProcessDetail_XSLDJModel(String num,String nam,String t,String a,String p,String s,String r){
        this.number=new SimpleStringProperty(num);
        this.name=new SimpleStringProperty(nam);
        this.type=new SimpleStringProperty(t);
        this.ammuont=new SimpleStringProperty(a);
        this.price=new SimpleStringProperty(p);
        this.sum=new SimpleStringProperty(s);
        this.remark=new SimpleStringProperty(r);
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

    public String getAmmuont() {
        return ammuont.get();
    }

    public SimpleStringProperty ammuontProperty() {
        return ammuont;
    }

    public void setAmmuont(String ammuont) {
        this.ammuont.set(ammuont);
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

    public String getRemark() {
        return remark.get();
    }

    public SimpleStringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }
}
