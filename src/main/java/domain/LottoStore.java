package domain;

import java.util.List;
import java.util.stream.LongStream;

import static constant.LottoConstant.LOTTO_PRICE;

public class LottoStore {

    private final NumbersGenerator numbersGenerator;

    public LottoStore(NumbersGenerator numbersGenerator) {
        this.numbersGenerator = numbersGenerator;
    }

    public long getLottoCount(long amount){
        return amount / LOTTO_PRICE;
    }

    public LottoGroup buyLottos(List<List<Integer>> lottosNumbers, long automaticLottoCount){
        LottoGroup passivityLottoGroup = buyPassivityLottos(lottosNumbers);
        LottoGroup automaticLottoGroup = buyAutomaticLottos(automaticLottoCount);

        return LottoGroup.combineLottoGroup(passivityLottoGroup, automaticLottoGroup);
    }

    private LottoGroup buyAutomaticLottos(long lottoCount){
        List<Lotto> lottos = LongStream.range(0,  lottoCount)
                .mapToObj(i -> createLotto())
                .toList();

        return new LottoGroup(lottos);
    }

    private LottoGroup buyPassivityLottos(List<List<Integer>> lottosNumbers){
        List<Lotto> lottos = lottosNumbers.stream()
                .map(Lotto::new)
                .toList();

        return new LottoGroup(lottos);
    }

    private Lotto createLotto(){
        return new Lotto(numbersGenerator.getNumbers());
    }
}
