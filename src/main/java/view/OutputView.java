package view;

import java.util.List;

public class OutputView {

    private static final int START_STATISTICS_NUMBER = 3;
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

    public void printLottoStatistics() {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
    }

    public void printLottoRanker(List<Integer> lottoRank, List<Integer> lottoPrice) {
        for (int i = START_STATISTICS_NUMBER; i < lottoRank.size() + START_STATISTICS_NUMBER; i++) {
            System.out.println(i + "개 일치 (" + lottoPrice.get(i - START_STATISTICS_NUMBER) + "원)- " + lottoRank.get(i - START_STATISTICS_NUMBER) + "개");
        }
    }

    public void printRateOfReturn(double rateOfReturn) {
        System.out.println("총 수익률은 " + rateOfReturn + "입니다.");
    }
}
