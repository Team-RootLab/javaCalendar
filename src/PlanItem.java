import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanItem {
    private Date date;
    private String detail;

    public static Date getDateFromString(String strDate){
        Date result = null;
        try {
            result = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public PlanItem(String date, String detail){
        this.date = PlanItem.getDateFromString(date);
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }

    public String getDetail() {
        return detail;
    }
}
