package generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
    private final List<Integer> lottoNumberRange;

    public RandomLottoNumberGenerator(){
        List<Integer> lottoNumbers = new ArrayList<>();
        for(int i = 1; i <= 45; i++ ){
            lottoNumbers.add(i);
        }
        this.lottoNumberRange = lottoNumbers;
    }

    @Override
    public List<Integer> generate() {
        Collections.shuffle(lottoNumberRange);
        return new ArrayList<>(lottoNumberRange.subList(0, 6));
    }
}
