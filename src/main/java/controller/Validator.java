package controller;

import java.util.Arrays;
import java.util.List;

public class Validator {
    private static final int MIN_LOTTO_COUNT = 1;
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_QUANTITY = 6;
    private static final int MAX_LOTTO_RANGE = 45;
    private static final int MIN_LOTTO_RANGE = 1;

    public List<Integer> validateLastWinningsInput(String input) {
        List<String> strings = Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
        List<Integer> winnings = validateWinningsInputIntegrity(strings);
        validateWinningsSize(winnings);
        validateWinningsRange(winnings);

        return winnings;
    }

    public Integer validatePriceInput(String input) {
        int purchasePrice = validatePriceInputIntegrity(input);
        int purchaseAmount = validateUnit(purchasePrice);
        validateLottoCount(purchaseAmount);
        return purchaseAmount;
    }

    private List<Integer> validateWinningsInputIntegrity(List<String> strings) {
        try {
            return strings.stream()
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수만 입력할 수 있습니다.");
        }
    }

    private void validateWinningsSize(List<Integer> winnings) {
        if (winnings.size() != LOTTO_QUANTITY) {
            throw new IllegalArgumentException("로또는 6개의 번호가 필요합니다.");
        }
    }

    private void validateWinningsRange(List<Integer> winnings) {
        int count = (int) winnings.stream()
                .filter(((number) -> number >= MIN_LOTTO_RANGE && number <= MAX_LOTTO_RANGE))
                .count();

        if (count != winnings.size()) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이만 입력 가능해야 합니다.");
        }
    }

    private int validateUnit(int purchasePrice) {
        int purchaseAmount = purchasePrice % LOTTO_PRICE;
        if (purchaseAmount > 0) {
            throw new IllegalArgumentException("로또는 천 원 단위로 구매 가능합니다.");
        }
        return purchasePrice / LOTTO_PRICE;
    }

    private void validateLottoCount(int purchaseAmount) {
        if (purchaseAmount < MIN_LOTTO_COUNT) {
            throw new IllegalArgumentException(
                    String.format("로또는 최소 %d장 이상 구매할 수 있습니다.", MIN_LOTTO_COUNT)
            );
        }
    }

    private int validatePriceInputIntegrity(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수만 입력할 수 있습니다.");
        }
    }
}
