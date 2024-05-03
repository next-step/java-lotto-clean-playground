package view;

import domain.Lotto;
import domain.LottoMaker;
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
        System.out.println(lotto.getLottoNumber());
    }

    public void printLottoQuantity(LottoMaker lottoMaker) {
        System.out.println(lottoMaker.getLottoQuantity() + "개를 구매했습니다.");
    }

    public void printPrizeResult(WinDiscriminator discriminator, int budget) {
        Prize prize = discriminator.getPrize();
        String fourthPrize = "3개 일치" + " (" + prize.getFourthPrize() + "원)- " + prize.getFourthPrizeQuantity() + "개";
        String thirdPrize = "4개 일치" + " (" + prize.getThirdPrize() + "원)- " + prize.getThirdPrizeQuantity() + "개";
        String secondPrize = "5개 일치" + " (" + prize.getSecondPrize() + "원)- " + prize.getSecondPrizeQuantity() + "개";
        String bonusPrize = "5개 일치, 보너스 볼 일치" + " (" + prize.getBonusPrize() + "원)- " + prize.getBonusPrizeQuantity() + "개";
        String firstPrize = "6개 일치" + " (" + prize.getFirstPrize() + "원)- " + prize.getFirstPrizeQuantity() + "개";

        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(fourthPrize);
        System.out.println(thirdPrize);
        System.out.println(secondPrize);
        System.out.println(bonusPrize);
        System.out.println(firstPrize);
        System.out.println("총 수익률은 " + prize.getTotalPrize() / budget + "입니다.");
    }
}
