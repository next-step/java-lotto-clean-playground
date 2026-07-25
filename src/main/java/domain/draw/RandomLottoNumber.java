package domain.draw;

import domain.lotto.Lotto;
import domain.lotto.wrap.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class RandomLottoNumber implements DrawLottoNumber{

    private static final List<LottoNumber> ballPool = IntStream.rangeClosed(1, 45).mapToObj(LottoNumber::new).toList();

    @Override
    public Lotto draw() {

        List<LottoNumber> balls = new ArrayList<>(ballPool);
        Collections.shuffle(balls);
        List<LottoNumber> picked = new ArrayList<>(balls.subList(0, 6));
        Collections.sort(picked);
        return new Lotto(picked);
    }
}
