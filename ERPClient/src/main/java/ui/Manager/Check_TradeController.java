package ui.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import vo.TradeRecord;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.PaymentModel;
import ui.util.AlertUtil;
import vo.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Check_TradeController implements Initializable{
    private Main main;
    private UserVO userVO;
    private TradeVO tradeVO;

    RemoteHelper helper = RemoteHelper.getInstance();

    @FXML
    Label label_number;
    @FXML
    Label label_name;
    @FXML
    Label label_sum;
    @FXML
    TextField tf_client;
    @FXML
    TextField tf_operator;
    @FXML
    TableView<PaymentModel> tableView;
    @FXML
    TableColumn tc_bank;
    @FXML
    TableColumn tc_money;
    @FXML
    TableColumn tc_remark;
    @FXML
    TextArea textArea;

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public Check_TradeController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo,TradeVO tradevo){
        this.main=main;
        this.userVO=uservo;
        this.tradeVO=tradevo;

        tableView.setEditable(true);
        tableView.refresh();

        label_name.setText(userVO.getName());

        label_number.setText(tradevo.getNumber());
        tf_client.setText(tradevo.getMember().getName());
        tf_operator.setText(tradevo.getOperator().getName());

        label_sum.setText("￥"+tradevo.getSum());

        tf_client.setEditable(false);
        tf_operator.setEditable(false);

        ArrayList<TradeRecord> list = tradevo.getRecordList();

        ObservableList<PaymentModel> data = FXCollections.observableArrayList();

        for(TradeRecord record:list){
            PaymentModel model = new PaymentModel(record.getBankName(),record.getMoney()+"",record.getRemark());
            data.add(model);
        }

        tc_bank.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        tc_money.setCellValueFactory(
                new PropertyValueFactory<>("money")
        );

        tc_remark.setCellValueFactory(
                new PropertyValueFactory<>("remark")
        );

        tableView.setItems(data);
    }
    @FXML
    public void pass() throws MalformedURLException,NotBoundException,RemoteException{
        helper.getTradeBLService().pass(tradeVO);
        AlertUtil.showInformationAlert("审批成功！");
        main.gotoSeeThrough(userVO);
    }

    @FXML
    public void fail() throws MalformedURLException,NotBoundException,RemoteException{
        helper.getTradeBLService().unpass(tradeVO);
        AlertUtil.showInformationAlert("审批成功！");
        main.gotoSeeThrough(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

    @FXML
    public void gotoSeeThrough(){
        main.gotoSeeThrough(userVO);
    }

    @FXML
    public void gotoManagerMain(){
        main.gotoManagerMain(userVO);
    }
}
