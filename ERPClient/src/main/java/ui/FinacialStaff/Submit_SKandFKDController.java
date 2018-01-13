package ui.FinacialStaff;

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
import vo.TradeVO;
import vo.UserVO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Submit_SKandFKDController implements Initializable{
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public Submit_SKandFKDController(){

    }//一个构造函数

    @FXML
    public void setMain(Main main,UserVO uservo,TradeVO tradevo){
        this.userVO=uservo;
        this.main=main;
        label_name.setText(userVO.getName());

        label_number.setText(tradevo.getNumber());
        label_sum.setText("￥"+tradevo.getSum());

        tf_client.setText(tradevo.getMember().getName());
        tf_operator.setText(tradevo.getOperator().getName());

        tf_client.setEditable(false);
        tf_operator.setEditable(false);

        tableView.setEditable(true);

        ObservableList<PaymentModel> data = FXCollections.observableArrayList();
        ArrayList<TradeRecord> list=tradevo.getRecordList();

        for(TradeRecord record : list){
            PaymentModel model=new PaymentModel(record.getBankName(),record.getMoney()+"",record.getRemark());
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
