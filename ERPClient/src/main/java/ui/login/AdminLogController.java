package ui.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import objects.ResultMessage;
import rmi.RemoteHelper;
import service.blservice.UserBLService;
import ui.Main;
import ui.util.AlertUtil;
import vo.AdminVO;
import vo.UserVO;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AdminLogController implements Initializable {
    private String type;
    private Main main;

    //退出按钮
    @FXML
    public Button exitButton ;

    //退出
    public void exit(ActionEvent e){
        main.exit();
    }
    //人员类型label
    @FXML
    public Label userKindLB;
    //登录按钮
    @FXML
    public Button loginButton;


    //用户名输入框
    @FXML
    public TextField adminNameTF;
    //密码输入框
    @FXML
    public PasswordField adminCodeTF;



    //返回上一层按钮
    @FXML
    public Button backButton;






    //检查账户及密码并登陆转换至人员主界面
    @FXML
    public void gotoLogin(ActionEvent e) throws RemoteException {
        RemoteHelper helper = RemoteHelper.getInstance();
        String name = adminNameTF.getText();
        String code = adminCodeTF.getText();

        if(name.equals("")||code.equals("")){
            AlertUtil.showWarningAlert("用户登录信息请填写完整");
        }
        else if (name.equals("admin")&&code.equals("admin"))
        {
            AdminVO adminVO= new AdminVO(1,"admin","admin");
            main.gotoAdminMain(adminVO);
        }
        else{
            AlertUtil.showErrorAlert("仅管理员权限，请重新输入");
        }
    }


    //返回上一层（初始界面）
    @FXML
    public void gotoBackOverview(ActionEvent e) {
        main.backToMain();
    }

    public void setMain(Main main,String type){
        this.main=main;
        this.type=type;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
