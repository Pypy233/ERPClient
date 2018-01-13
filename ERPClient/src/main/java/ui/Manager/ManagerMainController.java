package ui.Manager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import rmi.RemoteHelper;
import ui.Main;
import vo.UserVO;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerMainController implements Initializable{
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public ManagerMainController(){

    }//一个构造函数

    public void setMain(Main main, UserVO uservo){
        this.main=main;
        this.userVO=uservo;
        label_name.setText(uservo.getName());
    }

    @FXML//跳转到查看销售明细界面
    public void gotoSaleDetail(){ main.gotoSaleDetail(userVO);}

    @FXML//跳转到查看经营历程界面
    public void gotoSaleProcess(){ main.gotoSaleProcess(userVO);}

    @FXML//跳转到查看经营情况界面
    public void gotoSaleInformation(){ main.gotoSaleInformation(userVO);}

    @FXML//跳转到审批单据界面
    public void seeThrough(){
        main.gotoSeeThrough(userVO);
    }

    @FXML//跳转到制定销售策略界面
    public void gotoPromotion(){
main.gotoPromotion(userVO);
    }

    @FXML//登出
    public void logout(){
main.backToMain();;
    }
    @FXML
    public void checkLog(){
main.gotoLog(userVO);
    }
}
