package ui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import vo.UserVO;

public class UserModel {
    private final SimpleStringProperty line;
    private final SimpleStringProperty name;
    private final SimpleStringProperty kind;
    private final SimpleStringProperty code;
    private final SimpleStringProperty log;
    UserVO userVO;
    CheckBox cb;
    public UserModel(UserVO userVO, int i){
        this.cb=new CheckBox();
        this.line=new SimpleStringProperty(i+"");
        this.name=new SimpleStringProperty(userVO.getName());
        this.kind=new SimpleStringProperty(userVO.getType());
        this.code=new SimpleStringProperty(userVO.getPassword());
        this.log=new SimpleStringProperty("0");
        this.userVO=userVO;
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

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getLog() {
        return log.get();
    }

    public SimpleStringProperty logProperty() {
        return log;
    }

    public void setLog(String log) {
        this.log.set(log);
    }

    public CheckBox getCb() {
        return cb;
    }

    public void setCb(CheckBox cb) {
        this.cb = cb;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }
}

