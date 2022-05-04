/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.system.out.clientchatgui.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateUtils {
    
    public static final String Formateryyyymmdd_HHMMSS = "yyyy-mm-dd hh:mm:ss";
    
    
    /*
    *Formaterr Date dd-MM-yyy
    */
    public static String getDateNow(String formater){
            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat(formater);  
            String strDate = dateFormat.format(date);
            return strDate;
    }

    
}
