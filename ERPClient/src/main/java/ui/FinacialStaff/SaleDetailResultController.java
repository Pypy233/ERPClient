package ui.FinacialStaff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.util.StringConverter;
import objects.ExcelTools;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.GoodsSaleVO;
import vo.SaleVO;
import vo.UserVO;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.time.format.DateTimeFormatter;
import ui.model.SaleDetailResultModel;
import javafx.beans.property.SimpleStringProperty;

public class SaleDetailResultController implements Initializable{
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @FXML
    Label label_number;
    @FXML
    TableView<SaleDetailResultModel> tableView;
    @FXML
    TableColumn tc_time;
    @FXML
    TableColumn tc_goodsName;
    @FXML
    TableColumn tc_type;
    @FXML
    TableColumn tc_ammount;
    @FXML
    TableColumn tc_price;
    @FXML
    TableColumn tc_sum;


    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public SaleDetailResultController(){

    }//一个构造函数

    public void setMain(Main main,UserVO uservo, ArrayList<GoodsSaleVO> list){
        //tableView.refresh();
        this.main=main;
        this.userVO=uservo;
        label_name.setText(uservo.getName());
        //list包含了除了型号和商品名的所有信息
        ArrayList<String> goodsNames=new ArrayList<String>();//包含了所有商品名
        ArrayList<String> types=new ArrayList<String>();//包含了所有的商品型号

        for(int i=0;i<list.size();i++){
            goodsNames.add(list.get(i).getVo().getName());
            types.add(list.get(i).getVo().getType());
        }


        //下面开始建表格
        //tableView.setEditable(true);
        ObservableList<SaleDetailResultModel> data= FXCollections.observableArrayList();
        for(int i=0;i<list.size();i++){
            String date=list.get(i).getDate();
            System.out.println(date);
            String goodName=goodsNames.get(i);
            String type=types.get(i);
            int ammount=list.get(i).getSaleNumber();
            double price=list.get(i).getPrice();
            double sum=list.get(i).getTotalPrice();
            SaleDetailResultModel saleDetailResultModel=new SaleDetailResultModel(date,goodName,type,ammount+"",price+"",sum+"");
            data.add(saleDetailResultModel);
            System.out.println(data.get(i).sum);
        }

        tc_time.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );

        tc_goodsName.setCellValueFactory(
                new PropertyValueFactory<>("goodsName")
        );

        tc_type.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        tc_ammount.setCellValueFactory(
                new PropertyValueFactory<>("ammount")
        );

        tc_price.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );

        tc_sum.setCellValueFactory(
                new PropertyValueFactory<>("sum")
        );

        tableView.setItems(data);
    }

    @FXML
    public void gotoFinacialStaffMain(){
        if(userVO.getType().equals("总经理"))
            main.gotoManagerMain(userVO);
        else
            main.gotoFinacialStaffMain(userVO);
    }

    @FXML
    public void gotoSaleDetail(){
        main.gotoSaleDetail(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

    @FXML
    public void out() throws Exception{
        Date date = new Date();
        ObservableList<SaleDetailResultModel> list = tableView.getItems();
        int n=list.size();

        String a[][] = new String[7][n+1];
        a[0][0]="行号";
        a[1][0]="时间";
        a[2][0]="商品名";
        a[3][0]="型号";
        a[4][0]="数量";
        a[5][0]="单价";
        a[6][0]="总额";

        for(int i=0;i<n;i++){
            a[0][i+1]=i+1+"";
            a[1][i+1]=list.get(i).getDate();
            a[2][i+1]=list.get(i).getGoodsName();
            a[3][i+1]=list.get(i).getType();
            a[4][i+1]=list.get(i).getAmmount()+"";
            a[5][i+1]=list.get(i).getPrice()+"";
            a[6][i+1] =list.get(i).getSum()+"";
        }

        ExcelTools.export("销售明细"+date.getTime(),a);
        AlertUtil.showInformationAlert("导出成功！");

    }


}
