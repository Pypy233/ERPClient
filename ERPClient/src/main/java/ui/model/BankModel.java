package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class BankModel {
    private final SimpleStringProperty bankName;
    private final SimpleStringProperty bankMoney;

    public BankModel(String n,String m){
        this.bankName=new SimpleStringProperty(n);
        this.bankMoney=new SimpleStringProperty(m);
    }

    public String getBankName() {
        return bankName.get();
    }

    public SimpleStringProperty bankNameProperty() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName.set(bankName);
    }

    public String getBankMoney() {
        return bankMoney.get();
    }

    public SimpleStringProperty bankMoneyProperty() {
        return bankMoney;
    }

    public void setBankMoney(String bankMoney) {
        this.bankMoney.set(bankMoney);
    }
}
