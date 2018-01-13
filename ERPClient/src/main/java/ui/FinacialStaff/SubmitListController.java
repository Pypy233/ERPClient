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
import rmi.RemoteHelper;
import ui.Main;
import ui.model.DraftModel;
import ui.model.SubmitModel;
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

public class SubmitListController implements Initializable{
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @FXML
    TableView<SubmitModel> tableView;
    @FXML
    TableColumn tc_type;
    @FXML
    TableColumn tc_number;
    @FXML
    TableColumn tc_choose;
    @FXML
    TableColumn tc_state;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public SubmitListController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo)throws RemoteException, MalformedURLException,NotBoundException{
        this.main=main;
        this.userVO=uservo;
        label_name.setText(userVO.getName());

        tableView.setEditable(true);
        tableView.refresh();

        tc_type.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        tc_number.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );

        tc_state.setCellValueFactory(
                new PropertyValueFactory<>("state")
        );

        tc_choose.setCellValueFactory(
                new PropertyValueFactory<>("checkBox")
        );

        ArrayList<TradeVO> tradeList=helper.getTradeBLService().getAllList();
        ArrayList<CashVO> cashList=helper.getCashBLService().getAllList();

        ObservableList<SubmitModel> data = FXCollections.observableArrayList();

        for(TradeVO vo: tradeList){
            if(vo.getState().equals("Pass")){
                if(vo.getType().equals("Collect")){
                    SubmitModel model =new SubmitModel("收款单",vo.getNumber(),"已通过");
                    data.add(model);
                }
                else{
                    SubmitModel model=new SubmitModel("付款单",vo.getNumber(),"已通过");
                    data.add(model);
                }
            }
            else if(vo.getState().equals("Fail")){
                if(vo.getType().equals("Collect")){
                    SubmitModel model =new SubmitModel("收款单",vo.getNumber(),"未通过");
                    data.add(model);
                }
                else{
                    SubmitModel model=new SubmitModel("付款单",vo.getNumber(),"未通过");
                    data.add(model);
                }
            }
            else if(vo.getState().equals("Process")){
                if(vo.getType().equals("Collect")){
                    SubmitModel model =new SubmitModel("收款单",vo.getNumber(),"待审核");
                    data.add(model);
                }
                else{
                    SubmitModel model=new SubmitModel("付款单",vo.getNumber(),"待审核");
                    data.add(model);
                }
            }
        }

        for(CashVO vo:cashList){
            if(vo.getState().equals("Pass")){
                SubmitModel model = new SubmitModel("现金单",vo.getNumber(),"已通过");
                data.add(model);
            }

            else if(vo.getState().equals("Fail")){
                SubmitModel model = new SubmitModel("现金单",vo.getNumber(),"未通过");
                data.add(model);
            }

            else if(vo.getState().equals("Process")){
                SubmitModel model = new SubmitModel("现金单",vo.getNumber(),"待审核");
                data.add(model);
            }
        }


        tableView.setItems(data);

    }

    @FXML
    public void check() throws RemoteException, MalformedURLException,NotBoundException{
        ObservableList<SubmitModel> data = tableView.getItems();
        int counter=0;
        for(SubmitModel model:data){
            if(model.checkBox.isSelected())
                counter++;
        }

        if(counter==0)
            AlertUtil.showWarningAlert("请选择所要查看的项！");
        else if(counter>=2)
            AlertUtil.showWarningAlert("只能选择一个项查看！");
        else{
            for(SubmitModel model:data){
                if(model.checkBox.isSelected()){
                    if(model.getType().equals("收款单")||model.getType().equals("付款单")){
                        ArrayList<TradeVO> temp=helper.getTradeBLService().getAllList();
                        for(TradeVO vo:temp){
                            if(vo.getNumber().equals(model.getNumber())){
                                //跳转
                                main.gotoSubmit_SKandFKD(userVO,vo);
                                break;
                            }
                        }
                    }
                    else{
                        ArrayList<CashVO> temp=helper.getCashBLService().getAllList();
                        for(CashVO vo:temp){
                            if(vo.getNumber().equals(model.getNumber())){
                                //跳转
                                main.gotoSubmit_XJFYD(userVO,vo);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void gotoFinacialStaffMain(){
        main.gotoFinacialStaffMain(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }


}
