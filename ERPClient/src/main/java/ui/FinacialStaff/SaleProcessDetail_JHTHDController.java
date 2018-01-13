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
import ui.model.SaleProcessDetail_XSLDJModel;
import ui.util.AlertUtil;
import vo.*;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;


public class SaleProcessDetail_JHTHDController implements Initializable {
    private Main main;
    private UserVO userVO;
    private ArrayList<StockReturnVO> mainList;
    RemoteHelper helper = RemoteHelper.getInstance();
    private StockReturnVO stockReturnVO;

    @FXML
    TextArea textArea;
    @FXML
    TableView<SaleProcessDetail_XSLDJModel> tableView;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel, String> tc_number;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel, String> tc_name;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel, String> tc_type;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel, String> tc_ammount;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel, String> tc_price;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel, String> tc_sum;
    @FXML
    TableColumn<SaleProcessDetail_XSLDJModel, String> tc_remark;
    @FXML
    Label label_number;
    @FXML
    Label label_name;
    @FXML
    TextField tf_retailer;
    @FXML
    TextField tf_operator;
    @FXML
    Label label_sum;
    @FXML
    Button b_red;
    @FXML
    Button b_redAndCopy;


    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public SaleProcessDetail_JHTHDController() {

    }//一个构造函数

    public void setMain(Main main, UserVO uservo, StockReturnVO stockreturnvo, ArrayList<StockReturnVO> arrayList) {
        this.main = main;
        this.userVO = uservo;

        if(userVO.getType().equals("总经理")){
            b_red.setVisible(false);
            b_redAndCopy.setVisible(false);
        }
        else{
            b_red.setVisible(true);
            b_redAndCopy.setVisible(true);
        }

        this.stockReturnVO=stockreturnvo;
        label_name.setText(userVO.getName());
        mainList = arrayList;
        tableView.setEditable(true);
        tableView.refresh();

        label_number.setText(stockreturnvo.getNumber());
        tf_retailer.setText(stockreturnvo.getProvider());
        tf_operator.setText("4");
        textArea.setText(stockreturnvo.getRemark());
        label_sum.setText("￥" + stockreturnvo.getTotalPrice());

        Set<GoodsStockReturnVO> set = stockreturnvo.getStockSet();

        ObservableList<SaleProcessDetail_XSLDJModel> data = FXCollections.observableArrayList();

        for (GoodsStockReturnVO vo : set) {
            SaleProcessDetail_XSLDJModel model = new SaleProcessDetail_XSLDJModel(vo.getVo().getNumber() + "", vo.getVo().getName(), vo.getVo().getType(),
                    vo.getStockReturnNumber() + "", vo.getVo().getPurchasePrice() + "", vo.getStockReturnNumber()*vo.getVo().getPurchasePrice() + "", vo.getRemark());
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

        tf_operator.setEditable(false);
        tf_retailer.setEditable(false);
        textArea.setEditable(false);
    }

    @FXML
    public void red() throws RemoteException {
        StockReturnVO temp = helper.getStockReturnBLService().addStockReturnRed(stockReturnVO);
        AlertUtil.showInformationAlert("红冲成功！");
        helper.getLogBlService().addLog(userVO,"红冲", ResultMessage.Success);
    }

    @FXML
    public void redAndCopy() throws RemoteException {
        StockReturnVO stockreturnvo = helper.getStockReturnBLService().addStockReturnRed(stockReturnVO);
        AlertUtil.showInformationAlert("红冲成功！");
        helper.getLogBlService().addLog(userVO,"红冲并复制", ResultMessage.Success);
        Set<GoodsStockReturnVO> set = stockreturnvo.getStockSet();

        ObservableList<SaleProcessDetail_XSLDJModel> data = FXCollections.observableArrayList();

        for (GoodsStockReturnVO vo : set) {
            SaleProcessDetail_XSLDJModel model = new SaleProcessDetail_XSLDJModel(vo.getVo().getNumber() + "", vo.getVo().getName(), vo.getVo().getType(),
                    vo.getStockReturnNumber() + "", vo.getVo().getPurchasePrice() + "", vo.getTotalPrice() + "", vo.getRemark());
            data.add(model);
        }
        tc_number.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );
        tc_number.setCellFactory(TextFieldTableCell.<SaleProcessDetail_XSLDJModel>forTableColumn());
        tc_number.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel, String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setNumber(t.getNewValue());
                }
        );

        tc_name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        tc_name.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel, String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setName(t.getNewValue());
                }
        );

        tc_type.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        tc_type.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel, String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setType(t.getNewValue());
                }
        );

        tc_ammount.setCellValueFactory(
                new PropertyValueFactory<>("ammount")
        );
        tc_ammount.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel, String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setAmmuont(t.getNewValue());
                }
        );

        tc_price.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        tc_price.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel, String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setPrice(t.getNewValue());
                }
        );

        tc_sum.setCellValueFactory(
                new PropertyValueFactory<>("sum")
        );
        tc_sum.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel, String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setSum(t.getNewValue());
                }
        );

        tc_remark.setCellValueFactory(
                new PropertyValueFactory<>("remark")
        );
        tc_remark.setOnEditCommit(
                (TableColumn.CellEditEvent<SaleProcessDetail_XSLDJModel, String> t) -> {
                    ((SaleProcessDetail_XSLDJModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setRemark(t.getNewValue());
                }
        );
        tableView.refresh();
        tableView.setItems(data);

        tf_operator.setEditable(true);
        tf_retailer.setEditable(true);
        textArea.setEditable(true);
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
        main.gotoSaleProcessResult_JHTHD(userVO,mainList);
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

        ExcelTools.export("进货退货单"+date.getTime(),a);
        AlertUtil.showInformationAlert("导出成功！");
    }

}



