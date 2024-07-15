package view;

import domain.Rank;
import dto.LottoPaperDto;
import dto.LottoResultDto;
import dto.RowDto;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printRowNumber(int rowNum) {
        System.out.println(rowNum + "개를 구매했습니다.");
    }

    public final List<Integer> REWARD = Arrays.asList(5000, 50000, 1500000, 2000000000);

    public void printPaper(LottoPaperDto lottoPaper) {
        List<RowDto> rows = lottoPaper.getRows();
        for (int i = 0; i < lottoPaper.getRowNum(); i++) {
            System.out.println(rows.get(i).getNums().toString());
        }
        System.out.println();
    }

    public void printResult(LottoResultDto lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        Map<Rank, Integer> resultMap = lottoResult.getResultMap();
        for (Rank rank : Rank.values()) {
            printByRank(rank, resultMap);
        }
    }

    public void printByRank(Rank rank, Map<Rank, Integer> resultMap){
        if(rank == Rank.MISS){
            return;
        }
        System.out.printf("%d개 일치", rank.getMatchCount());
        if(rank == Rank.SECOND){
            System.out.printf(", 보너스 볼 일치(%d원) - %d개\n", rank.getRewardMoney(), resultMap.get(rank));
            return;
        }
        System.out.printf(" (%d원)- %d개\n", rank.getRewardMoney(), resultMap.get(rank));
    }

    public void printRewardRate(LottoResultDto lottoResultDto) {
        System.out.println("총 수익률은 " + lottoResultDto.getRewardRate() + "입니다.");
    }
}
