package view;

import java.util.*;
import domain.LottoGame;

public class Output {

    public static void ManualAndAutoNumOutput(int manual, int auto){
        System.out.println();
        System.out.println("수동으로 " + manual + "장, 자동으로 " + auto + "개를 구매했습니다.");
    }

    public static void LottoNumOutput(List<String> Lotto){
        System.out.print("[");
        for(int i = 0; i < Lotto.size(); i++){
            System.out.print(Lotto.get(i));
            if(i != Lotto.size() - 1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void ResultOfLotto(List<List<String>> LottoList, List<String> LastWeekLotto, String BonusBall, int moneyInput){
        int result3 = 0, result4 = 0, result5 = 0, result6 = 0, result5Bonus = 0;
        int matchNum = 0, matchBonus = 0;
        int resultMoney = 0;
        double rate = 0;

        for (List<String> Lotto : LottoList) {
            matchNum = LottoGame.matchNum(Lotto, LastWeekLotto);
            matchBonus = LottoGame.matchBonus(Lotto, BonusBall);
            if (matchNum == 3) {
                result3++;
                resultMoney += 5000;
            }
            if (matchNum == 4) {
                result4++;
                resultMoney += 50000;
            }
            if (matchNum == 5 && matchBonus == 0) {
                result5++;
                resultMoney += 1500000;
            }
            if (matchNum == 5 && matchBonus == 1) {
                result5Bonus++;
                resultMoney += 30000000;
            }
            if (matchNum == 6) {
                result6++;
                resultMoney += 2000000000;
            }
        }

        System.out.println("\n당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + result3 + "개");
        System.out.println("4개 일치 (50000원)- " + result4 + "개");
        System.out.println("5개 일치 (1500000원)- " + result5 + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000)원 - " + result5Bonus + "개");
        System.out.println("6개 일치 (2000000000원)-" + result6 + "개");
        rate = (double) resultMoney / moneyInput;
        System.out.println("총 수익률은 " + String.format("%.2f", rate) + "입니다.");
    }
}
