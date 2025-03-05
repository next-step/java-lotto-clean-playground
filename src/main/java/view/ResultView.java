package view;

import dto.LottoNumberResponseDto;

import java.util.List;

public class ResultView {

    public static void printLottoResult(List<LottoNumberResponseDto> tickets) {
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (LottoNumberResponseDto ticket : tickets) {
            System.out.println(ticket.getSortedLottoNumbers());
        }
    }

}
