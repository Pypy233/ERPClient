package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import objects.ExcelTools;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.BYDModel;
import ui.util.AlertUtil;
import vo.*;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

public class SaleProcessDetail_ZSDController implements Initializable{
    private Main main;
    private UserVO userVO;
    private PresentListVO presentListVO;
    private ArrayList<PresentListVO> mainList;

    RemoteHelper helper = RemoteHelper.getInstance();

    @FXML
    TableView<BYDModel> tableView;
    @FXML
    TableColumn<BYDModel,String> tc_number;
    @FXML
    TableColumn<BYDModel,String> tc_name;
    @FXML
    TableColumn<BYDModel,String> tc_type;
    @FXML
    TableColumn<BYDModel,String> tc_ammount;
    @FXML
    TableColumn<BYDModel,String> tc_price;
    @FXML
    Label label_number;
    @FXML
    Label label_name;
    @FXML
    Label label_date;
    @FXML
    TextField tf_operator;
    @FXML
    Button b_red;
    @FXML
    Button b_redAndCopy;

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public SaleProcessDetail_ZSDController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo,PresentListVO presentlistvo,ArrayList<PresentListVO> arrayList){
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

        this.presentListVO=presentlistvo;
        this.mainList=arrayList;

        label_number.setText(""+presentListVO.getId());
        label_name.setText(userVO.getName());
        label_date.setText(presentListVO.getDate());

        tf_operator.setText("4");

        Set<PresentVO> set = presentlistvo.getSet();

        ObservableList<BYDModel> data = FXCollections.observableArrayList();

        for(PresentVO vo:set){
            BYDModel model = new BYDModel(vo.getVo().getNumber()+"",vo.getVo().getName(),
                    vo.getVo().getType(),vo.getNumber()+"",vo.getVo().getPurchasePrice()+"");
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

        tableView.setItems(data);

        tf_operator.setEditable(false);
    }

    @FXML
    public void red() throws RemoteException{
        PresentListVO temp=helper.getPresentListBLService().addPresentListRed(presentListVO);
        AlertUtil.showInformationAlert("红冲成功！");
        helper.getLogBlService().addLog(userVO,"红冲", ResultMessage.Success);
    }

    @FXML
    public void redAndCopy() throws RemoteException{
        PresentListVO temp=helper.getPresentListBLService().addPresentListRed(presentListVO);
        AlertUtil.showInformationAlert("红冲成功！");
        helper.getLogBlService().addLog(userVO,"红冲并复制", ResultMessage.Success);
        Set<PresentVO> set = presentListVO.getSet();

        ObservableList<BYDModel> data = FXCollections.observableArrayList();

        for(PresentVO vo:set){
            BYDModel model = new BYDModel(vo.getVo().getNumber()+"",vo.getVo().getName(),
                    vo.getVo().getType(),vo.getNumber()+"",vo.getVo().getPurchasePrice()+"");
            data.add(model);
        }

        tc_number.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );
        tc_number.setCellFactory(TextFieldTableCell.<BYDModel>forTableColumn());
        tc_number.setOnEditCommit(
                (TableColumn.CellEditEvent<BYDModel,String> t) -> {
                    ((BYDModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setNumber(t.getNewValue());
                }
        );

        tc_name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        tc_name.setOnEditCommit(
                (TableColumn.CellEditEvent<BYDModel,String> t) -> {
                    ((BYDModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setName(t.getNewValue());
                }
        );

        tc_type.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        tc_type.setOnEditCommit(
                (TableColumn.CellEditEvent<BYDModel,String> t) -> {
                    ((BYDModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setType(t.getNewValue());
                }
        );

        tc_ammount.setCellValueFactory(
                new PropertyValueFactory<>("ammount")
        );
        tc_ammount.setOnEditCommit(
                (TableColumn.CellEditEvent<BYDModel,String> t) -> {
                    ((BYDModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setAmmuont(t.getNewValue());
                }
        );

        tc_price.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        tc_price.setOnEditCommit(
                (TableColumn.CellEditEvent<BYDModel,String> t) -> {
                    ((BYDModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setPrice(t.getNewValue());
                }
        );

        tableView.setItems(data);

        tf_operator.setEditable(true);
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

    @FXML
    public void gotoSaleProcessResult(){
        main.gotoSaleProcessResult_ZSD(userVO,mainList);
    }

    @FXML
    public void out() throws Exception{
        Date date = new Date();
        ObservableList<BYDModel> list = tableView.getItems();
        int n=list.size();

        String a[][] = new String[6][n+1];
        a[0][0]="行号";
        a[1][0]="商品编号";
        a[2][0]="名称";
        a[3][0]="型号";
        a[4][0]="数量";
        a[5][0]="进价";

        for(int i=0;i<n;i++){
            a[0][i+1]=i+1+"";
            a[1][i+1]=list.get(i).getNumber();
            a[2][i+1]=list.get(i).getName();
            a[3][i+1]=list.get(i).getType();
            a[4][i+1]=list.get(i).getAmmuont();
            a[5][i+1]=list.get(i).getPrice();
        }

        ExcelTools.export("赠送单"+date.getTime(),a);
        AlertUtil.showInformationAlert("导出成功！");
    }



}
