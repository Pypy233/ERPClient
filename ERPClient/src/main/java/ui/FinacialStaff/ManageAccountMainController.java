package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import objects.ResultMessage;
import rmi.RemoteHelper;
import service.blservice.BankBLService;
import ui.Main;
import ui.util.AlertUtil;
import vo.UserVO;
import vo.BankVO;
import ui.model.AccountModel;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;



import java.net.URL;
import java.util.ResourceBundle;

public class ManageAccountMainController implements Initializable {
    @FXML
    Label label_name;
    @FXML
    Label label_number;
    @FXML
    TableColumn<AccountModel,String> tc_account;//账户名
    @FXML
    TableColumn<AccountModel,Double> tc_money;//金额
    @FXML
    TextField tf_accountID;//账户名
    @FXML
    TableView<AccountModel> tableView;
    @FXML
    TableColumn<AccountModel,CheckBox> tc_choose;
    private  Main main;
    private UserVO userVO;

    RemoteHelper remoteHelper = RemoteHelper.getInstance();

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public ManageAccountMainController(){

    }

    public void setMain(Main main, UserVO uservo){
        this.main=main;
        this.userVO=uservo;
        label_name.setText(userVO.getName());
        tableView.refresh();
        tf_accountID.setText("请输入所要查询的账户...");
        tableView.setEditable(true);

    }

    @FXML
    public void gotoaddAccount(){main.gotoAddAccount(userVO);}

    @FXML
    public void gotodeleteAccount(){main.gotoDeleteAccount(userVO);}

    @FXML
    public void gotocheckAccount(){main.gotoManageAccount(userVO);}

    @FXML
    public void check() throws MalformedURLException,NotBoundException,RemoteException {
        String keywords = tf_accountID.getText();
        ArrayList<BankVO> list=new ArrayList<BankVO>();
        if(keywords.equals(""))
            list= remoteHelper.getBankBLService().getList();
        else
            list=remoteHelper.getBankBLService().getList(keywords);
        if(list.size()==0){
            AlertUtil.showErrorAlert("相关账户不存在!");
        }
        else {
            tableView.refresh();
            tableView.setEditable(true);
            ObservableList<AccountModel> data = FXCollections.observableArrayList();
            for (int i = 0; i < list.size(); i++) {
                AccountModel model = new AccountModel(list.get(i).getName(), list.get(i).getBalance());
                data.add(model);
            }

            tc_account.setCellValueFactory(
                    new PropertyValueFactory<>("account")
            );
            tc_account.setCellFactory(TextFieldTableCell.<AccountModel>forTableColumn());
            tc_account.setOnEditCommit(
                    (CellEditEvent<AccountModel, String> t) -> {
                        ((AccountModel) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAccount(t.getNewValue());
                    }
            );

            tc_money.setCellValueFactory(
                    new PropertyValueFactory<>("money")
            );


            tc_choose.setCellValueFactory(
                    new PropertyValueFactory<>("checkBox")
            );


            tableView.setItems(data);
        }

        }
        @FXML
        public void revise() throws MalformedURLException,NotBoundException,RemoteException{
           ObservableList<AccountModel> list=tableView.getItems();
           int symbol=0;
           for(AccountModel model:list){
               BankVO vo = new BankVO(model.getAccount(),model.getMoney());
               ArrayList<BankVO> check=remoteHelper.getBankBLService().getList();
               symbol=0;
               for(BankVO bankvo:check){
                   if(bankvo.getName().equals(vo.getName())){
                       symbol=1;
                       break;
                   }
               }
               if(symbol==0)
                   remoteHelper.getBankBLService().update(vo);
               else{
                   AlertUtil.showWarningAlert("存在重名账户！");
                   break;
               }


           }

        }

        @FXML
        public void delete()throws MalformedURLException,NotBoundException,RemoteException{
        ObservableList<AccountModel> list =tableView.getItems();
        int counter=0;
        for(int i=0;i<list.size();i++){
            if(list.get(i).checkBox.isSelected()){
                BankVO vo=new BankVO(list.get(i).getAccount(),list.get(i).getMoney());
                remoteHelper.getBankBLService().delete(vo);
                list.remove(i);
                i--;
                counter++;
            }
        }
        if(counter==0)
            AlertUtil.showWarningAlert("请选择所要删除的项！");
        else{
            AlertUtil.showInformationAlert("删除成功！");
            remoteHelper.getLogBlService().addLog(userVO,"删除银行账户", ResultMessage.Success);
            //重新显示
            tableView.refresh();
            tableView.setEditable(true);

            tc_account.setCellValueFactory(
                    new PropertyValueFactory<>("account")
            );
            tc_account.setCellFactory(TextFieldTableCell.<AccountModel>forTableColumn());
            tc_account.setOnEditCommit(
                    (CellEditEvent<AccountModel, String> t) -> {
                        ((AccountModel) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAccount(t.getNewValue());
                    }
            );

            tc_money.setCellValueFactory(
                    new PropertyValueFactory<>("money")
            );

            tc_choose.setCellValueFactory(
                    new PropertyValueFactory<>("checkBox")
            );

            tableView.setItems(list);
        }
        }

    @FXML
    public void logout(){main.backToMain();}
    @FXML
    public void returnToFinacialStaffMain(){main.gotoFinacialStaffMain(userVO);}
}
