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
        LottoNumber bonusBall = input.readBonusBall();

        WinningNumbers win = new WinningNumbers(winningNumbers, bonusBall);

        for (Lotto lotto : purchased) {
            Rank rank = lotto.match(win);
            result.record(rank);
        }

        output.printResultHeader();
        result.print(output);
        output.printEarningRate(result.calculateEarningRate(ticketCount * 1000));
    }
}
