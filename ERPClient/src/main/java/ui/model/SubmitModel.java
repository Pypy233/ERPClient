package ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class SubmitModel {
    private final SimpleStringProperty type;
    private final SimpleStringProperty number;
    private final SimpleStringProperty state;
    public CheckBox checkBox;

    public SubmitModel (String t ,String n ,String s){
        checkBox=new CheckBox();
        this.type=new SimpleStringProperty(t);
        this.number=new SimpleStringProperty(n);
        this.state=new SimpleStringProperty(s);
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

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
