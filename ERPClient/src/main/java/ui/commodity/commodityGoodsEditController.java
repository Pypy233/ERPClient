package ui.commodity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ui.Main;
import vo.UserVO;

public class commodityGoodsEditController {
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



    //商品编号 信息
    @FXML
    public TextArea goodsIDTA;
    //商品名称 信息
    @FXML
    public TextArea goodsNameTA;
    //商品库存 信息
    @FXML
    public TextArea goodsStockTA;
    //商品类型 信息
    @FXML
    public TextArea goodsKindTA;
    //商品进价 信息
    @FXML
    public TextArea goodsStockPriceTA;
    //商品零售价 信息
    @FXML
    public TextArea goodsSalePriceTA;
    //商品最新进价 信息
    @FXML
    public TextArea goodsRecentStockPriceTA;
    //商品最新零售价 信息
    @FXML
    public TextArea goodsRecentSalePriceTA;



    //“编辑资料”按钮
    @FXML
    public Button goodsEditButton;
    //“保存修改”按钮
    @FXML
    public Button goodsModifyConfirmButton;
    //“删除商品”按钮
    @FXML
    public Button goodsDeleteButton;




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


    //编辑资料
    @FXML
    public void goodsInfoEdit(ActionEvent e){


        //设置可编辑

    }
    //删除商品
    @FXML
    public void goodsDelete(ActionEvent e){
        //删除商品
        //跳转至GoodsMain界面

    }
    //保存修改
    @FXML
    public void goodsEditConfirm(ActionEvent e){

        //保存商品信息
        //跳转至GoodsMain界面

    }



    //登出
    @FXML
    public void gotoLog(ActionEvent e){
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
    }
    public void setMain(Main main,UserVO userVO,String order){
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("管理员"+userVO.getName());
        switch (order){
            case"Add":{

            }break;
            case"Search":{

            }break;
        }
    }
}
