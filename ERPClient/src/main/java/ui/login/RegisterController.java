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
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private Main main;
    private String type;
    //退出按钮
    @FXML
    public Button exitButton ;

    //退出
    public void exit(ActionEvent e){
        main.exit();
    }
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
    public PasswordField adminCodeTF;
    //确认密码输入框
    @FXML
    public PasswordField adminCodeAgainTF;


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
        System.out.println(name);
        System.out.println(code );
        UserVO userVO=new UserVO();
        userVO.setName(name);
        userVO.setPassword(code);
        userVO.setType(type);
        userVO.setLogin(false);
        if(name.equals("")||code.equals("")||codeAgain.equals("")){
            AlertUtil.showWarningAlert("注册信息请填写完整");
        }
        else if(!code.equals(codeAgain)){
            AlertUtil.showErrorAlert("对不起，您两次输入的密码不一致。");
        }
        //else if(helper.getUserBLService().register(userVO) == ResultMessage.Fail){
            //AlertUtil.showErrorAlert("此用户已被注册");
        //}
        else{
            helper.getUserBLService().register(userVO);
            AlertUtil.showInformationAlert("注册成功，请登录");
            helper.getLogBlService().addLog(userVO,"注册",ResultMessage.Success);
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
