package ui.model;

import javafx.beans.property.SimpleStringProperty;
import vo.SaleReturnVO;
import vo.SaleVO;
import vo.StockReturnVO;
import vo.StockVO;

public class ReceiptListModel {
    private final SimpleStringProperty number;
    private final SimpleStringProperty kind;
    private final SimpleStringProperty process;
    private final SimpleStringProperty date;
    private final SimpleStringProperty client;
    private final SimpleStringProperty operator;
    public ReceiptListModel(StockVO stockVO){
        this.number=new SimpleStringProperty(stockVO.getNumber());
        this.kind=new SimpleStringProperty("进货");
        this.process=new SimpleStringProperty(stockVO.getState());
        this.date=new SimpleStringProperty(stockVO.getDate());
        this.client=new SimpleStringProperty(stockVO.getProvider());
        this.operator=new SimpleStringProperty("4");
    }
    public ReceiptListModel(SaleVO saleVO){
        this.number=new SimpleStringProperty(saleVO.getNumber());
        this.kind=new SimpleStringProperty("销售");
        this.process=new SimpleStringProperty(saleVO.getState());
        this.date=new SimpleStringProperty(saleVO.getDate());
        this.client=new SimpleStringProperty(saleVO.getRetailer());
        this.operator=new SimpleStringProperty("4");
    }
    public ReceiptListModel(StockReturnVO stockReturnVO){
        this.number=new SimpleStringProperty(stockReturnVO.getNumber());
        this.kind=new SimpleStringProperty("进货退货");
        this.process=new SimpleStringProperty(stockReturnVO.getState());
        this.date=new SimpleStringProperty(stockReturnVO.getDate());
        this.client=new SimpleStringProperty(stockReturnVO.getProvider());
        this.operator=new SimpleStringProperty("4");
    }
    public ReceiptListModel(SaleReturnVO saleReturnVO){
        this.number=new SimpleStringProperty(saleReturnVO.getNumber());
        this.kind=new SimpleStringProperty("销售退货");
        this.process=new SimpleStringProperty(saleReturnVO.getState());
        this.date=new SimpleStringProperty(saleReturnVO.getDate());
        this.client=new SimpleStringProperty(saleReturnVO.getRetailer());
        this.operator=new SimpleStringProperty("4");
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

    public String getKind() {
        return kind.get();
    }

    public SimpleStringProperty kindProperty() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind.set(kind);
    }

    public String getProcess() {
        return process.get();
    }

    public SimpleStringProperty processProperty() {
        return process;
    }

    public void setProcess(String process) {
        this.process.set(process);
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

    public String getClient() {
        return client.get();
    }

    public SimpleStringProperty clientProperty() {
        return client;
    }

    public void setClient(String client) {
        this.client.set(client);
    }

    public String getOperator() {
        return operator.get();
    }

    public SimpleStringProperty operatorProperty() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator.set(operator);
    }
}
