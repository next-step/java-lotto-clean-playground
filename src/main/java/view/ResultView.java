package view;

import model.Constant;
import model.Lotto;
import model.LottoPrize;

import java.util.List;
import java.util.Map;

public class ResultView {

    public static void printLottos(int manualCount, List<Lotto> lottos) {

        System.out.println(Constant.LOTTO_BUY_RESULT_1 + manualCount + Constant.LOTTO_BUY_RESULT_2 + Constant.BLANK + (lottos.size() - manualCount) + Constant.LOTTO_BUY_RESULT_3);
        lottos.stream().forEach(lotto -> System.out.println(lotto));
        System.out.println(Constant.BLANK);
    }


    public static void printStatics(Map<LottoPrize, Integer> winningStates) {
        System.out.println(Constant.BLANK);
        System.out.println(Constant.WIN_RESULT);
        System.out.println(Constant.LINES);

        winningStates.forEach((prize, count) -> {
            String message = prize.getMatchCount() + Constant.STATICS_RESULT_1;
            if (prize.hasBonus()) {
                message += Constant.SEPARATOR + Constant.STATICS_RESULT_2;
            }
            message += Constant.OPEN_PARENTHESES;
            message += prize.getPrizeAmount() + Constant.STATICS_RESULT_3 + Constant.CLOSE_PARENTHESES + Constant.DASH + Constant.BLANK + count + Constant.STATICS_RESULT_4;
            System.out.println(message);
        });
    }


    public static void printProfit(double profit) {
        System.out.println(Constant.PROFIT_RESULT_1 + String.format("%.2f", profit) + Constant.PROFIT_RESULT_2);
    }


}
