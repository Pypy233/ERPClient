package ui.commodity;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ui.Main;
import vo.UserVO;

public class commodityClassifyKindController {

private Main main;
private UserVO userVO;
    //左侧“商品分类”按钮
    @FXML
    public Button classifyButton;
    //左侧“商品管理”按钮
    @FXML
    public Button goodsButton;
    //左侧“库存”按钮
    @FXML
    public Button stockButton;





    //类别列表
    @FXML
    public TableView kindTV;

    //类别列表 id栏
    @FXML
    public TableColumn kindIDTC;
    //类别列表 类别栏
    @FXML
    public TableColumn kindKindTC;
    //类别列表 描述栏
    @FXML
    public TableColumn kindDescriptionTC;




    //添加商品分类ID栏
    @FXML
    public TextField kindIDTF;
    //添加商品分类类别栏
    @FXML
    public TextField kindNameTF;
    //添加商品分类描述栏
    @FXML
    public TextField kindDescriptionTF;

    //“添加分类”按钮
    @FXML
    public Button addKindButton;



    //UserInfo 用户名
    @FXML
    public Label userNameLB;



    //右上角“登出”按钮
    @FXML
    public Button logoutButton;

    //右下角“返回上一层”按钮
    @FXML
    public Button backButton;




    //1.跳转商品分类界面
    //2.返回上一层
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
    @FXML
    public void gotoStock(ActionEvent e){
        main.gotoCommodityStock(userVO);
    }


    //增加类别
    @FXML
    public void addNewKind(ActionEvent e){

    }







    //登出
    @FXML
    public void gotoLog(ActionEvent e){
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
    }

    public void setMain(Main main,UserVO userVO){
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("管理员"+userVO.getName());
    }


}
