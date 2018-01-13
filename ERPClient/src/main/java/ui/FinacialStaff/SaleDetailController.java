package ui.FinacialStaff;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import rmi.RemoteHelper;
import ui.Main;
import ui.util.AlertUtil;
import vo.GoodsSaleVO;
import vo.SaleVO;
import vo.UserVO;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.time.format.DateTimeFormatter;

public class SaleDetailController implements Initializable  {
    private Main main;
    private UserVO userVO;
    RemoteHelper helper = RemoteHelper.getInstance();
    @FXML
    Label label_name;
    @FXML
    Label label_number;
    @FXML
    DatePicker from;
    @FXML
    DatePicker to;
    @FXML
    TextField tf_goodName;
    @FXML
    TextField tf_client;
    @FXML
    TextField tf_operator;
    public void initialize(URL location, ResourceBundle resources) {
        //不需要具体实现
    }
    public SaleDetailController(){

    }//一个构造函数

    @FXML
    public void setMain(Main main, UserVO uservo) {
        this.main=main;
        this.userVO=uservo;
        label_name.setText(uservo.getName());

        //初始化界面

        //先把格式改了
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        from.setConverter(converter);
        to.setConverter(converter);
        from.setValue(LocalDate.now());
        to.setValue(LocalDate.now());//将datepicker中的默认时间为当前时间
        tf_goodName.setText("");
        tf_client.setText("");
        tf_operator.setText("");
    }

    @FXML
    public void gotoSaleDetailResult() throws Exception{
        String from_date=from.getValue().toString();
        String to_date=to.getValue().toString();

        String goodName=tf_goodName.getText();
        String operator=tf_operator.getText();
        String client=tf_client.getText();

        ArrayList<SaleVO> list= new ArrayList<>();
        ArrayList<GoodsSaleVO> saleVOList = new ArrayList<>();
        list = helper.getSaleBLService().getSale(from_date, to_date, operator, client);
        System.out.println(list.size());
        System.out.println(goodName.equals(""));
        if(goodName.equals( "")){
            int count = 0;
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getSaleSet().size() != 0) {
                    count++;
                    for (GoodsSaleVO goodsSaleVO : list.get(i).getSaleSet()) {
                        saleVOList.add(goodsSaleVO);
                    }
                }
            }
            System.out.println(count);
        }
        else{
            for(int i = 0; i < list.size(); i++){
                for(GoodsSaleVO goodsSaleVO: list.get(i).getSaleSet()){
                    if(goodsSaleVO.getVo().getName().equals(goodName)){
                        goodsSaleVO.setDate(list.get(i).getDate());
                    saleVOList.add(goodsSaleVO);
                }
                }
            }
        }
        System.out.println(list.size());
        if(saleVOList.size() == 0)
            AlertUtil.showErrorAlert("未找到相应的销售明细！");
        else {
            main.gotoSaleDetailResult(userVO, saleVOList);

        }

        }

    @FXML
    public void logout(){
        main.backToMain();
    }

    @FXML
    public void gotoFinacialStaffMain(){
        if(userVO.getType().equals("总经理"))
            main.gotoManagerMain(userVO);
        else
            main.gotoFinacialStaffMain(userVO);
    }



}
