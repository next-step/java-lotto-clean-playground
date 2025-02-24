package service;

import domain.*;
import java.util.*;
import java.util.stream.*;

public class LottoService {

    private final static int LOTTO_PRICE = 1_000;

    public int calculateGetLottoAmount(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;
    }

    public void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < LOTTO_PRICE) {
            throw new IllegalArgumentException("최소 구매 금액은 " + LOTTO_PRICE + "입니다.");
        }

        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액 단위는 " + LOTTO_PRICE + "입니다.");
        }
    }

    public List<Lotto> createLottoList(int lottoAmount) {
        return IntStream.range(0, lottoAmount)
                .mapToObj(i -> LottoMachine.getRandomLotto())
                .collect(Collectors.toList());
    }

}
