package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TicketBundle implements Iterable<LottoTicket> {
    List<LottoTicket> ticketBundle = new ArrayList<>();

    @Override
    public Iterator<LottoTicket> iterator() {
        return ticketBundle.iterator();
    }

    public void add(LottoTicket ticket){
        ticketBundle.add(ticket);
    }

    public int getTotalPrice(List<Integer> winningNumbers, int bonusNumber){
        int price = 0;

        for(LottoTicket ticket : ticketBundle){
            int ticketGrade = ticket.getTicketGrade(winningNumbers, bonusNumber);
            price += ticket.getTicketPrice(ticketGrade);
        }

        return price;
    }

    public int getCountOfTicketGrade(int grade, List<Integer> winningNumbers, int bonusNumber){
        int count = 0;

        for (LottoTicket ticket : ticketBundle){
            int ticketGrade = ticket.getTicketGrade(winningNumbers, bonusNumber);
            if(ticketGrade == grade) count++;
        }

        return count;
    }

    public int getTicketCount(){
        return ticketBundle.size();
    }
}
