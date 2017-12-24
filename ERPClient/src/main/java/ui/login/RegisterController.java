package ui.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private Main main;
    private String type;
    //注册按钮
    @FXML
    public Button registerButton;



    //人员类型label
    @FXML
    public Label userKindLB;
    //用户名输入框
    @FXML
    public TextField adminNameTF;
    //密码输入框
    @FXML
    public TextField adminCodeTF;
    //确认密码输入框
    @FXML
    public TextField adminCodeAgainTF;


    //返回上一层按钮
    @FXML
    public Button backButton;



    //注册账户
    @FXML
    public void register(ActionEvent e) throws RemoteException {
        RemoteHelper helper=RemoteHelper.getInstance();
        String name=adminNameTF.getText();
        String code=adminCodeTF.getText();
        String codeAgain=adminCodeAgainTF.getText();
        if(name==""||code==""||codeAgain==""){
            AlertUtil.showWarningAlert("注册信息请填写完整");
        }
        else if(!code.equals(codeAgain)){
            AlertUtil.showErrorAlert("对不起，您两次输入的密码不一致。");
        }
        else if(helper.getUserBLService().getUserVO(name)!=null){
            AlertUtil.showErrorAlert("此用户已被注册");
        }
        else if(helper.getUserBLService().getUserVO(name)==null){
            UserVO userVO=new UserVO();
            userVO.setName(name);
            userVO.setPassword(code);
            userVO.setType(type);
            userVO.setLogin(false);
            helper.getUserBLService().register(userVO);
            AlertUtil.showInformationAlert("注册成功，请登录");
            main.gotoLog(type);
        }
    }

    //返回上一层 转向登录界面
    @FXML
    public void gotoLog() {
        main.gotoLog(type);
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
