package ui.sale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ui.Main;
import vo.UserVO;

public class saleMemberEditController {

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
    //客户 电话 文本框
    @FXML
    public TextArea memberPhoneTA;
    //客户 地址 文本框
    @FXML
    public TextArea memberAddressTA;
    //客户 邮编 文本框
    @FXML
    public TextArea memberPostcodeTA;
    //客户 电子邮箱 文本框
    @FXML
    public TextArea memberEmailTA;
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










    //编辑信息 按钮
    @FXML
    public Button memberInfoEditButton;
    //确认修改 按钮
    @FXML
    public Button memberModifyConfirmButton;
    //删除客户 按钮
    @FXML
    public Button memberDeleteButton;
    //新增客户 按钮
    @FXML
    public Button memberAddButton;


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


    //编辑信息
    @FXML
    public void memberInfoEdit(ActionEvent e){
        memberIDTA.setEditable(true);
        memberNameTA.setEditable(true);
        memberPhoneTA.setEditable(true);
        memberAddressTA.setEditable(true);
        memberPostcodeTA.setEditable(true);
        memberEmailTA.setEditable(true);
        memberReceivableLimitTA.setEditable(true);
        memberDefaultSalesmanTA.setEditable(true);
    }
    //删除客户
    @FXML
    public void memberDelete(ActionEvent e){

        //删除客户
        //跳转至MemberMain界面
        main.gotoSaleMain(userVO);
    }
    //确认修改
    @FXML
    public void memberEditConfirm(ActionEvent e){
        String name=memberNameTA.getText();
        String id=memberIDTA.getText();
        String phone=memberPhoneTA.getText();
        String address=memberAddressTA.getText();
        String postcode=memberPostcodeTA.getText();
        String email=memberEmailTA.getText();
        String recevableLimit=memberReceivableLimitTA.getText();
        String defaultSalesman=memberDefaultSalesmanTA.getText();
        //保存用户信息
        //跳转至MemberMain界面

    }
    //新增客户
    @FXML
    public void memberEditNew(ActionEvent e){
        String name=memberNameTA.getText();
        String id=memberIDTA.getText();
        String phone=memberPhoneTA.getText();
        String address=memberAddressTA.getText();
        String postcode=memberPostcodeTA.getText();
        String email=memberEmailTA.getText();
        String recevableLimit=memberReceivableLimitTA.getText();
        String defaultSalesman=memberDefaultSalesmanTA.getText();
        //清空所有信息，数据库新增一组数据，设置文本框可编辑
        //跳转至MemberMain界面

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
                memberIDTA.setEditable(true);
                memberNameTA.setEditable(true);
                memberPhoneTA.setEditable(true);
                memberAddressTA.setEditable(true);
                memberPostcodeTA.setEditable(true);
                memberEmailTA.setEditable(true);
                memberReceivableLimitTA.setEditable(true);
                memberDefaultSalesmanTA.setEditable(true);
                memberIDTA.setText("");
                memberNameTA.setText("");
                memberPhoneTA.setText("");
                memberAddressTA.setText("");
                memberPostcodeTA.setText("");
                memberEmailTA.setText("");
                memberReceivableLimitTA.setText("");
                memberDefaultSalesmanTA.setText("");
                memberDeleteButton.setDisable(true);
                memberModifyConfirmButton.setDisable(true);
                memberInfoEditButton.setDisable(true);
                memberAddButton.setDisable(false);
            }break;
            case"Search":{
                memberIDTA.setEditable(false);
                memberNameTA.setEditable(false);
                memberPhoneTA.setEditable(false);
                memberAddressTA.setEditable(false);
                memberPostcodeTA.setEditable(false);
                memberEmailTA.setEditable(false);
                memberReceivableLimitTA.setEditable(false);
                memberDefaultSalesmanTA.setEditable(false);
                //修改 再传一个值membervo.。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
                memberIDTA.setText("");
                memberNameTA.setText("");
                memberPhoneTA.setText("");
                memberAddressTA.setText("");
                memberPostcodeTA.setText("");
                memberEmailTA.setText("");
                memberReceivableLimitTA.setText("");
                memberDefaultSalesmanTA.setText("");

                memberDeleteButton.setDisable(false);
                memberModifyConfirmButton.setDisable(false);
                memberInfoEditButton.setDisable(false);
                memberAddButton.setDisable(true);
            }break;
        }
    }
}
