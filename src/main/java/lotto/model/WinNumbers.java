package lotto.model;

import static lotto.global.Constants.MAX_NUMBER;
import static lotto.global.Constants.MIN_NUMBER;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class WinNumbers {
    private final Set<Integer> winNumbers;

    private WinNumbers(Set<Integer> winNumbers) {
        this.winNumbers = winNumbers;
    }

    public static WinNumbers from(final String winNumbers) {
        final Set<Integer> numbers = convertWinNumbers(winNumbers);
        validate(numbers);

        return new WinNumbers(numbers);
    }

    private static Set<Integer> convertWinNumbers(final String winNumbers) {
        try {
            return Arrays.stream(winNumbers.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아니거나 형식이 잘못됨");
        }
    }

    private static void validate(Set<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < MIN_NUMBER || MAX_NUMBER < number) {
                throw new IllegalArgumentException("당첨 번호는 " + MIN_NUMBER + "이상, " + MAX_NUMBER + "이하여야 함");
            }
        }
    }

    public Set<Integer> getWinNumbers() {
        return winNumbers;
    }
}
