package objects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 */
public class DateHelper {
   public String getDate(){
      Calendar calendar = Calendar.getInstance();
      Date date = calendar.getTime();
      SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
      String s = f.format(date);
      return s;
   }

   public boolean isSameDay(String date1, String date2){
      return date1.equals(date2);
   }
   
   public boolean isBetween(String date,String begin,String end){
	   String[] time_date=date.split("-");
	   String[] time_begin=begin.split("-");
	   String[] time_end=end.split("-");
	   for(int i=0;i<3;i++){
		   int num_date=Integer.valueOf(time_date[i]);
		   int num_begin=Integer.valueOf(time_begin[i]);
		   int num_end=Integer.valueOf(time_end[i]);
		   if(num_date>num_end)return false;
		   if(num_date<num_begin) return false;
	   }
	   return true;
   }


}
