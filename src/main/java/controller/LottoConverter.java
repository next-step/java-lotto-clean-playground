package controller;

import domain.Lotto;
import domain.Lottos;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoConverter {
    public static Lottos convert(List<String> numbers) {
        List<Lotto> lottos = new java.util.ArrayList<>(Collections.emptyList());

        for (String number : numbers) {
            List<Integer> lottoNumbers = Arrays.stream(number.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            Lotto lotto = Lotto.of(lottoNumbers);
            lottos.add(lotto);
        }
        return Lottos.of(lottos);
    }
}
