package ui.model;

import javafx.beans.property.SimpleStringProperty;

public class ClientModel {
    private final SimpleStringProperty type;
    private final SimpleStringProperty name;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty shouldGet;
    private final SimpleStringProperty shouldPay;

    public ClientModel(String t,String n,String p,String sG,String sP){
        this.type=new SimpleStringProperty(t);
        this.name=new SimpleStringProperty(n);
        this.phone=new SimpleStringProperty(p);
        this.shouldGet=new SimpleStringProperty(sG);
        this.shouldPay=new SimpleStringProperty(sP);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getShouldGet() {
        return shouldGet.get();
    }

    public SimpleStringProperty shouldGetProperty() {
        return shouldGet;
    }

    public void setShouldGet(String shouldGet) {
        this.shouldGet.set(shouldGet);
    }

    public String getShouldPay() {
        return shouldPay.get();
    }

    public SimpleStringProperty shouldPayProperty() {
        return shouldPay;
    }

    public void setShouldPay(String shouldPay) {
        this.shouldPay.set(shouldPay);
    }
}
