package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class BYDModel {
    private final SimpleStringProperty number;
    private final SimpleStringProperty name;
    private final SimpleStringProperty type;
    private final SimpleStringProperty ammuont;
    private final SimpleStringProperty price;

    public BYDModel(String num,String nam,String t,String a,String p) {
        this.number = new SimpleStringProperty(num);
        this.name = new SimpleStringProperty(nam);
        this.type = new SimpleStringProperty(t);
        this.ammuont = new SimpleStringProperty(a);
        this.price=new SimpleStringProperty(p);
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
}
