package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import objects.ResultMessage;
import vo.CashRecord;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.*;
import ui.model.PayeeModel;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MakeCashController implements Initializable{
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @FXML
    Label label_number;
    @FXML
    Label label_operator;
    @FXML
    TextField tf_bank;
    @FXML
    TableView<PayeeModel> tableView;
    @FXML
    TableColumn<PayeeModel,String> tc_task;
    @FXML
    TableColumn<PayeeModel,String> tc_money;
    @FXML
    TableColumn<PayeeModel,String> tc_remark;
    @FXML
    Label label_sum;
    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public MakeCashController(){

    }

    public void setMain(Main main,UserVO uservo)throws MalformedURLException,NotBoundException,RemoteException{
        this.main=main;
        this.userVO=uservo;
        label_name.setText(userVO.getName());

        label_number.setText(helper.getCashBLService().getNewnumber());

        label_operator.setText(userVO.getName());

        tableView.setEditable(true);
        tableView.refresh();

        ObservableList<PayeeModel> data = FXCollections.observableArrayList(
                new PayeeModel("","",""),
                new PayeeModel("","",""),
                new PayeeModel("","",""),
                new PayeeModel("","",""),
                new PayeeModel("","",""),
                new PayeeModel("","",""),
                new PayeeModel("","",""),
                new PayeeModel("","",""),
                new PayeeModel("","",""),
                new PayeeModel("","",""),
                new PayeeModel("","",""),
                new PayeeModel("","",""),
                new PayeeModel("","","")

        );

        tc_task.setCellFactory(TextFieldTableCell.<PayeeModel>forTableColumn());
        tc_task.setOnEditCommit(
                (TableColumn.CellEditEvent<PayeeModel,String> t) -> {
                    ((PayeeModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setBank(t.getNewValue());
                }
        );

        tc_money.setCellFactory(TextFieldTableCell.<PayeeModel>forTableColumn());
        tc_money.setOnEditCommit(
                (TableColumn.CellEditEvent<PayeeModel,String> t) -> {
                    ((PayeeModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setMoney(t.getNewValue());
                }
        );

        tc_remark.setCellFactory(TextFieldTableCell.<PayeeModel>forTableColumn());
        tc_remark.setOnEditCommit(
                (TableColumn.CellEditEvent<PayeeModel,String> t) -> {
                    ((PayeeModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setRemark(t.getNewValue());
                }
        );

        tableView.setItems(data);
    }

    @FXML
    public void submit()throws MalformedURLException,NotBoundException,RemoteException{
        int counter=0;
        ObservableList<PayeeModel> list=tableView.getItems();
        double sum=0;

        ArrayList<CashRecord> cashRecords=new ArrayList<CashRecord>();

        String date=label_number.getText();
        char[] a=date.toCharArray();
        String year="";
        String month="";
        String day="";
        for(int i=6;i<=9;i++)
            year=year+a[i];
        for(int i=10;i<=11;i++)
            month=month+a[i];
        for(int i=12;i<=13;i++)
            day=day+a[i];
        date=year+"-"+month+"-"+day;

        for(PayeeModel model:list){
            if(!model.getBank().equals("")){
                counter++;
                sum=sum+Double.parseDouble(model.getMoney());
                CashRecord cashRecord=new CashRecord(model.getBank(),Double.parseDouble(model.getMoney()),model.getRemark());
                cashRecords.add(cashRecord);
            }
        }

        if(counter!=0){
            ArrayList<BankVO> bank=helper.getBankBLService().getList();
            int counter2=0;
            for(BankVO vo:bank){
                if(vo.getName().equals(tf_bank.getText()))
                    counter2++;
            }
            if(counter2==0)
                AlertUtil.showWarningAlert("没有找到相应的账户！");
            else{
                CashVO cashVO=new CashVO(label_number.getText(),userVO,tf_bank.getText(),cashRecords,sum,date,"Process");
                helper.getCashBLService().upload(cashVO);
                AlertUtil.showInformationAlert("提交成功！");
                helper.getLogBlService().addLog(userVO,"提交现金费用单", ResultMessage.Success);
            }
        }
    }

    @FXML
    public void save()throws MalformedURLException,NotBoundException,RemoteException{
        ObservableList<PayeeModel> list=tableView.getItems();
        double sum=0;

        ArrayList<CashRecord> cashRecords=new ArrayList<CashRecord>();

        for(PayeeModel model:list){
            if(!model.getBank().equals("")){
                sum=sum+Double.parseDouble(model.getMoney());
                CashRecord cashRecord=new CashRecord(model.getBank(),Double.parseDouble(model.getMoney()),model.getRemark());
                cashRecords.add(cashRecord);
            }
        }

        String date=label_number.getText();
        char[] a=date.toCharArray();
        String year="";
        String month="";
        String day="";
        for(int i=6;i<=9;i++)
            year=year+a[i];
        for(int i=10;i<=11;i++)
            month=month+a[i];
        for(int i=12;i<=13;i++)
            day=day+a[i];
        date=year+"-"+month+"-"+day;



        CashVO cashVO=new CashVO(label_number.getText(),userVO,tf_bank.getText(),cashRecords,sum,date,"Draft");
        helper.getCashBLService().addDraft(cashVO);
        AlertUtil.showInformationAlert("保存成功！");
        helper.getLogBlService().addLog(userVO,"保存现金费用单草稿", ResultMessage.Success);

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
    public void newCash(){
        main.gotoMakeCash(userVO);
    }
}
