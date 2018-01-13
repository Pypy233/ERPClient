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
import ui.model.MemberSearchModel;
import ui.util.AlertUtil;
import vo.MemberVO;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class saleMemberIDSearchController implements Initializable {

    private Main main;
    private UserVO userVO;
    private MemberVO memberVO;
    //客户管理 按钮

    //退出按钮
    @FXML
    public Button exitButton ;

    //退出
    public void exit(ActionEvent e){
        userVO.setLogin(false);
        main.exit();
    }


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


    //ID查询 文本框
    @FXML
    public TextField memberIDSearchTF;

    //查询 按钮
    @FXML
    public Button memberIDSearchButton;




    //客户信息查询列表
    @FXML
    public TableView<MemberSearchModel> memberIDSearchTV;

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




    //UserInfo 用户名
    @FXML
    public Label userNameLB;

    //
    @FXML
    public Button chooseB;



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
    public void memberIDSearch(ActionEvent e)throws RemoteException{
        RemoteHelper helper=RemoteHelper.getInstance();
        String memberID=memberIDSearchTF.getText();
        if(memberID.equals("")){
            AlertUtil.showWarningAlert("查询ID不能为空");
        }
        else if(helper.getMemberBLService().findMember(Integer.valueOf(memberID)).equals(null)){
            //不存在的ID
            AlertUtil.showErrorAlert("此ID不存在");
        }
        else if(!helper.getMemberBLService().findMember(Integer.valueOf(memberID)).equals(null)){
            //存在账户
            memberVO=helper.getMemberBLService().findMember(Integer.valueOf(memberID));

            //显示
            ObservableList<MemberSearchModel> data= FXCollections.observableArrayList(
                    new MemberSearchModel(memberVO)
            );

            memberIDTC.setCellValueFactory(new PropertyValueFactory<>("id"));
            memberNameTC.setCellValueFactory(new PropertyValueFactory<>("name"));
            memberKindTC.setCellValueFactory(new PropertyValueFactory<>("kind"));
            memberLevelTC.setCellValueFactory(new PropertyValueFactory<>("level"));
            memberDefaultSalesmanTC.setCellValueFactory((new PropertyValueFactory<>("operator")));
            memberIDSearchTV.setItems(data);
        }

    }

    //tableview实现

    @FXML
    public void chooseM(ActionEvent e){
        main.gotoSaleMemberInfoEdit(userVO,"Search",memberVO);
    }



    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        RemoteHelper helper=RemoteHelper.getInstance();
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);
    }
    public void setMain(Main main,UserVO userVO){
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("User "+userVO.getName());
        memberIDSearchTV.setEditable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
