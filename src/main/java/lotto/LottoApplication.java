package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoApplication {
    private static final int TICKET_PRICE = 1000;
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Money purchaseAmount = Money.of(readPurchaseAmount());
            validatePurchaseAmount(purchaseAmount);
            int numberOfTickets = calculateNumberOfTickets(purchaseAmount);
            LottoTickets tickets = generateTickets(numberOfTickets);
            printPurchaseCount(tickets.size());
            printTickets(tickets);

            LottoTicket winningTicket = readWinningTicket();
            ResultStatistics statistics = evaluate(tickets, winningTicket);
            printStatistics(statistics, purchaseAmount);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = INPUT.nextLine();
        return parseInteger(input);
    }

    private static int parseInteger(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("유효한 금액을 입력해 주세요.");
        }
    }

    private static void validatePurchaseAmount(Money purchaseAmount) {
        purchaseAmount.validateAtLeast(TICKET_PRICE);
        purchaseAmount.validateMultipleOf(TICKET_PRICE);
    }

    private static int calculateNumberOfTickets(Money purchaseAmount) {
        return purchaseAmount.divideBy(TICKET_PRICE);
    }

    private static LottoTickets generateTickets(int numberOfTickets) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        return lottoGenerator.generateMultiple(numberOfTickets);
    }

    private static void printPurchaseCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    private static void printTickets(LottoTickets tickets) {
        for (LottoTicket ticket : tickets.asList()) {
            System.out.println(LottoFormatter.format(ticket));
        }
    }

    private static LottoTicket readWinningTicket() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = INPUT.nextLine();
        String[] tokens = input.split(",");
        if (tokens.length != LottoTicket.SIZE) {
            throw new IllegalArgumentException("쉼표로 구분된 " + LottoTicket.SIZE + "개의 번호를 입력해 주세요.");
        }
        List<LottoNumber> numbers = new ArrayList<>();
        for (String token : tokens) {
            int value = parseInteger(token.trim());
            numbers.add(LottoNumber.of(value));
        }
        return new LottoTicket(numbers);
    }

    private static ResultStatistics evaluate(LottoTickets tickets, LottoTicket winningTicket) {
        ResultStatistics statistics = new ResultStatistics();
        for (LottoTicket ticket : tickets.asList()) {
            int matchCount = ticket.countMatches(winningTicket);
            for (Rank rank : Rank.values()) {
                if (rank.getMatchCount() == matchCount) {
                    statistics.add(rank);
                }
            }
        }
        return statistics;
    }

    private static void printStatistics(ResultStatistics statistics, Money purchaseAmount) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + statistics.getCount(Rank.THREE) + "개");
        System.out.println("4개 일치 (50000원)- " + statistics.getCount(Rank.FOUR) + "개");
        System.out.println("5개 일치 (1500000원)- " + statistics.getCount(Rank.FIVE) + "개");
        System.out.println("6개 일치 (2000000000원)- " + statistics.getCount(Rank.SIX) + "개");
        double profitRate = statistics.getTotalPrizeMoney().ratioAgainst(purchaseAmount);
        System.out.println("총 수익률은 " + String.format("%.2f", profitRate) + "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }
}
