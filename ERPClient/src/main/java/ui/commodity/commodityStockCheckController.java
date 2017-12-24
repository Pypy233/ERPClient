package ui.commodity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.Main;
import vo.UserVO;

public class commodityStockCheckController {
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




    //输入查找库存变动起始年份
    @FXML
    public TextArea searchStartYearTA;
    //输入查找库存变动起始月份
    @FXML
    public TextArea searchStartMonthTA;
    //输入查找库存变动起始日
    @FXML
    public TextArea searchStartDayTA;
    //输入查找库存变动截止年份
    @FXML
    public TextArea searchEndYearTA;
    //输入查找库存变动截止月份
    @FXML
    public TextArea searchEndMonthTA;
    //输入查找库存变动截止日
    @FXML
    public TextArea searchEndDayTA;

    //“查询”按钮
    @FXML
    public Button periodSearchButton;





    //库存变动列表
    @FXML
    public TableView stockChangeTV;

    //库存变动列表 单据编号
    @FXML
    public TableColumn stockReceiptIDTC;
    //库存变动列表 供应商
    @FXML
    public TableColumn stockSupplierTC;
    //库存变动列表 仓库
    @FXML
    public TableColumn stockWarehouseTC;
    //库存变动列表 操作员
    @FXML
    public TableColumn stockOperatorTC;
    //库存变动列表 入库商品
    @FXML
    public TableColumn stockGoodsTC;
    //库存变动列表 总额
    @FXML
    public TableColumn stockTotalPriceTC;
    //库存变动列表 备注
    @FXML
    public TableColumn stockRemarkTC;




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
    //返回上一层
    @FXML
    public void gotoStock(ActionEvent e){
        main.gotoCommodityStock(userVO);
    }


    //点击按钮查询
    @FXML
    public void gotoStockSearch(ActionEvent e){

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
