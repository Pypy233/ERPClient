package ui.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import vo.MemberVO;

public class MemberSearchModel {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty kind;
    private final SimpleIntegerProperty level;
    private final SimpleStringProperty operator;
    public MemberSearchModel(MemberVO memberVO){
        id=new SimpleStringProperty(memberVO.getNumber()+"");
        name=new SimpleStringProperty(memberVO.getName());
        kind=new SimpleStringProperty(memberVO.getMemberClass());
        level=new SimpleIntegerProperty(memberVO.getLevel());
        operator=new SimpleStringProperty(memberVO.getManagePerson());
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getKind() {
        return kind.get();
    }

    public void setKind(String kind) {
        this.kind.set(kind);
    }

    public int getLevel() {
        return level.get();
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public String getOperator() {
        return operator.get();
    }

    public void setOperator(String operator) {
        this.operator.set(operator);
    }
}
