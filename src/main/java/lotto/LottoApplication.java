package lotto;

import java.util.List;
import java.util.Scanner;

public class LottoApplication {
    private static final int TICKET_PRICE = 1000;
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            int purchaseAmount = readPurchaseAmount();
            validatePurchaseAmount(purchaseAmount);
            int numberOfTickets = calculateNumberOfTickets(purchaseAmount);
            List<List<Integer>> tickets = generateTickets(numberOfTickets);
            printPurchaseCount(numberOfTickets);
            printTickets(tickets);
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

    private static void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < TICKET_PRICE) {
            throw new IllegalArgumentException("구입 금액은 " + TICKET_PRICE + "원 이상이어야 합니다.");
        }
        if (purchaseAmount % TICKET_PRICE != 0) {
            throw new IllegalArgumentException(TICKET_PRICE + "원 단위로 입력해 주세요.");
        }
    }

    private static int calculateNumberOfTickets(int purchaseAmount) {
        return purchaseAmount / TICKET_PRICE;
    }

    private static List<List<Integer>> generateTickets(int numberOfTickets) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        return lottoGenerator.generateMultiple(numberOfTickets);
    }

    private static void printPurchaseCount(int numberOfTickets) {
        System.out.println(numberOfTickets + "개를 구매했습니다.");
    }

    private static void printTickets(List<List<Integer>> tickets) {
        for (List<Integer> ticket : tickets) {
            System.out.println(LottoFormatter.format(ticket));
        }
    }
}
