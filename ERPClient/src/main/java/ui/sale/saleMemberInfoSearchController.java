package ui.sale;

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
import ui.model.MemberInfoSearchModel;
import ui.util.AlertUtil;
import vo.GoodsVO;
import vo.MemberVO;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class saleMemberInfoSearchController implements Initializable {

    private Main main;
    private UserVO userVO;
    RemoteHelper helper=RemoteHelper.getInstance();
    ArrayList<MemberVO> memberVOS;

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


    //客户 姓名 文本框
    @FXML
    public TextArea memberNameTA;

    //客户 默认业务员 文本框
    @FXML
    public TextArea memberDefaultSalesmanTA;


    //客户类别
    @FXML
    public ChoiceBox<String> kindCB;



    //客户等级
    @FXML
    public ChoiceBox<String> levelCB;


    //查询 按钮
    @FXML
    public Button memberInfoSearchButton;




    //客户信息查询列表
    @FXML
    public TableView memberInfoSearchTV;

    //客户信息查询列表 ID栏
    @FXML
    public TableColumn memberIDTC;
    //客户信息查询列表 名称栏
    @FXML
    public TableColumn memberNameTC;
    //客户信息查询列表 类别栏
    @FXML
    public TableColumn memberKindTC;
    //客户信息查询列表 级别栏
    @FXML
    public TableColumn memberLevelTC;
    //客户信息查询列表 业务员栏
    @FXML
    public TableColumn memberDefaultSalesmanTC;
    //客户信息查询列表 勾选栏
    @FXML
    public TableColumn memberChooseTC;





    //UserInfo 用户名
    @FXML
    public Label userNameLB;


    //右上角 登出 按钮
    @FXML
    public Button logoutButton;
    //右下角 返回上一层 按钮
    @FXML
    public Button backButton;





    //跳转客户管理界面
    //返回上一层
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
    @FXML
    public void gotoReturn(ActionEvent e){
        main.gotoSaleReturn(userVO);
    }

    //查询 action
    @FXML
    public void memberInfoSearch(ActionEvent e)throws RemoteException{
        int level=levelCB.getSelectionModel().getSelectedIndex()+1;

        String kind="进货商";
        if(kindCB.getSelectionModel().getSelectedIndex()==0)
            kind="进货商";
        else if(kindCB.getSelectionModel().getSelectedIndex()==1)
            kind="销售商";

        String name=memberNameTA.getText();
        String dSalesman=memberDefaultSalesmanTA.getText();

        memberVOS=helper.getMemberBLService().find(kind,level,name,dSalesman);
        ObservableList<MemberInfoSearchModel> data= FXCollections.observableArrayList(

        );
        int n=memberVOS.size();
        for(int i=0;i<n;i++)
            data.add(new MemberInfoSearchModel(memberVOS.get(i)));
        memberChooseTC.setCellValueFactory(new PropertyValueFactory<>("box"));
        memberIDTC.setCellValueFactory(new PropertyValueFactory<>("id"));
        memberNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
        memberKindTC.setCellValueFactory(new PropertyValueFactory<>("kind"));
        memberLevelTC.setCellValueFactory(new PropertyValueFactory<>("level"));
        memberDefaultSalesmanTC.setCellValueFactory(new PropertyValueFactory<>("operator"));
        memberInfoSearchTV.setItems(data);

    }
    //
    @FXML
    public Button chooseG;

    //
    @FXML
    public void chooseM(ActionEvent e){
        ObservableList<MemberInfoSearchModel> list=memberInfoSearchTV.getItems();
        MemberVO vo=null;
        for(int i=0;i<list.size();i++) {
            if (list.get(i).getBox().isSelected() == true) {
                vo=memberVOS.get(i);

                break;
            }
        }
        if(!vo.equals(null))
            main.gotoSaleMemberInfoEdit(userVO, "Search", vo);
        else
        AlertUtil.showErrorAlert("请选择客户查看信息");
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
        levelCB.setItems(FXCollections.observableArrayList("一级用户","二级用户","三级用户","四级用户","五级用户（VIP）"));
        kindCB.setItems(FXCollections.observableArrayList("进货商","销售商"));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
