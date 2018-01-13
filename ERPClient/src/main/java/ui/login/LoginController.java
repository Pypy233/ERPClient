package ui.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import objects.ResultMessage;
import rmi.RemoteHelper;
import service.blservice.UserBLService;
import ui.Main;
import ui.util.AlertUtil;
import vo.UserVO;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
private String type;
private Main main;

    //退出按钮
    @FXML
    public Button exitButton ;

    //退出
    public void exit(ActionEvent e){
        main.exit();
    }
    //人员类型label
    @FXML
    public Label userKindLB;
    //登录按钮
    @FXML
    public Button loginButton;
    //注册按钮（跳转注册页面）
    @FXML
    public Button toRegisterButton;
    //修改密码按钮（跳转修改密码页面）
    @FXML
    public Button codeModifyButton;

    //用户名输入框
    @FXML
    public TextField adminNameTF;
    //密码输入框
    @FXML
    public PasswordField adminCodeTF;



    //返回上一层按钮
    @FXML
    public Button backButton;







    //转向修改密码界面
    @FXML
    public void gotoCodeModify(ActionEvent e){
        main.gotoModifyCode(type);
    }

    //转向注册界面
    @FXML
    public void gotoRegister(ActionEvent e) {
        main.gotoRegister(type);
    }


    //检查账户及密码并登陆转换至人员主界面
    @FXML
    public void gotoLogin(ActionEvent e) throws RemoteException {
        RemoteHelper helper = RemoteHelper.getInstance();
        String name = adminNameTF.getText();
        String code = adminCodeTF.getText();
        if(name.equals("")||code.equals("")){
            AlertUtil.showWarningAlert("用户登录信息请填写完整");
        }
        else if (helper.getUserBLService().check(name, code) .equals(ResultMessage.Success) )
        {
            UserVO userVO = helper.getUserBLService().getUserVO(name);
            if(!userVO.getType().equals(type)){
                AlertUtil.showWarningAlert("该账户权限职能不包括此项，请重新选择人员类别登录此账户");
            }
            else {
                if (!userVO.isLogin()) {
                    AlertUtil.showInformationAlert("登陆成功");
                    switch (type) {
                        case "总经理": {
                            main.gotoManagerMain(userVO);
                            userVO.setLogin(true);
                        }
                        break;
                        case "财务人员": {
                            main.gotoFinacialStaffMain(userVO);
                            userVO.setLogin(true);
                        }
                        break;
                        case "库存管理人员": {
                            main.gotoCommodityMain(userVO);
                            System.out.println(userVO.getType()+userVO.getName());
                            userVO.setLogin(true);
                        }
                        break;
                        case "进货销售人员": {
                            main.gotoSaleMain(userVO);
                            userVO.setLogin(true);

                        }
                        break;
                    }
                    helper.getLogBlService().addLog(userVO,"登录",ResultMessage.Success);
                } else {
                    AlertUtil.showWarningAlert("对不起，该账户已被登录");
                }
            }
        }
        else{
            AlertUtil.showErrorAlert("此账户用户名、密码不一致或不存在此用户");
        }
    }


    //返回上一层（初始界面）
    @FXML
    public void gotoBackOverview(ActionEvent e) {
        main.backToMain();
    }

    public void setMain(Main main,String type){
        this.main=main;
        this.type=type;
        userKindLB.setText(type);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
