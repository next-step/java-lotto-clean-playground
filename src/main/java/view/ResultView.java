package view;

import java.util.List;
import java.util.Map;

import domain.Lotto;
import domain.LottoList;
import domain.Winnings;

public class ResultView {

    public static void getLottoNumbers(LottoList lottoList) {
        int numberOfLotto = lottoList.getNumberOfLotto();
        List<Lotto> lottos = lottoList.getLottoList();

        System.out.println(String.format("%s개를 구매했습니다.", numberOfLotto));
        for (int i = 0; i < numberOfLotto; i++) {
            System.out.println(lottos.get(i).getLottoNumber());
        }
    }

    public static void printWinningState(LottoList lottoList, Lotto winningNumber, Map<Winnings, Integer> result, double rateOfResult) {
        System.out.println("당첨 통계 \n--------");
        for (Winnings winnings : Winnings.values()){
            System.out.println(String.format("%d개 일치 (%d원)- %d개",
                winnings.getMatchCount(),
                winnings.getWinningPrize(),
                result.get(winnings)));
        }
        System.out.println(String.format("총 수익률은 %.2f입니다.", rateOfResult));
    }
}
