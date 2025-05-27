package lotto;

import lotto.domain.*;
import lotto.input.PassivityLottoInput;
import lotto.input.PassivityNumberInput;
import lotto.input.WinningNumberInput;
import lotto.parser.WinningNumberParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Application {
    private static final int MINIMUM_AMOUNT = 1000;

    public static void main(String[] args) {
        new Application().run();
    }

    // 테스트 확인
    private void run() {
        int ticketCount = buyLotto();
        List<InputLottoNumber> ticket = prepareTicket(ticketCount);
        WinningNumber winningNumber = new WinningNumberInput().inputWinningNumber();
        LottoStatus lottoStatus = checkResult(ticket, winningNumber);
        lottoStatus.print(ticketCount, MINIMUM_AMOUNT);
    }

    private List<InputLottoNumber> prepareTicket(int ticketCount) {
        int passivityCount = new PassivityNumberInput(ticketCount).passivityLottoCount();
        List<InputLottoNumber> passivityTicket = new PassivityLottoInput(
                new WinningNumberParser()).inputPassivityTicket(passivityCount);
        List<InputLottoNumber> auto = LottoCounter.generateAuto(ticketCount, passivityCount);
        List<InputLottoNumber> totalTicket = new ArrayList<>();
        totalTicket.addAll(passivityTicket);
        totalTicket.addAll(auto);
        System.out.printf("수동 %d장, 자동 %d장 구매 완료%n", passivityCount, ticketCount - passivityCount);
        return totalTicket;
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
                            .toList();

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

    private static int inputMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("구입 금액 입력(금액은 1000원 이상 입력해주세요): ");
        int money = scanner.nextInt();
        while (money < MINIMUM_AMOUNT) {
            System.out.println("구매에 필요한 금액은 1000원 이상입니다 다시 입력해주세요");
            money = scanner.nextInt();
        }
        return money;
    }
}
