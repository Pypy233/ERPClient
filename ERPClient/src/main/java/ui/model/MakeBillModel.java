package ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class MakeBillModel {
    private final SimpleStringProperty date;
    CheckBox checkBox;

    public MakeBillModel(String d){
        this.date=new SimpleStringProperty(d);
        checkBox=new CheckBox();
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
