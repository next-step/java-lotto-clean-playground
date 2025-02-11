package domain;

import java.util.List;
import java.util.stream.LongStream;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

    private final NumbersGenerator numbersGenerator;

    public LottoStore(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public long getLottoCount(long amount){
        return amount / LOTTO_PRICE;
    }

    public LottoGroup buyLottos(long lottoCount){
        List<Lotto> lottoList = LongStream.range(0,  lottoCount)
                .mapToObj(i -> createLotto())
                .toList();

        return new LottoGroup(lottoList);
    }

    private Lotto createLotto(){
        return new Lotto(numbersGenerator.getNumbers());
    }
}
