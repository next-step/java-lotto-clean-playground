package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
=======
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final int MINIMUM_AMOUNT = 1000;

    public static void main(String[] args) {

        int ticketCount = buyLotto();
        List<InputLottoNumber> tickets = createTickets(ticketCount);
        WinningNumber winning = WinningNumber.inputWinningNumbers();
        LottoStatus status = checkResult(tickets, winning);
        printResults(status, ticketCount);
    }

    private static int buyLotto() {
        Money money = new Money(inputMoney());
        int ticketCount = money.countTickets(MINIMUM_AMOUNT);
        System.out.println(ticketCount + "개를 구매했습니다.");
        return ticketCount;
    }

    private static List<InputLottoNumber> createTickets(int count) {
        List<InputLottoNumber> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> lottoParts = LottoManage.pullOutNumbers();
            LottoManage.shuffleNumbers(lottoParts);
            List<Integer> picked = LottoManage.pickupLottoNumbers(lottoParts);
            List<LottoNumber> wrapped = picked.stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toList());

            tickets.add(InputLottoNumber.of(wrapped));
        }
        return tickets;
    }

    private static LottoStatus checkResult(
            List<InputLottoNumber> tickets,
            WinningNumber winning) {
        LottoStatus status = new LottoStatus();
        tickets.forEach(ticket -> {
            System.out.println(ticket);
            status.record(MatchResult.of(ticket.countMatching(winning)));
        });
        return status;
    }

    private static void printResults(LottoStatus status, int ticketCount) {
        System.out.println("\n당첨 통계\n---------");
        status.print();
        Money totalCost = new Money(ticketCount * MINIMUM_AMOUNT);
        Money totalPayout = status.getTotalPrizeAmount();
        double returnMoney = (double) totalPayout.getAmount() / totalCost.getAmount();
        System.out.printf("총 수익률: %.2f 입니다.%n", returnMoney);
=======
        int money = inputMoney();
        int count = money / MINIMUM_AMOUNT;
        for (int i = 0; i < count; i++) {
            List<Integer> lottoNumbers = LottoManage.pullOutNumbers();
            LottoManage.shuffleNumbers(lottoNumbers);
            List<Integer> ticket = LottoManage.pickupLottoNumbers(lottoNumbers);
            System.out.println(ticket);
            System.out.println("내가 찾고 싶은 숫자" + ticket.contains(14));
        }

    }

    private static int inputMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("구입 금액 입력(금액은 1000원 이상 입력해주세요): ");
        int money = scanner.nextInt();

=======

        while (money < MINIMUM_AMOUNT) {
            System.out.println("구매에 필요한 금액은 1000원 이상입니다 다시 입력해주세요");
            money = scanner.nextInt();
        }
        return money;
    }
}


=======

