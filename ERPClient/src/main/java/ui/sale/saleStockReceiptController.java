package ui.sale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.Main;
import vo.UserVO;

public class saleStockReceiptController {
    private Main main;
    private UserVO userVO;
    //客户管理 按钮
    @FXML
    public Button memberButton;
    //进货 按钮
    @FXML
    public Button stockInButton;
    //销售 按钮
    @FXML
    public Button saleButton;
    //退货 按钮
    @FXML
    public Button returnButton;


    //货单编号
    @FXML
    public Label receiptNumLB;
    //供应商
    @FXML
    public Label suppliersLB;
    //仓库
    @FXML
    public Label warehouseLB;
    //操作员
    @FXML
    public Label operatorNumLB;
    //总价
    @FXML
    public Label totalSumLB;

    //进货单商品列表
    @FXML
    public TableView goodsListTV;

    //进货单商品列表 序号栏
    @FXML
    public TableColumn goodsIDTC;
    //进货单商品列表 名称栏
    @FXML
    public TableColumn goodsNameTC;
    //进货单商品列表 单价栏
    @FXML
    public TableColumn goodsPriceTC;
    //进货单商品列表 数量栏
    @FXML
    public TableColumn goodsNumTC;
    //进货单商品列表 总额栏
    @FXML
    public TableColumn goodsTotalPriceTC;
    //进货单商品列表 备注栏
    @FXML
    public TableColumn goodsRemarkTC;




    //UserInfo 用户名
    @FXML
    public Label userNameLB;


    //右上角 登出 按钮
    @FXML
    public Button logoutButton;
    //右下角 返回上一层 按钮
    @FXML
    public Button backButton;
    //添加商品 按钮
    @FXML
    public Button addGoodsButton;
    //提交货单 按钮
    @FXML
    public Button submitReceiptButton;





    //跳转客户管理界面
    @FXML
    public void gotoMember(ActionEvent e){
        main.gotoSaleMember(userVO);
    }
    //跳转进货界面
    //返回上一层
    @FXML
    public void gotoStockIn(ActionEvent e){
        main.gotoSaleStock(userVO);
    }

    //跳转销售界面
    @FXML
    public void gotoSale(ActionEvent e){
        main.gotoSaleSale(userVO);
    }
    //跳转退货界面
    @FXML
    public void gotoReturn(ActionEvent e){
        main.gotoSaleReturn(userVO);
    }

    //添加商品
    @FXML
    public void stockAddGoods(ActionEvent e){

        //调用一个商品列表
        //添加完后自动生成总价等信息
    }
    //提交货单
    @FXML
    public void stockSubmit(ActionEvent e){


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
