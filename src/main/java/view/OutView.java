package view;


import model.Lotto;
import model.LottoStatistics;

import java.util.List;

public class OutView {

    public static void purchaseRecord(final List<Lotto> haveLottos) {

        System.out.println("\n" + haveLottos.size() + "개를 구매했습니다.");

        for (int i = 0; i < haveLottos.size(); i++) {
            lottoInfo(haveLottos.get(i).getLottoNumber());
        }
    }

    public static void lottoInfo(final List<Integer> lottoNumber) {

        System.out.print("[");

        for (int i = 0; i < 5; i++) {
            System.out.print(lottoNumber.get(i) + ", ");
        }

        System.out.println(lottoNumber.get(5) + "]");
    }

    public static void statisticInfo(

            final List<Integer> matchedCount,
            final List<Double> prize,
            final double rateToReturn
    ) {

        System.out.println('\n' + "당첨 통계");
        System.out.println("---------");

        for (int i = 3; i <= 6; i++) {
            String result = String.format("%d개 일치 (%.0f원) - %d개", i, prize.get(i - 3), matchedCount.get(i));
            System.out.println(result);
        }

        System.out.print(String.format("총 수익률은 %.02f입니다.", rateToReturn));

    }
}
