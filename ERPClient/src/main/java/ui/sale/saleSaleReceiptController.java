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
import ui.model.SaleModel;
import ui.util.AlertUtil;
import vo.*;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.*;

public class saleSaleReceiptController implements Initializable {

    RemoteHelper helper=RemoteHelper.getInstance();
    private SaleVO saleVO;
    private Main main;
    private UserVO userVO;
    private ArrayList<GoodsVO> goodsVOS;
    private Main lMain;
    private ArrayList<MemberVO> memberVOS;
    private MemberVO salesmanVO;
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
    //供应商
    @FXML
    public ChoiceBox<String> retailerCB;
    //仓库
    @FXML
    public Label warehouseLB;
    //操作员
    @FXML
    public Label operatorNumLB;
    //货单总价
    @FXML
    public Label totalSumLB;
    //折
    @FXML
    public Label discountLB;
    //折扣
    @FXML
    public TextField discountTF;
    //代金券
    @FXML
    public TextField voucherTF;
    //货单最终总价
    @FXML
    public Label finalLB;
    //货单备注
    @FXML
    public TextArea remark;

    //进货单商品列表
    @FXML
    public TableView<SaleModel> goodsListTV;

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
    public TableColumn <SaleModel,String> goodsNumTC;
    //进货单商品列表 总额栏
    @FXML
    public TableColumn goodsTotalPriceTC;
    //进货单商品列表 备注栏
    @FXML
    public TableColumn <SaleModel,String> goodsRemarkTC;


    //
    @FXML
    public ChoiceBox<String> StrategyCB;

    //////
    @FXML
    public Button refresh;
    @FXML
    public void refresh(ActionEvent e){
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        ObservableList<SaleModel> list=goodsListTV.getItems();
        double sum=0;
        for(int i=0;i<list.size();i++){
            list.get(i).setTotal(Double.valueOf(list.get(i).getPrice())*Double.valueOf(list.get(i).getNum())+"");
            sum+=Double.valueOf(list.get(i).getTotal());
        }

        totalSumLB.setText(decimalFormat.format(sum));
        String voucher=voucherTF.getText();
        String discount=discountTF.getText();
        int vou=0;double dis=0;
        if(discount=="")
            dis=1;
        else
            dis=Double.valueOf(discount)/10;
        vou=Integer.valueOf(voucher);
        sum=sum*dis-vou;
        finalLB.setText(decimalFormat.format(sum));

        if(sum<=0)
            AlertUtil.showWarningAlert("请酌情填写折扣与代金券");
        else
            AlertUtil.showInformationAlert("货单已生成");
    }



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
    //返回上一层
    @FXML
    public void gotoSale(ActionEvent e){
        main.gotoSaleSale(userVO);
    }
    //跳转退货界面
    @FXML
    public void gotoReturn(ActionEvent e){
        main.gotoSaleReturn(userVO);
    }

    //添加商品
    @FXML
    public void saleAddGoods(ActionEvent e){
        lMain.getGoodList(main,userVO,"sale");
    }

    //提交货单
    @FXML
    public void saleSubmit(ActionEvent e) throws RemoteException {
        Set<GoodsSaleVO> g=new HashSet<>();
        ObservableList<SaleModel> list=goodsListTV.getItems();
        double sum=0;
        for(int i=0;i<goodsVOS.size();i++){
            GoodsSaleVO goodsSaleVO=new GoodsSaleVO();
            goodsSaleVO.setVo(goodsVOS.get(i));
            goodsSaleVO.setPrice(goodsVOS.get(i).getRetailPrice());
            goodsSaleVO.setTotalPrice(Double.valueOf(list.get(i).getTotal()));
            goodsSaleVO.setRemark(list.get(i).getDes());
            g.add(goodsSaleVO);
            sum+=Double.valueOf(list.get(i).getTotal());
            helper.getGoodsSaleBLService().addGoodsSale(goodsVOS.get(i),Integer.valueOf(list.get(i).getNum()),Double.valueOf(list.get(i).getTotal()),list.get(i).getDes());
        }
        String dS=memberVOS.get(retailerCB.getSelectionModel().getSelectedIndex()).getManagePerson();
        helper.getSaleBLService().addSale(retailerCB.getValue(),dS,userVO.getName(),
                Double.valueOf(discountTF.getText())/10,Double.valueOf(voucherTF.getText()),remark.getText(),g);
        MemberVO vo = helper.getMemberBLService().findMemberByName(retailerCB.getValue()).get(0);
        vo.setCollection(vo.getCollection() + sum);
        helper.getMemberBLService().updateMember(vo);
        AlertUtil.showInformationAlert("货单已提交");
        helper.getLogBlService().addLog(userVO,"提交销售单", ResultMessage.Success);
        main.gotoSaleSale(userVO);
    }


    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);
    }
    public void setMain(Main main,UserVO userVO,ArrayList<GoodsVO> goodsVOS) throws RemoteException {
        saleVO=new SaleVO();
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("User "+userVO.getName());
        this.goodsVOS=goodsVOS;
        this.lMain=new Main();
        userNameLB.setText(userVO.getName());
        receiptNumLB.setText("（系统自动生成）");
        goodsListTV.setEditable(true);
        discountTF.setText("9.9");
        operatorNumLB.setText(userVO.getName());


        /*
        ObservableList<String> strategy=FXCollections.observableArrayList();
        for(int i=0;i<memberVOS.size();i++)
            strategy.add(memberVOS.get(i).getName());
        retailerCB.setItems(strategy);
        */

        memberVOS= helper.getMemberBLService().findMemberByClass("销售商");
        ObservableList<String> list=FXCollections.observableArrayList();
        for(int i=0;i<memberVOS.size();i++)
            list.add(memberVOS.get(i).getName());
        retailerCB.setItems(list);

        if(!goodsVOS.equals(null)) {
            int n = goodsVOS.size();

            ObservableList<SaleModel> data =
                    FXCollections.observableArrayList(
                    );
            for (int i = 0; i < n; i++)
                data.add(new SaleModel(goodsVOS.get(i), i + 1));
            //遍历输入
            goodsIDTC.setCellValueFactory(new PropertyValueFactory<>("line"));
            goodsNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
            goodsPriceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
            goodsTotalPriceTC.setCellValueFactory(new PropertyValueFactory<>("total"));
            goodsNumTC.setCellValueFactory(new PropertyValueFactory<>("num"));
            goodsNumTC.setCellFactory(TextFieldTableCell.<SaleModel>forTableColumn());
            goodsNumTC.setOnEditCommit(
                    (TableColumn.CellEditEvent<SaleModel, String> t) -> {
                        ((SaleModel) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNum(t.getNewValue());
                    });
            goodsRemarkTC.setCellValueFactory(new PropertyValueFactory<>("des"));
            goodsRemarkTC.setCellFactory(TextFieldTableCell.<SaleModel>forTableColumn());
            goodsRemarkTC.setOnEditCommit(
                    (TableColumn.CellEditEvent<SaleModel, String> t) -> {
                        ((SaleModel) t.getTableView().getItems().get(
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
