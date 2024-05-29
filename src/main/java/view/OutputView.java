package view;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final int RESET_NUMBER = 0;
    private static final int DEVIDE_LOTTO_COUNT_NUMBER = 1000;

    public void printGetLottoMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printLottoCount(int lottoCount) {
        System.out.println("\n" + lottoCount / DEVIDE_LOTTO_COUNT_NUMBER + "개를 구매했습니다.");
    }

    public void printLotto(List<Integer> lottoNumber) {
        System.out.println(lottoNumber);
    }

    public void LastWeekLottoNumber() {
        System.out.println("\n" + "지난 주 당첨 번호를 입력해 주세요.");
    }

    public void inputBonusBall() {
        System.out.println("\n보너스 볼을 입력해주세요.");
    }

    public void printLottoStatistics() {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
    }

    public void printLottoRank(List<String> lottoRankMessage, Map<String, Integer> lottoRank, List<String> sameLottoNumbers) {
        int count = RESET_NUMBER;
        for (String sameLottoNumber : sameLottoNumbers) {
            System.out.println(lottoRankMessage.get(count) + lottoRank.get(sameLottoNumber) + "개");
            count++;
        }
    }

    public void printRateOfReturn(double rateOfReturn) {
        System.out.println("총 수익률은 " + rateOfReturn + "입니다.");
    }
}
