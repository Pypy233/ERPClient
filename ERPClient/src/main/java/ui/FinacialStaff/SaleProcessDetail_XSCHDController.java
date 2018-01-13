package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import objects.ExcelTools;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.SaleProcessDetail_XSLDJModel;
import ui.util.AlertUtil;
import vo.*;

import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

import ui.model.SaleProcessResultModel;

public class SaleProcessDetail_XSCHDController implements Initializable{
    private Main main;
    private UserVO userVO;
    private SaleVO saleVO;
    private ArrayList<SaleVO> mainList;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @FXML
    Label label_number;
    @FXML
    Label label_title;
    @FXML
    TextField tf_client;
    @FXML
    TextField tf_member;
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
    TableColumn<SaleProcessDetail_XSLDJModel,String> tc_number;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel,String> tc_name;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel,String> tc_type;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel,String> tc_ammount;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel,String> tc_price;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel,String> tc_sum;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel,String> tc_remark;
    @FXML
    Button b_red;
    @FXML
    Button b_redAndCopy;



    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public SaleProcessDetail_XSCHDController(){

    }//一个构造函数

    public void setMain(Main main ,UserVO uservo ,SaleVO salevo ,ArrayList<SaleVO> arrayList){
        this.mainList=arrayList;
        this.saleVO=salevo;
        tableView.setEditable(true);
        tableView.refresh();
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
        label_name.setText(uservo.getName());
        label_title.setText("出货商品清单");

        label_number.setText(salevo.getNumber());
        tf_client.setText(salevo.getRetailer());
        tf_member.setText(salevo.getSalesman());
        tf_operator.setText("4");
        tf_beforeSum.setText(""+salevo.getTotalPrice());
        tf_discount.setText(""+salevo.getDiscount());
        tf_substitute.setText(""+salevo.getVoucher());
        tf_afterSum.setText(""+salevo.getPayPrice());
        textArea.setText(salevo.getRemark());

        Set<GoodsSaleVO> set = salevo.getSaleSet();

        ObservableList<SaleProcessDetail_XSLDJModel> data = FXCollections.observableArrayList();

        for(GoodsSaleVO goodsSaleVO:set){
            SaleProcessDetail_XSLDJModel model = new SaleProcessDetail_XSLDJModel(goodsSaleVO.getVo().getNumber()+"",
                    goodsSaleVO.getVo().getName(),goodsSaleVO.getVo().getType(),""+goodsSaleVO.getSaleNumber(),""+goodsSaleVO.getPrice()
            ,goodsSaleVO.saleNumber*goodsSaleVO.getVo().getPurchasePrice()+"",goodsSaleVO.getRemark());

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

        tf_client.setEditable(false);
        tf_member.setEditable(false);
        tf_operator.setEditable(false);
        tf_beforeSum.setEditable(false);
        tf_discount.setEditable(false);
        tf_substitute.setEditable(false);
        tf_afterSum.setEditable(false);

        textArea.setEditable(false);
    }

    @FXML
    public void red() throws RemoteException{
        SaleVO temp=helper.getSaleBLService().addSaleRed(saleVO);
        AlertUtil.showInformationAlert("红冲成功！");
        helper.getLogBlService().addLog(userVO,"红冲", ResultMessage.Success);
    }

    @FXML
    public void redAndCopy() throws RemoteException{
        SaleVO salevo=helper.getSaleBLService().addSaleRed(saleVO);
        AlertUtil.showInformationAlert("红冲成功！");
        helper.getLogBlService().addLog(userVO,"红冲并复制", ResultMessage.Success);
        Set<GoodsSaleVO> set = salevo.getSaleSet();

        ObservableList<SaleProcessDetail_XSLDJModel> data = FXCollections.observableArrayList();

        for(GoodsSaleVO goodsSaleVO:set){
            SaleProcessDetail_XSLDJModel model = new SaleProcessDetail_XSLDJModel(goodsSaleVO.getVo().getNumber()+"",
                    goodsSaleVO.getVo().getName(),goodsSaleVO.getVo().getType(),""+goodsSaleVO.getSaleNumber(),""+goodsSaleVO.getPrice()
                    ,goodsSaleVO.getTotalPrice()+"",goodsSaleVO.getRemark());

            data.add(model);
        }

        tc_number.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );
        tc_number.setCellFactory(TextFieldTableCell.<SaleProcessDetail_XSLDJModel>forTableColumn());
        tc_number.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel,String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setNumber(t.getNewValue());
                }
        );

        tc_name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        tc_name.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel,String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setName(t.getNewValue());
                }
        );

        tc_type.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        tc_type.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel,String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setType(t.getNewValue());
                }
        );

        tc_ammount.setCellValueFactory(
                new PropertyValueFactory<>("ammount")
        );
        tc_ammount.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel,String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setAmmuont(t.getNewValue());
                }
        );

        tc_price.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        tc_price.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel,String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setPrice(t.getNewValue());
                }
        );

        tc_sum.setCellValueFactory(
                new PropertyValueFactory<>("sum")
        );
        tc_sum.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel,String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setSum(t.getNewValue());
                }
        );

        tc_remark.setCellValueFactory(
                new PropertyValueFactory<>("remark")
        );
        tc_remark.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel,String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setRemark(t.getNewValue());
                }
        );
        tableView.refresh();

        tableView.setItems(data);

        tf_client.setEditable(true);
        tf_member.setEditable(true);
        tf_operator.setEditable(true);
        tf_beforeSum.setEditable(true);
        tf_discount.setEditable(true);
        tf_substitute.setEditable(true);
        tf_afterSum.setEditable(true);

        textArea.setEditable(true);



    }

    @FXML
    public void out() throws Exception{
        Date date = new Date();
        ObservableList<SaleProcessDetail_XSLDJModel> list = tableView.getItems();
        int n=list.size();

        String a[][] = new String[8][n+1];
        a[0][0]="行号";
        a[1][0]="商品编号";
        a[2][0]="名称";
        a[3][0]="型号";
        a[4][0]="数量";
        a[5][0]="单价";
        a[6][0]="金额";
        a[7][0]="商品备注";

        for(int i=0;i<n;i++){
            a[0][i+1]=i+1+"";
            a[1][i+1]=list.get(i).getNumber();
            a[2][i+1]=list.get(i).getName();
            a[3][i+1]=list.get(i).getType();
            a[4][i+1]=list.get(i).getAmmuont();
            a[5][i+1]=list.get(i).getPrice();
            a[6][i+1]=list.get(i).getSum();
            a[7][i+1]=list.get(i).getRemark();
        }

        ExcelTools.export("销售出货单"+date.getTime(),a);
        AlertUtil.showInformationAlert("导出成功！");
    }


    @FXML
    public void gotoSaleProcess(){
        main.gotoSaleProcessResult_XSCHD(userVO,mainList);
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



}
