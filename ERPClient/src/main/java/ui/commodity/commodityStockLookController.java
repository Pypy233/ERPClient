package ui.commodity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.StockLookModel;
import vo.*;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class commodityStockLookController implements Initializable {

    private Main main;
    private UserVO userVO;
    RemoteHelper helper=RemoteHelper.getInstance();
    ArrayList<StockVO> stockVOS;
    ArrayList<SaleVO> saleVOS;
    ArrayList<SaleReturnVO> saleReturnVOS;
    ArrayList<StockReturnVO> stockReturnVOS;


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




    //输入查找库存变动起始年份
    @FXML
    public TextField searchStartYearTA;
    //输入查找库存变动起始月份
    @FXML
    public TextField searchStartMonthTA;
    //输入查找库存变动起始日
    @FXML
    public TextField searchStartDayTA;
    //输入查找库存变动截止年份
    @FXML
    public TextField searchEndYearTA;
    //输入查找库存变动截止月份
    @FXML
    public TextField searchEndMonthTA;
    //输入查找库存变动截止日
    @FXML
    public TextField searchEndDayTA;

    //“查询”按钮
    @FXML
    public Button periodSearchButton;





    //库存变动列表
    @FXML
    public TableView<StockLookModel> stockChangeTV;

    //库存变动列表 单据编号
    @FXML
    public TableColumn stockReceiptIDTC;
    //库存变动列表 客户
    @FXML
    public TableColumn stockClientTC;
    //库存变动列表 仓库
    @FXML
    public TableColumn stockWarehouseTC;
    //库存变动列表 操作员
    @FXML
    public TableColumn stockOperatorTC;
    //库存变动列表 商品
    @FXML
    public TableColumn stockGoodsTC;
    //
    @FXML
    public TableColumn stockGoodsNumTC;
    //库存变动列表 总额
    @FXML
    public TableColumn stockTotalPriceTC;
    //库存变动列表 备注
    @FXML
    public TableColumn stockRemarkTC;
    //
    @FXML
    public TableColumn changeWayTC;




    //UserInfo 用户名
    @FXML
    public Label userNameLB;


//
    @FXML
    public TextField total;

    //右上角“登出”按钮
    @FXML
    public Button logoutButton;

    //右下角“返回上一层”按钮
    @FXML
    public Button backButton;



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


    //点击按钮查询
    @FXML
    public void gotoStockSearch(ActionEvent e)throws RemoteException {
        String bYear=searchStartYearTA.getText();
        String bMonth=searchStartMonthTA.getText();
        String bDay=searchStartDayTA.getText();
        String eYear=searchEndYearTA.getText();
        String eMonth=searchEndMonthTA.getText();
        String eDay=searchEndDayTA.getText();
        String start=""+bYear+"-"+bMonth+"-"+bDay;
        String end=""+eYear+"-"+eMonth+"-"+eDay;



        stockVOS=new ArrayList<>();
        saleVOS=new ArrayList<>();
        saleReturnVOS=new ArrayList<>();
        stockReturnVOS=new ArrayList<>();
        stockVOS=helper.getStockBLService().getStock(start,end,"","");
        saleVOS=helper.getSaleBLService().getSale(start,end,"","");

        saleReturnVOS=helper.getSaleReturnBLService().getSaleReturn(start,end,"","");
        stockReturnVOS=helper.getStockReturnBLService().getStockReturn(start,end,"","");
        System.out.println(start+"    "+end+"   "+stockVOS.size());
        ObservableList<StockLookModel> data =
                FXCollections.observableArrayList(
                );
        for(int i=0;i<stockVOS.size();i++){
            for(GoodsStockVO goodsStockVO:stockVOS.get(i).getStockSet())
                data.add(new StockLookModel(goodsStockVO,stockVOS.get(i)));
        }

        for(int i=0;i<saleVOS.size();i++){
            for(GoodsSaleVO goodsSaleVO:saleVOS.get(i).getSaleSet())
                data.add(new StockLookModel(goodsSaleVO,saleVOS.get(i)));
        }
        for(int i=0;i<stockReturnVOS.size();i++){
            for(GoodsStockReturnVO goodsStockReturnVO:stockReturnVOS.get(i).getStockSet())
                data.add(new StockLookModel(goodsStockReturnVO,stockReturnVOS.get(i)));
        }
        System.out.println(saleReturnVOS.size());
        for(int i=0;i<saleReturnVOS.size();i++){
            for(GoodsSaleReturnVO goodsSaleReturnVO:saleReturnVOS.get(i).getSaleReturnSet())
                data.add(new StockLookModel(goodsSaleReturnVO,saleReturnVOS.get(i)));
        }
        System.out.println(data.size());

        stockReceiptIDTC.setCellValueFactory(new PropertyValueFactory<>("id"));
        stockClientTC.setCellValueFactory(new PropertyValueFactory<>("client"));
        stockWarehouseTC.setCellValueFactory(new PropertyValueFactory<>("warehouse"));
        stockGoodsTC.setCellValueFactory(new PropertyValueFactory<>("good"));
        stockGoodsNumTC.setCellValueFactory(new PropertyValueFactory<>("number"));
        changeWayTC.setCellValueFactory(new PropertyValueFactory<>("changeWay"));
        stockOperatorTC.setCellValueFactory(new PropertyValueFactory<>("operator"));
        stockTotalPriceTC.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        stockRemarkTC.setCellValueFactory(new PropertyValueFactory<>("description"));
        stockChangeTV.setItems(data);

        int n=0;
        for(int i=0;i<data.size();i++)
        {
            if(data.get(i).getChangeWay().equals("进货"))
                n+=Integer.valueOf(data.get(i).getNumber());
            else n-=Integer.valueOf(data.get(i).getNumber());
        }
        total.setText(n+"");
    }




    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);
    }


    public void setMain(Main main,UserVO userVO){
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("User "+userVO.getName());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
