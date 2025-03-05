package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoGenerator {
    final Random random = new Random();

    public Lotto generate() {
        final List<Integer> lottoNumbers = new ArrayList<>();

        while (lottoNumbers.size() < LottoConstants.NUMBER_COUNT.getValue()) {
            final int candidateLottoNumber = random.nextInt(LottoConstants.MIN_NUMBER.getValue(),
                    LottoConstants.MAX_NUMBER.getValue());

            if (!lottoNumbers.contains(candidateLottoNumber)) {
                lottoNumbers.add(candidateLottoNumber);
            }
        }

        return new Lotto(lottoNumbers);
    }
}
