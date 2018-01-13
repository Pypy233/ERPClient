package ui.commodity;
//已完成
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.GoodsVO;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class commodityGoodsEditController implements Initializable {
    private Main main;
    private UserVO userVO;
    RemoteHelper helper=RemoteHelper.getInstance();

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



    //商品编号 信息
    @FXML
    public TextField goodsIDTA;
    //商品名称 信息
    @FXML
    public TextField goodsNameTA;
    //商品库存 信息
    @FXML
    public TextField goodsStockTA;
    //商品类型 信息
    @FXML
    public TextField goodsKindTA;
    //商品进价 信息
    @FXML
    public TextField goodsStockPriceTA;
    //商品零售价 信息
    @FXML
    public TextField goodsSalePriceTA;
    //商品最新进价 信息
    @FXML
    public TextField goodsRecentStockPriceTA;
    //商品最新零售价 信息
    @FXML
    public TextField goodsRecentSalePriceTA;



    //“编辑资料”按钮
    @FXML
    public Button goodsEditButton;
    //“保存修改”按钮
    @FXML
    public Button goodsModifyConfirmButton;
    //“删除商品”按钮
    @FXML
    public Button goodsDeleteButton;
    //“新增商品”按钮
    @FXML
    public Button goodsAddButton;




    //UserInfo 用户名
    @FXML
    public Label userNameLB;


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
    //返回上一层
    @FXML
    public void gotoGoods(ActionEvent e){
        main.gotoCommodityGoods(userVO);
    }

    //跳转库存界面
    @FXML
    public void gotoStock(ActionEvent e){
        main.gotoCommodityStock(userVO);
    }


    //编辑资料
    @FXML
    public void goodsInfoEdit(ActionEvent e){

        goodsIDTA.setEditable(false);
        goodsNameTA.setEditable(true);
        goodsStockTA.setEditable(true);
        goodsKindTA.setEditable(false);
        goodsStockPriceTA.setEditable(true);
        goodsSalePriceTA.setEditable(true);
        goodsRecentStockPriceTA.setEditable(false);
        goodsRecentSalePriceTA.setEditable(false);

        goodsRecentStockPriceTA.setText(goodsStockPriceTA.getText());
        goodsRecentSalePriceTA.setText(goodsSalePriceTA.getText());

        goodsEditButton.setDisable(true);
        goodsDeleteButton.setDisable(true);
        goodsModifyConfirmButton.setDisable(false);
        goodsAddButton.setDisable(true);
    }
    //删除商品
    @FXML
    public void goodsDelete(ActionEvent e)throws RemoteException{

        GoodsVO goodsVO=new GoodsVO();
        goodsVO.setName(goodsNameTA.getText());

        /////
        helper.getGoodsBLService().deleteGoods(goodsVO);
        AlertUtil.showInformationAlert("删除完成");
        main.gotoCommodityGoods(userVO);
        helper.getLogBlService().addLog(userVO,"删除商品 "+goodsVO.getName(), ResultMessage.Success);
    }

    //保存修改
    @FXML
    public void goodsEditConfirm(ActionEvent e)throws RemoteException{

        //保存商品信息
        //跳转至GoodsMain界面
        String id=goodsIDTA.getText();
        String name=goodsNameTA.getText();
        String type=goodsKindTA.getText();
        String stock=goodsStockTA.getText();
        String stockPrice=goodsStockPriceTA.getText();
        String salePrice=goodsSalePriceTA.getText();
        String recentStock=goodsRecentStockPriceTA.getText();
        String recentSale=goodsRecentSalePriceTA.getText();
        if(name==""||stock==""||stockPrice==""||salePrice==""||recentStock==""||recentSale=="")
            AlertUtil.showWarningAlert("请输入完整商品信息");
        else {
            GoodsVO goodsVO = new GoodsVO();
            goodsVO.setNumber(Integer.valueOf(id));
            goodsVO.setName(name);
            goodsVO.setType(type);
            goodsVO.setCommodityNum(Integer.valueOf(stock));
            goodsVO.setPurchasePrice(Double.valueOf(stockPrice));
            goodsVO.setRetailPrice(Double.valueOf(salePrice));
            goodsVO.setRecentPurPrice(Double.valueOf(recentStock));
            goodsVO.setRecentRetPrice(Double.valueOf(recentSale));
            helper.getGoodsBLService().updateGoods(goodsVO);
            AlertUtil.showInformationAlert("修改商品信息成功");
            main.gotoCommodityGoods(userVO);
            helper.getLogBlService().addLog(userVO,"编辑商品信息 "+goodsVO.getName(), ResultMessage.Success);
        }
    }
    //新增商品
    @FXML
    public void goodsAdd(ActionEvent e)throws RemoteException{
        //新增商品
        //跳转至GoodsMain界面
        String name=goodsNameTA.getText();
        String type=goodsKindTA.getText();
        String stock=goodsStockTA.getText();
        String stockPrice=goodsStockPriceTA.getText();
        String salePrice=goodsSalePriceTA.getText();
        if(name==""||stock==""||stockPrice==""||salePrice=="")
            AlertUtil.showWarningAlert("请输入完整商品信息");
        else {
            GoodsVO goodsVO = new GoodsVO();
            goodsVO.setName(name);
            goodsVO.setType(type);
            goodsVO.setCommodityNum(Integer.valueOf(stock));
            goodsVO.setPurchasePrice(Double.valueOf(stockPrice));
            goodsVO.setRetailPrice(Double.valueOf(salePrice));
            goodsVO.setRecentPurPrice(Double.valueOf(stockPrice));
            goodsVO.setRecentRetPrice(Double.valueOf(salePrice));
            helper.getGoodsBLService().addGoods(goodsVO);
            AlertUtil.showInformationAlert("商品已添加");
            main.gotoCommodityGoods(userVO);
            helper.getLogBlService().addLog(userVO,"新增商品 "+goodsVO.getName(), ResultMessage.Success);
        }

    }


    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);
    }
    public void setMain(Main main, UserVO userVO, String order, GoodsVO goodsVO)throws RemoteException{
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("User "+userVO.getName());
        switch (order){
            case"Add":{
                goodsIDTA.setText("");
                goodsNameTA.setText("");
                goodsStockTA.setText("");
                goodsKindTA.setText("默认分类");
                goodsStockPriceTA.setText("");
                goodsSalePriceTA.setText("");
                goodsRecentStockPriceTA.setText("初始为进价");
                goodsRecentSalePriceTA.setText("初始为零售价");

                goodsIDTA.setEditable(false);
                goodsNameTA.setEditable(true);
                goodsStockTA.setEditable(true);
                goodsKindTA.setEditable(false);
                goodsStockPriceTA.setEditable(true);
                goodsSalePriceTA.setEditable(true);
                goodsRecentStockPriceTA.setEditable(false);
                goodsRecentSalePriceTA.setEditable(false);

                goodsEditButton.setDisable(true);
                goodsDeleteButton.setDisable(true);
                goodsModifyConfirmButton.setDisable(true);
                goodsAddButton.setDisable(false);

            }break;
            case"Search":{
                goodsIDTA.setText(goodsVO.getNumber()+"");
                goodsNameTA.setText(goodsVO.getName());
                goodsStockTA.setText(goodsVO.getCommodityNum()+"");
                goodsKindTA.setText(goodsVO.getType()+"");
                goodsStockPriceTA.setText(goodsVO.getPurchasePrice()+"");
                goodsSalePriceTA.setText(goodsVO.getRetailPrice()+"");
                goodsRecentStockPriceTA.setText(goodsVO.getRecentPurPrice()+"");
                goodsRecentSalePriceTA.setText(goodsVO.getRetailPrice()+"");

                goodsIDTA.setEditable(false);
                goodsNameTA.setEditable(false);
                goodsStockTA.setEditable(false);
                goodsKindTA.setEditable(false);
                goodsStockPriceTA.setEditable(false);
                goodsSalePriceTA.setEditable(false);
                goodsRecentStockPriceTA.setEditable(false);
                goodsRecentSalePriceTA.setEditable(false);

                goodsEditButton.setDisable(false);
                goodsDeleteButton.setDisable(false);
                goodsModifyConfirmButton.setDisable(true);
                goodsAddButton.setDisable(true);
            }break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
