package util;

import vo.LottoNumber;
import java.util.ArrayList;

public class LottoValidator {
    
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int LOTTO_PRICE = 1000;

    // 로또 번호 범위 검증
    public static void validateLottoNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 " + MIN_LOTTO_NUMBER + "부터 " + MAX_LOTTO_NUMBER + "까지 가능합니다.");
        }
    }

    // 로또 번호 개수 검증
    public static void validateLottoNumberCount(ArrayList<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBER_COUNT + "개여야 합니다.");
        }
    }

    // 중복 번호 검증
    public static void validateDuplicateNumbers(ArrayList<LottoNumber> numbers) {
        ArrayList<LottoNumber> uniqueNumbers = new ArrayList<>();
        for (LottoNumber number : numbers) {
            if (uniqueNumbers.contains(number)) {
                throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
            }
            uniqueNumbers.add(number);
        }
    }

    // 로또 번호 전체 검증
    public static void validateLottoNumbers(ArrayList<LottoNumber> numbers) {
        validateLottoNumberCount(numbers);
        validateDuplicateNumbers(numbers);
    }

    // 구입 금액 검증
    public static void validateMoney(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("구입 금액을 입력해야 합니다.");
        }

        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException("구입 금액은 숫자만 입력할 수 있습니다.");
        }

        int money = Integer.parseInt(input);
        if (money < LOTTO_PRICE || money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 이상의 1000원 단위로 입력해야 합니다.");
        }
    }

    // 당첨 번호 입력 검증
    public static void validateWinningNumbersInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("당첨 번호를 입력해야 합니다.");
        }

        String[] numberStrings = input.split(",");
        if (numberStrings.length != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 6개를 입력해야 합니다.");
        }

        for (String numberString : numberStrings) {
            String trimmed = numberString.trim();
            if (!trimmed.matches("\\d+")) {
                throw new IllegalArgumentException("당첨 번호는 숫자만 입력할 수 있습니다.");
            }
        }
    }

    // 보너스 번호 검증
    public static void validateBonusNumber(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("보너스 번호를 입력해야 합니다.");
        }

        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException("보너스 번호는 숫자만 입력할 수 있습니다.");
        }
    }

    // 수동 로또 개수 검증
    public static void validateManualLottoCount(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("수동 로또 개수를 입력해야 합니다.");
        }

        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException("수동 로또 개수는 숫자만 입력할 수 있습니다.");
        }

        int count = Integer.parseInt(input);
        if (count < 0) {
            throw new IllegalArgumentException("수동 로또 개수는 0 이상이어야 합니다.");
        }
    }

    // 수동 로또 번호 입력 검증
    public static void validateManualLottoInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("수동 로또 번호를 입력해야 합니다.");
        }

        String[] numberStrings = input.split(",");
        for (String numberString : numberStrings) {
            String trimmed = numberString.trim();
            if (!trimmed.matches("\\d+")) {
                throw new IllegalArgumentException("당첨 번호는 숫자만 입력할 수 있습니다.");
            }
        }
    }
}
