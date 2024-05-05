package view;

import model.Lottos;
import model.OneLotto;
import model.RankCalculator;

import java.util.ArrayList;
import java.util.List;

public class LottoOutput {

    private static String makeLottoNumberToString(OneLotto oneLotto) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");

        List<Integer> lottoNumbers = oneLotto.getLottoNumbers();
        for (Integer lottoNumber : lottoNumbers) {
            buffer.append(lottoNumber.intValue());
            buffer.append(", ");
        }
        buffer.delete(buffer.length() - 2, buffer.length() - 1);
        buffer.append("]");
        return buffer.toString();
    }

    public static void printLottoNumbers(Lottos lottos) {
        System.out.println(lottos.getBalance() +
                "개를 구매했습니다.");
        List<OneLotto> oneLottoList = lottos.getMyLottos();
        for (OneLotto myLotto : lottos.getMyLottos()) {
            System.out.println(makeLottoNumberToString(myLotto));
        }
        System.out.println();
    }

    public static void printCorrectNumbers(RankCalculator rankCalculator, Lottos lottos) {
        List<Integer> money = rankCalculator.getMoney();
        List<Integer> correctNum = rankCalculator.getCorrectNum();

        System.out.println("당첨 통계");
        System.out.println("---------");
        for (int i = 2; i < 6; i++) {
            System.out.println((i + 1) + "개 일치 (" + money.get(i) + "원)- "
                    + correctNum.get(i) + "개");
        }

        double rate = rankCalculator.rateOfReturn(lottos.getBalance());
        System.out.println("총 수익률은 " + rate + "입니다.");
    }
}
