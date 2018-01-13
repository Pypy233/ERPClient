package ui.model;

import javafx.beans.property.SimpleStringProperty;
import vo.GoodsVO;

public class StockFreshModel {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty num;

    public StockFreshModel(GoodsVO goodsVO) {
        num =new SimpleStringProperty(goodsVO.getCommodityNum()+"");
        name=new SimpleStringProperty(goodsVO.getName());
        id=new SimpleStringProperty(goodsVO.getNumber()+"");
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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
}
