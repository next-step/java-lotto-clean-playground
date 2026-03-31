package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int PRICE = 1000;
    private static final int LOTTO_UPPER_BOUND = 45;

    public int getLottoCount(final int purchaseAmount) {
        return purchaseAmount / PRICE;
    }

    public ArrayList<ArrayList<Integer>> getAllLottos(final int purchaseAmount) {
        int lottoCount = getLottoCount(purchaseAmount);
        ArrayList<ArrayList<Integer>> allLottos = new ArrayList<>();

        for(int i = 0 ; i < lottoCount; i++) {
            ArrayList<Integer> singleLotto = getSingleLotto();
            allLottos.add(i, singleLotto);
        }

        return allLottos;
    }
    private ArrayList<Integer> generateLottoNumbersArray() {
        ArrayList<Integer> lottoNumbers = new ArrayList<>(45);
        for(int i = 0 ; i < LOTTO_UPPER_BOUND ; i++) {
            lottoNumbers.add(i, i + 1);
        }
        return lottoNumbers;
    }

    private ArrayList<Integer> getSingleLotto() {
        ArrayList<Integer> lottoNumbers = generateLottoNumbersArray();
        Collections.shuffle(lottoNumbers);
        List<Integer> subNumbers = lottoNumbers.subList(0, 6);
        Collections.sort(subNumbers);
        return new ArrayList<>(subNumbers);
    }
}
