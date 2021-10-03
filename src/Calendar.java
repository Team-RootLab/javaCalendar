import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

public class Calendar {
    static final private int[] MaxDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static final private int[] LeafMaxDays = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private HashMap<Date, PlanItem> planMap;

    public Calendar(){
        planMap = new HashMap<Date, PlanItem>();
    }

    public PlanItem searchSchedule(String strDate) {
        Date date = PlanItem.getDateFromString(strDate);
        PlanItem schedule = planMap.get(date);
        return schedule;
    }

    public void registerSchedule(String strDate, String schedule) {
        // TODO: 잘못된 입력일 경우를 대비한 예외처리 필요
        PlanItem item = new PlanItem(strDate, schedule);
        planMap.put(item.getDate(), item);
        System.out.println("일정을 저장했습니다.");
    }

    public boolean isLeapYear(int year){
        if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)){
            return true;
        }
        return false;
    }

    public int getFirstDay(int year, int month, int day) {
        int stdYear = 1970; // 기준 년
        int stdDay = 4; // 기준 요일, SU:0 ~ SA:6, 1970/1/1/TH: 3
        int count = 0;
        for (int i = stdYear; i < year; i++){
            count += isLeapYear(i) ? 366 : 365;
        }
        for (int i=1; i<month; i++){
            count += getMaxDaysOfMonth(year, i);
        }
        count += day;
        int firstDay = (stdDay + count - 1) % 7;
        return firstDay;
    }

    public int getMaxDaysOfMonth(int year, int month){
        if(isLeapYear(year)){
            return LeafMaxDays[month];
        }
        return MaxDays[month];
    }

    public void printCalendar(int year, int month, int firstDay){
        System.out.printf("<< %4d년 %d월 달력 >>\n", year, month);
        System.out.println("SU MO TU WE TH FR SA");
        System.out.println("--------------------");
        // 공백을 우선 출력
        for(int i=0; i<firstDay; i++){
            System.out.print("   ");
        }
        int maxDay = getMaxDaysOfMonth(year, month);
        for(int i=1; i<=maxDay; i++){
            System.out.printf("%2d ", i);
            if((i + firstDay) % 7 == 0){
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws ParseException {
        Prompt p = new Prompt();
        p.runPrompt();
    }
}