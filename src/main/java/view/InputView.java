package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public int getMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int money = scanner.nextInt();
        scanner.nextLine();
        return money;
    }

    public List<Integer> getWinningNumbers(){
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumbers = scanner.nextLine();
        return Arrays.stream(winningNumbers.split(","))
                .map(String::trim) // 공백 제거
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int getBonusNumber(){
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }

    public int getManualTicketNumber() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualTicketNumber = scanner.nextInt();
        scanner.nextLine();
        return manualTicketNumber;
    }

    public List<List<Integer>> getManualNumbers(int manualTicketNumber) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualTickets = new ArrayList<>();

        for (int i = 0; i < manualTicketNumber; i++) {
            String input = scanner.nextLine();
            List<Integer> numbers = parseNumbers(input);
            manualTickets.add(numbers);
        }

        return manualTickets;
    }

    private List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split("[,\\s]+")) // 쉼표나 공백으로 분리
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
