package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import objects.ExcelTools;
import vo.CashRecord;
import objects.ResultMessage;
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
import java.util.Date;
import java.util.ResourceBundle;

public class SaleProcessDetail_XJFYDController implements Initializable{
    private Main main;
    private UserVO userVO;
    private CashVO cashVO;
    private ArrayList<CashVO> mainList;
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
    @FXML
    Button b_red;
    @FXML
    Button b_redAndCopy;

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public SaleProcessDetail_XJFYDController() {

    }//一个构造函数

    public void setMain(Main main,UserVO uservo,CashVO cashvo,ArrayList<CashVO> arrayList){
        this.main=main;
        this.userVO=uservo;

        if(userVO.getType().equals("总经理")){
            b_red.setVisible(false);
            b_redAndCopy.setVisible(false);
        }
        else{
            b_red.setVisible(true);
            b_redAndCopy.setVisible(true);
        }

        this.cashVO=cashvo;
        this.mainList=arrayList;

        label_name.setText(userVO.getName());
        tf_bank.setText(cashVO.getName_bank());
        tf_operator.setText(cashVO.getOperator().getName());
        label_number.setText(cashVO.getNumber());

        label_sum.setText("￥"+cashVO.getSum());

        tableView.setEditable(true);
        tableView.refresh();

        ArrayList<CashRecord> list=cashVO.getRecordList();

        ObservableList<PaymentModel> data = FXCollections.observableArrayList();

        for(CashRecord record:list){
            PaymentModel model = new PaymentModel(record.getTask(),""+record.getMoney(),record.getRemark());
            data.add(model);
        }

        tc_name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        tc_money.setCellValueFactory(
                new PropertyValueFactory<>("money")
        );

        tc_remark.setCellValueFactory(
                new PropertyValueFactory<>("remark")
        );

        tableView.setItems(data);

        tf_operator.setEditable(false);
        tf_bank.setEditable(false);

    }

    @FXML
    public void red() throws MalformedURLException,NotBoundException,RemoteException{
        ResultMessage m = helper.getCashBLService().red(cashVO);
        AlertUtil.showInformationAlert("红冲成功！");
        helper.getLogBlService().addLog(userVO,"红冲", ResultMessage.Success);
    }

    @FXML
    public void redAndCopy() throws MalformedURLException,NotBoundException,RemoteException{
        ResultMessage m = helper.getCashBLService().red(cashVO);
        AlertUtil.showInformationAlert("红冲成功！");
        helper.getLogBlService().addLog(userVO,"红冲并复制", ResultMessage.Success);

        ArrayList<CashRecord> list=cashVO.getRecordList();
        ObservableList<PaymentModel> data = FXCollections.observableArrayList();

        for(CashRecord record:list){
            PaymentModel model = new PaymentModel(record.getTask(),""+record.getMoney(),record.getRemark());
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
        tf_bank.setEditable(true);
        tf_operator.setEditable(true);

    }

    @FXML
    public void logout(){
        main.backToMain();
    }

    @FXML
    public void gotoFinacialStaffMain(){

        if(userVO.getType().equals("总经理"))
            main.gotoManagerMain(userVO);
        else
            main.gotoFinacialStaffMain(userVO);
    }

    @FXML
    public void gotoSaleProcessResult(){
        main.gotoSaleProcessResult_XJD(userVO,mainList);
    }

    @FXML
    public void out() throws Exception{
        Date date = new Date();
        ObservableList<PaymentModel> list = tableView.getItems();
        int n=list.size();

        String a[][] = new String[4][n+1];
        a[0][0]="行号";
        a[1][0]="条目名";
        a[2][0]="转账金额";
        a[3][0]="备注";
        for(int i=0;i<n;i++){
            a[0][i+1]=i+1+"";
            a[1][i+1]=list.get(i).getBank();
            a[2][i+1]=list.get(i).getMoney();
            a[3][i+1]=list.get(i).getRemark();

        }

        ExcelTools.export("现金费用单"+date.getTime(),a);
        AlertUtil.showInformationAlert("导出成功！");
    }



}
