package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private List<Integer> lottoNumbers = new ArrayList<>();

    public Lotto() {
        generateLottoNumber();
    }

    private void generateLottoNumber(){

    }
    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
