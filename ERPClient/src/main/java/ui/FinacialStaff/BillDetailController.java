package ui.FinacialStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.BankModel;
import ui.model.ClientModel;
import ui.model.GoodsModel;
import vo.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BillDetailController implements Initializable{
    private Main main;
    private UserVO userVO;

    RemoteHelper helper=RemoteHelper.getInstance();

    @FXML
    Label label_name;
    @FXML
    TableView<GoodsModel> tv_goods;
    @FXML
    TableView<BankModel> tv_bank;
    @FXML
    TableView<ClientModel> tv_client;

    @FXML
    TableColumn tc_goodsType;
    @FXML
    TableColumn tc_goodsName;
    @FXML
    TableColumn tc_goodsAmmount;
    @FXML
    TableColumn tc_goodsNumber;
    @FXML
    TableColumn tc_inPrice;
    @FXML
    TableColumn tc_outPrice;
    @FXML
    TableColumn tc_rInPrice;
    @FXML
    TableColumn tc_rOutPrice;

    @FXML
    TableColumn tc_bankName;
    @FXML
    TableColumn tc_bankMoney;

    @FXML
    TableColumn tc_clientType;
    @FXML
    TableColumn tc_clientName;
    @FXML
    TableColumn tc_phone;
    @FXML
    TableColumn tc_shouldGet;
    @FXML
    TableColumn tc_shouldPay;

    public BillDetailController(){

    }

    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }

    public void setMain(Main main,UserVO uservo,AccountVO accountvo){
        this.main=main;
        this.userVO =uservo;

        tv_goods.setEditable(true);
        tv_bank.setEditable(true);
        tv_client.setEditable(true);

        label_name.setText(userVO.getName());

        ArrayList<GoodsVO> goodsList=accountvo.getGoodsList();
        ArrayList<BankVO> bankList=accountvo.getBankList();
        ArrayList<MemberVO> clientList=accountvo.getMemberList();

        ObservableList<GoodsModel> goodsData=FXCollections.observableArrayList();
        ObservableList<BankModel> bankData=FXCollections.observableArrayList();
        ObservableList<ClientModel> clientData=FXCollections.observableArrayList();

        for(GoodsVO vo : goodsList) {
            GoodsModel model = new GoodsModel(vo.getType(),vo.getName(),""+vo.getCommodityNum(),""+vo.getNumber(),vo.getPurchasePrice()+"",
                    vo.getRetailPrice()+"",vo.getRecentPurPrice()+"",vo.getRecentRetPrice()+"");
            goodsData.add(model);
            }

        for(BankVO vo:bankList){
            BankModel model=new BankModel(vo.getName(),vo.getBalance()+"");
            bankData.add(model);
        }

        for(MemberVO vo:clientList){
            ClientModel model = new ClientModel(vo.getMemberClass(),vo.getName(),vo.getPhoneNumber(),vo.getCollection()+"",vo.getPayment()+"");
            if(model.getName()!=null)
                clientData.add(model);
        }

        tc_goodsType.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        tc_goodsName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        tc_goodsAmmount.setCellValueFactory(
                new PropertyValueFactory<>("ammount")
        );
        tc_goodsNumber.setCellValueFactory(
                new PropertyValueFactory<>("number")
        );
        tc_inPrice.setCellValueFactory(
                new PropertyValueFactory<>("in_price")
        );
        tc_outPrice.setCellValueFactory(
                new PropertyValueFactory<>("out_price")
        );
        tc_rInPrice.setCellValueFactory(
                new PropertyValueFactory<>("r_inPrice")
        );
        tc_rOutPrice.setCellValueFactory(
                new PropertyValueFactory<>("r_outPrice")
        );

        tc_bankName.setCellValueFactory(
                new PropertyValueFactory<>("bankName")
        );
        tc_bankMoney.setCellValueFactory(
                new PropertyValueFactory<>("bankMoney")
        );

        tc_clientType.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        tc_clientName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        tc_phone.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );
        tc_shouldGet.setCellValueFactory(
                new PropertyValueFactory<>("shouldGet")
        );
        tc_shouldPay.setCellValueFactory(
                new PropertyValueFactory<>("shouldPay")
        );

        tv_goods.setItems(goodsData);
        tv_bank.setItems(bankData);
        tv_client.setItems(clientData);


    }

    @FXML
    public void gotoMakeBill(){
        main.gotoMakeBill(userVO);
    }

    @FXML
    public void gotoFinacialStaffMain(){
        main.gotoFinacialStaffMain(userVO);
    }

    @FXML
    public void logout(){
        main.backToMain();
    }

}
