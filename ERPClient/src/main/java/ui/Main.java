package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rmi.RemoteHelper;
import ui.Admin.AdminController;
import ui.FinacialStaff.*;
import ui.Manager.*;
import ui.commodity.*;
import ui.list.goodsListController;
import ui.list.receiptProcessController;
import ui.login.*;
import ui.sale.*;
import vo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application{

    private Stage stage;
    private Stage stg;
    // 弹窗
    private Stage extraStage;
    private Stage extraStage2;

    // 内部窗口
    private SplitPane rootLayout;

    private Scene scene;
    private Scene scene2;
    private final double MINIMUM_WINDOW_WIDTH = 900.0;
    private final double MINIMUM_WINDOW_HEIGHT = 700.0;
    private final double W1 = 1250.0;
    private final double H1 = 900.0;
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
    //得到商品列表
    public void getGoodList(Main main,UserVO userVO,String kind){
        try{
            /*Parent target = FXMLLoader.load(getClass().getResource("/fxml/GoodList.fxml"));
            Scene scene = new Scene(target); //创建场景；
            Stage stg=new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；*/
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/GoodList.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            this.scene2 = new Scene(pane);
            goodsListController c = (goodsListController) loader.getController();
            c.setMain(userVO,main,this,kind);
            stg=new Stage();
            stg.setScene(scene2);
            stg.sizeToScene();
            stg.centerOnScreen();
            stg.setResizable(false);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //得到单据列表
    public void getReceiptList(UserVO userVO,Main lMain){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/ReceiptProcess.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            this.scene2 = new Scene(pane);
            receiptProcessController c = (receiptProcessController) loader.getController();
            c.setMain(userVO,this);
            stg=new Stage();
            stg.setScene(scene2);
            stg.sizeToScene();
            stg.centerOnScreen();
            stg.setResizable(false);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
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
            LoginOverviewController c = (LoginOverviewController) loader.getController();
            c.setMain(this);
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
     *  跳转到管理员登录界面
     */
    public void adminLogin(String type) {
        try {
            AdminLogController adminLogController = (AdminLogController) replaceSceneContent("/fxml/adminLog.fxml");
            adminLogController.setMain(this, type);
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
     **管理员主界面
     */

    public void gotoAdminMain(AdminVO adminVO){

        try{
            AdminController adminController=(AdminController) replaceSceneContent("/fxml/Admin.fxml");
            adminController.setMain(this,adminVO);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     **鎬荤粡鐞嗕富鐣岄潰
     */

    public void gotoManagerMain(UserVO userVO){

        try {
            ManagerMainController modifyController = (ManagerMainController) replaceSceneContent("/fxml/ManagerMain.fxml");
            modifyController.setMain(this, userVO);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **璐㈠姟浜哄憳涓荤晫闈�
     */
    public void gotoFinacialStaffMain(UserVO uservo){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/FinacialStaffMain.fxml"));
            AnchorPane pane=(AnchorPane) loader.load();
            this.scene = new Scene(pane);

            FinacialStaffMainController finacialStaffMainController=(FinacialStaffMainController) loader.getController();
            finacialStaffMainController.setMain(this,uservo);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoManageAccount(UserVO uservo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/ManageAccountMain.fxml"));
            AnchorPane pane=(AnchorPane) loader.load();
            this.scene = new Scene(pane);

            ManageAccountMainController manageAccountMainController=(ManageAccountMainController) loader.getController();
            manageAccountMainController.setMain(this,uservo);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoMakePayee(UserVO uservo){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/MakePayee.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            MakePayeeMainController makePayeeMainController=(MakePayeeMainController) loader.getController();
            makePayeeMainController.setMain(this,uservo);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoMakePayment(UserVO uservo){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/MakePayment.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            MakePaymentMainController makePaymentMainController=(MakePaymentMainController) loader.getController();
            makePaymentMainController.setMain(this,uservo);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcess(UserVO uservo){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcess.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessController saleProcessController=(SaleProcessController) loader.getController();
            saleProcessController.setMain(this,uservo);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleDetail(UserVO uservo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleDetail.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            this.scene = new Scene(pane);

            SaleDetailController saleDetailController = (SaleDetailController) loader.getController();
            saleDetailController.setMain(this, uservo);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleInformation(UserVO uservo){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleInformation.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleInformationController saleInformationController=(SaleInformationController) loader.getController();
            saleInformationController.setMain(this,uservo);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoMakeBill(UserVO uservo){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/MakeBill.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            MakeBillController saleInformationController=(MakeBillController) loader.getController();
            saleInformationController.setMain(this,uservo);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    //璐㈠姟浜哄憳涓荤晫闈⑩�斺�旇处鎴风鐞嗛噷鐨�
    public void gotoAddAccount(UserVO uservo){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/AddAccount.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            AddAccountController addAccountController=(AddAccountController) loader.getController();
            addAccountController.setMain(this,uservo);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoDeleteAccount(UserVO uservo){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/DeleteAccount.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            DeleteAccountController deleteAccountController=(DeleteAccountController) loader.getController();
            deleteAccountController.setMain(this,uservo);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //鏌ョ湅閿�鍞槑缁嗙粨鏋�
    public void gotoSaleDetailResult(UserVO uservo, ArrayList<GoodsSaleVO> goodsSaleVOArrayList){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleDetailResult.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleDetailResultController saleDetailResultController=(SaleDetailResultController) loader.getController();
            saleDetailResultController.setMain(this,uservo,goodsSaleVOArrayList);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //鏌ョ湅缁忚惀鍘嗙▼缁撴灉
    public void gotoSaleProcessResult_XSCHD(UserVO uservo,ArrayList<SaleVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessResult_XSCHD.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessResult_XSCHDController saleProcessResultController=(SaleProcessResult_XSCHDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessResult_XSTHD(UserVO uservo,ArrayList<SaleReturnVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessResult_XSTHD.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessResult_XSTHDController saleProcessResultController=(SaleProcessResult_XSTHDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessResult_JHD(UserVO uservo,ArrayList<StockVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessResult_JHD.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessResult_JHDController saleProcessResultController=(SaleProcessResult_JHDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessResult_JHTHD(UserVO uservo,ArrayList<StockReturnVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessResult_JHTHD.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessResult_JHTHDController saleProcessResultController=(SaleProcessResult_JHTHDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessResult_SKandFKD(UserVO uservo,ArrayList<TradeVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessResult_FC&SCD.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessResult_SKandFKDController saleProcessResultController=(SaleProcessResult_SKandFKDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessResult_XJD(UserVO uservo,ArrayList<CashVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessResult_XJD.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessResult_XJDController saleProcessResultController=(SaleProcessResult_XJDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessResult_BYD(UserVO uservo,ArrayList<OverflowListVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessResult_BYD.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessResult_BYDController saleProcessResultController=(SaleProcessResult_BYDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessResult_BSD(UserVO uservo,ArrayList<LackListVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessResult_BSD.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessResult_BSDController saleProcessResultController=(SaleProcessResult_BSDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessResult_ZSD(UserVO uservo,ArrayList<PresentListVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessResult_ZSD.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessResult_ZSDController saleProcessResultController=(SaleProcessResult_ZSDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessDetail_XSCHD(UserVO uservo,SaleVO salevo,ArrayList<SaleVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessDetail_XSLDJ.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessDetail_XSCHDController saleProcessResultController=(SaleProcessDetail_XSCHDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,salevo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessDetail_XSTHD(UserVO uservo,SaleReturnVO salereturnvo,ArrayList<SaleReturnVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessDetail_XSTHD.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessDetail_XSTHDController saleProcessResultController=(SaleProcessDetail_XSTHDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,salereturnvo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessDetail_JHD(UserVO uservo,StockVO salereturnvo,ArrayList<StockVO> list){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/fxml/SaleProcessDetail_JHD.fxml"));
            AnchorPane pane = (AnchorPane)loader.load();
            this.scene = new Scene(pane);

            SaleProcessDetail_JHDController saleProcessResultController=(SaleProcessDetail_JHDController) loader.getController();
            saleProcessResultController.setMain(this,uservo,salereturnvo,list);

            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessDetail_JHTHD(UserVO uservo,StockReturnVO stockReturnVO,ArrayList<StockReturnVO> list){
        try{
            SaleProcessDetail_JHTHDController loginController=(SaleProcessDetail_JHTHDController) replaceSceneContent("/fxml/SaleProcessDetail_JHTHD.fxml");
            loginController.setMain(this,uservo,stockReturnVO,list);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessDetail_SKandFKD(UserVO uservo,TradeVO stockReturnVO,ArrayList<TradeVO> list){
        try{
            SaleProcessDetail_SKandFKDController loginController=(SaleProcessDetail_SKandFKDController) replaceSceneContent("/fxml/SaleProcessDetail_SK&FKD.fxml");
            loginController.setMain(this,uservo,stockReturnVO,list);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessDetail_XJFYD(UserVO uservo,CashVO cashvo,ArrayList<CashVO> list){
        try{
            SaleProcessDetail_XJFYDController loginController=(SaleProcessDetail_XJFYDController) replaceSceneContent("/fxml/SaleProcessDetail_XJFYD.fxml");
            loginController.setMain(this,uservo,cashvo,list);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessDetail_BYD(UserVO uservo,OverflowListVO overflowlistvo,ArrayList<OverflowListVO> list){
        try{
            SaleProcessDetail_BYDController loginController=(SaleProcessDetail_BYDController) replaceSceneContent("/fxml/SaleProcessDetail_BYD.fxml");
            loginController.setMain(this,uservo,overflowlistvo,list);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessDetail_BSD(UserVO uservo,LackListVO lacklistvo,ArrayList<LackListVO> list){
        try{
            SaleProcessDetail_BSDController loginController=(SaleProcessDetail_BSDController) replaceSceneContent("/fxml/SaleProcessDetail_BSD.fxml");
            loginController.setMain(this,uservo,lacklistvo,list);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSaleProcessDetail_ZSD(UserVO uservo,PresentListVO presentlistvo,ArrayList<PresentListVO> list){
        try{
            SaleProcessDetail_ZSDController loginController=(SaleProcessDetail_ZSDController) replaceSceneContent("/fxml/SaleProcessDetail_ZSD.fxml");
            loginController.setMain(this,uservo,presentlistvo,list);
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSeeThrough(UserVO uservo){
        try{
            SeeThroughController controller = (SeeThroughController)replaceSceneContent("/fxml/seeThrough.fxml");
            controller.setMain(this,uservo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoCheck_XSJHD(UserVO uservo,SaleVO salevo){
        try{
            Check_XSJHDController controller = (Check_XSJHDController) replaceSceneContent("/fxml/Check_XSLDJ.fxml");
            controller.setMain(this,uservo,salevo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoCheck_XSTHD(UserVO uservo,SaleReturnVO salereturnvo){
        try{
            Check_XSTHDController controller = (Check_XSTHDController) replaceSceneContent("/fxml/Check_XSTHD.fxml");
            controller.setMain(this,uservo,salereturnvo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoCheck_JHD(UserVO uservo,StockVO stockvo){
        try{
            Check_JHDController controller = (Check_JHDController) replaceSceneContent("/fxml/Check_JHD.fxml");
            controller.setMain(this,uservo,stockvo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoCheck_JHTHD(UserVO uservo,StockReturnVO stockreturnvo){
        try{
            Check_JHTHDController controller = (Check_JHTHDController) replaceSceneContent("/fxml/Check_JHTHD.fxml");
            controller.setMain(this,uservo,stockreturnvo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoCheck_Trade(UserVO uservo,TradeVO tradevo){
        try{
            Check_TradeController controller = (Check_TradeController) replaceSceneContent("/fxml/Check_SK&FKD.fxml");
            controller.setMain(this,uservo,tradevo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoCheck_Cash(UserVO uservo,CashVO cashvo){
        try{
            Check_CashController controller = (Check_CashController) replaceSceneContent("/fxml/Check_XJFYD.fxml");
            controller.setMain(this,uservo,cashvo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoBillDetail(UserVO uservo,AccountVO accountvo){
        try{
            BillDetailController controller = (BillDetailController) replaceSceneContent("/fxml/BillDetail.fxml");
            controller.setMain(this,uservo,accountvo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoMakeCash(UserVO uservo){
        try{
            MakeCashController controller = (MakeCashController) replaceSceneContent("/fxml/MakeCash.fxml");
            controller.setMain(this,uservo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoCheckDraft(UserVO uservo){
        try{
            DraftListController controller = (DraftListController) replaceSceneContent("/fxml/DraftList.fxml");
            controller.setMain(this,uservo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoDraft_SKandFKD(UserVO uservo,TradeVO tradevo){
        try{
            Draft_SKandFKDController controller = (Draft_SKandFKDController) replaceSceneContent("/fxml/Draft_SK&FKD.fxml");
            controller.setMain(this,uservo,tradevo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoDraft_XJFYD(UserVO uservo,CashVO cashvo){
        try{
            Draft_XJFYDController controller = (Draft_XJFYDController) replaceSceneContent("/fxml/Draft_XJFYD.fxml");
            controller.setMain(this,uservo,cashvo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoCheckSubmit(UserVO uservo){
        try{
            SubmitListController controller = (SubmitListController) replaceSceneContent("/fxml/SubmitList.fxml");
            controller.setMain(this,uservo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSubmit_SKandFKD(UserVO uservo,TradeVO tradevo){
        try{
            Submit_SKandFKDController controller = (Submit_SKandFKDController) replaceSceneContent("/fxml/Submit_SK&FKD.fxml");
            controller.setMain(this,uservo,tradevo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSubmit_XJFYD(UserVO uservo,CashVO cashvo){
        try{
            Submit_XJFYDController controller = (Submit_XJFYDController) replaceSceneContent("/fxml/Submit_XJFYD.fxml");
            controller.setMain(this,uservo,cashvo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoLog(UserVO uservo){
        try{
            LogController controller = (LogController) replaceSceneContent("/fxml/Log.fxml");
            controller.setMain(this,uservo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoPromotion(UserVO uservo){
        try{
            PromotionController controller = (PromotionController) replaceSceneContent("/fxml/Promotion.fxml");
            controller.setMain(this,uservo);
        }catch(Exception e){
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
            commodityClassifyKindController commodityClassifyKC=(commodityClassifyKindController) replaceSceneContent("/fxml/CommodityClassifyKind.fxml");
            commodityClassifyKC.setMain(this,userVO);

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
    public void gotoCommodityGoodsInfoEdit(UserVO userVO, String order, GoodsVO goodsVO){

        try{
            commodityGoodsEditController commodityGoodsEC=(commodityGoodsEditController) replaceSceneContent("/fxml/CommodityGoodsInfoEdit.fxml");
            commodityGoodsEC.setMain(this,userVO,order,goodsVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  库存  主界面
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
     **库存管理人员  库存  查看库存  界面
     */
    public void gotoCommodityStockLook(UserVO userVO){

        try{
            commodityStockLookController commodityStockCC=(commodityStockLookController) replaceSceneContent("/fxml/CommodityStockLook.fxml");
            commodityStockCC.setMain(this,userVO);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  库存  盘点库存  界面
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
     **库存管理人员  库存  赠送单查询  界面
     */
    public void gotoCommodityStockPresent(UserVO userVO, ArrayList<GoodsVO> goodsVOS){

        try{
            commodityStockPresentController commodityStockPC=( commodityStockPresentController) replaceSceneContent("/fxml/CommodityStockPresent.fxml");
            commodityStockPC.setMain(this,userVO,goodsVOS);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **库存管理人员  库存  库存更新查询  界面
     */
    public void gotoCommodityStockRefresh(UserVO userVO,ArrayList<GoodsVO> goodsVOS){

        try{
            commodityStockRefreshController commodityStockRC=(commodityStockRefreshController) replaceSceneContent("/fxml/CommodityStockRefresh.fxml");
            commodityStockRC.setMain(this,userVO,goodsVOS);

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
    public void gotoSaleMemberInfoEdit(UserVO userVO, String order, MemberVO memberVO){

        try{
            saleMemberEditController saleMemberEC=(saleMemberEditController) replaceSceneContent("/fxml/SalesmanMemberInfoEdit.fxml");
            saleMemberEC.setMain(this,userVO,order,memberVO);

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
    public void gotoSaleStockReceipt(UserVO userVO,ArrayList<GoodsVO> goodsVOS){

        try{
            saleStockReceiptController saleStockRC=(saleStockReceiptController) replaceSceneContent("/fxml/SalesmanStockReceipt.fxml");
            saleStockRC.setMain(this,userVO, goodsVOS);

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
     **进货销售人员  销售  界面
     */
    public void gotoSaleSaleReceipt(UserVO userVO,ArrayList<GoodsVO> goodsVOS){

        try{
            saleSaleReceiptController saleSaleRC=(saleSaleReceiptController) replaceSceneContent("/fxml/SalesmanSaleReceipt.fxml");
            saleSaleRC.setMain(this,userVO,goodsVOS);
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
    public void gotoSaleReturnStockReceipt(UserVO userVO,ArrayList<GoodsVO> goodsVOS){

        try{
            saleReturnStockReceiptController saleReturnSRC=(saleReturnStockReceiptController) replaceSceneContent("/fxml/SalesmanReturnStock.fxml");
            saleReturnSRC.setMain(this,userVO,goodsVOS);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /**
     **进货销售人员  退货  销售退货  界面
     */
    public void gotoSaleReturnSaleReceipt(UserVO userVO,ArrayList<GoodsVO> goodsVOS){

        try{
            saleReturnSaleReceiptController saleReturnSRC=(saleReturnSaleReceiptController) replaceSceneContent("/fxml/SalesmanReturnSale.fxml");
            saleReturnSRC.setMain(this,userVO,goodsVOS);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoCheckPromotion(UserVO uservo){
        try{
            PromotionListController controller = (PromotionListController) replaceSceneContent("/fxml/PromotionList.fxml");
            controller.setMain(this,uservo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gotoSeePromotion(UserVO uservo,PromotionVO promotionvo){
        try{
            SeePromotionController controller = (SeePromotionController) replaceSceneContent("/fxml/SeePromotion.fxml");
            controller.setMain(this,uservo,promotionvo);
        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

/**
 *商品列表 界面 待写。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
 */


    /**
    *界面退出
    */
    public void exit(){
        stage.close();
        closeExtraStage();
    }
    public void exit2(){
        stg.close();
    }

    /**
     *  关闭弹窗
     */
    public void closeExtraStage() {
        if (extraStage2!=null&&extraStage2.isShowing()) {
            extraStage2.hide();
        }
        if (extraStage!=null&&extraStage.isShowing()) {
            extraStage.hide();
        }
    }


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
