package ui.model;

import javafx.beans.property.SimpleStringProperty;
import vo.*;

public class StockLookModel {
    private final SimpleStringProperty id;
    private final SimpleStringProperty client;
    private final SimpleStringProperty warehouse;
    private final SimpleStringProperty good;
    private final SimpleStringProperty number;
    private final SimpleStringProperty changeWay;
    private final SimpleStringProperty operator;
    private final SimpleStringProperty totalPrice;
    private final SimpleStringProperty description;


    public StockLookModel(GoodsStockVO goodsStockVO,StockVO stockVO){

            id = new SimpleStringProperty(stockVO.getNumber());
            client = new SimpleStringProperty(stockVO.getProvider());
            warehouse = new SimpleStringProperty(  "1");
            good=new SimpleStringProperty(goodsStockVO.getVo().getName());
            number=new SimpleStringProperty(goodsStockVO.getVo().getCommodityNum()+"");
            changeWay=new SimpleStringProperty("进货");
            operator = new SimpleStringProperty(stockVO.getOperator()+ "");
            totalPrice = new SimpleStringProperty(goodsStockVO.getTotalPrice()+ "");
            description = new SimpleStringProperty(goodsStockVO.getRemark());
    }


    public StockLookModel(GoodsSaleVO goodsSaleVO, SaleVO saleVO){

        id = new SimpleStringProperty(saleVO.getNumber());
        client = new SimpleStringProperty(saleVO.getRetailer());
        warehouse = new SimpleStringProperty(  "1");
        good=new SimpleStringProperty(goodsSaleVO.getVo().getName());
        number=new SimpleStringProperty(goodsSaleVO.getVo().getNumber()+"");
        changeWay=new SimpleStringProperty("销售");
        operator = new SimpleStringProperty(saleVO.getOperator()+ "");
        totalPrice = new SimpleStringProperty(goodsSaleVO.getTotalPrice()+ "");
        description = new SimpleStringProperty(goodsSaleVO.getRemark());
    }


    public StockLookModel(GoodsStockReturnVO goodsStockReturnVO, StockReturnVO stockReturnVO){

        id = new SimpleStringProperty(stockReturnVO.getNumber());
        client = new SimpleStringProperty(stockReturnVO.getProvider());
        warehouse = new SimpleStringProperty(  "1");
        good=new SimpleStringProperty(goodsStockReturnVO.getVo().getName());
        number=new SimpleStringProperty(goodsStockReturnVO.getVo().getNumber()+"");
        changeWay=new SimpleStringProperty("进货退货");
        operator = new SimpleStringProperty(stockReturnVO.getOperator()+ "");
        totalPrice = new SimpleStringProperty(goodsStockReturnVO.getTotalPrice()+ "");
        description = new SimpleStringProperty(goodsStockReturnVO.getRemark());
    }


    public StockLookModel(GoodsSaleReturnVO goodsSaleReturnVO, SaleReturnVO saleReturnVO){

        id = new SimpleStringProperty(saleReturnVO.getNumber());
        client = new SimpleStringProperty(saleReturnVO.getRetailer());
        warehouse = new SimpleStringProperty(  "1");
        good=new SimpleStringProperty(goodsSaleReturnVO.getVo().getName());
        number=new SimpleStringProperty(goodsSaleReturnVO.getVo().getNumber()+"");
        changeWay=new SimpleStringProperty("销售退货");
        operator = new SimpleStringProperty(saleReturnVO.getOperator()+ "");
        totalPrice = new SimpleStringProperty(goodsSaleReturnVO.getTotalPrice()+ "");
        description = new SimpleStringProperty(goodsSaleReturnVO.getRemark());
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
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

    public String getWarehouse() {
        return warehouse.get();
    }

    public SimpleStringProperty warehouseProperty() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse.set(warehouse);
    }

    public String getGood() {
        return good.get();
    }

    public SimpleStringProperty goodProperty() {
        return good;
    }

    public void setGood(String good) {
        this.good.set(good);
    }

    public String getChangeWay() {
        return changeWay.get();
    }

    public SimpleStringProperty changeWayProperty() {
        return changeWay;
    }

    public void setChangeWay(String changeWay) {
        this.changeWay.set(changeWay);
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

    public String getTotalPrice() {
        return totalPrice.get();
    }

    public SimpleStringProperty totalPriceProperty() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice.set(totalPrice);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
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
}
