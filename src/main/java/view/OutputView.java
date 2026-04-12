package view;

import domain.LottoTicket;
import domain.TicketBundle;

public class OutputView {

    public void printLottoList(TicketBundle ticketBundle){
        System.out.println( ticketBundle.getTicketCount() + "개를 구매했습니다.");

        for (LottoTicket lottoTicket : ticketBundle) {
            System.out.println(lottoTicket.getTicketNumbers().toString());
        }
    }

    public void printResult(int first, int second, int third, int fourth, int fifth,double revenue) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + fifth +"개");
        System.out.println("4개 일치 (50000원)- " + fourth +"개");
        System.out.println("5개 일치 (1500000원)- " + third +"개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원)-  " + second +"개");
        System.out.println("6개 일치 (2000000000원)- " + first +"개");
        System.out.println("총 수익률은 " + revenue + "입니다.");

    }
}
