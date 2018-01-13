package ui.commodity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import vo.GoodsVO;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class commodityStockMainController implements Initializable {

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
    //右下角“返回上一层”按钮
    @FXML
    public Button backButton;

    //下面跳转 查看库存
    @FXML
    public Button toStockLookButton;
    //下面跳转 “库存 盘点库存.fxml”按钮
    @FXML
    public Button toStockChangeButton;
    //下面跳转 赠送单
    @FXML
    public Button toStockPresentButton;
    //下面跳转 库存更新
    @FXML
    public Button toStockRefreshButton;




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



    //点击按钮跳转盘点库存界面
    @FXML
    public void gotoStockLook(ActionEvent e){
        main.gotoCommodityStockLook(userVO);
    }

    //点击按钮跳转盘点库存界面
    @FXML
    public void gotoStockCheck(ActionEvent e){
        main.gotoCommodityStockCheck(userVO);
    }

    //点击按钮跳转盘点库存界面
    @FXML
    public void gotoStockPresent(ActionEvent e){
        main.gotoCommodityStockPresent(userVO,new ArrayList<GoodsVO>());
    }

    //点击按钮跳转盘点库存界面
    @FXML
    public void gotoStockRefresh(ActionEvent e){
        main.gotoCommodityStockRefresh(userVO,new ArrayList<GoodsVO>());
    }


    //返回上一层
    @FXML
    public void gotoCommodity(ActionEvent e){
        main.gotoCommodityMain(userVO);
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
