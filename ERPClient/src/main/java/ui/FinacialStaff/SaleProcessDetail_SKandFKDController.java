package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import objects.ExcelTools;
import objects.ResultMessage;
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
import java.util.Date;
import java.util.ResourceBundle;

public class SaleProcessDetail_SKandFKDController implements Initializable{
    private Main main;
    private UserVO userVO;
    private TradeVO tradeVO;
    private ArrayList<TradeVO> mainList;
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
    TextArea textArea;
    @FXML
    TableView<PaymentModel> tableView;
    @FXML
    TableColumn<PaymentModel,String> tc_bank;
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

    public SaleProcessDetail_SKandFKDController() {

    }//一个构造函数

    public void setMain(Main main,UserVO uservo,TradeVO tradevo,ArrayList<TradeVO> arrayList){
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

        this.mainList=arrayList;
        this.tradeVO=tradevo;
        tableView.setEditable(true);
        tableView.refresh();

        label_number.setText(tradeVO.getNumber());
        tf_client.setText(tradeVO.getMember().getName());
        tf_operator.setText(tradeVO.getOperator().getName());
        label_sum.setText("￥"+tradeVO.getSum());

        ArrayList<TradeRecord> list = tradeVO.getRecordList();

        ObservableList<PaymentModel> data = FXCollections.observableArrayList();

        for(TradeRecord record:list){
            PaymentModel model = new PaymentModel(record.getBankName(),""+record.getMoney(),record.getRemark());
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

        tf_operator.setEditable(false);
        tf_client.setEditable(false);
        textArea.setEditable(false);
    }

    @FXML
    public void red() throws MalformedURLException,NotBoundException,RemoteException{
        ResultMessage m;
        if(label_number.getText().substring(0,3).equals("FKD")) {
            m = helper.getTradeBLService().red_pay(tradeVO);
            AlertUtil.showInformationAlert("红冲成功！");
            helper.getLogBlService().addLog(userVO,"红冲", ResultMessage.Success);
        }
        else{
            m = helper.getTradeBLService().red_collect(tradeVO);
            AlertUtil.showInformationAlert("红冲成功！");
            helper.getLogBlService().addLog(userVO,"红冲", ResultMessage.Success);
        }
    }

    @FXML
    public void redAndCopy() throws MalformedURLException,NotBoundException,RemoteException{
        ResultMessage m;
        if(label_number.getText().substring(0,3).equals("FKD")) {
            m = helper.getTradeBLService().red_pay(tradeVO);
            AlertUtil.showInformationAlert("红冲成功！");
            helper.getLogBlService().addLog(userVO,"红冲并复制", ResultMessage.Success);
        }
        else{
            m = helper.getTradeBLService().red_collect(tradeVO);
            AlertUtil.showInformationAlert("红冲成功！");
            helper.getLogBlService().addLog(userVO,"红冲并复制", ResultMessage.Success);
        }

        ArrayList<TradeRecord> list = tradeVO.getRecordList();

        ObservableList<PaymentModel> data = FXCollections.observableArrayList();

        for(TradeRecord record:list){
            PaymentModel model = new PaymentModel(record.getBankName(),""+record.getMoney(),record.getRemark());
            data.add(model);
        }

        tc_bank.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        tc_bank.setCellFactory(TextFieldTableCell.<PaymentModel>forTableColumn());
        tc_bank.setOnEditCommit(
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

        tf_operator.setEditable(true);
        tf_client.setEditable(true);
        textArea.setEditable(true);


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
        main.gotoSaleProcessResult_SKandFKD(userVO,mainList);
    }

    @FXML
    public void out() throws Exception{
        Date date = new Date();
        ObservableList<PaymentModel> list = tableView.getItems();
        int n=list.size();

        String a[][] = new String[4][n+1];
        a[0][0]="行号";
        a[1][0]="银行账户";
        a[2][0]="转账金额";
        a[3][0]="备注";
        for(int i=0;i<n;i++){
            a[0][i+1]=i+1+"";
            a[1][i+1]=list.get(i).getBank();
            a[2][i+1]=list.get(i).getMoney();
            a[3][i+1]=list.get(i).getRemark();

        }

        ExcelTools.export("收款单或付款单"+date.getTime(),a);
        AlertUtil.showInformationAlert("导出成功！");
    }



}
