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

        output.printPurchaseMessage(ticketCount);

        List<Lotto> purchased = machine.generateLottos(ticketCount);
        output.printLottos(purchased);

        List<LottoNumber> winningNumbers = input.readWinningNumbers();

        WinningNumbers win = new WinningNumbers(winningNumbers);

        for (Lotto lotto : purchased) {
            int match = lotto.countMatch(win);
            result.record(match);
        }

        output.printResultHeader();
        result.print(output);
        output.printEarningRate(result.calculateEarningRate(ticketCount * 1000));
    }
}
