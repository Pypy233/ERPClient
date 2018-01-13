package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import objects.ResultMessage;
import vo.TradeRecord;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.PaymentModel;
import ui.util.AlertUtil;
import vo.MemberVO;
import vo.TradeVO;
import vo.UserVO;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Draft_SKandFKDController implements Initializable{
    private Main main;
    private UserVO userVO;
    private ObservableList<PaymentModel> data=FXCollections.observableArrayList();
    private TradeVO tradeVO;

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
    TableColumn<PaymentModel,String> tc_bank;
    @FXML
    TableColumn<PaymentModel,String> tc_money;
    @FXML
    TableColumn<PaymentModel,String> tc_remark;


    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public Draft_SKandFKDController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo,TradeVO tradevo){
        this.main=main;
        this.userVO=uservo;
        this.tradeVO=tradevo;

        tableView.setEditable(true);
        tf_operator.setEditable(false);

        label_name.setText(userVO.getName());
        label_number.setText(tradeVO.getNumber());
        label_sum.setText("￥"+tradeVO.getSum());

        tf_client.setText(tradeVO.getMember().getName());
        tf_operator.setText(tradeVO.getOperator().getName());

        ArrayList<TradeRecord> list = tradeVO.getRecordList();
        for(TradeRecord record:list){
            PaymentModel model = new PaymentModel(record.getBankName(),record.getMoney()+"",record.getRemark());
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
    }

    @FXML
    public void add(){
        PaymentModel model = new PaymentModel("","","");
        data.add(model);
        tableView.setItems(data);
    }

    @FXML
    public void save() throws MalformedURLException,NotBoundException,RemoteException{

        ArrayList<MemberVO> memberList=helper.getMemberBLService().findMemberByName(tf_client.getText());
        MemberVO membervo = new MemberVO();
        membervo.setName(tf_client.getText());


        ArrayList<TradeRecord> list = new ArrayList<TradeRecord>();

        double sum=0;

        for(PaymentModel model : data){
            TradeRecord record = new TradeRecord(model.getBank(),Double.parseDouble(model.getMoney()),model.getRemark());
            sum=sum+record.getMoney();
            list.add(record);
        }

        tradeVO.setRecordList(list);
        tradeVO.setSum(sum);

        helper.getTradeBLService().updateDraft(tradeVO);
        AlertUtil.showInformationAlert("保存成功！");
        helper.getLogBlService().addLog(userVO,"保存付款单或收款单草稿", ResultMessage.Success);

    }

    @FXML
    public void submit() throws MalformedURLException,NotBoundException,RemoteException{
        ArrayList<MemberVO> memberList=helper.getMemberBLService().findMemberByName(tf_client.getText());
        ArrayList<TradeRecord> list = new ArrayList<TradeRecord>();

        double sum=0;

        for(PaymentModel model : data){
            TradeRecord record = new TradeRecord(model.getBank(),Double.parseDouble(model.getMoney()),model.getRemark());
            sum=sum+record.getMoney();
            list.add(record);
        }

        tradeVO.setRecordList(list);
        tradeVO.setSum(sum);

        if(memberList.size()==0)
            AlertUtil.showWarningAlert("不存在该客户！");
        else{
            tradeVO.setMember(memberList.get(0));
            helper.getTradeBLService().upload(tradeVO);
            AlertUtil.showInformationAlert("提交成功！");
            helper.getLogBlService().addLog(userVO,"提交付款单或收款单", ResultMessage.Success);
        }

    }

    @FXML
    public void delete() throws MalformedURLException,NotBoundException,RemoteException{
        helper.getTradeBLService().deleteDraft(tradeVO);
        AlertUtil.showInformationAlert("删除成功！");
        helper.getLogBlService().addLog(userVO,"删除付款单或收款单草稿", ResultMessage.Success);
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
