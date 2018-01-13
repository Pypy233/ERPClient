package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class LogModel {
    private final SimpleStringProperty name;
    private final SimpleStringProperty operation;

    public LogModel (String n,String o){
        this.name=new SimpleStringProperty(n);
        this.operation=new SimpleStringProperty(o);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getOperation() {
        return operation.get();
    }

    public SimpleStringProperty operationProperty() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation.set(operation);
    }
}
