package view;

import dto.LottoResultDto;
import dto.LottosDto;

public class LottoOutputView implements LottoView {

    private static final LottoOutputView lottoOutputView = new LottoOutputView();

    private LottoOutputView() {
    }

    public static LottoOutputView getInstance() {
        return lottoOutputView;
    }

    public void printLottoAmount(int manualLottoAmount, int randomLottoAmount) {
        System.out.println("수동으로 " + manualLottoAmount + "장, 자동으로 " + randomLottoAmount + "개를 구매했습니다.");
    }

    public void printLottos(LottosDto lottosDto) {
        for (String output : lottosDto.getValues()) {
            System.out.println(output);
        }

        printEmptyLine();
    }

    public void printLottoResultHeader() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printLottoResult(LottoResultDto lottoResultDto) {
        boolean bonusBallEqual = lottoResultDto.isBonusBallEqual();
        int equalCount = lottoResultDto.getEqualCount();
        int prizeAmount = lottoResultDto.getPrizeAmount();
        int lottoAmount = lottoResultDto.getLottoAmount();

        if (bonusBallEqual) {
            System.out.printf("%d개 일치, 보너스 볼 일치 (%d원)- %d개\n", equalCount, prizeAmount, lottoAmount);
            return;
        }

        System.out.printf("%d개 일치 (%d원)- %d개\n", equalCount, prizeAmount, lottoAmount);
    }

    public void printTotalProfitRate(double totalProfitRate) {
        StringBuilder output = new StringBuilder("총 수익률은 " + totalProfitRate + "입니다.");

        if (isLoss(totalProfitRate)) {
            output.append("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }

        System.out.println(output);
    }

    private boolean isLoss(double totalProfitRate) {
        return totalProfitRate < 1;
    }

}
