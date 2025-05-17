package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoRank;
import domain.LottoResult;
import domain.Lottos;

public class OutputView {
    public void printPurchasePriceInputMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printPurchasedLottos(Lottos lottos) {
        System.out.println("\n" + lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers().getLottoNumbers().stream()
                    .map(LottoNumber::getNumber)
                    .toList()
            );
        }
    }

    public void printWinningLottoInputMessage() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumberInputMessage(){
        System.out.println("\n보너스 볼을 입력해 주세요.");
    }

    public void printLottoResult(LottoResult lottoResult) {
        System.out.println("\n당첨 통계\n---------");
        for (LottoRank rank : LottoRank.values()) {
            System.out.println(rank.getMessage() + " - " + lottoResult.getLottoResultMap().get(rank) + "개");
        }
        System.out.printf("총 수익률은 %.2f입니다.", lottoResult.getROI());
    }

    public void printManualLottoCountInputMessage() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void printManualLottosInputMessage() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }
}
