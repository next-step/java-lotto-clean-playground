package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DISUNITE_COMMA = ",";

    private final Scanner scanner = new Scanner(System.in);

    public int getMoney() {
        System.out.println("금액을 입력하세요:");
        return scanner.nextInt();
    }

    public List<Integer> getWinNumbers() {
        System.out.println("지난 주 당첨 번호를 입력하시오 (쉼표로 구분):");
        String winNumbers = scanner.next();
        return Arrays.stream(winNumbers.split(DISUNITE_COMMA))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
