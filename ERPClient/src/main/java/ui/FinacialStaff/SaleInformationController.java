package ui.FinacialStaff;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import rmi.RemoteHelper;
import ui.Main;
import vo.UserVO;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class SaleInformationController implements Initializable{
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @FXML
    Label label_expense;
    @FXML
    Label label_afterdiscount;
    @FXML
    Label label_get;//利润

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public SaleInformationController(){

    }//一个构造函数

    @FXML
    public void setMain(Main main, UserVO uservo)throws MalformedURLException,NotBoundException,RemoteException {
        this.main=main;
        this.userVO=uservo;
        label_name.setText(uservo.getName());
        DecimalFormat f = new DecimalFormat(".00");
        double in=helper.getManageBLService().getIncome();
        String income="￥"+f.format(in);
        String expense="￥"+f.format(helper.getManageBLService().getExpense());
        double get=helper.getManageBLService().getIncome()-helper.getManageBLService().getExpense();

        label_afterdiscount.setText(income);
        label_expense.setText(expense);
        label_get.setText("￥"+f.format(get));

    }

    @FXML
    public void gotoFinacialStaffMain(){
        if(userVO.getType().equals("总经理"))
            main.gotoManagerMain(userVO);
        else
            main.gotoFinacialStaffMain(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }
}
