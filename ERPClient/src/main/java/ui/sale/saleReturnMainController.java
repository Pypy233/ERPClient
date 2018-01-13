package ui.sale;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.GoodsVO;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class saleReturnMainController implements Initializable {
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


    //选择进货退货还是销售退货
    @FXML
    public ChoiceBox returnCB;



    //UserInfo 用户名
    @FXML
    public Label userNameLB;



    //右上角 登出 按钮
    @FXML
    public Button logoutButton;
    //右下角 返回上一层 按钮
    @FXML
    public Button backButton;
    //新建进货单
    @FXML
    public Button returnReceiptNewButton;



    //跳转客户管理界面
    @FXML
    public void gotoMember(ActionEvent e){
        main.gotoSaleMember(userVO);
    }
    //跳转进货界面
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



    //新建退货单 分类型
    @FXML
    public void gotoReturnNew(ActionEvent e){

        if(returnCB.getValue()==null)
            AlertUtil.showWarningAlert("请选择单据类型");
       else if(returnCB.getValue().equals("销售退货")){
            main.gotoSaleReturnSaleReceipt(userVO,new ArrayList<GoodsVO>());
        }

        else if(returnCB.getValue().equals("进货退货")){
            main.gotoSaleReturnStockReceipt(userVO,new ArrayList<GoodsVO>());
        }

    }




    //返回上一层（进货销售人员主界面）
    @FXML
    public void gotoSaleMain(ActionEvent e){
        main.gotoSaleMain(userVO);
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
        returnCB.setItems(FXCollections.observableArrayList("销售退货","进货退货"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
