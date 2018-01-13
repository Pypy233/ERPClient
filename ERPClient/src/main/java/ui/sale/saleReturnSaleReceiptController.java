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
import ui.model.ReturnSaleModel;
import ui.util.AlertUtil;
import vo.*;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class saleReturnSaleReceiptController implements Initializable {

    RemoteHelper helper=RemoteHelper.getInstance();
    private SaleReturnVO saleReturnVO;
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

    //总价
    @FXML
    public Label totalSumLB;

    //进货单商品列表
    @FXML
    public TableView<ReturnSaleModel> goodsListTV;

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
    public TableColumn<ReturnSaleModel,String> goodsNumTC;
    //进货单商品列表 总额栏
    @FXML
    public TableColumn goodsTotalPriceTC;
    //进货单商品列表 备注栏
    @FXML
    public TableColumn<ReturnSaleModel,String> goodsRemarkTC;


    //
    @FXML
    public ChoiceBox<String> retailerCB;


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

    //
    @FXML
    public TextArea remark;




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


    //
    @FXML
    public Button  refresh;
    //
    @FXML
    public void setFresh(ActionEvent e){
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        ObservableList<ReturnSaleModel> list=goodsListTV.getItems();
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
        lMain.getGoodList(main,userVO,"saleReturn");
        //调用一个商品列表
        //tableView增加商品信息
    }

    //提交货单
    @FXML
    public void returnSubmit(ActionEvent e)throws RemoteException{
        if(retailerCB.getValue()==null){
            AlertUtil.showWarningAlert("请选择客户");
        }
        else {
            saleReturnVO = new SaleReturnVO();
            //读取货单数据
            Set<GoodsSaleReturnVO> g = new HashSet<>();
            ObservableList<ReturnSaleModel> list = goodsListTV.getItems();
            double sum=0;
            for (int i = 0; i < goodsVOS.size(); i++) {
                GoodsSaleReturnVO goodsSaleReturnVO = new GoodsSaleReturnVO();
                goodsSaleReturnVO.setVo(goodsVOS.get(i));
                goodsSaleReturnVO.setSaleReturnNumber(Integer.valueOf(list.get(i).getNum()));
                ////////////////////////  //goodsStockVO.setPrice(goodsVOS.get(i).getPurchasePrice());//////少一个
                goodsSaleReturnVO.setTotalPrice(Double.valueOf(list.get(i).getTotal()));
                goodsSaleReturnVO.setRemark(list.get(i).getDes());
                g.add(goodsSaleReturnVO);
                sum+=Double.valueOf(list.get(i).getTotal());
                helper.getGoodsSaleReturnBLService().addGoodsSaleReturn(goodsVOS.get(i),Integer.valueOf(list.get(i).getNum()),Double.valueOf(list.get(i).getTotal()),list.get(i).getDes());
            }
            String dS = memberVOS.get(retailerCB.getSelectionModel().getSelectedIndex()).getManagePerson();
            helper.getSaleReturnBLService().addSaleReturn(retailerCB.getValue(), dS, userVO.getName(), remark.getText(), g);
            AlertUtil.showInformationAlert("货单已提交");
            MemberVO vo = helper.getMemberBLService().findMemberByName(retailerCB.getValue()).get(0);
            vo.setPayment(vo.getPayment() + sum);
            helper.getMemberBLService().updateMember(vo);
            helper.getLogBlService().addLog(userVO, "提交销售退货单", ResultMessage.Success);
            main.gotoSaleReturn(userVO);
            //
            //传退货单的类型
        }
    }


    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);
    }
    public void setMain(Main main,UserVO userVO,ArrayList<GoodsVO> goodsVOS) throws RemoteException {
        saleReturnVO =new SaleReturnVO();
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("管理员"+userVO.getName());
        this.goodsVOS=goodsVOS;
        this.lMain=new Main();
        userNameLB.setText("User "+userVO.getName());
        receiptNumLB.setText("（系统自动生成）");
        goodsListTV.setEditable(true);
        suppliersLB.setText(userVO.getName());
        memberVOS= helper.getMemberBLService().findMemberByClass("销售商");
        ObservableList<String> list= FXCollections.observableArrayList();
        for(int i=0;i<memberVOS.size();i++)
            list.add(memberVOS.get(i).getName());
        retailerCB.setItems(list);

        if(!goodsVOS.equals(null)) {
            int n = goodsVOS.size();

            ObservableList<ReturnSaleModel> data =
                    FXCollections.observableArrayList(
                    );
            for (int i = 0; i < n; i++)
                data.add(new ReturnSaleModel(goodsVOS.get(i), i + 1));
            //遍历输入
            goodsIDTC.setCellValueFactory(new PropertyValueFactory<>("line"));
            goodsNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
            goodsPriceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
            goodsTotalPriceTC.setCellValueFactory(new PropertyValueFactory<>("total"));
            goodsNumTC.setCellValueFactory(new PropertyValueFactory<>("num"));
            goodsNumTC.setCellFactory(TextFieldTableCell.<ReturnSaleModel>forTableColumn());
            goodsNumTC.setOnEditCommit(
                    (TableColumn.CellEditEvent<ReturnSaleModel, String> t) -> {
                        ((ReturnSaleModel) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNum(t.getNewValue());
                    });
            goodsRemarkTC.setCellValueFactory(new PropertyValueFactory<>("des"));
            goodsRemarkTC.setCellFactory(TextFieldTableCell.<ReturnSaleModel>forTableColumn());
            goodsRemarkTC.setOnEditCommit(
                    (TableColumn.CellEditEvent<ReturnSaleModel, String> t) -> {
                        ((ReturnSaleModel) t.getTableView().getItems().get(
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
