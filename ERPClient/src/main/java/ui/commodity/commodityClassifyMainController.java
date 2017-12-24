package ui.commodity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.Main;
import vo.UserVO;

public class commodityClassifyMainController {

    private Main main;
    private UserVO userVO;
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


    //下面跳转 “商品分类 商品类别.fxml”按钮
    @FXML
    public Button toClassifyButton;

    //右下角“返回上一层”按钮
    @FXML
    public Button backButton;

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



    //跳转商品类别界面
    @FXML
    public void gotoClassifyKind(ActionEvent e){
        main.gotoCommodityClassifyKind(userVO);
    }



    //返回上一层
    @FXML
    public void gotoCommodity(ActionEvent e){
        main.gotoCommodityMain(userVO);
    }
    //登出
    @FXML
    public void gotoLog(ActionEvent e){
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
    }

    public void setMain(Main main,UserVO userVO){
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("管理员"+userVO.getName());
    }

}
