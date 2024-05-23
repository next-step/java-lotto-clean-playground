package exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record BuyDirectNumber(
        List<Integer> value
) {

    public static BuyDirectNumber from(String directText) {

        List<Integer> directNumber = validateFormat(directText);
        validateCollectNumber(directNumber);

        return new BuyDirectNumber(directNumber);
    }

    private static List<Integer> validateFormat(String collectText) {

        List<Integer> numbers;

        try {
            numbers = Arrays.stream(collectText.split(", "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 형식에 맞게 다시 입력해주세요");
            throw e;
        }

        return numbers;
    }

    private static void validateCollectNumber(List<Integer> collectNumber) {

        if (collectNumber.size() != 6) throw new IllegalArgumentException("[ERROR] 6개의 로또번호를 입력하세요");

        collectNumber.stream().
                forEach(number -> {
                    validateDuplicate(collectNumber, number);
                    if (number < 1 || number > 45)
                        throw new IllegalArgumentException("[ERROR] 1에서 45까지의 숫자만 입력하세요");
                });
    }

    private static void validateDuplicate(final List<Integer> collectNumber, final int number) {

        int count = 0;

        for (int i = 0; i < collectNumber.size(); i++) {
            if (collectNumber.get(i) == number) count++;
        }

        if (count >= 2) throw new IllegalArgumentException("[ERROR] 서로 다른 숫자만 입력하세요");
    }
}
