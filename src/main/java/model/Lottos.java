package model;

import util.RandomLottoNumbersGenerator;

import java.util.ArrayList;
import java.util.List;

import static model.LottoConstraints.LOTTO_PRICE;

public class Lottos {

    private final List<LottoNumbers> lottoNumbersList;

    private Lottos(List<LottoNumbers> lottoNumbersList) {
        this.lottoNumbersList = new ArrayList<>(lottoNumbersList);
    }

    public static Lottos purchase(int purchaseAmount, List<LottoNumbers> manualLottoNumbersList) {
        validatePurchase(purchaseAmount, manualLottoNumbersList.size());

        List<LottoNumbers> lottos = new ArrayList<>();
        for (LottoNumbers manualLottoNumbers : manualLottoNumbersList) {
            lottos.add(manualLottoNumbers.getCopy());
        }
        for (int i = 0; i < calculateRandomLottoAmount(purchaseAmount, manualLottoNumbersList.size()); i++) {
            lottos.add(RandomLottoNumbersGenerator.getRandomLottoNumbers());
        }

        return new Lottos(lottos);
    }

    public int size() {
        return lottoNumbersList.size();
    }

    public List<LottoNumbers> getLottoNumbersList() {
        return List.copyOf(lottoNumbersList);
    }

    private static void validatePurchase(int purchaseAmount, int manualLottoAmount) {
        if (isLessThanPrice(purchaseAmount)) {
            throw new IllegalArgumentException(String.format("금액은 최소 %d원 이상이어야 합니다 - 실제 값: %d", LOTTO_PRICE, purchaseAmount));
        }
        if (isNotDivisible(purchaseAmount)) {
            throw new IllegalArgumentException(String.format("금액은 %d원 단위여야 합니다 - 실제 값: %d", LOTTO_PRICE, purchaseAmount));
        }
        if (isNotPurchasable(purchaseAmount, manualLottoAmount)) {
            throw new IllegalArgumentException(String.format("입력된 금액으로 구매하기에 너무 많은 로또입니다 - 한 장당 가격: %d", LOTTO_PRICE));
        }
    }

    private static boolean isLessThanPrice(int purchaseAmount) {
        return purchaseAmount < LOTTO_PRICE;
    }

    private static boolean isNotDivisible(int purchaseAmount) {
        return (purchaseAmount % LOTTO_PRICE) != 0;
    }

    private static boolean isNotPurchasable(int purchaseAmount, int lottoAmount) {
        return (purchaseAmount / LOTTO_PRICE) < lottoAmount;
    }

    private static int calculateRandomLottoAmount(int purchaseAmount, int manualLottoAmount) {
        return (purchaseAmount / LOTTO_PRICE) - manualLottoAmount;
    }

}
