package domain.generator;

import domain.Lotto;
import domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoGenerator implements LottoGenerator {

    private final List<LottoNumber> lottoNumbersRange;

    public AutoLottoGenerator() {
        this.lottoNumbersRange = IntStream.rangeClosed(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public Lotto generateLotto() {
        Collections.shuffle(lottoNumbersRange);
        List<LottoNumber> autoLottoNumber = new ArrayList<>(lottoNumbersRange.subList(0, 6));
        Collections.sort(autoLottoNumber);
        return new Lotto(autoLottoNumber);
    }

    @Override
    public List<Lotto> generateLottos(int count) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            autoLottos.add(generateLotto());
        }
        return autoLottos;
    }
}
