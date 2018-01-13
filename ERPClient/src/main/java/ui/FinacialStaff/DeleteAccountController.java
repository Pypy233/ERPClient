package ui.FinacialStaff;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.BankVO;
import vo.UserVO;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeleteAccountController implements Initializable{
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @FXML
    Label label_number;
    @FXML
    TextField tf_account;
    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public DeleteAccountController(){

    }//一个构造函数

    @FXML
    public void setMain(Main main, UserVO uservo){
        this.main=main;
        this.userVO=uservo;
        label_name.setText(uservo.getName());
        tf_account.setText("请输入所要删除的账户名...");
    }

    @FXML
    public void gotoFinacialStaffMain(){main.gotoFinacialStaffMain(userVO);}

    @FXML
    public void logout(){main.initUI();}

    @FXML
    public void gotoDeleteAccount(){main.gotoDeleteAccount(userVO);}

    @FXML
    public void gotoAddAccount(){main.gotoAddAccount(userVO);}

    @FXML
    public void gotoCheckAccount(){main.gotoManageAccount(userVO);}

    @FXML
    public void delete()throws MalformedURLException,NotBoundException,RemoteException {
        ArrayList<BankVO> list=new ArrayList<BankVO>();
        list=helper.getBankBLService().getList();
        String account=tf_account.getText();
        int symbol=0;
        for(BankVO bankvo:list){
            if(bankvo.getName().equals(account))
                symbol=1;
        }
        if(symbol==0){
            AlertUtil.showErrorAlert("未找到所要删除的账户！");
            tf_account.setText("请输入所要删除的账户名...");
        }
        else{
            for(BankVO bankvo:list){
                if(bankvo.getName().equals(account))
                    helper.getBankBLService().delete(bankvo);
            }

            AlertUtil.showErrorAlert("删除成功！");
            helper.getLogBlService().addLog(userVO,"删除银行账户", ResultMessage.Success);
            tf_account.setText("请输入所要删除的账户名...");
        }
    }
}
