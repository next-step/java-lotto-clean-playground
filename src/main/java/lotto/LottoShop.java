package lotto;

import static lotto.Constants.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.Collection;

import lotto.utils.LottoNumberGenerator;

public class LottoShop {
    public Collection<Lotto> buyRandom(int payment) {
        int amount = payment / LOTTO_PRICE;
        Collection<Lotto> lottos = new ArrayList<>();

        System.out.printf("%n%s개를 구매했습니다.%n", amount);

        for (int i = 0; i < amount; i++) {
            lottos.add(new Lotto(LottoNumberGenerator.generate()));
        }

        return lottos;
    }
}
