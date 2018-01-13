package ui.model;

import javafx.beans.property.SimpleStringProperty;
import vo.GoodsVO;

public class ReturnStockModel {
    private final SimpleStringProperty line;
    private final SimpleStringProperty name;
    private final SimpleStringProperty price;
    private final SimpleStringProperty num;
    private final SimpleStringProperty total;
    private final SimpleStringProperty des;
    public ReturnStockModel(GoodsVO goodsVO,int i){
        line=new SimpleStringProperty(i+"");
        name=new SimpleStringProperty(goodsVO.getName());
        price=new SimpleStringProperty(goodsVO.getRetailPrice()+"");
        num=new SimpleStringProperty(goodsVO.getCommodityNum()+"");
        total=new SimpleStringProperty(goodsVO.getRetailPrice()*goodsVO.getCommodityNum()+"");
        des=new SimpleStringProperty("备注");
    }

    public String getLine() {
        return line.get();
    }

    public SimpleStringProperty lineProperty() {
        return line;
    }

    public void setLine(String line) {
        this.line.set(line);
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

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getNum() {
        return num.get();
    }

    public SimpleStringProperty numProperty() {
        return num;
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    public String getTotal() {
        return total.get();
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public void setTotal(String total) {
        this.total.set(total);
    }

    public String getDes() {
        return des.get();
    }

    public SimpleStringProperty desProperty() {
        return des;
    }

    public void setDes(String des) {
        this.des.set(des);
    }
}
