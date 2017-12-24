package ui.commodity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.Main;
import vo.UserVO;

public class commodityGoodsMainController {

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


    //“查询方式1”按钮
    @FXML
    public Button goodsIDSearchButton;
    //“查询方式1”按钮
    @FXML
    public Button goodsInfoSearchButton;
    //“新增商品”按钮
    @FXML
    public Button goodsNewButton;



    //UserInfo 用户名
    @FXML
    public Label userNameLB;


    //右上角“登出”按钮
    @FXML
    public Button logoutButton;
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



    //ID 查询 跳转界面
    @FXML
    public void gotoGoodsID(ActionEvent e){
        main.gotoCommodityGoodsIDSearch(userVO);
    }


    //模糊查询 跳转界面
    @FXML
    public void gotoGoodsInfo(ActionEvent e){
        main.gotoCommodityGoodsInfoSearch(userVO);
    }



    //新增商品，跳至增删改查界面
    @FXML
    public void goodsNew(ActionEvent e){
        main.gotoCommodityGoodsInfoEdit(userVO,"Add");
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
