package ui.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.Main;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginOverviewController implements Initializable{

    private Main main;

    //退出按钮
    @FXML
    public Button exitButton ;

    //退出
    public void exit(ActionEvent e){
        main.exit();
    }


    //总经理登录按钮 跳转至登录页面
    @FXML
    public Button managerLoginButton;

    //总经理登录按钮 跳转至登录页面
    @FXML
    public Button accountantLoginButton;
    //总经理登录按钮 跳转至登录页面
    @FXML
    public Button stockLoginButton;
    //总经理登录按钮 跳转至登录页面
    @FXML
    public Button saleLoginButton;
    //管理员界面
    @FXML
    public Button adminButton;
    //
    @FXML
    public void adminLog(ActionEvent e){
        main.adminLogin("admin");
    }


    @FXML
    public void gotoLog(ActionEvent e){

       if( e.getSource()==managerLoginButton)
        main.gotoLog("总经理");
       else if( e.getSource()==accountantLoginButton)
            main.gotoLog("财务人员");
       else if( e.getSource()==stockLoginButton)
            main.gotoLog("库存管理人员");
       else if( e.getSource()==saleLoginButton)
            main.gotoLog("进货销售人员");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMain(Main main){
        this.main = main;
    }


}
