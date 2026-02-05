package view;

import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public int readManualCount(int totalCount) {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualCount = Integer.parseInt(scanner.nextLine().trim());
        validateManualCount(manualCount, totalCount);
        return manualCount;
    }

    public List<LottoTicket> readManualTickets(int manualCount) {
        if (manualCount == 0) return List.of();

        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            tickets.add(readOneManualTicket());
        }
        return tickets;
    }

    public WinningNumbers readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<Integer> numbers = parseLine(scanner.nextLine());

        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonus = Integer.parseInt(scanner.nextLine().trim());

        return WinningNumbers.of(numbers, bonus);
    }

    private LottoTicket readOneManualTicket() {
        List<Integer> ints = parseLine(scanner.nextLine());
        List<LottoNumber> numbers = new ArrayList<>();
        for (int n : ints) numbers.add(LottoNumber.of(n));
        return new LottoTicket(new Lotto(numbers));
    }

    private List<Integer> parseLine(String line) {
        String[] parts = line.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String p : parts) {
            numbers.add(Integer.parseInt(p.trim()));
        }
        return numbers;
    }

    private void validateManualCount(int manualCount, int totalCount) {
        if (manualCount < 0) throw new IllegalArgumentException("수동 구매 수는 0 이상이어야 합니다.");
        if (manualCount > totalCount) throw new IllegalArgumentException("수동 구매 수는 전체 구매 수를 초과할 수 없습니다.");
    }
}
