package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.DraftModel;
import ui.util.AlertUtil;
import vo.CashVO;
import vo.TradeVO;
import vo.UserVO;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DraftListController implements Initializable{
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @FXML
    TableView<DraftModel> tableView;
    @FXML
    TableColumn tc_type;
    @FXML
    TableColumn tc_number;
    @FXML
    TableColumn tc_choose;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public DraftListController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo) throws MalformedURLException,NotBoundException,RemoteException {
        this.main=main;
        this.userVO=uservo;
        label_name.setText(userVO.getName());

        ArrayList<CashVO> cashList=helper.getCashBLService().getAllList();
        ArrayList<TradeVO> tradeList=helper.getTradeBLService().getAllList();

        ObservableList<DraftModel> data = FXCollections.observableArrayList();

        for(TradeVO vo:tradeList){
            if(vo.getState().equals("Draft")){
                if(vo.getType().equals("Collect")){
                    DraftModel model = new DraftModel("收款单",vo.getNumber());
                    data.add(model);
                }
                else{
                    DraftModel model = new DraftModel("付款单",vo.getNumber());
                    data.add(model);
                }
            }
        }

        for(CashVO vo:cashList){
            if(vo.getState().equals("Draft")){
                DraftModel model = new DraftModel("现金单",vo.getNumber());
                data.add(model);
            }
        }

        tableView.setEditable(true);
        tableView.refresh();

        tc_type.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        tc_number.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );

        tc_choose.setCellValueFactory(
                new PropertyValueFactory<>("checkBox")
        );

        tableView.setItems(data);
    }

    @FXML
    public void delete()throws MalformedURLException,NotBoundException,RemoteException{
        ObservableList<DraftModel> data =tableView.getItems();
        int counter=0;
        for(int i=0;i<data.size();i++){
            if(data.get(i).checkBox.isSelected()){
                counter++;
                if(data.get(i).getType().equals("付款单")||data.get(i).getType().equals("收款单")){
                    ArrayList<TradeVO> temp=helper.getTradeBLService().getAllList();
                    for(TradeVO vo:temp){
                        if(vo.getNumber().equals(data.get(i).getNumber())){
                            helper.getTradeBLService().deleteDraft(vo);
                            data.remove(i);
                            helper.getLogBlService().addLog(userVO,"删除付款单或收款单草稿", ResultMessage.Success);
                        }
                    }
                }
                else{
                    ArrayList<CashVO> temp=helper.getCashBLService().getAllList();
                    for(CashVO vo:temp){
                        if(vo.getNumber().equals(data.get(i).getNumber())){
                            helper.getCashBLService().deleteDraft(vo);
                            data.remove(i);
                            helper.getLogBlService().addLog(userVO,"删除现金费用单草稿", ResultMessage.Success);
                        }
                    }
                }
                i--;
            }
        }
        if(counter!=0)
            AlertUtil.showInformationAlert("删除成功！");

        else
            AlertUtil.showWarningAlert("请选择所要删除的项！");

        tableView.setEditable(true);
        tableView.refresh();

        tc_type.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        tc_number.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );

        tc_choose.setCellValueFactory(
                new PropertyValueFactory<>("checkBox")
        );

        tableView.setItems(data);
    }

    @FXML
    public void check()throws MalformedURLException,NotBoundException,RemoteException{
        ObservableList<DraftModel> data = tableView.getItems();
        int counter=0;
        for(DraftModel model : data){
            if(model.checkBox.isSelected())
                counter++;
        }

        if(counter==0)
            AlertUtil.showWarningAlert("请选择所要查看的项！");
        else if(counter>=2)
            AlertUtil.showWarningAlert("请选择一个项进行查看！");
        else{
            for(DraftModel model : data){
                if(model.checkBox.isSelected()){
                    if(model.getType().equals("收款单")||model.getType().equals("付款单")){
                        //跳转到相应的界面
                        ArrayList<TradeVO> temp = helper.getTradeBLService().getAllList();
                        for(TradeVO vo:temp){
                            if(vo.getNumber().equals(model.getNumber())){
                                main.gotoDraft_SKandFKD(userVO,vo);
                                break;
                            }
                        }
                    }
                    else{
                       ArrayList<CashVO> temp = helper.getCashBLService().getAllList();
                       for(CashVO vo:temp){
                           if(vo.getNumber().equals(model.getNumber())){
                               //跳转
                               main.gotoDraft_XJFYD(userVO,vo);
                               break;
                           }
                       }
                    }
                }
            }
        }
    }

    @FXML
    public void logout(){main.backToMain();}

    @FXML
    public void gotoFinacialStaffMain(){
        main.gotoFinacialStaffMain(userVO);
    }
}
