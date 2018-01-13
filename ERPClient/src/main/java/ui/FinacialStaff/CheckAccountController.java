package ui.FinacialStaff;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import rmi.RemoteHelper;
import ui.Main;
import vo.UserVO;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckAccountController implements Initializable {
    @FXML
    Label label_name;
    @FXML
    Label label_number;
    private Main main;
    private UserVO userVO;

    RemoteHelper remoteHelper = RemoteHelper.getInstance();

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public CheckAccountController(){

    }

    public void setMain(Main main, UserVO uservo){
        this.main=main;
        this.userVO=uservo;
        label_name.setText(userVO.getName());

    }
}
