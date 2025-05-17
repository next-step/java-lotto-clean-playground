import java.util.List;
import java.util.Scanner;

public class LottoApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int amount = InputReader.readPurchaseAmount(scanner);

        LottoMachine machine = new LottoMachine();
        List<List<Integer>> lottoTickets = machine.generateLottos(amount);

        System.out.println();
        System.out.println(lottoTickets.size() + "개를 구매했습니다.");
        for (List<Integer> ticket : lottoTickets) {
            System.out.println(ticket);
        }

        scanner.close();
    }
}
