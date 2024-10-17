package lotto.view;

import lotto.model.TicketPrize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printTickets(String lottoTicketsStr,int NumOfPassivityTickets, int NumOfRandomTickets){
        System.out.println("수동으로 "+NumOfPassivityTickets+"장, 자동으로 "+NumOfRandomTickets+"개를 구매했습니다.");
        System.out.println(lottoTicketsStr);
    }

    public void printStatistics(){
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printResultStatistics(Map<Integer, Integer> statistics,int BONUSBALL_KEY) {
        for (Map.Entry<Integer, Integer> entry : statistics.entrySet()) {
            int matchNum = entry.getKey();
            int price = TicketPrize.decisionPrice(matchNum);
            int count = entry.getValue();
            String result = settingResultStr(matchNum, price, count,BONUSBALL_KEY);
            System.out.println(result);
        }
    }

    public String settingResultStr(int matchNum, int price, int count,int BONUSBALL_KEY) {
        String result=null;
        if (matchNum == 3 || matchNum == 4 || matchNum == 5) {
            result = matchNum + "개 일치 (" + price + "원) - " + count + "개";
        } else if (matchNum == BONUSBALL_KEY) {
            result = "5개 일치, 보너스 볼 일치(" + price + "원) - " + count + "개";
        } else if (matchNum == 6) {
            result = matchNum + "개 일치 (" + price + "원) - " + count + "개";
        }
        return result;
    }


    public void printProfit(double profitRate){
        String resultProfit = "총 수익률은 " + profitRate + "입니다.";
        if (profitRate > 1) resultProfit = resultProfit.concat("(기준이 1이기 때문에 결과적으로 이익이라는 의미임)");
        else if (profitRate < 1) resultProfit = resultProfit.concat("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        System.out.println(resultProfit);
    }
}
