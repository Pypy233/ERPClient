package ui.sale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.UserVO;

import java.rmi.RemoteException;

public class saleMemberIDSearchController {

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


    //ID查询 文本框
    @FXML
    public TextField memberIDSearchTF;

    //查询 按钮
    @FXML
    public Button memberIDSearchButton;




    //客户信息查询列表
    @FXML
    public TableView memberInfoSearchTV;

    //客户信息查询列表 ID栏
    @FXML
    public TableColumn memberIDTC;
    //客户信息查询列表 名称栏
    @FXML
    public TableColumn memberNameTC;
    //客户信息查询列表 类别栏
    @FXML
    public TableColumn memberKindTC;
    //客户信息查询列表 级别栏
    @FXML
    public TableColumn memberLevelTC;
    //客户信息查询列表 业务员栏
    @FXML
    public TableColumn memberDefaultSalesmanPriceTC;




    //UserInfo 用户名
    @FXML
    public Label userNameLB;




    //右上角 登出 按钮
    @FXML
    public Button logoutButton;
    //右下角 返回上一层 按钮
    @FXML
    public Button backButton;





    //跳转客户管理界面
    //返回上一层
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

    //查询 action
    @FXML
    public void memberIDSearch(ActionEvent e)throws RemoteException{
        RemoteHelper helper=RemoteHelper.getInstance();
        String memberID=memberIDSearchTF.getText();
        if(memberID==""){
            AlertUtil.showWarningAlert("查询ID不能为空");
        }
        else if(1==1){
            //不存在的ID
            AlertUtil.showErrorAlert("此ID不存在");
        }
        else if(1!=1){
            //存在账户
            //显示
        }
    }

    //tableview实现




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
