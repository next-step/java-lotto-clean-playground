package lotto.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;

public class ViewInput {
    private final Scanner scanner = new Scanner(System.in);

    public int readTotalPrice() {
        String line = scanner.nextLine();
        int totalPrice;

        try {
            totalPrice = Integer.parseInt(line);
        } catch (RuntimeException e) {
            throw new LottoPriceException.Malformed("가격은 정수 숫자여야 합니다.", e);
        }

        validateTotalPrice(totalPrice);
        return totalPrice;
    }

    private void validateTotalPrice(int totalPrice) {
        if (totalPrice < 0) {
            throw new LottoPriceException.Illegal("가격이 음수입니다.");
        }

        if (totalPrice == 0) {
            throw new LottoPriceException.Illegal("복권 0원치를 살 수 없습니다.");
        }
    }

    public LottoNumbers readLottoNumbers() {
        String line = scanner.nextLine();
        try {
            return parseLottoNumbers(line);
        } catch (RuntimeException e) {
            throw new ViewInputException("로또 숫자 파싱에 실패했습니다.", e);
        }
    }

    private LottoNumbers parseLottoNumbers(String line) {
        List<LottoNumber> numbers = Stream.of(line.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();
        return new LottoNumbers(numbers);
    }

    public LottoNumber readBonusNumber() {
        String line = scanner.nextLine();
        try {
            return parseBonusNumber(line);
        } catch (RuntimeException e) {
            throw new ViewInputException("보너스 숫자 파싱에 실패했습니다.", e);
        }
    }

    private static LottoNumber parseBonusNumber(String line) {
        int number = Integer.parseInt(line.trim());
        return new LottoNumber(number);
    }
}
