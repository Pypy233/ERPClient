package ui.Manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.CheckModel;
import ui.model.SaleProcessDetail_XSLDJModel;
import ui.util.AlertUtil;
import vo.*;

import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class Check_XSJHDController implements Initializable{
    private Main main;
    private UserVO userVO;
    private SaleVO saleVO;
    RemoteHelper helper = RemoteHelper.getInstance();

    @FXML
    Label label_name;
    @FXML
    Label label_number;
    @FXML
    TextField tf_client;
    @FXML
    TextField tf_member;//业务员
    @FXML
    TextField tf_operator;
    @FXML
    TextField tf_beforeSum;
    @FXML
    TextField tf_discount;
    @FXML
    TextField tf_substitute;
    @FXML
    TextField tf_afterSum;
    @FXML
    TextArea textArea;
    @FXML
    TableView<SaleProcessDetail_XSLDJModel> tableView;
    @FXML
    TableColumn tc_number;
    @FXML
    TableColumn tc_name;
    @FXML
    TableColumn tc_type;
    @FXML
    TableColumn tc_ammount;
    @FXML
    TableColumn tc_price;
    @FXML
    TableColumn tc_sum;
    @FXML
    TableColumn tc_remark;



    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public Check_XSJHDController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo,SaleVO salevo){
        this.main=main;
        this.userVO=uservo;
        this.saleVO=salevo;
        label_name.setText(userVO.getName());

        tableView.setEditable(true);
        tableView.refresh();

        label_number.setText(salevo.getNumber());
        tf_client.setText(salevo.getRetailer());
        tf_member.setText(salevo.getSalesman());
        tf_operator.setText("4");
        tf_beforeSum.setText(salevo.getTotalPrice()+"");
        tf_discount.setText(salevo.getDiscount()+"");
        tf_afterSum.setText(salevo.getPayPrice()+"");
        tf_substitute.setText(salevo.getVoucher()+"");

        textArea.setText(salevo.getRemark());

        tf_client.setEditable(false);
        tf_member.setEditable(false);
        tf_operator.setEditable(false);
        tf_beforeSum.setEditable(false);
        tf_discount.setEditable(false);
        tf_afterSum.setEditable(false);
        tf_substitute.setEditable(false);

        textArea.setEditable(false);

        Set<GoodsSaleVO> set=salevo.getSaleSet();

        ObservableList<SaleProcessDetail_XSLDJModel> data=FXCollections.observableArrayList();

        for(GoodsSaleVO vo:set){
            SaleProcessDetail_XSLDJModel model = new SaleProcessDetail_XSLDJModel(vo.getVo().getNumber()+"",vo.getVo().getName(),vo.getVo().getType(),
                    vo.getSaleNumber()+"",vo.getPrice()+"",vo.getSaleNumber()*vo.getVo().getPurchasePrice()+"",vo.getRemark());
            data.add(model);
        }

        tc_number.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );

        tc_name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        tc_type.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        tc_ammount.setCellValueFactory(
                new PropertyValueFactory<>("ammuont")
        );

        tc_price.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );

        tc_sum.setCellValueFactory(
                new PropertyValueFactory<>("sum")
        );

        tc_remark.setCellValueFactory(
                new PropertyValueFactory<>("remark")
        );

        tableView.setItems(data);

    }

    @FXML
    public void pass() throws RemoteException{
        helper.getSaleBLService().passSaleCheck(saleVO);
        AlertUtil.showInformationAlert("审批成功！");
        main.gotoSeeThrough(userVO);
    }

    @FXML
    public void fail() throws RemoteException{
        helper.getSaleBLService().failSaleCheck(saleVO);
        AlertUtil.showInformationAlert("审批成功！");
        main.gotoSeeThrough(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

    @FXML
    public void gotoSeeThrough(){
        main.gotoSeeThrough(userVO);
    }

    @FXML
    public void gotoManagerMain(){
        main.gotoManagerMain(userVO);
    }
}
