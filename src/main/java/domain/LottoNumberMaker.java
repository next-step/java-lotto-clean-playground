package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberMaker {

    List<Integer> lottoNumbers = new ArrayList<>();

    public List<Integer> getLottoNumbers() {
        List<Integer> ret = new ArrayList<>();

        initLottoNumbers();
        shuffleLottoNumbers();

        for (int i = 0; i < 6; i++) {
            ret.add(lottoNumbers.get(i));
        }

        sortLottoNumbers(ret);
        return ret;
    }

    private void initLottoNumbers() {
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(i);
        }
    }

    private void shuffleLottoNumbers() {
        Collections.shuffle(lottoNumbers);
    }

    private void sortLottoNumbers(List<Integer> lottoNumbers) {
        Collections.sort(lottoNumbers);
    }
}
