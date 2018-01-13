package ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class CheckModel {
    private final SimpleStringProperty type;
    private final SimpleStringProperty number;
    public CheckBox pass;
    public CheckBox fail;
    public CheckBox choose;

    public CheckBox getChoose() {
        return choose;
    }

    public void setChoose(CheckBox choose) {
        this.choose = choose;
    }

    public CheckModel(String t, String n){
        this.type=new SimpleStringProperty(t);
        this.number=new SimpleStringProperty(n);
        pass = new CheckBox();
        fail = new CheckBox();
        choose = new CheckBox();
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

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public CheckBox getPass() {
        return pass;
    }

    public void setPass(CheckBox pass) {
        this.pass = pass;
    }

    public CheckBox getFail() {
        return fail;
    }

    public void setFail(CheckBox fail) {
        this.fail = fail;
    }
}
