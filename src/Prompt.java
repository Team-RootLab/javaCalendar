import java.text.ParseException;
import java.util.Scanner;

public class Prompt {

    private final static String PROMPT = "> ";

    public void printMenu(){
        System.out.println("+----------------------+");
        System.out.println("| 1. 일정 등록");
        System.out.println("| 2. 일정 검색");
        System.out.println("| 3. 달력 보기");
        System.out.println("| h. 도움말 q. 종료");
        System.out.println("+----------------------+");
        System.out.println ("(1, 2, 3, h, q)");
    }

    public void showCalendar(Scanner scanner, Calendar cal){
        System.out.println("년도를 입력하세요. (exit: -1)");
        System.out.print(PROMPT);
        int year = scanner.nextInt();
        if(year == -1){
            System.out.println("종료합니다.");
            return;
        }
        System.out.println("달을 입력하세요. (exit: -1)");
        System.out.print(PROMPT);
        int month = scanner.nextInt();
        if(month == -1){
            System.out.println("종료합니다.");
            return;
        }
        if(month > 12 || month < 1) {
            System.out.println("달은 1부터 12까지의 숫자여야 합니다.");
            showCalendar(scanner, cal);
            return;
        }
        int firstDay = cal.getFirstDay(year, month, 1);
        // 달력 출력
        cal.printCalendar(year, month, firstDay);
    }

    private void promptRegister(Scanner scanner, Calendar cal) {
        System.out.println("[일정 등록]");
        System.out.println("일정을 저장할 날짜를 입력하세요. (yyyy-MM-dd)");
        String strDate = scanner.next();
        System.out.println("일정을 입력하세요.");
        scanner.nextLine();
        String schedule = scanner.nextLine();
        // System.out.printf("%s %s\n", strDate, schedule);
        cal.registerSchedule(strDate, schedule);
    }

    private void promptSearch(Scanner scanner, Calendar cal) throws ParseException {
        System.out.println("[일정 검색]");
        System.out.println("일정을 검색할 날짜를 입력하세요. (yyyy-MM-dd)");
        String strDate = scanner.next();
        PlanItem schedule = cal.searchSchedule(strDate);
        if(schedule == null){
            System.out.println("등록되지 않은 일정입니다.");
            return;
        }
        System.out.printf("%s의 일정은 %s입니다.\n", strDate , schedule.getDetail());
    }

    public void runPrompt() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Calendar cal = new Calendar();
        label:
        while (true){
            printMenu();
            String seleted = scanner.next();
            switch (seleted) {
                case "1":
                    promptRegister(scanner, cal);
                    break;
                case "2":
                    promptSearch(scanner, cal);
                    break;
                case "3":
                    showCalendar(scanner, cal);
                    break;
                case "h":
                    printMenu();
                    break;
                case "q":
                    break label;
                default:
                    System.out.println("잘못된 입력입니다. 다시입력하세요.");
            }
        }
        scanner.close();
    }
}
