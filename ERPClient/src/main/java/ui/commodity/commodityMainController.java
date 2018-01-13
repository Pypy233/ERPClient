package ui.commodity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;


public class commodityMainController implements Initializable {

    private Main main;
    private UserVO userVO;

    //退出按钮
    @FXML
    public Button exitButton ;

    //退出
    public void exit(ActionEvent e){
        userVO.setLogin(false);
        main.exit();
    }

    //左侧“商品分类”按钮
    @FXML
    public Button classifyButton;
    //左侧“商品管理”按钮
    @FXML
    public Button goodsButton;
    //左侧“库存”按钮
    @FXML
    public Button stockButton;


    //UserInfo 用户名
    @FXML
    public Label userNameLB;



    //右上角“登出”按钮
    @FXML
    public Button logoutButton;


    //跳转商品分类界面
    @FXML
    public void gotoClassify(ActionEvent e){
        main.gotoCommodityClassify(userVO);
    }
    //跳转商品管理界面
    @FXML
    public void gotoGoods(ActionEvent e){
        main.gotoCommodityGoods(userVO);
    }

    //跳转库存界面
    @FXML
    public void gotoStock(ActionEvent e){
        main.gotoCommodityStock(userVO);
    }

    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        RemoteHelper helper=RemoteHelper.getInstance();
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);
    }

    public void setMain(Main main,UserVO userVO){
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("User "+userVO.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
