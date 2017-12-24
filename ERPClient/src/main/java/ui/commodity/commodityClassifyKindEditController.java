package ui.commodity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.Main;
import ui.util.AlertUtil;
import vo.UserVO;

public class commodityClassifyKindEditController {

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




    //类别名称编辑处
    @FXML
    public TextField kindNameTF;
    //类别描述编辑处
    @FXML
    public TextField kindDescriptionTF;





    //类别列表
    @FXML
    public TableView goodsTV;

    //类别商品列表 id栏
    @FXML
    public TableColumn goodsIDTC;
    //类别商品列表 名称栏
    @FXML
    public TableColumn goodsNameTC;
    //类别商品列表 库存栏
    @FXML
    public TableColumn goodsStockTC;
    //类别商品列表 进价栏
    @FXML
    public TableColumn goodsStockPriceTC;
    //类别商品列表 零售价栏
    @FXML
    public TableColumn goodsSalePriceTC;
    //类别商品列表 最新进价栏
    @FXML
    public TableColumn goodsLatestStockPriceTC;
    //类别商品列表 最新零售价栏
    @FXML
    public TableColumn goodsLatestSalePriceTC;





    //“添加商品”按钮
    @FXML
    public Button addGoodsButton;
    //“确认修改”按钮
    @FXML
    public Button modifyConfirmButton;


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


    //增加类别中的商品
    @FXML
    public void addGoodsIn(ActionEvent e){


        //新增一个商品列表界面供选择***********************************************************************************************************还未做
        //在新界面里面选择商品，返回值在TableView增加商品项
    }

    //确认修改
    @FXML
    public void kindModifyConfirm(ActionEvent e){
        String name=kindNameTF.getText();
        String description=kindDescriptionTF.getText();
        if(name=="")
            AlertUtil.showWarningAlert("请输入该分类的名字");
        else{
            //修改分类信息（名字、描述。商品列表）

            //跳转界面
            main.gotoCommodityClassifyKind(userVO);
        }

    }


    //返回上一层（商品类别界面）
    @FXML
    public void gotoClassifyKind(ActionEvent e){
        main.gotoCommodityClassifyKind(userVO);
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
