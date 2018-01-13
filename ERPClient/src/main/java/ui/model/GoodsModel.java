package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class GoodsModel {
    private final SimpleStringProperty type;
    private final SimpleStringProperty name;
    private final SimpleStringProperty ammount;
    private final SimpleStringProperty number;
    private final SimpleStringProperty in_price;
    private final SimpleStringProperty out_price;
    private final SimpleStringProperty r_inPrice;
    private final SimpleStringProperty r_outPrice;

    public String getAmmount() {
        return ammount.get();
    }

    public SimpleStringProperty ammountProperty() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount.set(ammount);
    }

    public GoodsModel(String t, String na, String a, String nu, String ip, String op, String rip, String rop){
        this.type=new SimpleStringProperty(t);
        this.name=new SimpleStringProperty(na);
        this.ammount=new SimpleStringProperty(a);
        this.number=new SimpleStringProperty(nu);
        this.in_price=new SimpleStringProperty(ip);
        this.out_price=new SimpleStringProperty(op);

        this.r_inPrice=new SimpleStringProperty(rip);
        this.r_outPrice=new SimpleStringProperty(rop);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getIn_price() {
        return in_price.get();
    }

    public SimpleStringProperty in_priceProperty() {
        return in_price;
    }

    public void setIn_price(String in_price) {
        this.in_price.set(in_price);
    }

    public String getOut_price() {
        return out_price.get();
    }

    public SimpleStringProperty out_priceProperty() {
        return out_price;
    }

    public void setOut_price(String out_price) {
        this.out_price.set(out_price);
    }

    public String getR_inPrice() {
        return r_inPrice.get();
    }

    public SimpleStringProperty r_inPriceProperty() {
        return r_inPrice;
    }

    public void setR_inPrice(String r_inPrice) {
        this.r_inPrice.set(r_inPrice);
    }

    public String getR_outPrice() {
        return r_outPrice.get();
    }

    public SimpleStringProperty r_outPriceProperty() {
        return r_outPrice;
    }

    public void setR_outPrice(String r_outPrice) {
        this.r_outPrice.set(r_outPrice);
    }
}
