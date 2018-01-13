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
import ui.model.GoodsInfoSearchModel;
import ui.model.GoodsSearchModel;
import ui.util.AlertUtil;
import vo.GoodsVO;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class commodityGoodsInfoSearchController implements Initializable {


    RemoteHelper helper=RemoteHelper.getInstance();

    private Main main;
    private UserVO userVO;
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




    //输入查找的商品名称
    @FXML
    public TextField goodsNameSearchTF;
    //输入查找的商品类型
    @FXML
    public TextField goodsKindSearchTF;
    //“查询”按钮
    @FXML
    public Button goodsInfoSearchButton;





    //类别列表
    @FXML
    public TableView goodsInfoSearchTV;

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

    //类别商品列表 零售价栏
    @FXML
    public TableColumn chooseTC;


    //UserInfo 用户名
    @FXML
    public Label userNameLB;


    //
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
    public void goodsInfoSearch(ActionEvent e)throws RemoteException{
        String name=goodsNameSearchTF.getText();
        String kind=goodsKindSearchTF.getText();
        goodsVOS=new ArrayList<>();
        if(name.equals("")&&kind.equals("")){
            AlertUtil.showWarningAlert("请填写完整的查找信息");
        }
        else{
            ArrayList<GoodsVO> all=helper.getGoodsBLService().getCurrentGoods();
            for(int i=0;i<all.size();i++)
                if(all.get(i).getType().contains(kind)||all.get(i).getName().contains(name))
                        goodsVOS.add(all.get(i));

            System.out.println(goodsVOS.size()+" "+name+" "+kind+" ");
            ArrayList list = helper.getGoodsBLService().findGoods("", "", "电");
            System.out.println(list.size());
           //建表显示
            ObservableList<GoodsInfoSearchModel> data= FXCollections.observableArrayList(
            );
            int n=goodsVOS.size();
            for(int i=0;i<n;i++)
                data.add(new GoodsInfoSearchModel(goodsVOS.get(i)));
            chooseTC.setCellValueFactory(new PropertyValueFactory<>("box"));
            goodsIDTC.setCellValueFactory(new PropertyValueFactory<>("number"));
            goodsNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
            goodsKindTC.setCellValueFactory(new PropertyValueFactory<>("type"));
            goodsStockTC.setCellValueFactory(new PropertyValueFactory<>("commodityNum"));
            goodsStockPriceTC.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
            goodsSalePriceTC.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
            goodsInfoSearchTV.setItems(data);
            //
        }
    }

    //choose
    @FXML
    public void choose(ActionEvent e){
        ObservableList<GoodsInfoSearchModel> list=goodsInfoSearchTV.getItems();
        GoodsVO goodsVO=new GoodsVO();
        for(int i=0;i<list.size();i++) {
            if (list.get(i).getBox().isSelected() == true) {
                goodsVO=goodsVOS.get(i);
                break;
            }
        }
        if(!goodsVO.equals(null))
            main.gotoCommodityGoodsInfoEdit(userVO, "Search", goodsVO);
        else
        AlertUtil.showErrorAlert("请选择商品查看信息");
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
        goodsVOS=new ArrayList<GoodsVO>();
        goodsKindSearchTF.setText("");
        goodsNameSearchTF.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
