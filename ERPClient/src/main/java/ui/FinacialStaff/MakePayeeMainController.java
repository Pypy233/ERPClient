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
import vo.TradeRecord;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.MemberVO;
import vo.UserVO;
import vo.TradeVO;
import ui.model.PayeeModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MakePayeeMainController implements Initializable {
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
    Label label_payeeNumber;
    @FXML
    TextField tf_client;
    @FXML
    TableView<PayeeModel> tableView;
    @FXML
    TableColumn<PayeeModel,String> tc_bank;
    @FXML
    TableColumn<PayeeModel,String> tc_money;
    @FXML
    TableColumn<PayeeModel,String> tc_remark;
    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public MakePayeeMainController(){

    }

    @FXML
    public void setMain(Main main, UserVO uservo) throws IOException,MalformedURLException,NotBoundException,RemoteException{
        this.main=main;
        this.userVO=uservo;
        label_name.setText(uservo.getName());
        tf_client.setText("请输入客户姓名...");
        label_operator.setText(uservo.getName());
        label_payeeNumber.setText(helper.getTradeBLService().getNewnumber_collect());

        tableView.setEditable(true);

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


        tc_bank.setCellFactory(TextFieldTableCell.<PayeeModel>forTableColumn());
        tc_bank.setOnEditCommit(
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
    public void gotoFinacialStaffMain(){main.gotoFinacialStaffMain(userVO);}

    @FXML
    public void newPayee(){main.gotoMakePayee(userVO);}

    @FXML
    public void submit() throws MalformedURLException,NotBoundException,RemoteException{
        int counter=0;
        ObservableList<PayeeModel> list=tableView.getItems();
        double sum=0;

        ArrayList<TradeRecord> tradeRecords=new ArrayList<TradeRecord>();

        String date=label_payeeNumber.getText();
        char[] a=date.toCharArray();
        String year="";
        String month="";
        String day="";
        for(int i=4;i<=7;i++)
            year=year+a[i];
        for(int i=8;i<=9;i++)
            month=month+a[i];
        for(int i=10;i<=11;i++)
            day=day+a[i];
        date=year+"-"+month+"-"+day;

        for(PayeeModel model:list){
            if(!model.getBank().equals("")){
                counter++;
                sum=sum+Double.parseDouble(model.getMoney());
                TradeRecord tradeRecord=new TradeRecord(model.getBank(),Double.parseDouble(model.getMoney()),model.getRemark());
                tradeRecords.add(tradeRecord);
            }
        }

        if(counter!=0){
            ArrayList<MemberVO> member=helper.getMemberBLService().findMemberByName(tf_client.getText());
            if(member.size()==0)
                AlertUtil.showWarningAlert("客户不存在！");
            else if(member.size()==1) {
                TradeVO tradeVO = new TradeVO("Collect", label_payeeNumber.getText(), member.get(0), userVO, tradeRecords, sum, date, "Process");
                ResultMessage resultMessage = helper.getTradeBLService().upload(tradeVO);
                AlertUtil.showInformationAlert("提交成功！");
                helper.getLogBlService().addLog(userVO,"提交收款单", ResultMessage.Success);
            }
            else{
                //重名用户
            }
        }

    }

    @FXML
    public void save()throws MalformedURLException,NotBoundException,RemoteException{
        ObservableList<PayeeModel> list=tableView.getItems();
        double sum=0;

        ArrayList<TradeRecord> tradeRecords=new ArrayList<TradeRecord>();

        String date=label_payeeNumber.getText();
        char[] a=date.toCharArray();
        String year="";
        String month="";
        String day="";
        for(int i=4;i<=7;i++)
            year=year+a[i];
        for(int i=8;i<=9;i++)
            month=month+a[i];
        for(int i=10;i<=11;i++)
            day=day+a[i];
        date=year+"-"+month+"-"+day;

        for(PayeeModel model:list){
            if(!model.getBank().equals("")){
                sum=sum+Double.parseDouble(model.getMoney());
                TradeRecord tradeRecord=new TradeRecord(model.getBank(),Double.parseDouble(model.getMoney()),model.getRemark());
                tradeRecords.add(tradeRecord);
            }
        }
        MemberVO memberVO=new MemberVO();
        memberVO.setName(tf_client.getText());
        System.out.println(memberVO.getName());
        TradeVO tradeVO = new TradeVO("Collect", label_payeeNumber.getText(), memberVO, userVO, tradeRecords, sum, date, "Draft ");
        ResultMessage resultMessage = helper.getTradeBLService().addDraft(tradeVO);
        AlertUtil.showInformationAlert("保存成功！");
        helper.getLogBlService().addLog(userVO,"保存收款单", ResultMessage.Success);
    }

    @FXML
    public void logout(){main.backToMain();}



}
