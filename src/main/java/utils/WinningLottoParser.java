package utils;

import domain.Lotto;
import domain.LottoNumber;
import domain.WinningLotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoParser {

    private static final String DELIMITER = ",";
    private static final int REQUIRED_SIZE = 6;
    private static final String ERROR_INVALID_FORMAT = "[ERROR] 당첨 번호는 쉼표(,)로 구분된 숫자 6개여야 합니다.";

    public static WinningLotto parse(String winningNumbersInput, String bonusNumberInput) {
        List<LottoNumber> winningNumbers = parseWinningNumbers(winningNumbersInput);
        Lotto lotto = new Lotto(winningNumbers);

        LottoNumber bonusNumber = parseBonusNumber(bonusNumberInput);

        return new WinningLotto(lotto, bonusNumber);
    }

    private static List<LottoNumber> parseWinningNumbers(String input) {
        List<Integer> numbers = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(WinningLottoParser::parseNumber)
                .collect(Collectors.toList());

        validateSize(numbers);

        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private static int parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }
    }

    private static LottoNumber parseBonusNumber(String bonusInput) {
        try {
            int bonusValue = Integer.parseInt(bonusInput.trim());
            return new LottoNumber(bonusValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }
}
