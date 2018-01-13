package ui.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ModifyController implements Initializable{

    private Main main;
    private String type;
    //退出按钮
    @FXML
    public Button exitButton ;

    //退出
    public void exit(ActionEvent e){
        main.exit();
    }
    //修改密码按钮
    @FXML
    public Button modifyButton;



    //人员类型label
    @FXML
    public Label userKindLB;
    //用户名输入框
    @FXML
    public TextField adminNameTF;
    //密码输入框
    @FXML
    public PasswordField adminOriginCodeTF;
    //确认密码输入框
    @FXML
    public PasswordField adminNewCodeTF;


    //返回上一层按钮
    @FXML
    public Button backButton;



    //转向注册界面
    //返回上一层
    @FXML
    public void gotoLog(ActionEvent e) {
        main.gotoLog(type);
    }

    //修改密码，跳转至登录界面
    @FXML
    public void modifyCode(ActionEvent e) throws RemoteException {
        String name=adminNameTF.getText();
        String originCode=adminOriginCodeTF.getText();
        String newCode=adminNewCodeTF.getText();
        RemoteHelper helper=RemoteHelper.getInstance();
        if(name.equals("")||originCode.equals("")||newCode.equals("")){
            AlertUtil.showWarningAlert("");
        }
        else if(helper.getUserBLService().check(name,originCode)!= ResultMessage.Success){
            AlertUtil.showErrorAlert("原账户输入有误");
        }
        else if(helper.getUserBLService().check(name,originCode)== ResultMessage.Success){
            AlertUtil.showInformationAlert("密码修改成功");
            helper.getUserBLService().updatePassword(name,newCode,type);
            helper.getLogBlService().addLog(helper.getUserBLService().getUserVO(name),"修改密码",ResultMessage.Success);
            main.gotoLog(type);
        }

    }


    public void setMain(Main main,String type){
        this.main=main;
        this.type=type;
        userKindLB.setText(type);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
