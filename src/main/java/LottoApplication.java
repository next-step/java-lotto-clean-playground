import java.util.*;
import java.util.stream.Collectors;

public class LottoApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputView output = new OutputView();
        InputReader input = new InputReader(sc);
        LottoMachine machine = new LottoMachine();
        LottoResult result = new LottoResult();

        int ticketCount = input.readPurchaseAmount() / 1000;
        int manualCount =input.readManualLottoCount();
        int autoCount = ticketCount - manualCount;

        if (autoCount < 0){
            System.out.println("구입 금액보다 수동 구매 로또 수가 더 많습니다.");
            return;
        }

        List<Lotto> manualLottos = new ArrayList<>();
        if (manualCount > 0) {
            manualLottos = input.readManualLottos(manualCount);
        }

        List<Lotto> autoLottos = machine.generateLottos(autoCount);

        List<Lotto> allLottos = new ArrayList<>();
        allLottos.addAll(manualLottos);
        allLottos.addAll(autoLottos);

        output.printPurchaseMessage(manualCount, autoCount);
        output.printLottos(allLottos);

        List<LottoNumber> winningNumbers = input.readWinningNumbers();
        LottoNumber bonusBall = input.readBonusBall();

        WinningNumbers win = new WinningNumbers(winningNumbers, bonusBall);

        for (Lotto lotto : allLottos) {
            Rank rank = lotto.match(win);
            result.record(rank);
        }

        output.printResultHeader();
        result.print(output);
        output.printEarningRate(result.calculateEarningRate(ticketCount * 1000));
    }
}
