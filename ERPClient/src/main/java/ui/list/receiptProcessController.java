package ui.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rmi.RemoteHelper;
import ui.Main;
import ui.model.ReceiptListModel;
import ui.util.AlertUtil;
import vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class receiptProcessController {
    RemoteHelper helper=RemoteHelper.getInstance();
    private Main lMain;
    private UserVO userVO;
    ArrayList<StockVO> stockVOS;
    ArrayList<SaleVO> saleVOS;
    ArrayList<StockReturnVO> stockReturnVOS;
    ArrayList<SaleReturnVO> saleReturnVOS;

    //返回
    @FXML
    public Button exit;

    //
    @FXML
    public TableView receiptList;
    //
    @FXML
    public TableColumn receiptIDTC;
    //
    @FXML
    public TableColumn kindTC;
    //
    @FXML
    public TableColumn processTC;
    //
    @FXML
    public TableColumn dateTC;
    //
    @FXML
    public TableColumn clientTC;
    //
    @FXML
    public TableColumn operatorTC;






    public void setMain(UserVO userVO,Main lMain) throws RemoteException {
        this.lMain=lMain;
        this.userVO=userVO;
//////////////////////////////////////////...............................................
        stockVOS=new ArrayList<>();
        saleVOS=new ArrayList<>();
        stockReturnVOS=new ArrayList<>();
        saleReturnVOS=new ArrayList<>();

        stockVOS=helper.getStockBLService().getStockProcessList();
        saleVOS=helper.getSaleBLService().getSaleProcessList();
        stockReturnVOS=helper.getStockReturnBLService().getStockReturnProcessList();
        saleReturnVOS=helper.getSaleReturnBLService().getSaleReturnProcessList();


        ObservableList<ReceiptListModel> data =
                FXCollections.observableArrayList(
                );
        int n=0;

        String name=userVO.getName();
        n=stockVOS.size();
        for(int i=0;i<n;i++)
            //if(stockVOS.get(i).getOperator().equals(name))
            data.add(new ReceiptListModel(stockVOS.get(i)));
        n=stockReturnVOS.size();
        for(int i=0;i<n;i++)
            //if(stockReturnVOS.get(i).getOperator().equals(name))
            data.add(new ReceiptListModel(stockReturnVOS.get(i)));
        n=saleVOS.size();
        for(int i=0;i<n;i++)
            //if(saleVOS.get(i).getOperator().equals(name))
            data.add(new ReceiptListModel(saleVOS.get(i)));
        n=saleReturnVOS.size();

            for (int i = 0; i < n; i++)
                //if (saleReturnVOS.get(i).getOperator().equals(name))
                    data.add(new ReceiptListModel(saleReturnVOS.get(i)));

        //遍历输入
        receiptIDTC.setCellValueFactory(new PropertyValueFactory<>("number"));
        kindTC.setCellValueFactory(new PropertyValueFactory<>("kind"));
        processTC.setCellValueFactory(new PropertyValueFactory<>("process"));
        dateTC.setCellValueFactory(new PropertyValueFactory<>("date"));
        clientTC.setCellValueFactory(new PropertyValueFactory<>("client"));
        operatorTC.setCellValueFactory(new PropertyValueFactory<>("operator"));
        receiptList.setItems(data);
    }
}
