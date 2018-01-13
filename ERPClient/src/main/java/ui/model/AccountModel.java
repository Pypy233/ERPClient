package ui.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class AccountModel {
    private final SimpleStringProperty account;
    private final SimpleDoubleProperty money;
    public CheckBox checkBox;


    public SimpleStringProperty accountProperty() {
        return account;
    }

    public SimpleDoubleProperty moneyProperty() {
        return money;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public AccountModel(String a, double m){
        this.checkBox=new CheckBox();
        this.account=new SimpleStringProperty(a);
        this.money=new SimpleDoubleProperty(m);

    }

    public String getAccount(){
        return account.get();
    }

    public void setAccount(String str){
        account.set(str);
    }

    public double getMoney(){
        return money.get();
    }

    public void setMoney(double m){
        money.set(m);
    }

}
