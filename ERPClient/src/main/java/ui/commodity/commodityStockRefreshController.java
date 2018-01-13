package ui.commodity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.StockFreshModel;
import ui.util.AlertUtil;
import vo.*;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class commodityStockRefreshController implements Initializable {


    RemoteHelper helper = RemoteHelper.getInstance();
    private Main main;
    private UserVO userVO;
    private ArrayList<GoodsVO> goodsVOS;
    private Main lMain;


    //退出按钮
    @FXML
    public Button exitButton;

    //退出
    public void exit(ActionEvent e) {
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


    //
    @FXML
    public Button chooseB;
    //
    @FXML
    public Button warningB;
    //
    @FXML
    public Button submitB;


    //
    @FXML
    public ChoiceBox<String> kindCB;

    //
    @FXML
    public TextField warningTF;


    //库存更新列表
    @FXML
    public TableView<StockFreshModel> freshListTV;

    //库存变动列表 编号
    @FXML
    public TableColumn numTC;
    //库存更新列表 商品名
    @FXML
    public TableColumn nameTC;
    //库存更新列表 数量
    @FXML
    public TableColumn<StockFreshModel, String> commodityTC;


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
    public void gotoClassify(ActionEvent e) {
        main.gotoCommodityClassify(userVO);
    }

    //跳转商品管理界面
    @FXML
    public void gotoGoods(ActionEvent e) {
        main.gotoCommodityGoods(userVO);
    }

    //跳转库存界面
    //返回上一层
    @FXML
    public void gotoStock(ActionEvent e) {
        main.gotoCommodityStock(userVO);
    }


    //选择商品
    @FXML
    public void choose(ActionEvent e) {
        submitB.setDisable(false);
        lMain.getGoodList(main, userVO, "fresh");
    }

    //查询报警
    @FXML
    public void warningSearch(ActionEvent e) throws RemoteException {
        freshListTV.setEditable(false);
        String warningValueS = warningTF.getText();
        if (warningValueS == "")
            AlertUtil.showWarningAlert("请输入警戒值");
        else {
            int warningValue = Integer.valueOf(warningValueS);
            ArrayList<GoodsVO> gVOS = helper.getGoodsBLService().getCurrentGoods();
            goodsVOS = new ArrayList<GoodsVO>();
            for (int i = 0; i < gVOS.size(); i++)
                if (gVOS.get(i).getCommodityNum() < warningValue)
                    goodsVOS.add(gVOS.get(i));
            if (!goodsVOS.equals(null)) {
                int n = goodsVOS.size();

                ObservableList<StockFreshModel> data =
                        FXCollections.observableArrayList(
                        );
                for (int i = 0; i < n; i++)
                    data.add(new StockFreshModel(goodsVOS.get(i)));
                //遍历输入
                numTC.setCellValueFactory(new PropertyValueFactory<>("id"));
                nameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
                commodityTC.setCellValueFactory(new PropertyValueFactory<>("num"));
                freshListTV.setItems(data);
            }

        }
        helper.getLogBlService().addLog(userVO,"库存报警", ResultMessage.Success);
        submitB.setDisable(true);
    }

    //提交
    @FXML
    public void submit(ActionEvent e) throws RemoteException {
        String kind = kindCB.getValue();
        Set<GoodsLackVO> goodsLackVOSet = new HashSet<>();
        Set<GoodsOverflowVO> goodsOverflowVOSet = new HashSet<>();
        ObservableList<StockFreshModel> list = freshListTV.getItems();
        if (kind.equals("库存报损")) {
            for (int i = 0; i < list.size(); i++) {
                GoodsLackVO goodsLackVO = new GoodsLackVO();
                goodsLackVO.setId(Integer.valueOf(list.get(i).getId()));
                goodsLackVO.setTrueNumber(Integer.valueOf(list.get(i).getNum()));
                goodsLackVO.setVo(goodsVOS.get(i));
                helper.getGoodsLackBLService().addGoodsLack(goodsLackVO);
                goodsLackVOSet.add(goodsLackVO);
            }
            LackListVO lackListVO = new LackListVO();
            lackListVO.setOperator(userVO.getName());
            lackListVO.setSet(goodsLackVOSet);
            helper.getLackListBLService().addLackList(lackListVO);
            //helper.get/////////////
            helper.getLogBlService().addLog(userVO,"提交库存报损单", ResultMessage.Success);
        }
        else if (kind.equals("库存报溢")) {
            for (int i = 0; i < list.size(); i++) {
                GoodsOverflowVO goodsOverflowVO = new GoodsOverflowVO();
                goodsOverflowVO.setId(Integer.valueOf(list.get(i).getId()));
                goodsOverflowVO.setTrueNumber(Integer.valueOf(list.get(i).getNum()));
                goodsOverflowVO.setVo(goodsVOS.get(i));
                helper.getGoodsOverflowBLService().addGoodsOverflow(goodsOverflowVO);
                goodsOverflowVOSet.add(goodsOverflowVO);
            }
            OverflowListVO overflowListVO = new OverflowListVO();
            overflowListVO.setOperator(userVO.getName());
            overflowListVO.setSet(goodsOverflowVOSet);
            helper.getOverflowListBLService().addOverflowList(overflowListVO);
            //helper.get/////////////
            helper.getLogBlService().addLog(userVO,"提交库存报溢单", ResultMessage.Success);
        }

        AlertUtil.showInformationAlert("单据提交成功");
        main.gotoCommodityStock(userVO);
    }



    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);

    }


    public void setMain(Main main,UserVO userVO,ArrayList<GoodsVO> goodsVOS){
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("管理员"+userVO.getName());
        this.goodsVOS=goodsVOS;
        this.lMain=new Main();
        userNameLB.setText("User "+userVO.getName());
        freshListTV.setEditable(true);
        submitB.setDisable(false);
        kindCB.setItems(FXCollections.observableArrayList("库存报损","库存报溢","库存报警"));

        if(!goodsVOS.equals(null)) {
            int n = goodsVOS.size();

            ObservableList<StockFreshModel> data =
                    FXCollections.observableArrayList(
                    );
            for (int i = 0; i < n; i++)
                data.add(new StockFreshModel(goodsVOS.get(i)));
            //遍历输入
            numTC.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
            commodityTC.setCellValueFactory(new PropertyValueFactory<>("num"));
            commodityTC.setCellFactory(TextFieldTableCell.<StockFreshModel>forTableColumn());
            commodityTC.setOnEditCommit(
                    (TableColumn.CellEditEvent<StockFreshModel, String> t) -> {
                        ((StockFreshModel) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNum(t.getNewValue());
                    });
            freshListTV.setItems(data);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
