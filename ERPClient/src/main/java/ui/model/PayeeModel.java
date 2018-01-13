package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class PayeeModel {
    private final SimpleStringProperty bank;
    private final SimpleStringProperty money;
    private final SimpleStringProperty remark;

    public PayeeModel(String b,String m,String r){
        this.bank=new SimpleStringProperty(b);
        this.money=new SimpleStringProperty(m);
        this.remark=new SimpleStringProperty(r);
    }

    public String getBank() {
        return bank.get();
    }

    public SimpleStringProperty bankProperty() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank.set(bank);
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
