package view;

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
}
