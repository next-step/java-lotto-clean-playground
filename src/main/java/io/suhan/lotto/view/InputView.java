package io.suhan.lotto.view;

import java.util.Arrays;
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

        return Arrays.stream(scanner.next().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }
}
