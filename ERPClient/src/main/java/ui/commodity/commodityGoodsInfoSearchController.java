package ui.commodity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.Main;
import vo.UserVO;

public class commodityGoodsInfoSearchController {

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




    //输入查找的商品名称
    @FXML
    public TextField goodsNameSearchTF;
    //输入查找的商品类型
    @FXML
    public TextField goodsKindSearchTF;
    //“查询”按钮
    @FXML
    public Button goodsInfoSearchButton;





    //类别列表
    @FXML
    public TableView goodsInfoSearchTV;

    //类别商品列表 id栏
    @FXML
    public TableColumn goodsIDTC;
    //类别商品列表 名称栏
    @FXML
    public TableColumn goodsNameTC;
    //类别商品列表 类别栏
    @FXML
    public TableColumn goodsKindTC;
    //类别商品列表 库存栏
    @FXML
    public TableColumn goodsStockTC;
    //类别商品列表 进价栏
    @FXML
    public TableColumn goodsStockPriceTC;
    //类别商品列表 零售价栏
    @FXML
    public TableColumn goodsSalePriceTC;




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
    //返回上一层
    @FXML
    public void gotoGoods(ActionEvent e){
        main.gotoCommodityGoods(userVO);
    }

    //跳转库存界面
    @FXML
    public void gotoStock(ActionEvent e){
        main.gotoCommodityStock(userVO);
    }



    //查询 action
    @FXML
    public void goodsInfoSearch(ActionEvent e){

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
