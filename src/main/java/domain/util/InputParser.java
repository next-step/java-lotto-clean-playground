package domain.util;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Money;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    private InputParser() {}

    public static Lotto parseLotto(String input) {
        List<LottoNumber> numbers = Arrays.stream(input.split(","))
                .map(String::strip)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();

        return new Lotto(numbers);
    }

    public static LottoNumber parseBonusNumber(String bonusInput) {
        return new LottoNumber(Integer.parseInt(bonusInput.strip()));
    }

    public static Money parseMoney(String input) {
        long amount = Long.parseLong(input.strip());
        return new Money(amount);
    }

    public static int parseManualLottoCount(String input) {
        return Integer.parseInt(input.strip());
    }

    public static Lottos parseManualLottos(List<String> lines) {
        List<Lotto> manualLottoList = lines.stream()
                .map(InputParser::parseLotto)
                .toList();
        return new Lottos(manualLottoList);
    }
}
