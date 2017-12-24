package ui.sale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.Main;
import vo.UserVO;

public class saleMemberInfoSearchController {

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


    //客户编号 文本框
    @FXML
    public TextArea memberIDTA;
    //客户 姓名 文本框
    @FXML
    public TextArea memberNameTA;
    //客户 地址 文本框
    @FXML
    public TextArea memberAddressTA;
    //客户 邮编 文本框
    @FXML
    public TextArea memberPostcodeTA;
    //客户 应收额度 文本框
    @FXML
    public TextArea memberReceivableLimitTA;
    //客户 默认业务员 文本框
    @FXML
    public TextArea memberDefaultSalesmanTA;


    //客户类别 进货商 选择box
    @FXML
    public CheckBox supplierCB;
    //客户类别 销售商 选择box
    @FXML
    public CheckBox retailerCB;



    //客户等级 5级 选择box
    @FXML
    public CheckBox levelFiveCB;
    //客户等级 4级 选择box
    @FXML
    public CheckBox levelFourCB;
    //客户等级 3级 选择box
    @FXML
    public CheckBox levelThreeCB;
    //客户等级 2级 选择box
    @FXML
    public CheckBox levelTwoCB;
    //客户等级 1级 选择box
    @FXML
    public CheckBox levelOneCB;


    //查询 按钮
    @FXML
    public Button memberInfoSearchButton;




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
    public void memberInfoSearch(ActionEvent e){
        String name=memberNameTA.getText();
        String id=memberIDTA.getText();
        String address=memberAddressTA.getText();
        String postcode=memberPostcodeTA.getText();
        String recevableLimit=memberReceivableLimitTA.getText();
        String defaultSalesman=memberDefaultSalesmanTA.getText();



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
