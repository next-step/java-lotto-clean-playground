package io.suhan.lotto.view;

import static io.suhan.lotto.model.executor.PurchaseExecutor.PRICE_PER_LOTTO;
import static io.suhan.lotto.model.lotto.Lotto.LOTTO_SIZE;

import io.suhan.lotto.model.lotto.LottoNumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_DELIMITER = ",";

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

    public static int getValidBalance() {
        while (true) {
            try {
                int balance = getBalance();

                if (balance < PRICE_PER_LOTTO) {
                    throw new IllegalArgumentException("금액은 " + PRICE_PER_LOTTO + "원 보다 크거나 같아야 합니다.");
                }

                return balance;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Set<Integer> getValidWonNumbers() {
        while (true) {
            try {
                return getWonNumbers();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static LottoNumber getValidBonusNumber() {
        while (true) {
            try {
                int number = getBonusNumber();

                return new LottoNumber(number);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getValidManualCount() {
        while (true) {
            try {
                int manualCount = InputView.getManualCount();

                if (manualCount < 0) {
                    throw new IllegalArgumentException("로또 수는 0 또는 양수만 입력할 수 있습니다.");
                }

                return manualCount;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Set<Integer>> getValidManualNumbers(int count) {
        while (true) {
            try {
                return getManualNumbers(count);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Set<Integer> parseNumbers(String input) {
        Set<Integer> numbers = Arrays.stream(input.split(INPUT_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_SIZE + "개여야 합니다.");
        }

        return numbers;
    }
}
