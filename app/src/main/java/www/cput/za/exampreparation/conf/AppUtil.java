package www.cput.za.exampreparation.conf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Game330 on 2016-04-27.
 */
public class AppUtil {

    public static Date getDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date value = null;
        try {
            value = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }


    public static String getBaserURI(){
        return "localhost";
    }


}
