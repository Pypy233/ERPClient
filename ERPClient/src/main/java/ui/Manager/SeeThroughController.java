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

public class SeeThroughController implements Initializable{
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();


    @FXML
    Label label_name;
    @FXML
    TableView<CheckModel> tableView;
    @FXML
    TableColumn<CheckModel,String> tc_type;
    @FXML
    TableColumn<CheckModel,String> tc_number;
    @FXML
    TableColumn<CheckModel,CheckBox> tc_pass;
    @FXML
    TableColumn<CheckModel,CheckBox> tc_fail;
    @FXML
    TableColumn<CheckModel,CheckBox> tc_choose;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public SeeThroughController(){

    }//一个构造函数

    public void setMain(Main main, UserVO uservo) throws MalformedURLException,NotBoundException,RemoteException{
        this.main=main;
        this.userVO=uservo;
        label_name.setText(userVO.getName());
        tableView.setEditable(true);
        tableView.refresh();
        ArrayList<SaleVO> saleVOList=new ArrayList<SaleVO>();
        saleVOList=helper.getSaleBLService().getSaleProcessList();
        ArrayList<SaleReturnVO> saleReturnVOList =new ArrayList<SaleReturnVO>();
        saleReturnVOList=helper.getSaleReturnBLService().getSaleReturnProcessList();
        ArrayList<StockVO> stockVOList = new ArrayList<StockVO>();
        stockVOList=helper.getStockBLService().getStockProcessList();
        ArrayList<StockReturnVO> stockReturnList =new ArrayList<StockReturnVO>();
        stockReturnList= helper.getStockReturnBLService().getStockReturnProcessList();
        ArrayList<TradeVO> tradeVOList = helper.getTradeBLService().getAllList();
        ArrayList<CashVO> cashVOList = helper.getCashBLService().getAllList();

        ObservableList<CheckModel> data = FXCollections.observableArrayList();
        for(SaleVO vo:saleVOList){
            if(vo.getState().equals("Process")) {
                CheckModel model = new CheckModel("销售出货单", vo.getNumber());
                data.add(model);
            }
        }

        for(SaleReturnVO vo:saleReturnVOList){
            if(vo.getState().equals("Process")) {
                CheckModel model = new CheckModel("销售退货单", vo.getNumber());
                data.add(model);
            }
       }

        for(StockVO vo:stockVOList){
            if(vo.getState().equals("Process" )){
                CheckModel model = new CheckModel("进货单", vo.getNumber());
                data.add(model);
            }
        }

        for(StockReturnVO vo:stockReturnList){
            if(vo.getState().equals("Process" )) {
                CheckModel model = new CheckModel("进货退货单", vo.getNumber());
                data.add(model);
            }
        }

        for(TradeVO vo:tradeVOList){
            if(vo.getState().equals("Process" )) {
                if (vo.getType().equals("Collect")) {
                    CheckModel model = new CheckModel("收款单", vo.getNumber());
                    data.add(model);
                } else {
                    CheckModel model = new CheckModel("付款单", vo.getNumber());
                    data.add(model);
                }
            }
        }

        for(CashVO vo:cashVOList){
            if(vo.getState().equals("Process" )) {
                CheckModel model = new CheckModel("现金费用单", vo.getNumber());
                data.add(model);
            }
        }

        tc_type.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        tc_number.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );

        tc_pass.setCellValueFactory(
                new PropertyValueFactory<>("pass")
        );

        tc_fail.setCellValueFactory(
                new PropertyValueFactory<>("fail")
        );

        tc_choose.setCellValueFactory(
                new PropertyValueFactory<>("choose")
        );

        tableView.setItems(data);

    }

    @FXML
    public void confirm() throws MalformedURLException,NotBoundException,RemoteException{
        ObservableList<CheckModel> data = tableView.getItems();
        int counter=0;
        for(int i=0;i<data.size();i++){
            if(data.get(i).choose.isSelected())
                counter++;
        }
        if(counter!=0)
            AlertUtil.showWarningAlert("操作错误！");
        else{
            for(int i=0;i<data.size();i++){
                if(data.get(i).pass.isSelected()&&(!data.get(i).fail.isSelected())){
                    if(data.get(i).getType().equals("销售出货单")){
                        ArrayList<SaleVO> temp = helper.getSaleBLService().getSaleProcessList();
                        for(SaleVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getSaleBLService().passSaleCheck(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("销售退货单")){
                        ArrayList<SaleReturnVO> temp = helper.getSaleReturnBLService().getSaleReturnProcessList();
                        for(SaleReturnVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getSaleReturnBLService().passSaleReturnCheck(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("进货单")){
                        ArrayList<StockVO> temp = helper.getStockBLService().getStockProcessList();
                        for(StockVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getStockBLService().passStockCheck(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("进货退货单")){
                        ArrayList<StockReturnVO> temp = helper.getStockReturnBLService().getStockReturnProcessList();
                        for(StockReturnVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getStockReturnBLService().passStockReturnCheck(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("收款单")){
                        ArrayList<TradeVO> temp = helper.getTradeBLService().getProcessedCollectList();
                        for(TradeVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getTradeBLService().pass(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("付款单")){
                        ArrayList<TradeVO> temp = helper.getTradeBLService().getProcessedPayList();
                        for(TradeVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getTradeBLService().pass(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("现金费用单")){
                        ArrayList<CashVO> temp = helper.getCashBLService().getProcessedList();
                        for(CashVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getCashBLService().pass(vo);
                        }
                    }
                    data.remove(i);
                    i--;
                }
                else if(data.get(i).fail.isSelected()&&(!data.get(i).pass.isSelected())){
                    if(data.get(i).getType().equals("销售出货单")){
                        ArrayList<SaleVO> temp = helper.getSaleBLService().getSaleProcessList();
                        for(SaleVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getSaleBLService().failSaleCheck(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("销售退货单")){
                        ArrayList<SaleReturnVO> temp = helper.getSaleReturnBLService().getSaleReturnProcessList();
                        for(SaleReturnVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getSaleReturnBLService().failSaleReturnCheck(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("进货单")){
                        ArrayList<StockVO> temp = helper.getStockBLService().getStockProcessList();
                        for(StockVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getStockBLService().failStockCheck(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("进货退货单")){
                        ArrayList<StockReturnVO> temp = helper.getStockReturnBLService().getStockReturnProcessList();
                        for(StockReturnVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getStockReturnBLService().failStockReturnCheck(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("收款单")){
                        ArrayList<TradeVO> temp = helper.getTradeBLService().getProcessedCollectList();
                        for(TradeVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getTradeBLService().unpass(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("付款单")){
                        ArrayList<TradeVO> temp = helper.getTradeBLService().getProcessedPayList();
                        for(TradeVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getTradeBLService().unpass(vo);
                        }
                    }

                    else if(data.get(i).getType().equals("现金费用单")){
                        ArrayList<CashVO> temp = helper.getCashBLService().getProcessedList();
                        for(CashVO vo:temp){
                            if(vo.getNumber().equals(data.get(i).getNumber()))
                                helper.getCashBLService().unpass(vo);
                        }
                    }
                    data.remove(i);
                    i--;
                }

                else{

                }

            }

            tableView.refresh();
            tableView.setItems(data);

        }
    }

    @FXML
    public void see() throws MalformedURLException,NotBoundException,RemoteException{
        int counter=0;
        int counter2=0;
        ObservableList<CheckModel> data = tableView.getItems();
        for(int i=0;i<data.size();i++){
            if(data.get(i).fail.isSelected()||data.get(i).pass.isSelected())
                counter++;
            else if(data.get(i).choose.isSelected())
                counter2++;
        }
        if(counter!=0)
            AlertUtil.showWarningAlert("选择错误！");
        else if(counter2==0&&counter==0)
            AlertUtil.showWarningAlert("请选择要查看的单据！");
        else if(counter2>=2&&counter==0)
            AlertUtil.showWarningAlert("只能选择一个查看！");
        else if(counter2==1&&counter==0){
            for(CheckModel model : data ){
                if(model.choose.isSelected()){
                    if(model.getType().equals("销售出货单")){
                        ArrayList<SaleVO> list = helper.getSaleBLService().getSaleProcessList();
                        for(SaleVO vo:list){
                            if(model.getNumber().equals(vo.getNumber())){
                                //这里实现跳转到查看销售出货单的细节界面
                                main.gotoCheck_XSJHD(userVO,vo);
                                break;
                            }
                        }
                    }

                    else if(model.getType().equals("销售退货单")){
                        ArrayList<SaleReturnVO> list = helper.getSaleReturnBLService().getSaleReturnProcessList();
                        for(SaleReturnVO vo:list){
                            if(model.getNumber().equals(vo.getNumber())){
                                //这里实现跳转到查看销售退货单细节界面
                                main.gotoCheck_XSTHD(userVO,vo);
                                break;
                            }
                        }
                    }

                    else if(model.getType().equals("进货单")){
                        ArrayList<StockVO> list = helper.getStockBLService().getStockProcessList();
                        for(StockVO vo:list){
                            if(model.getNumber().equals(vo.getNumber())){
                                //这里实现跳转到查看进货单细节界面
                                main.gotoCheck_JHD(userVO,vo);
                                break;
                            }
                        }
                    }

                    else if(model.getType().equals("进货退货单")){
                        ArrayList<StockReturnVO> list = helper.getStockReturnBLService().getStockReturnProcessList();
                        for(StockReturnVO vo:list){
                            if(model.getNumber().equals(vo.getNumber())){
                                //这里实现跳转到查看进货退货单细节界面
                                main.gotoCheck_JHTHD(userVO,vo);
                                break;
                            }
                        }
                    }

                    else if(model.getType().equals("收款单")){
                        ArrayList<TradeVO> list= helper.getTradeBLService().getProcessedCollectList();
                        for(TradeVO vo:list){
                            if(model.getNumber().equals(vo.getNumber())){
                                //这里实现跳转到查看收款单细节界面
                                main.gotoCheck_Trade(userVO,vo);
                                break;
                            }
                        }
                    }

                    else if(model.getType().equals("付款单")){
                        ArrayList<TradeVO> list = helper.getTradeBLService().getProcessedPayList();
                        for(TradeVO vo:list){
                            if(model.getNumber().equals(vo.getNumber())){
                                //这里实现跳转到查看付款单细节界面
                                main.gotoCheck_Trade(userVO,vo);
                                break;
                            }
                        }
                    }

                    else if(model.getType().equals("现金费用单")){
                        ArrayList<CashVO> list = helper.getCashBLService().getProcessedList();
                        for(CashVO vo:list){
                            if(model.getNumber().equals(vo.getNumber())){
                                //这里实现跳转到查看现金费用单细节界面
                                main.gotoCheck_Cash(userVO,vo);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

    @FXML
    public void gotoManagerMain(){
        main.gotoManagerMain(userVO);
    }
}
