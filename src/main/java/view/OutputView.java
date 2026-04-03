package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoResult;
import domain.LottoTicket;
import java.util.List;

public class OutputView {
    public void showLottoResults(LottoResult result, Double profitRate) {
        showLottoStatistics(result);
        showProfitRate(profitRate);
    }

    public void showLottoStatistics(LottoResult result) {
        System.out.println("\n당첨 통계\n---------");
        System.out.println("3개 일치 (5000원)- " + result.threeCorrectCount() + "개");
        System.out.println("4개 일치 (50000원)- " + result.fourCorrectCount() + "개");
        System.out.println("5개 일치 (1500000원)- " + result.fiveCorrectCount() + "개");
        System.out.println("6개 일치 (2000000000원)- " + result.sixCorrectCount() + "개");
    }

    public void showProfitRate(Double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.", profitRate);
        if (profitRate > 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 이득이라는 의미임)");
            return;
        }
        if (profitRate == 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 본전이라는 의미임)");
            return;
        }
        System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }

    public void showLottoTickets(Lotto lotto) {
        System.out.println();
        System.out.println(lotto.getNumberOfTickets() + "개를 구매했습니다.");
        for (LottoTicket lottoTicket : lotto.getTickets()) {
            showLottoTicket(lottoTicket);
        }
        System.out.println();
    }

    private void showLottoTicket(LottoTicket lottoTicket) {
        List<LottoNumber> ticket = lottoTicket.getLottoNumbers();
        System.out.println(ticket);
    }
}
