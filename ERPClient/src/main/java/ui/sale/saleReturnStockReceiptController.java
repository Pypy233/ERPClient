package ui.sale;

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
import ui.model.ReturnStockModel;
import ui.model.StockModel;
import ui.util.AlertUtil;
import vo.*;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class saleReturnStockReceiptController implements Initializable {

    RemoteHelper helper=RemoteHelper.getInstance();
    private StockReturnVO stockReturnVO;
    private Main main;
    private UserVO userVO;
    private ArrayList<GoodsVO> goodsVOS;
    private Main lMain;
    private ArrayList<MemberVO> memberVOS;


    //退出按钮
    @FXML
    public Button exitButton ;

    //退出
    public void exit(ActionEvent e){
        userVO.setLogin(false);
        main.exit();
    }

    //客户管理 按钮
    @FXML
    public Button memberButton;
    //进货 按钮
    @FXML
    public Button stockInButton;
    //销售 按钮
    @FXML
    public Button saleButton;
    //退货 按钮
    @FXML
    public Button returnButton;


    //货单编号
    @FXML
    public Label receiptNumLB;
    //货单类型
    @FXML
    public Label receiptKindLB;
    //供应商
    @FXML
    public Label suppliersLB;
    //仓库
    @FXML
    public Label warehouseLB;
    //操作员
    @FXML
    public Label operatorLB;
    //总价
    @FXML
    public Label totalSumLB;

    //进货单商品列表
    @FXML
    public TableView<ReturnStockModel> goodsListTV;

    //进货单商品列表 序号栏
    @FXML
    public TableColumn goodsIDTC;
    //进货单商品列表 名称栏
    @FXML
    public TableColumn goodsNameTC;
    //进货单商品列表 单价栏
    @FXML
    public TableColumn goodsPriceTC;
    //进货单商品列表 数量栏
    @FXML
    public TableColumn<ReturnStockModel,String> goodsNumTC;
    //进货单商品列表 总额栏
    @FXML
    public TableColumn goodsTotalPriceTC;
    //进货单商品列表 备注栏
    @FXML
    public TableColumn<ReturnStockModel,String> goodsRemarkTC;


    //
    @FXML
    public ChoiceBox<String> supplierCB;


    //
    @FXML
    public TextArea remark;


    //UserInfo 用户名
    @FXML
    public Label userNameLB;




    //右上角 登出 按钮
    @FXML
    public Button logoutButton;
    //右下角 返回上一层 按钮
    @FXML
    public Button backButton;
    //添加商品 按钮
    @FXML
    public Button addGoodsButton;
    //提交货单 按钮
    @FXML
    public Button submitReceiptButton;




    //跳转客户管理界面
    @FXML
    public void gotoMember(ActionEvent e){
        main.gotoSaleMember(userVO);
    }
    //跳转进货界面
    @FXML
    public void gotoStockIn(ActionEvent e){
        main.gotoSaleStock(userVO);
    }

    //跳转销售界面
    @FXML
    public void gotoSale(ActionEvent e){
        main.gotoSaleSale(userVO);
    }
    //跳转退货界面
    //返回上一层
    @FXML
    public void gotoReturn(ActionEvent e){
        main.gotoSaleReturn(userVO);
    }



    //生成货单
    @FXML
    public Button refresh;
    //
    @FXML
    public void setFresh(){
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        ObservableList<ReturnStockModel> list=goodsListTV.getItems();
        double sum=0;
        for(int i=0;i<list.size();i++){
            list.get(i).setTotal(Double.valueOf(list.get(i).getPrice())*Double.valueOf(list.get(i).getNum())+"");
            sum+=Double.valueOf(list.get(i).getTotal());
        }
        totalSumLB.setText(decimalFormat.format(sum));
        AlertUtil.showInformationAlert("货单已生成");
    }


    //添加商品
    @FXML
    public void returnAddGoods(ActionEvent e)throws RemoteException{
        lMain.getGoodList(main,userVO,"stockReturn");
        //调用一个商品列表
        //tableView添加商品信息
    }


    //提交货单
    @FXML
    public void returnSubmit(ActionEvent e)throws RemoteException{
        Set<GoodsStockReturnVO> g=new HashSet<>();
        ObservableList<ReturnStockModel> list=goodsListTV.getItems();
        for(int i=0;i<goodsVOS.size();i++){
            GoodsStockReturnVO goodsStockReturnVO=new GoodsStockReturnVO();
            goodsStockReturnVO.setVo(goodsVOS.get(i));
            goodsStockReturnVO.setStockReturnNumber(Integer.valueOf(list.get(i).getNum()));
            ////////////////////////  //goodsStockVO.setPrice(goodsVOS.get(i).getPurchasePrice());//////少一个
            goodsStockReturnVO.setTotalPrice(Double.valueOf(list.get(i).getTotal()));
            goodsStockReturnVO.setRemark(list.get(i).getDes());
            g.add(goodsStockReturnVO);
            helper.getGoodsStockReturnBLService().addGoodsStockReturn(goodsVOS.get(i),Integer.valueOf(list.get(i).getNum()),Double.valueOf(list.get(i).getTotal()),list.get(i).getDes());

        }
        String dS=memberVOS.get(supplierCB.getSelectionModel().getSelectedIndex()).getManagePerson();
        stockReturnVO=new StockReturnVO();
        stockReturnVO.setOperator(userVO.getName());
        stockReturnVO.setProvider(supplierCB.getValue());
        stockReturnVO.setRemark(remark.getText());
        stockReturnVO.setStockSet(g);//方法命名错误
        stockReturnVO.setTotalPrice(Double.valueOf(totalSumLB.getText()));
        stockReturnVO.setCommodityNumber(1);
        helper.getStockReturnBLService().addStockReturn(supplierCB.getValue(),remark.getText(),g,userVO.getName());
        MemberVO memberVO = helper.getMemberBLService().findMemberByName(supplierCB.getValue()).get(0);
        memberVO.setPayment(Double.valueOf(totalSumLB.getText())+memberVO.getPayment());
        helper.getMemberBLService().updateMember(memberVO);
        AlertUtil.showInformationAlert("货单已提交");
        helper.getLogBlService().addLog(userVO,"提交进货退货单", ResultMessage.Success);
        main.gotoSaleReturn(userVO);
    }


    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);
    }
    public void setMain(Main main,UserVO userVO,ArrayList<GoodsVO> goodsVOS) throws RemoteException {
        stockReturnVO =new StockReturnVO();
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("User "+userVO.getName());
        this.goodsVOS=goodsVOS;
        this.lMain=new Main();
        userNameLB.setText(userVO.getName());
        receiptNumLB.setText("（系统自动生成）");
        goodsListTV.setEditable(true);
        operatorLB.setText(userVO.getName());
        memberVOS=new ArrayList<>();
        memberVOS= helper.getMemberBLService().findMemberByClass("进货商");
        System.out.println(memberVOS.size());
        ObservableList<String> list= FXCollections.observableArrayList();
        for(int i=0;i<memberVOS.size();i++)
            list.add(memberVOS.get(i).getName());
        supplierCB.setItems(list);

        if(!goodsVOS.equals(null)) {
            int n = goodsVOS.size();

            ObservableList<ReturnStockModel> data =
                    FXCollections.observableArrayList(
                    );
            for (int i = 0; i < n; i++)
                data.add(new ReturnStockModel(goodsVOS.get(i), i + 1));
            //遍历输入
            goodsIDTC.setCellValueFactory(new PropertyValueFactory<>("line"));
            goodsNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
            goodsPriceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
            goodsTotalPriceTC.setCellValueFactory(new PropertyValueFactory<>("total"));
            goodsNumTC.setCellValueFactory(new PropertyValueFactory<>("num"));
            goodsNumTC.setCellFactory(TextFieldTableCell.<ReturnStockModel>forTableColumn());
            goodsNumTC.setOnEditCommit(
                    (TableColumn.CellEditEvent<ReturnStockModel, String> t) -> {
                        ((ReturnStockModel) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNum(t.getNewValue());
                    });
            goodsRemarkTC.setCellValueFactory(new PropertyValueFactory<>("des"));
            goodsRemarkTC.setCellFactory(TextFieldTableCell.<ReturnStockModel>forTableColumn());
            goodsRemarkTC.setOnEditCommit(
                    (TableColumn.CellEditEvent<ReturnStockModel, String> t) -> {
                        ((ReturnStockModel) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDes(t.getNewValue());
                    });

            goodsListTV.setItems(data);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
