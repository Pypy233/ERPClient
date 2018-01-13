package ui.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.GoodsLModel;
import ui.util.AlertUtil;
import vo.GoodsVO;
import vo.UserVO;

import java.rmi.RemoteException;
import java.util.ArrayList;


public class goodsListController {

    RemoteHelper helper=RemoteHelper.getInstance();
    private Main main;
    private Main lMain;
    private String kind;
    private UserVO userVO;
    ArrayList<GoodsVO> gVOS;
    ArrayList<GoodsVO> goodsVOS;
    //选择按钮
    @FXML
    public Button chooseG;
    //添加按钮
    @FXML
    public Button addG;

    //
    @FXML
    public TableView goodsList;
    //
    @FXML
    public TableColumn glIDTC;
    //
    @FXML
    public TableColumn glNameTC;
    //
    @FXML
    public TableColumn glKindTC;
    //
    @FXML
    public TableColumn glCommodityTC;
    //
    @FXML
    public TableColumn glStockPriceTC;
    //
    @FXML
    public TableColumn glRetailPriceTC;
    //
    @FXML
    public TableColumn glRecentSPTC;
    //
    @FXML
    public TableColumn glRecentRPTC;
    //
    @FXML
    public TableColumn chooseTC;



    //
    @FXML
    public void addG(ActionEvent e) {
        ObservableList<GoodsLModel> list=goodsList.getItems();
        for(int i=0;i<list.size();i++) {
            if (list.get(i).getCb().isSelected()==true) {
                goodsVOS.add(gVOS.get(i));
            }
        }

        if (kind == "sale") {
           main.gotoSaleSaleReceipt(userVO,goodsVOS);
           AlertUtil.showInformationAlert("添加成功");
           lMain.exit2();
        }
        else if(kind == "stock") {
            main.gotoSaleStockReceipt(userVO,goodsVOS);
            AlertUtil.showInformationAlert("添加成功");
            lMain.exit2();
        }
        else if(kind == "saleReturn") {
            main.gotoSaleReturnSaleReceipt(userVO,goodsVOS);
            AlertUtil.showInformationAlert("添加成功");
            lMain.exit2();
        }
        else if(kind == "stockReturn") {
            main.gotoSaleReturnStockReceipt(userVO,goodsVOS);
            AlertUtil.showInformationAlert("添加成功");
            lMain.exit2();
        }
        else if(kind == "present") {
            main.gotoCommodityStockPresent(userVO,goodsVOS);
            AlertUtil.showInformationAlert("添加成功");
            lMain.exit2();
        }
        else if(kind == "fresh") {
            main.gotoCommodityStockRefresh(userVO,goodsVOS);
            AlertUtil.showInformationAlert("添加成功");
            lMain.exit2();
        }
    }


    public void setMain(UserVO userVO,Main main,Main lMain,String kind) throws RemoteException {
        this.main=main;
        this.lMain=lMain;
        this.kind=kind;
        this.userVO=userVO;

        gVOS=helper.getGoodsBLService().getCurrentGoods();
        goodsVOS=new ArrayList<GoodsVO>();
        ObservableList<GoodsLModel> data =
                FXCollections.observableArrayList(
                );
        int n=gVOS.size();
        for(int i=0;i<n;i++)
            data.add(new GoodsLModel(gVOS.get(i)));
        //遍历输入
        glIDTC.setCellValueFactory(new PropertyValueFactory<>("number"));
        glNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        glKindTC.setCellValueFactory(new PropertyValueFactory<>("type"));
        glCommodityTC.setCellValueFactory(new PropertyValueFactory<>("commodityNum"));
        glStockPriceTC.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        glRetailPriceTC.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        glRecentSPTC.setCellValueFactory(new PropertyValueFactory<>("recentPurPrice"));
        glRecentRPTC.setCellValueFactory(new PropertyValueFactory<>("recentRetPrice"));
        chooseTC.setCellValueFactory(new PropertyValueFactory<>("cb"));
        goodsList.setItems(data);
    }

}
