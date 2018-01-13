package ui.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import vo.MemberVO;

import javafx.scene.control.CheckBox;



public class MemberInfoSearchModel {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty kind;
    private final SimpleIntegerProperty level;
    private final SimpleStringProperty operator;
     CheckBox box = new CheckBox();
    public MemberInfoSearchModel(MemberVO memberVO){
        id=new SimpleStringProperty(memberVO.getNumber()+"");
        name=new SimpleStringProperty(memberVO.getName());
        kind=new SimpleStringProperty(memberVO.getMemberClass());
        level=new SimpleIntegerProperty(memberVO.getLevel());
        operator=new SimpleStringProperty(memberVO.getManagePerson());
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

    public String getKind() {
        return kind.get();
    }

    public SimpleStringProperty kindProperty() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind.set(kind);
    }

    public int getLevel() {
        return level.get();
    }

    public SimpleIntegerProperty levelProperty() {
        return level;
    }

    public void setLevel(int level) {
        this.level.set(level);
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

    public CheckBox getBox() {
        return box;
    }

    public void setBox(CheckBox box) {
        this.box = box;
    }
}
