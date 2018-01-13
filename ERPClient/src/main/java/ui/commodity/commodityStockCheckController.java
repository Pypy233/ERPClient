package ui.commodity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.ExcelTools;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.StockCheckModel;
import ui.util.AlertUtil;
import vo.CommodityVO;
import vo.GoodsVO;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class commodityStockCheckController implements Initializable {
    private Main main;
    private UserVO userVO;
    private CommodityVO commodityVO;
    RemoteHelper helper=RemoteHelper.getInstance();
    ArrayList<GoodsVO> goodsVOS;

    //退出按钮
    @FXML
    public Button exitButton ;

    //退出
    public void exit(ActionEvent e){
        userVO.setLogin(false);
        main.exit();
    }


    //左侧“商品分类”按钮
    @FXML
    public Button classifyButton;
    //左侧“商品管理”按钮
    @FXML
    public Button goodsButton;
    //左侧“库存”按钮
    @FXML
    public Button stockButton;






    //库存变动列表
    @FXML
    public TableView<StockCheckModel> stockTV;

    //库存列表 行号
    @FXML
    public TableColumn stockReceiptLineTC;
    //库存列表 供应商
    @FXML
    public TableColumn stockNameTC;
    //库存列表 仓库
    @FXML
    public TableColumn stockKindTC;
    //库存列表 操作员
    @FXML
    public TableColumn stockNumTC;
    //库存列表 入库商品
    @FXML
    public TableColumn stockAveragePriceTC;




    //UserInfo 用户名
    @FXML
    public Label userNameLB;


    //导出
    @FXML
    public Button export;


    //右上角“登出”按钮
    @FXML
    public Button logoutButton;

    //右下角“返回上一层”按钮
    @FXML
    public Button backButton;
    //跳转商品分类界面



    @FXML
    public void export(ActionEvent e) throws Exception {
        //导出
        Date date=new Date();
        int n=goodsVOS.size();

        String a[][]=new String[5][n+1];
        a[0][0]="行号";
        a[1][0]="商品名称";
        a[2][0]="商品类型";
        a[3][0]="商品库存";
        a[4][0]="库存均价";
        for(int i=0;i<n;i++){
            a[0][i+1]=(i+1)+"";
            a[1][i+1]=goodsVOS.get(i).getName();
            a[2][i+1]=goodsVOS.get(i).getType();
            a[3][i+1]=goodsVOS.get(i).getCommodityNum()+"";
            a[4][i+1]=(goodsVOS.get(i).getRecentPurPrice()+goodsVOS.get(i).getRecentRetPrice())/2+"";
        }
        ExcelTools.export("StockDetail"+date.getTime(),a);
        helper.getLogBlService().addLog(userVO,"导出库存快照 ", ResultMessage.Success);
        AlertUtil.showInformationAlert("库存快照已导出");
        main.gotoCommodityStock(userVO);
    }


    //跳转商品分类界面
    @FXML
    public void gotoClassify(ActionEvent e){
        main.gotoCommodityClassify(userVO);
    }
    //跳转商品管理界面
    @FXML
    public void gotoGoods(ActionEvent e){
        main.gotoCommodityGoods(userVO);
    }

    //跳转库存界面
    //返回上一层
    @FXML
    public void gotoStock(ActionEvent e){
        main.gotoCommodityStock(userVO);
    }





    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);
    }


    public void setMain(Main main, UserVO userVO) throws RemoteException {
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("User "+userVO.getName());
        ObservableList<StockCheckModel> data =
                FXCollections.observableArrayList(

                );
       goodsVOS=helper.getGoodsBLService().getCurrentGoods();
        for(int i=0;i<goodsVOS.size();i++)
            data.add(new StockCheckModel(i,goodsVOS.get(i)));
       //遍历输入
        stockReceiptLineTC.setCellValueFactory(new PropertyValueFactory<>("line"));
        stockNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockKindTC.setCellValueFactory(new PropertyValueFactory<>("kind"));
        stockNumTC.setCellValueFactory(new PropertyValueFactory<>("commodityNum"));
        stockReceiptLineTC.setCellValueFactory(new PropertyValueFactory<>("line"));
        stockAveragePriceTC.setCellValueFactory(new PropertyValueFactory<>("averagePrice"));
        stockTV.setItems(data);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
