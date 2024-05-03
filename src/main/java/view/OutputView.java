package view;

import model.LottoService;
import model.Prize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    private final static String PRINT_HEAD="당첨 통계";
    private final static String PRINT_DIVIDER="---------";
    private final static String BUY_MESSAGE = "개를 구매했습니다.";

    public void countPrint (int count){
        System.out.println("\n" + count + BUY_MESSAGE);

    }
    public void lottoPrint(List<Integer> numbers) {
        System.out.println(numbers);
    }

    public void printWinningResult(Map<Integer,Integer> stats, double revenue, Prize prize){
        System.out.println();
        System.out.println(PRINT_HEAD);
        System.out.println(PRINT_DIVIDER);
        for(int i=3;i<=6;i++){
            int count=stats.getOrDefault(i,0);
            int realPrize=prize.getPrize(i);
            System.out.printf("%d개 일치 (%d원)- %d개\n", i, realPrize, count);
        }
        System.out.printf("총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 %s라는 의미임)\n", revenue, revenue >= 1 ? "이득이" : "손해");
    }
}
