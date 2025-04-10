package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumberGenerator {

    private static final int MIN_OF_RANDOM_NUMBERS = 1;
    private static final int MAX_OF_RANDOM_NUMBERS = 45;
    private static final int NUMBER_OF_RANDOM_NUMBERS = 6;

    private final List<LottoNumber> allLottoNumbers;

    public LottoNumberGenerator() {
        this.allLottoNumbers = new ArrayList<>();
        for (int lottoNumber = MIN_OF_RANDOM_NUMBERS; lottoNumber <= MAX_OF_RANDOM_NUMBERS; lottoNumber++) {
            this.allLottoNumbers.add(LottoNumber.from(lottoNumber));
        }
    }

    public List<Integer> generate() {
        Collections.shuffle(allLottoNumbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>(allLottoNumbers.subList(0, NUMBER_OF_RANDOM_NUMBERS));
        return lottoNumbers.stream()
                .map(LottoNumber::getLottoNumber).sorted()
                .collect(Collectors.toList());
    }

}
