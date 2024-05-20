package domain;

import java.util.Collections;
import java.util.List;

public class Lotto{

    private final List<Integer> lottoNumber;

    public Lotto(CreateLottoNumber createLottoNumber) {
        this.lottoNumber = createLottoNumber.getRandomLottoNumber();
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }

    private void sortLottoNumber(List<Integer> lottoNumber) {
        Collections.sort(lottoNumber);
    }
}
