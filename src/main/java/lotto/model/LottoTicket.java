package lotto.model;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private List<Integer> ticket;
    private String ticketStr;
    private LottoNums lottoNumList= new LottoNums();

    public LottoTicket() {
        this.ticket = lottoNumList.createRandomList();
        changeNumListToStr();
    }

    public LottoTicket(String passivityNum) {
        List<Integer> passivityNumbers = lottoNumList.createPassivityList(passivityNum);
        this.ticket = passivityNumbers;
        changeNumListToStr();
    }

    public LottoTicket(List<Integer> ticket, String ticketStr) {
        this.ticket = ticket;
        this.ticketStr = ticketStr;
    }

    //번호 리스트를 스트링으로 바꾸기
    public void changeNumListToStr() {
        this.ticketStr = ticket.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public List<Integer> getLottoTicket() {
        return ticket;
    }

    public String getTicketStr() {
        return ticketStr;
    }
}
