package view;

import java.util.List;

public class OutputView {

    private static final int DEVIDE_LOTTO_COUNT_NUMBER = 1000;

    public void printGetLottoMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printPassiveLottoCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void printPassiveLottoNumber() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }

    public void printLottoCount(int lottoCount, int passiveLottoNumberCount) {
        System.out.println("\n수동으로 " + passiveLottoNumberCount + "장, 자동으로 " + ((lottoCount / DEVIDE_LOTTO_COUNT_NUMBER) - passiveLottoNumberCount) + "개를 구매했습니다.");
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

    public void printLottoRank(String lottoRank) {
        System.out.println(lottoRank);
    }

    public void printRateOfReturn(double rateOfReturn) {
        System.out.println("총 수익률은 " + rateOfReturn + "입니다.");
    }
}
