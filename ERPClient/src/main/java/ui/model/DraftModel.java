package ui.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class DraftModel {
    private final SimpleStringProperty type;
    private final SimpleStringProperty number;
    public CheckBox checkBox;

    public DraftModel(String t,String n){
        this.type=new SimpleStringProperty(t);
        this.number=new SimpleStringProperty(n);
        checkBox=new CheckBox();
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
