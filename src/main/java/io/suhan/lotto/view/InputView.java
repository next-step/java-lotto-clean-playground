package io.suhan.lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getBalance() {
        System.out.println("구입할 금액을 입력해주세요.");

        return scanner.nextInt();
    }

    public static Set<Integer> getWonNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");

        return parseNumbers(scanner.next());
    }

    public static int getBonusNumber() {
        System.out.println("\n보너스 볼을 입력해주세요.");

        return scanner.nextInt();
    }

    public static int getManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해주세요.");

        return scanner.nextInt();
    }

    public static List<Set<Integer>> getManualNumbers(int count) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<Set<Integer>> numbers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            numbers.add(parseNumbers(scanner.next()));
        }

        return numbers;
    }

    private static Set<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }
}
