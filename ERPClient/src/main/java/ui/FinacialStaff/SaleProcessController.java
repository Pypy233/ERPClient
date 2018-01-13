package ui.FinacialStaff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import rmi.RemoteHelper;
import ui.Main;
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

public class SaleProcessController implements Initializable {
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @FXML
    Label label_number;
    @FXML
    DatePicker from;
    @FXML
    DatePicker to;
    @FXML
    TextField tf_client;
    @FXML
    TextField tf_operator;
    @FXML
    ChoiceBox choiceBox;

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public SaleProcessController(){

    }//一个构造函数

    @FXML
    public void setMain(Main main, UserVO uservo){
        this.main=main;
        this.userVO=uservo;
        label_name.setText(uservo.getName());

        //初始化界面

        //先把格式改了
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        from.setConverter(converter);
        to.setConverter(converter);
        from.setValue(LocalDate.now());
        to.setValue(LocalDate.now());//将datepicker中的默认时间为当前时间

        choiceBox.setValue("单据类型");

        choiceBox.setItems(FXCollections.observableArrayList("销售出货单","销售退货单","进货单","进货退货单","付款单"
        ,"收款单","现金费用单","报溢单","报损单"));

    }

    @FXML
    public void checkResult() throws RemoteException, MalformedURLException,NotBoundException {
        String from_date=from.getValue().toString();
        String to_date=to.getValue().toString();
        String client=tf_client.getText();
        String operator=tf_operator.getText();
        String type=choiceBox.getValue().toString();


        if(type.equals("单据类型"))
            AlertUtil.showWarningAlert("请选择所要查看的单据类型！");
        else{
            if(type.equals("销售出货单")){
                ArrayList<SaleVO> list = new ArrayList<>();
                list = helper.getSaleBLService().getSale(from_date,to_date,operator,client);
                main.gotoSaleProcessResult_XSCHD(userVO,list);
            }
            else if(type.equals("销售退货单")){
                ArrayList<SaleReturnVO> list= new ArrayList<>();
                list =helper.getSaleReturnBLService().getSaleReturn(from_date,to_date,operator,client);
                main.gotoSaleProcessResult_XSTHD(userVO,list);
            }
            else if(type.equals("进货单")){
                ArrayList<StockVO> list= new ArrayList<>();
                list =helper.getStockBLService().getStock(from_date,to_date,operator,client);
                main.gotoSaleProcessResult_JHD(userVO,list);
            }
            else if(type.equals("进货退货单")){
                ArrayList<StockReturnVO> list =  new ArrayList<>();
                list =helper.getStockReturnBLService().getStockReturn(from_date,to_date,operator,client);
                main.gotoSaleProcessResult_JHTHD(userVO,list);
            }
            else if(type.equals("付款单")){
                ArrayList<TradeVO> list= new ArrayList<>();
                list =helper.getTradeBLService().findPay(from_date,to_date,client,operator);
                main.gotoSaleProcessResult_SKandFKD(userVO,list);
            }
            else if(type.equals("收款单")){
                ArrayList<TradeVO> list= new ArrayList<>();
                list =helper.getTradeBLService().findCollect(from_date,to_date,client,operator);
                main.gotoSaleProcessResult_SKandFKD(userVO,list);
            }
            else if(type.equals("现金费用单")){
                ArrayList<CashVO> list= new ArrayList<>();
                list =helper.getCashBLService().findCash(from_date,to_date,operator);
                main.gotoSaleProcessResult_XJD(userVO,list);
            }
            else if(type.equals("报溢单")){
                ArrayList<OverflowListVO> list= new ArrayList<>();
                list =helper.getOverflowListBLService().getOverflowList(from_date,to_date,client);
                main.gotoSaleProcessResult_BYD(userVO,list);
            }
            else if(type.equals("报损单")){
                ArrayList<LackListVO> list= new ArrayList<>();
                list =helper.getLackListBLService().getLackList(from_date,to_date,client);
                main.gotoSaleProcessResult_BSD(userVO,list);
            }
            else if(type.equals("赠送单")){
                ArrayList<PresentListVO> list= new ArrayList<>();
                list =helper.getPresentListBLService().getPresentList(from_date,to_date,operator,client);
                main.gotoSaleProcessResult_ZSD(userVO,list);
            }
        }

        }
    @FXML
    public void gotoFinacialStaffMain(){
        if(userVO.getType().equals("总经理"))
            main.gotoManagerMain(userVO);
        else
            main.gotoFinacialStaffMain(userVO);
    }
    @FXML
    public void logout(){
        main.backToMain();
    }
}
