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
            int totalTickets = calculateNumberOfTickets(purchaseAmount);
            int manualCount = readManualCount(totalTickets);
            LottoTickets manualTickets = readManualTickets(manualCount);
            LottoTickets autoTickets = generateTickets(totalTickets - manualCount);
            LottoTickets allTickets = mergeTickets(manualTickets, autoTickets);
            printManualAutoCount(manualCount, autoTickets.size());
            printTickets(allTickets);

            LottoTicket winningTicket = readWinningTicket();
            LottoNumber bonusNumber = readBonusNumber(winningTicket);
            WinningNumbers winningNumbers = WinningNumbers.of(winningTicket, bonusNumber);
            ResultStatistics statistics = evaluate(allTickets, winningNumbers);
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

    private static int readManualCount(int totalTickets) {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualCount = parseInteger(INPUT.nextLine());
        if (manualCount < 0 || manualCount > totalTickets) {
            throw new IllegalArgumentException("수동 구매 수는 0 이상이고 총 구매 수 이하여야 합니다.");
        }
        return manualCount;
    }

    private static LottoTickets readManualTickets(int manualCount) {
        List<LottoTicket> tickets = new ArrayList<>();
        if (manualCount == 0) {
            return new LottoTickets(tickets);
        }
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualCount; i++) {
            LottoTicket ticket = readSingleManualTicket();
            tickets.add(ticket);
        }
        return new LottoTickets(tickets);
    }

    private static LottoTicket readSingleManualTicket() {
        String line = INPUT.nextLine();
        String[] tokens = line.split(",");
        validateTokenLength(tokens);
        List<LottoNumber> numbers = parseLottoNumbers(tokens);
        return new LottoTicket(numbers);
    }

    private static void validateTokenLength(String[] tokens) {
        if (tokens.length != LottoTicket.SIZE) {
            throw new IllegalArgumentException("쉼표로 구분된 " + LottoTicket.SIZE + "개의 번호를 입력해 주세요.");
        }
    }

    private static List<LottoNumber> parseLottoNumbers(String[] tokens) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (String token : tokens) {
            int value = parseInteger(token.trim());
            numbers.add(LottoNumber.of(value));
        }
        return numbers;
    }

    private static LottoTickets mergeTickets(LottoTickets manual, LottoTickets auto) {
        List<LottoTicket> merged = new ArrayList<>();
        merged.addAll(manual.asList());
        merged.addAll(auto.asList());
        return new LottoTickets(merged);
    }

    private static void printManualAutoCount(int manualCount, int autoCount) {
        System.out.println();
        System.out.println("수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
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

    private static ResultStatistics evaluate(LottoTickets tickets, WinningNumbers winningNumbers) {
        ResultStatistics statistics = new ResultStatistics();
        for (LottoTicket ticket : tickets.asList()) {
            Rank rank = decideRank(ticket, winningNumbers);
            addRankIfPresent(statistics, rank);
        }
        return statistics;
    }

    private static Rank decideRank(LottoTicket ticket, WinningNumbers winningNumbers) {
        int matchCount = ticket.countMatches(winningNumbers.getWinningTicket());
        boolean bonusMatched = ticket.contains(winningNumbers.getBonusNumber());
        return Rank.from(matchCount, bonusMatched);
    }

    private static void addRankIfPresent(ResultStatistics statistics, Rank rank) {
        if (rank == null) {
            return;
        }
        statistics.add(rank);
    }

    private static void printStatistics(ResultStatistics statistics, Money purchaseAmount) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        printRankLine(Rank.THREE.getLabel(), statistics.getCount(Rank.THREE));
        printRankLine(Rank.FOUR.getLabel(), statistics.getCount(Rank.FOUR));
        printRankLine(Rank.FIVE.getLabel(), statistics.getCount(Rank.FIVE));
        printRankLine(Rank.SECOND.getLabel(), statistics.getCount(Rank.SECOND));
        printRankLine(Rank.SIX.getLabel(), statistics.getCount(Rank.SIX));
        double profitRate = statistics.getTotalPrizeMoney().ratioAgainst(purchaseAmount);
        System.out.println("총 수익률은 " + String.format("%.2f", profitRate) + "입니다.");
    }

    private static void printRankLine(String label, int count) {
        System.out.println(label + " - " + count + "개");
    }

    private static LottoNumber readBonusNumber(LottoTicket winningTicket) {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = INPUT.nextLine();
        int value = parseInteger(input.trim());
        LottoNumber bonus = LottoNumber.of(value);
        if (winningTicket.contains(bonus)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
        return bonus;
    }
}
