package ui.model;

import javafx.beans.property.SimpleStringProperty;
import vo.GoodsVO;

public class ClassifyModel {
    private final SimpleStringProperty name;
    private final SimpleStringProperty kind;
    private final SimpleStringProperty id;

    public ClassifyModel(GoodsVO goodsVO){
        this.name=new SimpleStringProperty(goodsVO.getName());
        this.kind=new SimpleStringProperty(goodsVO.getType());
        this.id=new SimpleStringProperty(goodsVO.getNumber()+"");
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
}
