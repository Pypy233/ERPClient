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
import ui.model.GoodsPresentModel;
import ui.util.AlertUtil;
import vo.*;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class commodityStockPresentController implements Initializable {



    private Main main;
    private Main lMain;
    private UserVO userVO;
    RemoteHelper helper=RemoteHelper.getInstance();
    ArrayList<GoodsVO> goodsVOS;
    ArrayList<PresentVO> presentVOS;
    PresentListVO presentListVO;
    ArrayList<MemberVO> memberVOS;


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




    //“增加商品”按钮
    @FXML
    public Button addPresentButton;
    //提交
    @FXML
    public Button submitP;





    //赠送单
    @FXML
    public TableView<GoodsPresentModel> presentTV;

    //赠送单 商品ID
    @FXML
    public TableColumn presentGoodsIDTC;
    //赠送单 商品名
    @FXML
    public TableColumn presentGoodsNameTC;
    //赠送单 商品单价
    @FXML
    public TableColumn presentGoodsPriceTC;
    //赠送单 数量
    @FXML
    public TableColumn<GoodsPresentModel,String> presentGoodsNumTC;


    //选择客户
    @FXML
    public ChoiceBox<String> pTo;


    //
    @FXML
    public Label totalP;
    //
    @FXML
    public Button refresh;



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

    //增加商品
    @FXML
    public void addG(ActionEvent e){
        lMain.getGoodList(main,userVO,"present");
    }


    //生成赠送单
    @FXML
    public void setFresh(ActionEvent e){
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        ObservableList<GoodsPresentModel> list=presentTV.getItems();
        double sum=0;
        for(int i=0;i<list.size();i++){
            sum+=Double.valueOf(list.get(i).getNum())*goodsVOS.get(i).getRetailPrice();
        }
        totalP.setText(decimalFormat.format(sum));
    }

    //提交货单
    @FXML
    public void newList(ActionEvent e)throws RemoteException {

        Set<PresentVO> g=new HashSet<>();
        ObservableList<GoodsPresentModel> list=presentTV.getItems();
        for(int i=0;i<goodsVOS.size();i++){
            PresentVO presentVO=new PresentVO();
            presentVO.setId(goodsVOS.get(i).getNumber());
            presentVO.setNumber(Integer.valueOf(list.get(i).getNum()));
            presentVO.setVo(goodsVOS.get(i));
            helper.getPresentBLService().addPresent(presentVO);
            g.add(presentVO);
        }
        presentListVO=new PresentListVO();
        presentListVO.setClient(pTo.getValue());
        presentListVO.setOperator(userVO.getName());
        presentListVO.setSet(g);
        helper.getPresentListBLService().addPresentList(presentListVO);
        //helper.get.............................................................................................
        AlertUtil.showInformationAlert("货单已提交");
        helper.getLogBlService().addLog(userVO,"提交赠送单", ResultMessage.Success);
        main.gotoCommodityStock(userVO);
    }




    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);
    }


    public void setMain(Main main, UserVO userVO, ArrayList<GoodsVO> goodsVOS) throws RemoteException {
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("User "+userVO.getName());
        lMain=new Main();
        this.goodsVOS=goodsVOS;
        presentTV.setEditable(true);
        memberVOS=helper.getMemberBLService().findMemberByClass("销售商");
        ObservableList<String> list= FXCollections.observableArrayList();
        for(int i=0;i<memberVOS.size();i++)
            list.add(memberVOS.get(i).getName());
        pTo.setItems(list);

        if(!goodsVOS.equals(null)) {
            int n=goodsVOS.size();
            ObservableList<GoodsPresentModel> data =
                    FXCollections.observableArrayList(
                    );
            for (int i = 0; i < n; i++)
                data.add(new GoodsPresentModel(goodsVOS.get(i)));

            presentGoodsIDTC.setCellValueFactory(new PropertyValueFactory<>("id"));
            presentGoodsNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
            presentGoodsPriceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
            presentGoodsNumTC.setCellValueFactory(new PropertyValueFactory<>("num"));
            presentGoodsNumTC.setCellFactory(TextFieldTableCell.<GoodsPresentModel>forTableColumn());
            presentGoodsNumTC.setOnEditCommit(
                    (TableColumn.CellEditEvent<GoodsPresentModel, String> t) -> {
                        ((GoodsPresentModel) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNum(t.getNewValue());
                    });

            presentTV.setItems(data);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
