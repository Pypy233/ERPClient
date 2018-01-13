package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import objects.ResultMessage;
import vo.CashRecord;
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

public class Draft_XJFYDController implements Initializable{
    private Main main;
    private UserVO userVO;
    private ObservableList<PaymentModel> data=FXCollections.observableArrayList();
    private CashVO cashVO;

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
    TableColumn<PaymentModel,String> tc_name;
    @FXML
    TableColumn<PaymentModel,String> tc_money;
    @FXML
    TableColumn<PaymentModel,String> tc_remark;




    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public Draft_XJFYDController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo,CashVO cashvo){
        this.main=main;
        this.userVO=uservo;
        this.cashVO=cashvo;

        tableView.setEditable(true);
        tf_operator.setEditable(false);

        label_name.setText(userVO.getName());
        label_number.setText(cashVO.getNumber());
        label_sum.setText("￥"+cashVO.getSum());

        tf_bank.setText(cashVO.getName_bank());
        tf_operator.setText(cashVO.getOperator().getName());

        ArrayList<CashRecord> list = cashVO.getRecordList();
        for(CashRecord record:list){
            PaymentModel model = new PaymentModel(record.getTask(),record.getMoney()+"",record.getRemark());
            data.add(model);
        }

        tc_name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        tc_name.setCellFactory(TextFieldTableCell.<PaymentModel>forTableColumn());
        tc_name.setOnEditCommit(
                (TableColumn.CellEditEvent<PaymentModel, String> t) -> {
                    ((PaymentModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setBank(t.getNewValue());
                });

        tc_money.setCellValueFactory(
                new PropertyValueFactory<>("money")
        );
        tc_money.setCellFactory(TextFieldTableCell.<PaymentModel>forTableColumn());
        tc_money.setOnEditCommit(
                (TableColumn.CellEditEvent<PaymentModel, String> t) -> {
                    ((PaymentModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setMoney(t.getNewValue());
                });

        tc_remark.setCellValueFactory(
                new PropertyValueFactory<>("remark")
        );
        tc_remark.setCellFactory(TextFieldTableCell.<PaymentModel>forTableColumn());
        tc_remark.setOnEditCommit(
                (TableColumn.CellEditEvent<PaymentModel, String> t) -> {
                    ((PaymentModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setRemark(t.getNewValue());
                });

        tableView.setItems(data);
    }

    @FXML
    public void add(){
        PaymentModel model = new PaymentModel("","","");
        data.add(model);
        tableView.setItems(data);
    }

    @FXML
    public void save() throws MalformedURLException,NotBoundException,RemoteException{
        cashVO.setName_bank(tf_bank.getText());


        ArrayList<CashRecord> list = new ArrayList<CashRecord>();

        double sum=0;

        for(PaymentModel model : data){
            CashRecord record = new CashRecord(model.getBank(),Double.parseDouble(model.getMoney()),model.getRemark());
            sum=sum+record.getMoney();
            list.add(record);
        }

        cashVO.setRecordList(list);
        cashVO.setSum(sum);
        helper.getCashBLService().updateDraft(cashVO);
        AlertUtil.showInformationAlert("保存成功！");
        helper.getLogBlService().addLog(userVO,"保存现金费用单草稿", ResultMessage.Success);

    }

    @FXML
    public void submit() throws MalformedURLException,NotBoundException,RemoteException{
        cashVO.setName_bank(tf_bank.getText());


        ArrayList<CashRecord> list = new ArrayList<CashRecord>();

        double sum=0;

        for(PaymentModel model : data){
            CashRecord record = new CashRecord(model.getBank(),Double.parseDouble(model.getMoney()),model.getRemark());
            sum=sum+record.getMoney();
            list.add(record);
        }

        cashVO.setRecordList(list);
        cashVO.setSum(sum);

        ArrayList<BankVO> bankList =helper.getBankBLService().getList();
        int counter=0;
        for(BankVO vo:bankList){
            if(vo.getName().equals(cashVO.getName_bank()))
                counter++;
        }
        if(counter==0)
            AlertUtil.showWarningAlert("没有相应的银行账户！");
        else{
            helper.getCashBLService().upload(cashVO);
            AlertUtil.showInformationAlert("提交成功！");
        }

    }

    @FXML
    public void delete() throws MalformedURLException,NotBoundException,RemoteException{
        helper.getCashBLService().deleteDraft(cashVO);
        AlertUtil.showInformationAlert("删除成功！");
        main.gotoCheckDraft(userVO);
    }

    @FXML
    public void gotoFinacialStaffMain(){
        main.gotoFinacialStaffMain(userVO);
    }

    @FXML
    public void gotoDraftList(){
        main.gotoCheckDraft(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

}
