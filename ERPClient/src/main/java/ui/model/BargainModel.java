package ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;


public class BargainModel {
    private final SimpleStringProperty goods1;
    private final SimpleStringProperty ammount1;
    private final SimpleStringProperty goods2;
    private final SimpleStringProperty ammount2;
    private final SimpleStringProperty goods3;
    private final SimpleStringProperty ammount3;
    private final SimpleStringProperty discount;

    public String getDiscount() {
        return discount.get();
    }

    public SimpleStringProperty discountProperty() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount.set(discount);
    }

    public CheckBox checkBox;

    public BargainModel(String g1,String a1,String g2,String a2,String g3,String a3,String d){
        this.goods1=new SimpleStringProperty(g1);
        this.ammount1=new SimpleStringProperty(a1);
        this.goods2=new SimpleStringProperty(g2);
        this.ammount2=new SimpleStringProperty(a2);
        this.goods3=new SimpleStringProperty(g3);
        this.ammount3=new SimpleStringProperty(a3);
        this.discount=new SimpleStringProperty(d);
        checkBox=new CheckBox();
    }

    public String getGoods1() {
        return goods1.get();
    }

    public SimpleStringProperty goods1Property() {
        return goods1;
    }

    public void setGoods1(String goods1) {
        this.goods1.set(goods1);
    }

    public String getAmmount1() {
        return ammount1.get();
    }

    public SimpleStringProperty ammount1Property() {
        return ammount1;
    }

    public void setAmmount1(String ammount1) {
        this.ammount1.set(ammount1);
    }

    public String getGoods2() {
        return goods2.get();
    }

    public SimpleStringProperty goods2Property() {
        return goods2;
    }

    public void setGoods2(String goods2) {
        this.goods2.set(goods2);
    }

    public String getAmmount2() {
        return ammount2.get();
    }

    public SimpleStringProperty ammount2Property() {
        return ammount2;
    }

    public void setAmmount2(String ammount2) {
        this.ammount2.set(ammount2);
    }

    public String getGoods3() {
        return goods3.get();
    }

    public SimpleStringProperty goods3Property() {
        return goods3;
    }

    public void setGoods3(String goods3) {
        this.goods3.set(goods3);
    }

    public String getAmmount3() {
        return ammount3.get();
    }

    public SimpleStringProperty ammount3Property() {
        return ammount3;
    }

    public void setAmmount3(String ammount3) {
        this.ammount3.set(ammount3);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
