package ui.FinacialStaff;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import rmi.RemoteHelper;
import ui.Main;
import vo.UserVO;

import java.net.URL;
import java.util.ResourceBundle;

public class FinacialStaffMainController implements Initializable {
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
        }
        public FinacialStaffMainController(){

        }//一个构造函数

    @FXML
    public void setMain(Main main, UserVO uservo){
        this.main=main;
        this.userVO=uservo;
        label_name.setText(uservo.getName());
        }

    @FXML//跳转到账户管理界面
    public void gotoManageAccount(){
        main.gotoManageAccount(userVO);
    }

    @FXML//跳转到制定收款单界面
    public void gotoMakePayee(){
        main.gotoMakePayee(userVO);
    }

    @FXML//跳转到制定付款单界面
    public void gotoMakePayment(){
        main.gotoMakePayment(userVO);
    }

    @FXML//跳转到制定现金单界面
    public void gotoMakeCash(){main.gotoMakeCash(userVO);}

    @FXML//跳转到查看销售明细界面
    public void gotoSaleDetail(){ main.gotoSaleDetail(userVO);}

    @FXML//跳转到查看经营历程界面
    public void gotoSaleProcess(){ main.gotoSaleProcess(userVO);}

    @FXML//跳转到查看经营情况界面
    public void gotoSaleInformation(){
        main.gotoSaleInformation(userVO);
    }

    @FXML//跳转到期初建账界面
    public void gotoMakeBill(){ main.gotoMakeBill(userVO);}

    @FXML//登出
    public void logOut(){main.backToMain();}

    @FXML//查看草稿
    public void checkDraft(){main.gotoCheckDraft(userVO);}

    @FXML//查看日志
    public void checkLog(){main.gotoLog(userVO);}

    @FXML//查看已提交单据
    public void checkSub(){main.gotoCheckSubmit(userVO);}
}
