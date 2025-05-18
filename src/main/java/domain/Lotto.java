package domain;

import java.util.*;

public class Lotto {
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> list){
        lottoNumbers = list;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public List<Integer> getNumbers() {
        return lottoNumbers;
    }

}


