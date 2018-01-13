package ui.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.UserModel;
import ui.util.AlertUtil;
import vo.AdminVO;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private Main main;
    private AdminVO adminVO;
    RemoteHelper helper=RemoteHelper.getInstance();
    ArrayList<UserVO> userVOS;
    ObservableList<UserModel> data;

    //退出按钮
    @FXML
    public Button exitButton ;

    //退出
    public void exit(ActionEvent e){
        main.exit();
    }

    //
    @FXML
    public TableView<UserModel> tableView;
    //
    @FXML
    public TableColumn<UserModel,String> name;
    //
    @FXML
    public TableColumn<UserModel,String> code;
    //
    @FXML
    public TableColumn line;
    //
    @FXML
    public TableColumn<UserModel,String> kind;
    //
    @FXML
    public TableColumn<UserModel,String> log;
    //
    @FXML
    public TableColumn chooseB;


    //
    @FXML
    public Button add;
    //
    @FXML
    public Button delete;
    //
    @FXML
    public TextField newName;
    //
    @FXML
    public TextField newCode;
    //
    @FXML
    public ChoiceBox<String> choiceBox;

    //
    @FXML
    public void addNew(ActionEvent e) throws RemoteException {
        String kind=choiceBox.getValue();
        String name=newName.getText();
        String code=newCode.getText();
        UserVO userVO=new UserVO();
        userVO.setLogin(false);
        userVO.setName(name);
        userVO.setType(kind);
        userVO.setPassword(code);
        ////////////////helper.;
        helper.getAdminBLService().addAdvancedUser(userVO);
        data.add(new UserModel(userVO,data.size()+1));
    }
    //
    @FXML
    public void deleteOld(ActionEvent e) throws RemoteException {

        List<UserModel> check= tableView.getItems();
        for(int i=0;i<check.size();i++)
            if(check.get(i).getCb().isSelected()==true){
                helper.getAdminBLService().deleteUser(check.get(i).getUserVO());
            data.removeAll(check.get(i));

            }
        AlertUtil.showInformationAlert("删除成功");
    }




    public void setMain(Main main,AdminVO adminVO) throws RemoteException {
        //
        this.main=main;
        userVOS=helper.getAdminBLService().getAllUsers();
        choiceBox.setItems(FXCollections.observableArrayList("总经理","财务人员","库存管理人员","进货销售人员"));
        tableView.setEditable(true);

        data= FXCollections.observableArrayList(

        );

        int n=userVOS.size();
        for(int i=0;i<n;i++)
            data.add(new UserModel(userVOS.get(i),i+1));
        chooseB.setCellValueFactory(new PropertyValueFactory<>("cb"));
        line.setCellValueFactory(new PropertyValueFactory<>("line"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(TextFieldTableCell.<UserModel>forTableColumn());
        name.setOnEditCommit(
                (TableColumn.CellEditEvent<UserModel, String> t) -> {
                    ((UserModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setName(t.getNewValue());
                });
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        code.setCellFactory(TextFieldTableCell.<UserModel>forTableColumn());
        code.setOnEditCommit(
                (TableColumn.CellEditEvent<UserModel, String> t) -> {
                    ((UserModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setCode(t.getNewValue());
                });
        log.setCellValueFactory(new PropertyValueFactory<>("log"));
        log.setCellFactory(TextFieldTableCell.<UserModel>forTableColumn());
        log.setOnEditCommit(
                (TableColumn.CellEditEvent<UserModel, String> t) -> {
                    ((UserModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setLog(t.getNewValue());
                });
        kind.setCellValueFactory(new PropertyValueFactory<>("kind"));
        kind.setCellFactory(TextFieldTableCell.<UserModel>forTableColumn());
        kind.setOnEditCommit(
                (TableColumn.CellEditEvent<UserModel, String> t) -> {
                    ((UserModel) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setKind(t.getNewValue());
                });
        tableView.setItems(data);

    }

    //
    @FXML
    public Button back;
    //
    @FXML
    public void back(ActionEvent e){
        main.adminLogin("admin");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
