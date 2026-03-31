package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int PRICE = 1000;
    private static final int LOTTO_UPPER_BOUND = 45;

    private final ArrayList<Integer> lottoNumbers = new ArrayList<>(45);

    public int getLottoCount(final int purchaseAmount) {
        return purchaseAmount / PRICE;
    }

    public ArrayList<List<Integer>> saveLottos(final int purchaseAmount) {
        int lottoCount = getLottoCount(purchaseAmount);
        ArrayList<List<Integer>> allLottos = new ArrayList<>();

        for(int i = 0 ; i < lottoCount; i++) {
            List<Integer> singleLotto = getSingleLotto();
            allLottos.add(i, singleLotto);
        }

        return allLottos;
    }
    private void generateLottoNumbersArray() {
        for(int i = 0 ; i < LOTTO_UPPER_BOUND ; i++) {
            lottoNumbers.add(i, i + 1);
        }
    }

    private List<Integer> getSingleLotto() {
        generateLottoNumbersArray();
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, 6);
    }
}
