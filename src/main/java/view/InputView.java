package view;

import domain.Lotto;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {
    public static final int PRICE_PER_ONE_LOTTO_TICKET = 1000;
    public static Scanner lottoScanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputLottoTotalPrice() {
        System.out.println("구입 금액을 입력해 주세요.");
        while (true) {
            try {
                String input = lottoScanner.nextLine();
                int price = Integer.parseInt(input);
                validatePurchaseAmount(price);
                return price;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 구입 금액은 숫자로만 입력해야 합니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage() + " 다시 입력해 주세요.");
            }
        }
    }

    public static int inputUserSelectedLottoCount(int totalCount) {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        while (true) {
            try {
                String input = lottoScanner.nextLine();
                int manualCount = Integer.parseInt(input);
                validateManualCount(manualCount, totalCount);
                return manualCount;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 로또 개수는 숫자로만 입력해야 합니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage() + " 다시 입력해 주세요.");
            }
        }
    }

    public static void printUserSelectedLottoNumbersPrompt() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }

    public static List<Integer> readLottoNumbers() {
        String numbersString = lottoScanner.nextLine();
        return parseLottoNumbers(numbersString);
    }

    public static String inputWinningLottoNumbers() {
        System.out.println("\n지난 주 당첨번호를 입력해 주세요");
        while (true) {
            try {
                String winningLottoNumbers = lottoScanner.nextLine();
                new Lotto(parseLottoNumbers(winningLottoNumbers));
                return winningLottoNumbers;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 당첨 번호는 숫자로만 구성되어야 합니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage() + " 다시 입력해 주세요.");
            }
        }
    }

    public static String inputBonusBallNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        while (true) {
            try {
                String bonusNumberStr = lottoScanner.nextLine();
                validateBonusBall(bonusNumberStr);
                return bonusNumberStr;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 보너스 볼은 숫자로만 입력해야 합니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage() + " 다시 입력해 주세요.");
            }
        }
    }

    public static void closeScanner(Scanner scanner) {
        if (scanner != null) {
            scanner.close();
        }
    }

    private static void validatePurchaseAmount(int price) {
        if (price < PRICE_PER_ONE_LOTTO_TICKET) {
            throw new IllegalArgumentException("구입 금액은 " + PRICE_PER_ONE_LOTTO_TICKET + "원 이상이어야 합니다.");
        }
        if (price % PRICE_PER_ONE_LOTTO_TICKET != 0) {
            throw new IllegalArgumentException("구입 금액은 " + PRICE_PER_ONE_LOTTO_TICKET + "원 단위로 입력해야 합니다.");
        }
    }

    private static void validateManualCount(int manualCount, int totalCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException("수동 구매 개수는 0 이상이어야 합니다.");
        }
        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 개수는 전체 구매 개수(" + totalCount + "개)를 초과할 수 없습니다.");
        }
    }

    private static List<Integer> parseLottoNumbers(String numbersString) {
        return Arrays.stream(numbersString.split(",\\s*"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void validateBonusBall(String bonusNumberStr) {
        if (bonusNumberStr == null || bonusNumberStr.trim().isEmpty()) {
            throw new IllegalArgumentException("보너스 볼 번호를 입력해야 합니다.");
        }
        if (bonusNumberStr.contains(" ") || bonusNumberStr.contains(",")) {
            throw new IllegalArgumentException("보너스 볼은 하나의 숫자만 입력해야 합니다.");
        }
        int bonusNumber = Integer.parseInt(bonusNumberStr);
        if (bonusNumber < Lotto.LOTTO_NUMBER_LOWER_BOUND || bonusNumber > Lotto.LOTTO_NUMBER_BOUND) {
            throw new IllegalArgumentException("보너스 볼은 " + Lotto.LOTTO_NUMBER_LOWER_BOUND
                                                + "과 " + Lotto.LOTTO_NUMBER_BOUND + " 사이의 숫자여야 합니다.");
        }
    }
}
