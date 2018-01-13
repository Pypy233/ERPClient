package ui.model;

import javafx.beans.property.SimpleStringProperty;
import vo.GoodsVO;


public class StockCheckModel {
    private final SimpleStringProperty line;
    private final SimpleStringProperty name;
    private final SimpleStringProperty kind;
    private final SimpleStringProperty commodityNum;
    private final SimpleStringProperty averagePrice;



    public StockCheckModel(int l,GoodsVO goodsVO) {

            line = new SimpleStringProperty( (l+1)+"");
            name = new SimpleStringProperty(goodsVO.getName());
            kind = new SimpleStringProperty(goodsVO.getType());
            commodityNum = new SimpleStringProperty(goodsVO.getCommodityNum() + "");
            averagePrice = new SimpleStringProperty((goodsVO.getRecentRetPrice()+ goodsVO.getRecentPurPrice())/2+ "");

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

    public String getKind() {
        return kind.get();
    }

    public SimpleStringProperty kindProperty() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind.set(kind);
    }

    public String getCommodityNum() {
        return commodityNum.get();
    }

    public SimpleStringProperty commodityNumProperty() {
        return commodityNum;
    }

    public void setCommodityNum(String commodityNum) {
        this.commodityNum.set(commodityNum);
    }

    public String getAveragePrice() {
        return averagePrice.get();
    }

    public SimpleStringProperty averagePriceProperty() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice.set(averagePrice);
    }
}

