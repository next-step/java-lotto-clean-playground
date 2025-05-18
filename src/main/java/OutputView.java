import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void printPurchaseMessage(int manualCount, int autoCount) {
        System.out.println("\n수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            List<Integer> numbers = lotto.getNumbers().stream()
                    .map(LottoNumber::getValue)
                    .collect(Collectors.toList());
            System.out.println(numbers);
        }
    }

    public void printInputWinningNumbersPrompt() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printResultHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printResultLine(Rank rank, int count) {
        if (rank == Rank.SECOND) {
            System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개%n",
                    rank.getMatchCount(), rank.getPrize(), count);
        } else {
            System.out.printf("%d개 일치 (%d원)- %d개%n",
                    rank.getMatchCount(), rank.getPrize(), count);
        }
    }

    public void printEarningRate(double rate) {
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)%n", rate);
    }
}
