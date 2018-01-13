package ui.Manager;

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
import ui.model.BargainModel;
import ui.model.CheckModel;
import ui.model.PromotionModel;
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

public class PromotionController implements Initializable{
    private UserVO userVO;
    private Main main;

    private ObservableList<BargainModel> data1 = FXCollections.observableArrayList();
    private ObservableList<PromotionModel> data2 = FXCollections.observableArrayList();

    RemoteHelper helper=RemoteHelper.getInstance();

    @FXML
    Label label_name;
    @FXML
    TableView<BargainModel> tableView1;
    @FXML
    TableView<PromotionModel> tableView2;
    @FXML
    TableColumn<BargainModel,String> tc_goods1;
    @FXML
    TableColumn<BargainModel,String> tc_goods1ammount;
    @FXML
    TableColumn<BargainModel,String> tc_goods2;
    @FXML
    TableColumn<BargainModel,String> tc_goods2ammount;
    @FXML
    TableColumn<BargainModel,String> tc_goods3;
    @FXML
    TableColumn<BargainModel,String> tc_goods3ammount;
    @FXML
    TableColumn<BargainModel,String> tc_discount1;
    @FXML
    TableColumn<PromotionModel,String> tc_gift1;
    @FXML
    TableColumn<PromotionModel,String> tc_gift1ammount;
    @FXML
    TableColumn<PromotionModel,String> tc_gift2;
    @FXML
    TableColumn<PromotionModel,String> tc_gift2ammount;
    @FXML
    TableColumn<PromotionModel,String> tc_gift3;
    @FXML
    TableColumn<PromotionModel,String> tc_gift3ammount;
    @FXML
    TableColumn<PromotionModel,CheckBox> tc_level1;
    @FXML
    TableColumn<PromotionModel,CheckBox> tc_level2;
    @FXML
    TableColumn<PromotionModel,CheckBox> tc_level3;
    @FXML
    TableColumn<PromotionModel,CheckBox> tc_level4;
    @FXML
    TableColumn<PromotionModel,CheckBox> tc_level5;
    @FXML
    TableColumn<PromotionModel,String> tc_max;
    @FXML
    TableColumn<PromotionModel,String> tc_min;
    @FXML
    TableColumn<PromotionModel,String> tc_substitute;
    @FXML
    TableColumn<PromotionModel,String> tc_discount2;
    @FXML
    DatePicker from;
    @FXML
    DatePicker to;



    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public PromotionController(){

    }//一个构造函数

    public void setMain (Main main,UserVO uservo){
        this.main=main;
        this.userVO=uservo;
        label_name.setText(userVO.getName());

        tableView1.setEditable(true);
        tableView2.setEditable(true);

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

        tc_goods1.setCellValueFactory(
                new PropertyValueFactory<>("goods1")
        );
        tc_goods1.setCellFactory(TextFieldTableCell.<BargainModel>forTableColumn());
        tc_goods1.setOnEditCommit(
                (TableColumn.CellEditEvent<BargainModel,String> t) -> {
                    ((BargainModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setGoods1(t.getNewValue());
                }
        );

        tc_goods1ammount.setCellValueFactory(
                new PropertyValueFactory<>("ammount1")
        );
        tc_goods1ammount.setCellFactory(TextFieldTableCell.<BargainModel>forTableColumn());
        tc_goods1ammount.setOnEditCommit(
                (TableColumn.CellEditEvent<BargainModel,String> t) -> {
                    ((BargainModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmmount1(t.getNewValue());
                }
        );

        tc_goods2.setCellValueFactory(
                new PropertyValueFactory<>("goods2")
        );
        tc_goods2.setCellFactory(TextFieldTableCell.<BargainModel>forTableColumn());
        tc_goods2.setOnEditCommit(
                (TableColumn.CellEditEvent<BargainModel,String> t) -> {
                    ((BargainModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setGoods2(t.getNewValue());
                }
        );

        tc_goods2ammount.setCellValueFactory(
                new PropertyValueFactory<>("ammount2")
        );
        tc_goods2ammount.setCellFactory(TextFieldTableCell.<BargainModel>forTableColumn());
        tc_goods2ammount.setOnEditCommit(
                (TableColumn.CellEditEvent<BargainModel,String> t) -> {
                    ((BargainModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmmount2(t.getNewValue());
                }
        );

        tc_goods3.setCellValueFactory(
                new PropertyValueFactory<>("goods3")
        );
        tc_goods3.setCellFactory(TextFieldTableCell.<BargainModel>forTableColumn());
        tc_goods3.setOnEditCommit(
                (TableColumn.CellEditEvent<BargainModel,String> t) -> {
                    ((BargainModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setGoods3(t.getNewValue());
                }
        );

        tc_goods3ammount.setCellValueFactory(
                new PropertyValueFactory<>("ammount3")
        );
        tc_goods3ammount.setCellFactory(TextFieldTableCell.<BargainModel>forTableColumn());
        tc_goods3ammount.setOnEditCommit(
                (TableColumn.CellEditEvent<BargainModel,String> t) -> {
                    ((BargainModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmmount3(t.getNewValue());
                }
        );

        tc_discount1.setCellValueFactory(
                new PropertyValueFactory<>("discount")
        );
        tc_discount1.setCellFactory(TextFieldTableCell.<BargainModel>forTableColumn());
        tc_discount1.setOnEditCommit(
                (TableColumn.CellEditEvent<BargainModel,String> t) -> {
                    ((BargainModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setDiscount(t.getNewValue());
                }
        );

        //

        tc_gift1.setCellValueFactory(
                new PropertyValueFactory<>("gift1")
        );
        tc_gift1.setCellFactory(TextFieldTableCell.<PromotionModel>forTableColumn());
        tc_gift1.setOnEditCommit(
                (TableColumn.CellEditEvent<PromotionModel,String> t) -> {
                    ((PromotionModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setGift1(t.getNewValue());
                }
        );

        tc_gift1ammount.setCellValueFactory(
                new PropertyValueFactory<>("ammount1")
        );
        tc_gift1ammount.setCellFactory(TextFieldTableCell.<PromotionModel>forTableColumn());
        tc_gift1ammount.setOnEditCommit(
                (TableColumn.CellEditEvent<PromotionModel,String> t) -> {
                    ((PromotionModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmmount1(t.getNewValue());
                }
        );

        tc_gift2.setCellValueFactory(
                new PropertyValueFactory<>("gift2")
        );
        tc_gift2.setCellFactory(TextFieldTableCell.<PromotionModel>forTableColumn());
        tc_gift2.setOnEditCommit(
                (TableColumn.CellEditEvent<PromotionModel,String> t) -> {
                    ((PromotionModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setGift2(t.getNewValue());
                }
        );

        tc_gift2ammount.setCellValueFactory(
                new PropertyValueFactory<>("ammount2")
        );
        tc_gift2ammount.setCellFactory(TextFieldTableCell.<PromotionModel>forTableColumn());
        tc_gift2ammount.setOnEditCommit(
                (TableColumn.CellEditEvent<PromotionModel,String> t) -> {
                    ((PromotionModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmmount2(t.getNewValue());
                }
        );

        tc_gift3.setCellValueFactory(
                new PropertyValueFactory<>("gift3")
        );
        tc_gift3.setCellFactory(TextFieldTableCell.<PromotionModel>forTableColumn());
        tc_gift3.setOnEditCommit(
                (TableColumn.CellEditEvent<PromotionModel,String> t) -> {
                    ((PromotionModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setGift3(t.getNewValue());
                }
        );

        tc_gift3ammount.setCellValueFactory(
                new PropertyValueFactory<>("ammount3")
        );
        tc_gift3ammount.setCellFactory(TextFieldTableCell.<PromotionModel>forTableColumn());
        tc_gift3ammount.setOnEditCommit(
                (TableColumn.CellEditEvent<PromotionModel,String> t) -> {
                    ((PromotionModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmmount3(t.getNewValue());
                }
        );

        tc_level1.setCellValueFactory(
                new PropertyValueFactory<>("level1")
        );

        tc_level2.setCellValueFactory(
                new PropertyValueFactory<>("level2")
        );

        tc_level3.setCellValueFactory(
                new PropertyValueFactory<>("level3")
        );

        tc_level4.setCellValueFactory(
                new PropertyValueFactory<>("level4")
        );

        tc_level5.setCellValueFactory(
                new PropertyValueFactory<>("level5")
        );

        tc_max.setCellValueFactory(
                new PropertyValueFactory<>("max")
        );
        tc_max.setCellFactory(TextFieldTableCell.<PromotionModel>forTableColumn());
        tc_max.setOnEditCommit(
                (TableColumn.CellEditEvent<PromotionModel,String> t) -> {
                    ((PromotionModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setMax(t.getNewValue());
                }
        );

        tc_min.setCellValueFactory(
                new PropertyValueFactory<>("min")
        );
        tc_min.setCellFactory(TextFieldTableCell.<PromotionModel>forTableColumn());
        tc_min.setOnEditCommit(
                (TableColumn.CellEditEvent<PromotionModel,String> t) -> {
                    ((PromotionModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setMin(t.getNewValue());
                }
        );

        tc_substitute.setCellValueFactory(
                new PropertyValueFactory<>("substitute")
        );
        tc_substitute.setCellFactory(TextFieldTableCell.<PromotionModel>forTableColumn());
        tc_substitute.setOnEditCommit(
                (TableColumn.CellEditEvent<PromotionModel,String> t) -> {
                    ((PromotionModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setSubstitute(t.getNewValue());
                }
        );

        tc_discount2.setCellValueFactory(
                new PropertyValueFactory<>("discount")
        );
        tc_discount2.setCellFactory(TextFieldTableCell.<PromotionModel>forTableColumn());
        tc_discount2.setOnEditCommit(
                (TableColumn.CellEditEvent<PromotionModel,String> t) -> {
                    ((PromotionModel)t.getTableView().getItems().get(t.getTablePosition().getRow())).setDiscount(t.getNewValue());
                }
        );

        tableView1.setItems(data1);
        tableView2.setItems(data2);


    }

    @FXML
    public void add_1(){
        BargainModel model =new BargainModel("","","","","","","");
        data1.add(model);
        tableView1.setItems(data1);
    }

    @FXML
    public void add_2(){
        PromotionModel model = new PromotionModel("","","","","",
                "","","","","");
        data2.add(model);
        tableView2.setItems(data2);
    }

    @FXML
    public void newPromotion(){
        main.gotoPromotion(userVO);
    }

    @FXML
    public void confirm() throws RemoteException,MalformedURLException,NotBoundException{
        String from_date=from.getValue().toString();
        String to_date=to.getValue().toString();

        ArrayList<BargainSet> bargainList = new ArrayList<BargainSet>();
        for(BargainModel model : data1){
            ArrayList<Goods> list = new ArrayList<Goods>();
            if(!model.getGoods1().equals("")){
                Goods goods = new Goods();
                goods.setName(model.getGoods1());
                goods.setQuantity(Integer.parseInt(model.getAmmount1()));
                list.add(goods);
            }

            if(!model.getGoods2().equals("")){
                Goods goods = new Goods();
                goods.setName(model.getGoods2());
                goods.setQuantity(Integer.parseInt(model.getAmmount2()));
                list.add(goods);
            }

            if(!model.getGoods3().equals("")){
                Goods goods = new Goods();
                goods.setName(model.getGoods3());
                goods.setQuantity(Integer.parseInt(model.getAmmount3()));
                list.add(goods);
            }

            double discount=Double.parseDouble(model.getDiscount());

            BargainSet bargainSet = new BargainSet(list,discount);
            bargainList.add(bargainSet);
        }

        ArrayList<PromotionStrategy> strategyList = new ArrayList<PromotionStrategy>();
        for(PromotionModel model : data2){
            ArrayList<Goods> list = new ArrayList<Goods>();
            if(!model.getGift1().equals("")){
                Goods goods = new Goods();
                goods.setName(model.getGift1());
                goods.setQuantity(Integer.parseInt(model.getAmmount1()));
                list.add(goods);
            }

            if(!model.getGift2().equals("")){
                Goods goods = new Goods();
                goods.setName(model.getGift2());
                goods.setQuantity(Integer.parseInt(model.getAmmount2()));
                list.add(goods);
            }

            if(!model.getGift3().equals("")){
                Goods goods = new Goods();
                goods.setName(model.getGift3());
                goods.setQuantity(Integer.parseInt(model.getAmmount3()));
                list.add(goods);
            }

            ArrayList<Integer> list2 = new ArrayList<Integer>();
            if(model.level1.isSelected())
                list2.add(1);
            if(model.level2.isSelected())
                list2.add(2);
            if(model.level3.isSelected())
                list2.add(3);
            if(model.level4.isSelected())
                list2.add(4);
            if(model.level5.isSelected())
                list2.add(5);

            double max=Double.parseDouble(model.getMax());
            double min=Double.parseDouble(model.getMin());
            double substitute=Double.parseDouble(model.getSubstitute());
            double discount=Double.parseDouble(model.getDiscount());

            PromotionStrategy strategy = new PromotionStrategy(list2,min,max,list,substitute,discount);
            strategyList.add(strategy);
        }

        PromotionVO vo = new PromotionVO(bargainList,strategyList,from_date,to_date);

        ResultMessage message = helper.getPromotionBLService().add(vo);
        AlertUtil.showInformationAlert("提交成功");

        //helper.getLogBLService().addLog(userVO,"制定促销策略",message);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

    @FXML
    public void gotoManagerMain(){
        main.gotoManagerMain(userVO);
    }

    @FXML
    public void checkPromotion(){
        main.gotoCheckPromotion(userVO);
    }


}
