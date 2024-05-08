package view;

import domain.Lotto;
import domain.Prize;
import domain.WinDiscriminator;

import java.util.List;

public class OutputView {

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
    }

    private void printLotto(Lotto lotto) {
        System.out.println(lotto.lottoNumber());
    }

    public void printLottoQuantity(int ManualLottoQuantity, int AutoLottoQuantity) {
        System.out.println("수동으로 " + ManualLottoQuantity + "장, 자동으로" + AutoLottoQuantity + "개를 구매했습니다.");
    }

    public void printPrizeResult(WinDiscriminator discriminator, int budget) {
        String fourthPrize = "3개 일치" + " (" + Prize._4TH_PRIZE.getPrize() + "원)- " + Prize._4TH_PRIZE.getQuantity() + "개";
        String thirdPrize = "4개 일치" + " (" + Prize._3TH_PRIZE.getPrize() + "원)- " + Prize._3TH_PRIZE.getQuantity() + "개";
        String secondPrize = "5개 일치" + " (" + Prize._2ND_PRIZE.getPrize() + "원)- " + Prize._2ND_PRIZE.getQuantity() + "개";
        String bonusPrize = "5개 일치, 보너스 볼 일치" + " (" + Prize.BONUS_PRIZE.getPrize() + "원)- " + Prize.BONUS_PRIZE.getQuantity() + "개";
        String firstPrize = "6개 일치" + " (" + Prize._1ST_PRIZE.getPrize() + "원)- " + Prize._1ST_PRIZE.getQuantity() + "개";

        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(fourthPrize);
        System.out.println(thirdPrize);
        System.out.println(secondPrize);
        System.out.println(bonusPrize);
        System.out.println(firstPrize);
        System.out.println("총 수익률은 " + Prize.getTotalPrize() / budget + "입니다.");
    }
}
