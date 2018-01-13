package ui.FinacialStaff;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.UserVO;
import vo.BankVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAccountController implements Initializable{
    @FXML
    Label label_name;
    @FXML
    Label label_number;
    @FXML
    TextField tf_account;
    @FXML
    TextField tf_money;
    private Main main;
    private UserVO userVO;

    RemoteHelper remoteHelper = RemoteHelper.getInstance();

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public AddAccountController(){

    }

    public void setMain(Main main, UserVO uservo){
        this.main=main;
        this.userVO=uservo;
        label_name.setText(userVO.getName());

    }

    @FXML
    public void gotocheckAccount(){main.gotoManageAccount(userVO);}//共用一个界面
    @FXML
    public void gotodeleteAccount(){main.gotoDeleteAccount(userVO);}
    @FXML
    public void gotoaddAccount(){main.gotoAddAccount(userVO);}
    @FXML
    public void logout(){main.initUI();}
    @FXML
    public void gotoFinacialStaffMain(){main.gotoFinacialStaffMain(userVO);}//返回到财务人员主界面

    @FXML public void add()throws MalformedURLException,NotBoundException,RemoteException {
        String newName=tf_account.getText();
        double newMoney=Double.parseDouble(tf_money.getText());

        ArrayList<BankVO> list=new ArrayList<BankVO>();
        int symbol=0;
        for(BankVO bankvo:list){
            if(bankvo.getName().equals(newName))
                symbol=1;
        }

        if(symbol==1){
            AlertUtil.showErrorAlert("已存在相同用户名！");
            tf_account.setText("请输入账户名称...");
            tf_money.setText("请输入账户金额...");
        }

        else{
            BankVO newBankvo=new BankVO(newName,newMoney);
            remoteHelper.getBankBLService().add(newBankvo);
            AlertUtil.showInformationAlert("添加成功！");
            remoteHelper.getLogBlService().addLog(userVO,"添加银行账户", ResultMessage.Success);
            tf_account.setText("请输入账户名称...");
            tf_money.setText("请输入账户金额...");
        }

    }

}
