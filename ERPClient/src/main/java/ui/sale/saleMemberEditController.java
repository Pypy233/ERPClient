package ui.sale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.MemberVO;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class saleMemberEditController implements Initializable {

    private Main main;
    private UserVO userVO;
    private MemberVO memberVO;
    RemoteHelper helper=RemoteHelper.getInstance();
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


    //客户编号 文本框
    @FXML
    public TextField memberIDTA;
    //客户 姓名 文本框
    @FXML
    public TextField memberNameTA;
    //客户 电话 文本框
    @FXML
    public TextField memberPhoneTA;
    //客户 地址 文本框
    @FXML
    public TextField memberAddressTA;
    //客户 邮编 文本框
    @FXML
    public TextField memberPostcodeTA;
    //客户 电子邮箱 文本框
    @FXML
    public TextField memberEmailTA;
    //客户 应收额度 文本框
    @FXML
    public TextField memberReceivableLimitTA;
    //客户 默认业务员 文本框
    @FXML
    public TextField memberDefaultSalesmanTA;
    //客户 应付 文本框
    @FXML
    public TextField payTA;
    //客户 应收 文本框
    @FXML
    public TextField getTA;



    //客户类别
    @FXML
    public ChoiceBox<String> kindCB;



    //客户等级
    @FXML
    public ChoiceBox<String> levelCB;


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
        memberIDTA.setEditable(false);
        memberNameTA.setEditable(true);
        memberPhoneTA.setEditable(true);
        memberAddressTA.setEditable(true);
        memberPostcodeTA.setEditable(true);
        memberEmailTA.setEditable(true);
        memberReceivableLimitTA.setEditable(true);
        memberDefaultSalesmanTA.setEditable(true);
        getTA.setEditable(false);
        payTA.setEditable(false);

        memberDeleteButton.setDisable(true);
        memberModifyConfirmButton.setDisable(false);
        memberInfoEditButton.setDisable(true);
        memberAddButton.setDisable(true);
    }
    //删除客户
    @FXML
    public void memberDelete(ActionEvent e)throws RemoteException{
        //删除客户
        helper.getMemberBLService().deleteMember(memberVO);
        //跳转至MemberMain界面
        helper.getLogBlService().addLog(userVO,"删除客户 "+memberVO.getName(), ResultMessage.Success);
        main.gotoSaleMain(userVO);
    }
    //确认修改
    @FXML
    public void memberEditConfirm(ActionEvent e)throws RemoteException{
        String name=memberNameTA.getText();
        String phone=memberPhoneTA.getText();
        String address=memberAddressTA.getText();
        String postcode=memberPostcodeTA.getText();
        String email=memberEmailTA.getText();
        String receivableLimit=memberReceivableLimitTA.getText();
        String defaultSalesman=memberDefaultSalesmanTA.getText();

        int level=levelCB.getSelectionModel().getSelectedIndex()+1;
        System.out.println(level);

        String kind="进货商";
        if(kindCB.getSelectionModel().getSelectedIndex()==0)
            kind="进货商";
        else if(kindCB.getSelectionModel().getSelectedIndex()==1)
            kind="销售商";

        //保存用户信息
        memberVO.setName(name);
        memberVO.setPhoneNumber(phone);
        memberVO.setAddress(address);
        memberVO.setMailAddress(email);
        memberVO.setPostcode(postcode);
        memberVO.setManagePerson(defaultSalesman);
        memberVO.setCollectionLimit(Double.valueOf(receivableLimit));
        memberVO.setLevel(level);
        memberVO.setMemberClass(kind);
        helper.getMemberBLService().updateMember(memberVO);
        //跳转至MemberMain界面
        helper.getLogBlService().addLog(userVO,"修改客户信息 "+memberVO.getName(), ResultMessage.Success);
        main.gotoSaleMain(userVO);
    }
    //新增客户
    @FXML
    public void memberEditNew(ActionEvent e)throws RemoteException{
        String name=memberNameTA.getText();
        String phone=memberPhoneTA.getText();
        String address=memberAddressTA.getText();
        String postcode=memberPostcodeTA.getText();
        String email=memberEmailTA.getText();
        String receivableLimit=memberReceivableLimitTA.getText();
        String defaultSalesman=memberDefaultSalesmanTA.getText();

        int level=levelCB.getSelectionModel().getSelectedIndex()+1;
        String kind="进货商";
        if(kindCB.getSelectionModel().getSelectedIndex()==0)
            kind="进货商";
        else if(kindCB.getSelectionModel().getSelectedIndex()==1)
            kind="销售商";
        //清空所有信息，数据库新增一组数据，设置文本框可编辑
        memberVO=new MemberVO();
        memberVO.setName(name);
        memberVO.setPhoneNumber(phone);
        memberVO.setAddress(address);
        memberVO.setMailAddress(email);
        memberVO.setPostcode(postcode);
        memberVO.setManagePerson(defaultSalesman);
        memberVO.setCollectionLimit(Double.valueOf(receivableLimit));
        memberVO.setLevel(level);
        memberVO.setMemberClass(kind);
        memberVO.setCollection(0);
        memberVO.setPayment(0);

        helper.getMemberBLService().addMember(memberVO);
        AlertUtil.showInformationAlert("客户已新增");
        helper.getLogBlService().addLog(userVO,"新增客户 "+memberVO.getName(), ResultMessage.Success);
        //跳转至MemberMain界面
        main.gotoSaleMain(userVO);
    }



    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);

    }
    public void setMain(Main main, UserVO userVO, String order, MemberVO memberVO){
        this.main=main;
        this.userVO=userVO;
        this.memberVO=memberVO;
        userNameLB.setText("User "+userVO.getName());
        getTA.setEditable(false);
        payTA.setEditable(false);
        levelCB.setItems(FXCollections.observableArrayList("一级用户","二级用户","三级用户","四级用户","五级用户（VIP）"));
        kindCB.setItems(FXCollections.observableArrayList("进货商","销售商"));

        switch (order){
            case"Add":{
                memberIDTA.setEditable(false);
                memberNameTA.setEditable(true);
                memberPhoneTA.setEditable(true);
                memberAddressTA.setEditable(true);
                memberPostcodeTA.setEditable(true);
                memberEmailTA.setEditable(true);
                memberReceivableLimitTA.setEditable(true);
                memberDefaultSalesmanTA.setEditable(true);
                getTA.setEditable(false);
                payTA.setEditable(false);
                memberIDTA.setText("");
                memberNameTA.setText("");
                memberPhoneTA.setText("");
                memberAddressTA.setText("");
                memberPostcodeTA.setText("");
                memberEmailTA.setText("");
                memberReceivableLimitTA.setText("");
                memberDefaultSalesmanTA.setText("");
                payTA.setText("0.0");
                getTA.setText("0.0");
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
                getTA.setEditable(false);
                payTA.setEditable(false);

                memberIDTA.setText(""+memberVO.getNumber());
                memberNameTA.setText(""+memberVO.getName());
                memberPhoneTA.setText(""+memberVO.getPhoneNumber());
                memberAddressTA.setText(""+memberVO.getAddress());
                memberPostcodeTA.setText(""+memberVO.getPostcode());
                memberEmailTA.setText(""+memberVO.getMailAddress());
                memberReceivableLimitTA.setText(""+memberVO.getCollectionLimit());
                memberDefaultSalesmanTA.setText(""+memberVO.getManagePerson());
                kindCB.setValue(memberVO.getMemberClass());
                payTA.setText(memberVO.getPayment()+"");
                getTA.setText(""+memberVO.getCollection());
                int level=memberVO.getLevel();
                levelCB.getSelectionModel().select(level-1);
                if(memberVO.getMemberClass().equals("进货商"))
                    kindCB.getSelectionModel().select(0);
                else
                    kindCB.getSelectionModel().select(1);

                memberDeleteButton.setDisable(false);
                memberModifyConfirmButton.setDisable(true);
                memberInfoEditButton.setDisable(false);
                memberAddButton.setDisable(true);
            }break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
