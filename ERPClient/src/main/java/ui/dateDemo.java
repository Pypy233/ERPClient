package ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateDemo {
    public static String getDate(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String s = f.format(date);
        return s;
    }
    public static void main(String []args){
        System.out.println(getDate());
    }
}
