package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import objects.ResultMessage;
import sun.rmi.runtime.Log;
import ui.model.LogModel;
import vo.*;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import ui.model.PaymentModel;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LogController implements Initializable{

    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();

    @FXML
    Label label_name;
    @FXML
    TableView<LogModel> tableView;
    @FXML
    TableColumn tc_user;
    @FXML
    TableColumn tc_operation;

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public LogController(){

    }//一个构造函数

    public void setMain(Main main ,UserVO uservo) throws RemoteException{
        this.userVO=uservo;
        this.main=main;
        label_name.setText(userVO.getName());
        ArrayList<LogVO> list=helper.getLogBlService().getLog(userVO);

        ObservableList<LogModel> data=FXCollections.observableArrayList();
        for(LogVO vo:list){
            LogModel model = new LogModel (vo.getName(),vo.getOperation());
            data.add(model);
        }

        tableView.setEditable(true);

        tc_user.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        tc_operation.setCellValueFactory(
                new PropertyValueFactory<>("operation")
        );

        tableView.setItems(data);
    }

    @FXML
    public void logout(){main.backToMain();}

    @FXML
    public void gotoFinacialStaffMain(){
        if(userVO.getType().equals("总经理"))
            main.gotoManagerMain(userVO);
        else
            main.gotoFinacialStaffMain(userVO);
    }

}
