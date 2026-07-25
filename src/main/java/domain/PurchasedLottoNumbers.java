package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurchasedLottoNumbers {
    private final List<List<Integer>> purchasedLottoNumbers;

    public PurchasedLottoNumbers(List<List<Integer>> purchasedLottoNumbers) {
        this.purchasedLottoNumbers = new ArrayList<>(purchasedLottoNumbers);
    }

    public List<Integer> get(int index) {
        return purchasedLottoNumbers.get(index);
    }

    public int size() {
        return purchasedLottoNumbers.size();
    }

    public List<List<Integer>> getPurchasedLottoNumbers() {
        return Collections.unmodifiableList(purchasedLottoNumbers);
    }
}
