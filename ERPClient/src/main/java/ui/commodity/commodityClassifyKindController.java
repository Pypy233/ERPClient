package ui.commodity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import objects.ResultMessage;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.ClassifyModel;
import vo.ClassifyVO;
import vo.GoodsVO;
import vo.UserVO;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;

public class commodityClassifyKindController implements Initializable {

    RemoteHelper helper=RemoteHelper.getInstance();
    private Main main;
    private UserVO userVO;
    ArrayList<GoodsVO> goodsVOS;

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






    //UserInfo 用户名
    @FXML
    public Label userNameLB;


    //商品编号 信息
    @FXML
    public TextField goodsIDTA;
    //商品名称 信息
    @FXML
    public TextField goodsNameTA;
    //商品库存 信息
    @FXML
    public TextField goodsStockTA;
    //商品类型 信息
    @FXML
    public TextField goodsKindTA;
    //商品进价 信息
    @FXML
    public TextField goodsStockPriceTA;
    //商品零售价 信息
    @FXML
    public TextField goodsSalePriceTA;
    //商品最新进价 信息
    @FXML
    public TextField goodsRecentStockPriceTA;
    //商品最新零售价 信息
    @FXML
    public TextField goodsRecentSalePriceTA;


    //
    @FXML
    public TreeView<String> treeView;








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

    //
    @FXML
    public VBox vb;
    //
    @FXML
    AnchorPane setss;



    Node rootIcon =
            new ImageView(new Image(getClass().getResourceAsStream("/image/kind.png")));
    Image depIcon =
            new Image(getClass().getResourceAsStream("/image/kind.png"));




    //登出
    @FXML
    public void gotoLog(ActionEvent e) throws RemoteException {
        userVO.setLogin(false);
        main.gotoLog(userVO.getType());
        helper.getLogBlService().addLog(userVO,"登出", ResultMessage.Success);
    }

    public void setMain(Main main,UserVO userVO) throws RemoteException {
        this.main=main;
        this.userVO=userVO;
        userNameLB.setText("User "+userVO.getName());



        goodsIDTA.setText("");
        goodsNameTA.setText("");
        goodsKindTA.setText("");
        goodsStockTA.setText("");
        goodsStockPriceTA.setText("");
        goodsSalePriceTA.setText("");
        goodsRecentStockPriceTA.setText("");
        goodsRecentSalePriceTA.setText("");

        goodsIDTA.setEditable(false);
        goodsNameTA.setEditable(false);
        goodsKindTA.setEditable(false);
        goodsStockTA.setEditable(false);
        goodsStockPriceTA.setEditable(false);
        goodsSalePriceTA.setEditable(false);
        goodsRecentStockPriceTA.setEditable(false);
        goodsRecentSalePriceTA.setEditable(false);


         goodsVOS=helper.getGoodsBLService().getCurrentGoods();
        List<ClassifyModel> list = new ArrayList<>(Arrays.<ClassifyModel>asList(

        ));
       for (int i=0;i<goodsVOS.size();i++)
           list.add(new ClassifyModel(goodsVOS.get(i)));

        TreeItem<String> rootNode =
                new TreeItem<>("所有商品", rootIcon);

        rootNode.setExpanded(true);
        for (ClassifyModel classifyModel:list) {
            TreeItem<String> empLeaf = new TreeItem<>(classifyModel.getName());

            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren()) {
                if (depNode.getValue().contentEquals(classifyModel.getKind())){
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem depNode = new TreeItem(classifyModel.getKind(),
                        new ImageView(depIcon)
                );
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }



        treeView= new TreeView<>(rootNode);
        treeView.setStyle("");

        treeView.setEditable(true);
        treeView.setCellFactory((TreeView<String> p) ->
                new TextFieldTreeCellImpl());
        treeView.setVisible(true);

        setss.getChildren().add(treeView);
        treeView.setLayoutX(50);
        treeView.setLayoutY(70);
        treeView.setMaxHeight(400);


    }
    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;
        private final ContextMenu addMenuNode = new ContextMenu();
        private final ContextMenu addMenuLeaf = new ContextMenu();
        private final ContextMenu addMenuRoot=new ContextMenu();
        private final ContextMenu addMenuSingleNode=new ContextMenu();

        public TextFieldTreeCellImpl() {
            //Leaf
            MenuItem lookMenuItem=new MenuItem("detail");
            MenuItem deleteGMenuItem=new MenuItem("delete");
            addMenuLeaf.getItems().add(lookMenuItem);
            addMenuLeaf.getItems().add(deleteGMenuItem);

            lookMenuItem.setOnAction((ActionEvent t) -> {
                if(getTreeItem().getChildren()!=null) {
                    String name=getTreeItem().getValue();
                    try {
                        GoodsVO goodsVO=findByName(name);
                        //GoodsVO goodsVO=helper.getGoodsBLService().findGoods("",name,getTreeItem().getParent().getValue()).get(0);
                        goodsIDTA.setText(goodsVO.getNumber()+"");
                        goodsNameTA.setText(goodsVO.getName());
                        goodsKindTA.setText(goodsVO.getType());
                        goodsStockTA.setText(goodsVO.getCommodityNum()+"");
                        goodsStockPriceTA.setText(goodsVO.getPurchasePrice()+"");
                        goodsSalePriceTA.setText(goodsVO.getRetailPrice()+"");
                        goodsRecentStockPriceTA.setText(goodsVO.getRecentPurPrice()+"");
                        goodsRecentSalePriceTA.setText(goodsVO.getRecentRetPrice()+"");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
            });
            deleteGMenuItem.setOnAction((ActionEvent t) ->{
                if(getTreeItem().getParent().getChildren().size()==1)
                    getTreeItem().getParent().getChildren().add(new TreeItem<>());
                String name=getTreeItem().getValue();
                String kind=getTreeItem().getParent().getValue();
                TreeItem p=getTreeItem().getParent();


                try {
                    GoodsVO goodsVO=findByName(name);
                    goodsVO.setType("默认分类");
                    helper.getGoodsBLService().updateGoods(goodsVO);
                    treeView.refresh();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                ClassifyVO classifyVO=new ClassifyVO();
                classifyVO.setName(kind);
                Set<GoodsVO> g=new HashSet<>();
                for(int i=0;i<p.getChildren().size();i++) {
                    try {
                        g.add(findByName(getTreeItem().getParent().getChildren().get(i).getValue()));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    helper.getClassifyBLService().updateClassify(classifyVO);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                //
                getTreeItem().getParent().getChildren().remove(getTreeItem());
                try {
                    helper.getLogBlService().addLog(userVO,"删除商品 "+name, ResultMessage.Success);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });


            //Node
            MenuItem addOneMenuItem = new MenuItem("add");

            addMenuNode.getItems().add(addOneMenuItem);


            addOneMenuItem.setOnAction((ActionEvent t) -> {
                TreeItem newOne =
                        new TreeItem<>("new");
                if(getTreeItem().getChildren().get(0).getValue()==null)
                    getTreeItem().getChildren().remove(0,1);

                getTreeItem().getChildren().add(newOne);

                ClassifyVO classifyVO=new ClassifyVO();
                Set<GoodsVO> g=new HashSet<>();
                GoodsVO goodsVO=new GoodsVO();
                g.add(goodsVO);
                classifyVO.setName("分类");
                classifyVO.setGoodsSet(g);
                try {
                    helper.getClassifyBLService().addClassify(classifyVO);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    helper.getLogBlService().addLog(userVO,"新建商品（类）",ResultMessage.Success);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });



            //SingleNode
            MenuItem addMenuItem = new MenuItem("add");
            MenuItem kindNewKindMenuItem=new MenuItem("new");
            MenuItem deleteMenuItem=new MenuItem("delete");
            addMenuSingleNode.getItems().add(addMenuItem);
            addMenuSingleNode.getItems().add(kindNewKindMenuItem);
            addMenuSingleNode.getItems().add(deleteMenuItem);

            addMenuItem.setOnAction((ActionEvent t) -> {
                TreeItem newOne =
                        new TreeItem<>("new");
                if(getTreeItem().getChildren().get(0).getValue()==null)
                    getTreeItem().getChildren().remove(0,1);

                getTreeItem().getChildren().add(newOne);

                ClassifyVO classifyVO=new ClassifyVO();
                Set<GoodsVO> g=new HashSet<>();
                GoodsVO goodsVO=new GoodsVO();
                g.add(goodsVO);
                classifyVO.setName("分类");
                classifyVO.setGoodsSet(g);
                try {
                    helper.getClassifyBLService().addClassify(classifyVO);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    helper.getLogBlService().addLog(userVO,"新建商品（类）",ResultMessage.Success);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
            kindNewKindMenuItem.setOnAction((ActionEvent t) -> {
                getTreeItem().getChildren().remove(0,1);
                TreeItem<String> newOne =
                        new TreeItem<>("new",new ImageView(depIcon));
                newOne.getChildren().add(new TreeItem<String>());
                getTreeItem().getChildren().add(newOne);
                ClassifyVO classifyVO=new ClassifyVO();
                Set<GoodsVO> g=new HashSet<>();
                GoodsVO goodsVO=new GoodsVO();
                g.add(goodsVO);
                classifyVO.setName("分类");
                classifyVO.setGoodsSet(g);
                try {
                    helper.getClassifyBLService().addClassify(classifyVO);

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    helper.getLogBlService().addLog(userVO,"新建商品（类）",ResultMessage.Success);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
            deleteMenuItem.setOnAction((ActionEvent t) ->{
                String name=getTreeItem().getValue();
                getTreeItem().getParent().getChildren().remove(getTreeItem());
                try {
                    helper.getLogBlService().addLog(userVO,"删除商品类 "+name,ResultMessage.Success);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });

            //Root
            MenuItem newKindMenuItem=new MenuItem("new");
            addMenuRoot.getItems().add(newKindMenuItem);

            newKindMenuItem.setOnAction((ActionEvent t) -> {
                TreeItem<String> newOne =
                        new TreeItem<>("new",new ImageView(depIcon));
                newOne.getChildren().add(new TreeItem<String>());
                getTreeItem().getChildren().add(newOne);
                ClassifyVO classifyVO=new ClassifyVO();
                Set<GoodsVO> g=new HashSet<>();
                GoodsVO goodsVO=new GoodsVO();
                g.add(goodsVO);
                classifyVO.setName("分类");
                classifyVO.setGoodsSet(g);
                try {
                    helper.getClassifyBLService().addClassify(classifyVO);

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    helper.getLogBlService().addLog(userVO,"新建商品（类）",ResultMessage.Success);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });














        }

        @Override
        public void startEdit() {
            super.startEdit();
            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                    try {
                        helper.getLogBlService().addLog(userVO,"命名商品类 "+getString(),ResultMessage.Success);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    if (
                            getTreeItem().isLeaf()
                            ){
                        setContextMenu(addMenuLeaf);
                    }
                    else if(getTreeItem().getParent()==null){

                        setContextMenu(addMenuRoot);
                    }
                    else {
                        if (getTreeItem().getChildren().get(0).getValue() == null)
                            setContextMenu(addMenuSingleNode);
                        else
                            setContextMenu(addMenuNode);
                    }
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased((KeyEvent t) -> {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(textField.getText());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            });

        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public GoodsVO findByName(String name) throws RemoteException {
        helper.getGoodsBLService().getCurrentGoods();
        GoodsVO goodsVO=new GoodsVO();
        for(int i=0;i<goodsVOS.size();i++)
            if(goodsVOS.get(i).getName().equals(name))
                goodsVO=goodsVOS.get(i);
        return goodsVO;
    }
}
