package view;

import dto.LottoPaperDto;
import dto.LottoResultDto;
import dto.RowDto;
import java.util.Arrays;
import java.util.List;

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
        List<Integer> result = lottoResult.getResult();
        for (int i = 0; i < result.size(); i++) {
            System.out.printf("%d개 일치 (%d원)- %d개\n", i + 3, REWARD.get(i), result.get(i));
        }
    }

    public void printRewardRate(LottoResultDto lottoResultDto) {
        System.out.println("총 수익률은 " + lottoResultDto.getRewardRate() + "입니다.");
    }
}
