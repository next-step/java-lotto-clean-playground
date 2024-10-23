package view;

import java.util.*;
import domain.LottoGame;
import domain.Rank;

public class Output {

    public static void manualAndAutoNumOutput(int manual, int auto){
        System.out.println();
        System.out.println("수동으로 " + manual + "장, 자동으로 " + auto + "개를 구매했습니다.");
    }

    public static void lottoNumOutput(List<String> Lotto){
        System.out.print("[");
        for(int i = 0; i < Lotto.size(); i++){
            System.out.print(Lotto.get(i));
            if(i != Lotto.size() - 1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void resultOfLotto(List<List<String>> LottoList, List<String> LastWeekLotto, String BonusBall, int moneyInput) {
        Map<Rank, Integer> resultMap = new HashMap<>();
        int resultMoney = 0;

        for (Rank rank : Rank.values()) {
            resultMap.put(rank, 0);
        }

        for (List<String> Lotto : LottoList) {
            Rank rank = LottoGame.getRank(Lotto, LastWeekLotto, BonusBall);
            resultMap.put(rank, resultMap.get(rank) + 1);
            resultMoney += rank.getPrize();
        }

        System.out.println("\n당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원) - " + resultMap.get(Rank.THREE) + "개");
        System.out.println("4개 일치 (50000원) - " + resultMap.get(Rank.FOUR) + "개");
        System.out.println("5개 일치 (1500000원) - " + resultMap.get(Rank.FIVE) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30000000원) - " + resultMap.get(Rank.FIVE_BONUS) + "개");
        System.out.println("6개 일치 (2000000000원) - " + resultMap.get(Rank.SIX) + "개");

        double rate = (double) resultMoney / moneyInput;
        System.out.println("총 수익률은 " + String.format("%.2f", rate) + "입니다.");
    }
}
