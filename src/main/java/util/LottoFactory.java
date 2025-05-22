package util;

import domain.Lotto;
import domain.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {

    public static Lotto generateAutoLotto() {
        List<LottoNumber> lottoNumbers = RandomNumbersGenerator.generateSixLottoNumbers().stream()
                .map(LottoNumber::new).collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    public static Lotto generateManualLotto(final List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream().map(LottoNumber::new).collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }
}
