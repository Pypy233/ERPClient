package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.MakeBillModel;
import ui.util.AlertUtil;
import vo.AccountVO;
import vo.UserVO;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
public class MakeBillController implements Initializable {
    private Main main;
    private UserVO userVO;

    RemoteHelper helper=RemoteHelper.getInstance();

    @FXML
    Label label_name;
    @FXML
    TableView<MakeBillModel> tableView;
    @FXML
    TableColumn tc_date;
    @FXML
    TableColumn tc_choose;

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public MakeBillController(){

    }//一个构造函数

    @FXML
    public void setMain(Main main, UserVO uservo)throws MalformedURLException,NotBoundException,RemoteException {
        tableView.setEditable(true);
        tableView.refresh();
        this.main=main;
        this.userVO=uservo;
        label_name.setText(uservo.getName());
        ArrayList<AccountVO> list=helper.getAccountBLService().getList();

        ObservableList<MakeBillModel> data=FXCollections.observableArrayList();
        if(list.size()!=0) {
            for (AccountVO vo : list) {
                MakeBillModel model = new MakeBillModel(vo.getDate());
                data.add(model);
            }
        }

        tc_date.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );

        tc_choose.setCellValueFactory(
                new PropertyValueFactory<>("checkBox")
        );

        tableView.setItems(data);


    }

    @FXML
    public void seeBillDetail()throws MalformedURLException,NotBoundException,RemoteException{
        ObservableList<MakeBillModel> list =tableView.getItems();
        if(list.size()!=0){
            int counter=0;
            for(MakeBillModel model:list){
                if(model.getCheckBox().isSelected())
                    counter++;
            }
            if(counter==0)
                AlertUtil.showWarningAlert("请选择所要查看的期初账目！");
            else if(counter>=2)
                AlertUtil.showWarningAlert("只能查看一个账目！");
            else{
                for(MakeBillModel model:list){
                    if(model.getCheckBox().isSelected()){
                        ArrayList<AccountVO> temp = helper.getAccountBLService().getList();
                        for(AccountVO vo:temp){
                            if(vo.getDate().equals(model.getDate())){
                                main.gotoBillDetail(userVO,vo);
                            }
                        }

                    }
                }
            }
        }
    }

    @FXML
    public void newReceipt()throws MalformedURLException,NotBoundException,RemoteException{
        Date date = new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String today=f.format(date);
        ArrayList<AccountVO> list =helper.getAccountBLService().getList();
        int symbol=0;
        for(AccountVO vo:list){
            if(vo.getDate().equals(today)){
                symbol=1;
                break;
            }
        }

        if(symbol==1)
            AlertUtil.showWarningAlert("当天账目已存在！");
        else{
            helper.getAccountBLService().create();
            AlertUtil.showInformationAlert("新建成功！");
            helper.getLogBlService().addLog(userVO,"新建期初账成功", ResultMessage.Success);
        }
    }

    @FXML
    public void checkPrimary(){
        main.gotoMakeBill(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

    @FXML
    public void gotoFinacialStaffMain(){
        main.gotoFinacialStaffMain(userVO);
    }


}
