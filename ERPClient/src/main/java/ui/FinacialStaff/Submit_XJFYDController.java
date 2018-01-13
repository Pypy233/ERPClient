package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import vo.CashRecord;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.PaymentModel;
import vo.CashVO;
import vo.UserVO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Submit_XJFYDController implements Initializable{
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();

    @FXML
    Label label_name;
    @FXML
    Label label_number;
    @FXML
    Label label_sum;
    @FXML
    TextField tf_bank;
    @FXML
    TextField tf_operator;
    @FXML
    TableView<PaymentModel> tableView;
    @FXML
    TableColumn tc_name;
    @FXML
    TableColumn tc_money;
    @FXML
    TableColumn tc_remark;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public Submit_XJFYDController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo,CashVO cashvo){
        this.main=main;
        this.userVO =uservo;

        label_name.setText(userVO.getName());
        label_sum.setText("￥"+cashvo.getSum());
        label_number.setText(cashvo.getNumber());

        tf_bank.setText(cashvo.getName_bank());
        tf_operator.setText(cashvo.getOperator().getName());
        tf_bank.setEditable(false);
        tf_operator.setEditable(false);

        tableView.setEditable(true);
        tableView.refresh();

        tc_name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        tc_money.setCellValueFactory(
                new PropertyValueFactory<>("money")
        );

        tc_remark.setCellValueFactory(
                new PropertyValueFactory<>("remark")
        );

        ObservableList<PaymentModel> data=FXCollections.observableArrayList();
        ArrayList<CashRecord> list=cashvo.getRecordList();
        for(CashRecord record :list){
            PaymentModel model=new PaymentModel(record.getTask(),record.getMoney()+"",record.getRemark()) ;
            data.add(model);
        }

        tableView.setItems(data);
    }

    @FXML
    public void gotoFinacialStaffMain(){
        main.gotoFinacialStaffMain(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

    @FXML
    public void gotoSubmitList(){
        main.gotoCheckSubmit(userVO);
    }
}
