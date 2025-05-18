package domain;

import java.util.*;

public class Lotto {
    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> list){
        lotto = list;
    }

    @Override
    public String toString() {
        return lotto.toString();
    }

    public List<LottoNumber> getNumbers() {
        return lotto;
    }

}


