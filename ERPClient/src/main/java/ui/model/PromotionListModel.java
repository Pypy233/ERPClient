package ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class PromotionListModel {
    private final SimpleStringProperty date;
    public CheckBox checkBox;

    public PromotionListModel(String d){
        checkBox = new CheckBox();
        this.date=new SimpleStringProperty(d);
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
