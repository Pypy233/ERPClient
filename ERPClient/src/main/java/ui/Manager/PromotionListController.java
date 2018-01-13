package ui.Manager;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.*;
import ui.util.AlertUtil;
import vo.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class PromotionListController implements Initializable{
    private UserVO userVO;
    private Main main;

    RemoteHelper helper = RemoteHelper.getInstance();

    @FXML
    Label label_name;
    @FXML
    TableView<PromotionListModel> tableView;
    @FXML
    TableColumn tc_date;
    @FXML
    TableColumn tc_choose;

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public PromotionListController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo) throws RemoteException,MalformedURLException,NotBoundException{
        this.main=main;
        this.userVO=uservo;
        label_name.setText(userVO.getName());

        ArrayList<PromotionVO> list = helper.getPromotionBLService().getList();

        tableView.setEditable(true);

        ObservableList<PromotionListModel> data = FXCollections.observableArrayList();
        for(PromotionVO vo:list){
            PromotionListModel model = new PromotionListModel(vo.getBegin()+"到"+vo.getEnd());
            data.add(model);
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
    public void see() throws RemoteException,MalformedURLException,NotBoundException{
        ObservableList<PromotionListModel> data = tableView.getItems();
        int counter=0;
        for(PromotionListModel model : data){
            if(model.checkBox.isSelected())
                counter++;
        }
        if(counter==0)
            AlertUtil.showWarningAlert("请选择所要查看的单据！");
        else if(counter>=2)
            AlertUtil.showWarningAlert("只能选择一个进行查看！");
        else{
            for(PromotionListModel model : data){
                if(model.checkBox.isSelected()){
                    ArrayList<PromotionVO> temp = helper.getPromotionBLService().getList();
                    for(PromotionVO vo:temp){
                        if(vo.getBegin().equals(model.getDate().substring(0,10))&&
                                vo.getEnd().equals(model.getDate().substring(11,model.getDate().length()))){
                            //跳转到相应界面
                            main.gotoSeePromotion(userVO,vo);
                            break;
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void delete() throws RemoteException,MalformedURLException,NotBoundException{
        ObservableList<PromotionListModel> data = tableView.getItems();
        int counter=0;
        for(int i=0;i<data.size();i++){
            if(data.get(i).checkBox.isSelected()){
                counter++;
                ArrayList<PromotionVO> temp = helper.getPromotionBLService().getList();
                for(PromotionVO vo:temp){
                    if(vo.getBegin().equals(data.get(i).getDate().substring(0,10))&&
                            vo.getEnd().equals(data.get(i).getDate().substring(11,data.get(i).getDate().length())))
                        helper.getPromotionBLService().delete(vo);
                }

                data.remove(i);
                i--;
            }
        }
        if(counter==0)
            AlertUtil.showWarningAlert("请选择所要删除的策略！");
        else
            AlertUtil.showInformationAlert("删除成功");
    }

    @FXML
    public void checkPromotion(){
        main.gotoCheckPromotion(userVO);
    }

    @FXML
    public void newPromotion(){
        main.gotoPromotion(userVO);
    }

    @FXML
    public void gotoManagerMain(){
        main.gotoManagerMain(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

}
