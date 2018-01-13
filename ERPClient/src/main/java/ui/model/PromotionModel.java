package ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class PromotionModel {
    private final SimpleStringProperty gift1;
    private final SimpleStringProperty ammount1;
    private final SimpleStringProperty gift2;
    private final SimpleStringProperty ammount2;
    private final SimpleStringProperty gift3;
    private final SimpleStringProperty ammount3;
    private final SimpleStringProperty max;
    private final SimpleStringProperty min;
    private final SimpleStringProperty substitute;
    private final SimpleStringProperty discount;

    public CheckBox level1;
    public CheckBox level2;
    public CheckBox level3;
    public CheckBox level4;
    public CheckBox level5;

    public PromotionModel(String gift1, String ammount1, String gift2, String ammount2, String gift3, String ammount3, String max, String min, String substitute, String discount) {
        this.gift1 = new SimpleStringProperty(gift1);
        this.ammount1 = new SimpleStringProperty(ammount1);
        this.gift2 = new SimpleStringProperty(gift2);
        this.ammount2 = new SimpleStringProperty(ammount2);
        this.gift3 = new SimpleStringProperty(gift3);
        this.ammount3 = new SimpleStringProperty(ammount3);
        this.max = new SimpleStringProperty(max);
        this.min = new SimpleStringProperty(min);
        this.substitute = new SimpleStringProperty(substitute);
        this.discount = new SimpleStringProperty(discount);

        level1=new CheckBox();
        level2=new CheckBox();
        level3=new CheckBox();
        level4=new CheckBox();
        level5=new CheckBox();
    }

    public String getGift1() {
        return gift1.get();
    }

    public SimpleStringProperty gift1Property() {
        return gift1;
    }

    public void setGift1(String gift1) {
        this.gift1.set(gift1);
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

    public String getGift2() {
        return gift2.get();
    }

    public SimpleStringProperty gift2Property() {
        return gift2;
    }

    public void setGift2(String gift2) {
        this.gift2.set(gift2);
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

    public String getGift3() {
        return gift3.get();
    }

    public SimpleStringProperty gift3Property() {
        return gift3;
    }

    public void setGift3(String gift3) {
        this.gift3.set(gift3);
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

    public String getMax() {
        return max.get();
    }

    public SimpleStringProperty maxProperty() {
        return max;
    }

    public void setMax(String max) {
        this.max.set(max);
    }

    public String getMin() {
        return min.get();
    }

    public SimpleStringProperty minProperty() {
        return min;
    }

    public void setMin(String min) {
        this.min.set(min);
    }

    public String getSubstitute() {
        return substitute.get();
    }

    public SimpleStringProperty substituteProperty() {
        return substitute;
    }

    public void setSubstitute(String substitute) {
        this.substitute.set(substitute);
    }

    public String getDiscount() {
        return discount.get();
    }

    public SimpleStringProperty discountProperty() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount.set(discount);
    }

    public CheckBox getLevel1() {
        return level1;
    }

    public void setLevel1(CheckBox level1) {
        this.level1 = level1;
    }

    public CheckBox getLevel2() {
        return level2;
    }

    public void setLevel2(CheckBox level2) {
        this.level2 = level2;
    }

    public CheckBox getLevel3() {
        return level3;
    }

    public void setLevel3(CheckBox level3) {
        this.level3 = level3;
    }

    public CheckBox getLevel4() {
        return level4;
    }

    public void setLevel4(CheckBox level4) {
        this.level4 = level4;
    }

    public CheckBox getLevel5() {
        return level5;
    }

    public void setLevel5(CheckBox level5) {
        this.level5 = level5;
    }
}
