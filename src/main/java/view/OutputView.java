package view;

import model.LottoService;
import model.Match;
import model.Prize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    private final static String PRINT_HEAD="당첨 통계";
    private final static String PRINT_DIVIDER="---------";
    private final static String BUY_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";

    public void countPrint (int manualNum,int count){
        System.out.printf("\n"+BUY_MESSAGE, manualNum, count);
    }

    public void printWinningResult(Map<Integer,Integer> stats, double revenue, Prize prize){
        System.out.println();
        System.out.println(PRINT_HEAD);
        System.out.println(PRINT_DIVIDER);
        printStatsResult(stats,prize);
        System.out.printf("총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 %s라는 의미임)\n", revenue, revenue >= 1 ? "이득이" : "손해");
    }

    public void printStatsResult(Map<Integer,Integer> stats,Prize prize){

        for(int i=3;i<=6;i++){
            int count=stats.getOrDefault(i,0);
            int realPrize=prize.getPrize(i);
            System.out.printf("%d개 일치 (%d원)- %d개\n", i, realPrize, count);
            if(i==5)
                print2ndResult(stats,prize);
        }
    }

    public void print2ndResult(Map<Integer,Integer> stats,Prize prize){
        int count= stats.getOrDefault(7,0);
        int realPrize=prize.getPrize(7);
        System.out.printf("5개 일치, 보너스 볼 일치 (%d원)- %d개\n",realPrize,count);
    }

}