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
import ui.model.GoodsSearchModel;
import ui.util.AlertUtil;
import vo.GoodsVO;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class commodityGoodsIDSearchController implements Initializable {

    RemoteHelper helper=RemoteHelper.getInstance();
    private Main main;
    private UserVO userVO;
    private ArrayList<GoodsVO> goodsVOS;

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




    //输入查找的商品ID
    @FXML
    public TextField goodsIDSearchTF;
    //“查询”按钮
    @FXML
    public Button goodsIDSearchButton;





    //类别列表
    @FXML
    public TableView<GoodsSearchModel> goodsIDSearchTV;

    //类别商品列表 id栏
    @FXML
    public TableColumn goodsIDTC;
    //类别商品列表 名称栏
    @FXML
    public TableColumn goodsNameTC;
    //类别商品列表 类别栏
    @FXML
    public TableColumn goodsKindTC;
    //类别商品列表 库存栏
    @FXML
    public TableColumn goodsStockTC;
    //类别商品列表 进价栏
    @FXML
    public TableColumn goodsStockPriceTC;
    //类别商品列表 零售价栏
    @FXML
    public TableColumn goodsSalePriceTC;



    //UserInfo 用户名
    @FXML
    public Label userNameLB;


    @FXML
    public Button chooseB;




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



    //查询 action
    @FXML
    public void goodsIDSearch(ActionEvent e)throws RemoteException{
        String id=goodsIDSearchTF.getText();
        goodsVOS=new ArrayList<GoodsVO>();
        if(id.equals(""))
            AlertUtil.showWarningAlert("请填写查找的ID号");
        else {
            ArrayList<GoodsVO> all = new ArrayList<>();
            all = helper.getGoodsBLService().getCurrentGoods();
            for (int i = 0; i < all.size(); i++)
                if (all.get(i).getNumber() == Integer.valueOf(id))
                    goodsVOS.add(all.get(i));


                //建表显示
                ObservableList<GoodsSearchModel> data = FXCollections.observableArrayList(

                );
                if (goodsVOS.size() == 0)
                    AlertUtil.showErrorAlert("没有此ID下的商品");
                else
                    data.add( new GoodsSearchModel(goodsVOS.get(0)));
                goodsIDTC.setCellValueFactory(new PropertyValueFactory<>("number"));
                goodsNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
                goodsKindTC.setCellValueFactory(new PropertyValueFactory<>("type"));
                goodsStockTC.setCellValueFactory(new PropertyValueFactory<>("commodityNum"));
                goodsStockPriceTC.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
                goodsSalePriceTC.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
                goodsIDSearchTV.setItems(data);
            }

    }

    public void chooseSearch(ActionEvent e){
        if(goodsVOS.size()==0)
            AlertUtil.showWarningAlert("请查询");
        else
            main.gotoCommodityGoodsInfoEdit(userVO,"Search",goodsVOS.get(0));
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
