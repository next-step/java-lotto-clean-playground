package domain;

import java.util.List;
import java.util.stream.LongStream;

public class LottoStore {

    public static final int LOTTO_PRICE = 1_000;

    private final NumbersGenerator numbersGenerator;

    public LottoStore(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public long getLottoCount(long amount){
        return amount / LOTTO_PRICE;
    }

    public LottoGroup buyLottos(long lottoCount){
        List<Lotto> lottos = LongStream.range(0,  lottoCount)
                .mapToObj(i -> createLotto())
                .toList();

        return new LottoGroup(lottos);
    }

    private Lotto createLotto(){
        return new Lotto(numbersGenerator.getNumbers());
    }
}
