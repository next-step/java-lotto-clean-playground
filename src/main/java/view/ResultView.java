package view;
import java.util.List;

public class ResultView {

    public static void printOrderTickets(int ticketCount){
        System.out.println();
        System.out.println(ticketCount + "개를 구매했습니다.");
    }

    public static void printTickets(int ticketCount ,List<String> formattedTickets){
        for (String ticket : formattedTickets) {
            System.out.println(ticket);
        }
    }
}
