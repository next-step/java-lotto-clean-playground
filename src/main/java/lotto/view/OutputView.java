package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printInputMoney() {
        System.out.println("구입금액을 입력해주세요.");
    }

    public static void printLotto(List<List<Integer>> numbers) {
        System.out.printf("%d개를 구매했습니다.%n", numbers.size());
        for (List<Integer> number : numbers) {
            String result = number.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
            System.out.printf("[%s]%n", result);
        }
    }
}
