package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rmi.RemoteHelper;
import ui.commodity.*;
import ui.login.LoginController;
import ui.login.LoginOverviewController;
import ui.login.ModifyController;
import ui.login.RegisterController;
import ui.sale.*;
import vo.UserVO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application{

    private Stage stage;
    // 弹窗
    private Stage extraStage;
    private Stage extraStage2;

    // 内部窗口
    private SplitPane rootLayout;

    private Scene scene;
    private final double MINIMUM_WINDOW_WIDTH = 1250.0;
    private final double MINIMUM_WINDOW_HEIGHT = 900.0;

    RemoteHelper helper=RemoteHelper.getInstance();

    public Main(){}

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ERP");
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setResizable(false);
        initUI();
        stage.show();
    }
    /**
     * 初始化主界面
     */
    public void initUI() {
        try {
            LoginOverviewController loginOverviewController = (LoginOverviewController) replaceSceneContent(
                    "/fxml/Initialization.fxml");
            loginOverviewController.setMain(this);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * 返回主界面
     */
    public void backToMain(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/Initialization.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            this.scene = new Scene(pane);
            LoginOverviewController overviewController = (LoginOverviewController) loader.getController();
            overviewController.setMain(this);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
    **登录界面
     */

    public void gotoLog(String type){

        try{
                LoginController loginController=(LoginController) replaceSceneContent("/fxml/Login.fxml");
                loginController.setMain(this,type);

            } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            }
    }

    /**
     *  跳转到注册界面
     */
    public void gotoRegister(String type) {
        try {
            RegisterController registerController = (RegisterController) replaceSceneContent("/fxml/Register.fxml");
            registerController.setMain(this, type);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     *  跳转到修改密码界面
     */
    public void gotoModifyCode(String type) {
        try {
            ModifyController modifyController = (ModifyController) replaceSceneContent("/fxml/ModifyCode.fxml");
            modifyController.setMain(this, type);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }





    /**
     **总经理主界面
     */

    public void gotoManagerMain(UserVO userVO){

        try{

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **财务人员主界面
     */
    public void gotoAccountantMain(UserVO userVO){

        try{

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }




    /**
     **库存管理人员主界面
     */
    public void gotoCommodityMain(UserVO userVO){

        try{
            commodityMainController commodityMC=(commodityMainController) replaceSceneContent("/fxml/CommodityMain.fxml");
            commodityMC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  商品分类  主界面
     */
    public void gotoCommodityClassify(UserVO userVO){

        try{
            commodityClassifyMainController commodityClassifyMC=(commodityClassifyMainController) replaceSceneContent("/fxml/CommodityClassifyMain.fxml");
            commodityClassifyMC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  商品分类  商品类别  界面
     */
    public void gotoCommodityClassifyKind(UserVO userVO){

        try{
            commodityClassifyKindController commodityClassifyKC=(commodityClassifyKindController) replaceSceneContent("/fxml/CommodityClassifyKindMain.fxml");
            commodityClassifyKC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  商品分类  类别编辑  界面
     */
    public void gotoCommodityClassifyKindEdit(UserVO userVO){

        try{
            commodityClassifyKindEditController commodityClassifyKEC=(commodityClassifyKindEditController) replaceSceneContent("/fxml/CommodityClassifyKindEdit.fxml");
            commodityClassifyKEC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  商品管理  主界面
     */
    public void gotoCommodityGoods(UserVO userVO){

        try{
            commodityGoodsMainController commodityGoodsMC=(commodityGoodsMainController) replaceSceneContent("/fxml/CommodityGoodsMain.fxml");
            commodityGoodsMC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  商品管理  ID查询  界面
     */
    public void gotoCommodityGoodsIDSearch(UserVO userVO){

        try{
            commodityGoodsIDSearchController commodityGoodsIDSC=(commodityGoodsIDSearchController) replaceSceneContent("/fxml/CommodityGoodsIDSearch.fxml");
            commodityGoodsIDSC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  商品管理  信息查询  界面
     */
    public void gotoCommodityGoodsInfoSearch(UserVO userVO){

        try{
            commodityGoodsInfoSearchController commodityGoodsISC=(commodityGoodsInfoSearchController) replaceSceneContent("/fxml/CommodityGoodsInfoSearch.fxml");
            commodityGoodsISC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  商品管理  信息编辑  界面
     */
    public void gotoCommodityGoodsInfoEdit(UserVO userVO,String order){

        try{
            commodityGoodsEditController commodityGoodsEC=(commodityGoodsEditController) replaceSceneContent("/fxml/CommodityGoodsInfoEdit.fxml");
            commodityGoodsEC.setMain(this,userVO,order);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  盘点库存  主界面
     */
    public void gotoCommodityStock(UserVO userVO){

        try{
            commodityStockMainController commodityStockMC=(commodityStockMainController) replaceSceneContent("/fxml/CommodityStockMain.fxml");
            commodityStockMC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  盘点库存  库存变动查询  界面
     */
    public void gotoCommodityStockCheck(UserVO userVO){

        try{
            commodityStockCheckController commodityStockCheckMC=(commodityStockCheckController) replaceSceneContent("/fxml/CommodityStockCheck.fxml");
            commodityStockCheckMC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员主界面
     */
    public void gotoSaleMain(UserVO userVO){

        try{
            saleMainController saleMC=(saleMainController) replaceSceneContent("/fxml/SalesmanMain.fxml");
            saleMC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     **进货销售人员  管理客户  主界面
     */
    public void gotoSaleMember(UserVO userVO){

        try{
            saleMemberMainController saleMemberMC=(saleMemberMainController) replaceSceneContent("/fxml/SalesmanMemberMain.fxml");
            saleMemberMC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  管理客户  ID查询  界面
     */
    public void gotoSaleMemberIDSearch(UserVO userVO){

        try{
            saleMemberIDSearchController saleMemberIDSC=(saleMemberIDSearchController) replaceSceneContent("/fxml/SalesmanMemberIDSearch.fxml");
            saleMemberIDSC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  管理客户  类别查询  界面
     */
    public void gotoSaleMemberKindSearch(UserVO userVO){

        try{
            saleMemberKindSearchController saleMemberKSC=(saleMemberKindSearchController) replaceSceneContent("/fxml/SalesmanMemberKindSearch.fxml");
            saleMemberKSC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  管理客户  信息查询  界面
     */
    public void gotoSaleMemberInfoSearch(UserVO userVO){
        try{
            saleMemberInfoSearchController saleMemberISC=(saleMemberInfoSearchController) replaceSceneContent("/fxml/SalesmanMemberInfoSearch.fxml");
            saleMemberISC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  管理客户  增删改查  界面
     */
    public void gotoSaleMemberInfoEdit(UserVO userVO,String order){

        try{
            saleMemberEditController saleMemberEC=(saleMemberEditController) replaceSceneContent("/fxml/SalesmanMemberInfoEdit.fxml");
            saleMemberEC.setMain(this,userVO,order);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  进货  主界面
     */
    public void gotoSaleStock(UserVO userVO){

        try{
            saleStockMainController saleStockMC=(saleStockMainController) replaceSceneContent("/fxml/SalesmanStockMain.fxml");
            saleStockMC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  进货  新建进货单  界面
     */
    public void gotoSaleStockReceipt(UserVO userVO){

        try{
            saleStockReceiptController saleStockRC=(saleStockReceiptController) replaceSceneContent("/fxml/SalesmanStockReceipt.fxml");
            saleStockRC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  销售  主界面
     */
    public void gotoSaleSale(UserVO userVO){

        try{
            saleSaleMainController saleSaleMC=(saleSaleMainController) replaceSceneContent("/fxml/SalesmanSaleMain.fxml");
            saleSaleMC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  销售  主界面
     */
    public void gotoSaleSaleReceipt(UserVO userVO){

        try{
            saleSaleReceiptController saleSaleRC=(saleSaleReceiptController) replaceSceneContent("/fxml/SalesmanSaleReceipt.fxml");
            saleSaleRC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  退货  主界面
     */
    public void gotoSaleReturn(UserVO userVO){

        try{
            saleReturnMainController saleReturnMC=(saleReturnMainController) replaceSceneContent("/fxml/SalesmanReturnMain.fxml");
            saleReturnMC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  退货  进货退货  界面
     */
    public void gotoSaleReturnStockReceipt(UserVO userVO){

        try{
            saleReturnStockReceiptController saleReturnSRC=(saleReturnStockReceiptController) replaceSceneContent("/fxml/SalesmanReturnStock.fxml");
            saleReturnSRC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  退货  销售退货  界面
     */
    public void gotoSaleReturnSaleReceipt(UserVO userVO){

        try{
            saleReturnSaleReceiptController saleReturnSRC=(saleReturnSaleReceiptController) replaceSceneContent("/fxml/SalesmanReturnSale.fxml");
            saleReturnSRC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

/**
 *商品列表 界面 待写。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
 */




    /**
     *  界面跳转
     * @param fxml
     * @return
     * @throws Exception
     */
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane pane = (AnchorPane) loader.load();
        this.scene = new Scene(pane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.setResizable(false);
        return (Initializable) loader.getController();
    }
    public Stage getPrimaryStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void getInstance(String[] args){
        Application.launch(args);
    }
}
