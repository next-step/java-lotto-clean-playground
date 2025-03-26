package view;

public class InputValidator {

    private static final String LOTTO_NUMBERS_PATTERN = "^\\d+(,\\s*\\d+)*$";

    public static void validateLottoNumbersInputPattern(String lottoNumbers) {
        if (!lottoNumbers.matches(LOTTO_NUMBERS_PATTERN)) {
            throw new IllegalArgumentException("로또 번호 입력은 쉼표로 구분되어야 합니다!");
        }
    }
}
