package ui.sale;

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

public class saleSaleMainController implements Initializable {

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
    public Button saleReceiptNewButton;


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


    //新建销售单 跳转界面到新建销售单界面
    @FXML
    public void gotoSaleNew(ActionEvent e){
        main.gotoSaleSaleReceipt(userVO,new ArrayList<GoodsVO>());
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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
