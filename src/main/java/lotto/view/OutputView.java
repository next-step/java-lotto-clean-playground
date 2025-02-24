package lotto.view;

import java.util.Map;

import lotto.global.Rank;
import lotto.model.Lotto;
import lotto.model.LottoResult;
import lotto.model.Lottos;

public class OutputView {
    public void printAskInputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printAskInputCustom() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void printAskInputLottoNumbers() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public void printLottosHistory(final Lottos lottos) {
        printLottoSize(lottos.getCustomSize(), lottos.size() - lottos.getCustomSize());
        lottos.getLottos()
            .forEach(this::printLottoNumbers);
    }

    public void printAskInputWinNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printAskInputBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
    }

    public void printLottoResult(final LottoResult result) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        printResult(result);
    }

    private void printLottoSize(final int customSize, final int autoSize) {
        System.out.printf("%n수동으로 %s장, 자동으로 %s개를 구매했습니다.%n", customSize, autoSize);
    }

    private void printLottoNumbers(final Lotto lotto) {
        System.out.println(lotto);
    }

    private void printResult(final LottoResult lottoResult) {
        final Map<Rank, Integer> result = lottoResult.getResult();
        for (Rank rank : Rank.valuesWithoutUnLuck()) {
            System.out.printf("%d개 일치 (%d원)- %d개%n", rank.getMatchCount(), rank.getReward(), result.get(rank));
        }

        System.out.printf("총 수익률을 %.2f입니다", lottoResult.getRateOfReturn());
    }
}
