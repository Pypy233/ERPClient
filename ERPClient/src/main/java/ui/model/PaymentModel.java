package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class PaymentModel {
    private final SimpleStringProperty name;
    private final SimpleStringProperty money;
    private final SimpleStringProperty remark;

    public PaymentModel(String n,String m,String r){
        this.name=new SimpleStringProperty(n);
        this.money=new SimpleStringProperty(m);
        this.remark=new SimpleStringProperty(r);
    }

    public String getBank() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setBank(String name) {
        this.name.set(name);
    }

    public String getMoney() {
        return money.get();
    }

    public SimpleStringProperty moneyProperty() {
        return money;
    }

    public void setMoney(String money) {
        this.money.set(money);
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
