package view;


import model.Lotto;
import model.Rating;
import model.RatingInfo;

import java.util.List;

public class OutView {

    public static void purchaseRecord(final List<Lotto> haveLottos, final int directCount, final int autoCount) {

        String str = String.format('\n' + "수동으로 %d장, 자동으로 %d장을 구매했습니다.", directCount, autoCount);
        System.out.println(str);

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

    public static void statisticInfo(RatingInfo ratingInfo, double rateToReturn) {

        System.out.println('\n' + "당첨 통계");
        System.out.println("---------");

        for (Rating rating : Rating.values()) {

            if (rating.getReward() == 0) continue;
            System.out.println(findBonusOrNot(ratingInfo, rating));
        }

        System.out.print(String.format("총 수익률은 %.02f입니다.", rateToReturn));
    }

    private static String findBonusOrNot(RatingInfo ratingInfo, Rating rating) {

        if (rating.getReward() == 30000000) {

            return String.format("%d개 일치, 보너스 볼 일치(%d원)- %d개",
                    rating.getMatchCount(), rating.getReward(), ratingInfo.getCount(rating));
        }

        return String.format("%d개 일치, 보너스 볼 일치(%d원)- %d개"
                , rating.getMatchCount(), rating.getReward(), ratingInfo.getCount(rating));
    }
}
