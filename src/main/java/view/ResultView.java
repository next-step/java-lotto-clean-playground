package view;

import java.util.List;
import java.util.Map;

import domain.Lotto;
import domain.LottoStatus;
import domain.LottoNumber;
import domain.Winnings;

public class ResultView {

    public static void printLottoNumbers(LottoStatus lottoStatus, int manualLottoCount) {
        int autoLottoCount = lottoStatus.getLottoList().size() - manualLottoCount;
        List<Lotto> lottoList = lottoStatus.getLottoList();

        System.out.println(String.format("수동으로 %s개, 자동으로 %s개를 구매했습니다.", manualLottoCount, autoLottoCount));
        for (Lotto lotto : lottoList) {
            List<Integer> lottoNumber = lotto.getLottoNumber().stream()
                    .map(LottoNumber::getNumber)
                        .toList();
            System.out.println(lottoNumber);
        }
    }

    public static void printWinningState(Map<Winnings, Integer> result, double rateOfResult) {
        System.out.println("당첨 통계 \n--------");
        for (Winnings winnings : Winnings.values()) {
            System.out.println(String.format(getResultMessage(winnings),
                winnings.getMatchCount(),
                winnings.getWinningPrize(),
                result.get(winnings)));
        }
        System.out.println(String.format("총 수익률은 %.2f입니다.", rateOfResult));
    }

    public static String getResultMessage(Winnings winnings) {
        if (winnings == Winnings.SECOND_PLACE) {
            return "%d개 일치, 보너스 볼 일치(%d원)- %d개";
        }
        return "%d개 일치 (%d원)- %d개";
    }
}
