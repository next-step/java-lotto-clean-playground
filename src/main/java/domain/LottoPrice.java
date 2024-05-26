package domain;

import java.util.ArrayList;
import java.util.List;

public enum LottoPrice {

    THREE(5000, "3개 일치 (5000원)- "),
    FOUR(50000, "4개 일치 (50000원)- "),
    FIVE(1500000, "5개 일치 (1500000원)- "),
    SIX(2000000000, "6개 일치 (2000000000원)- ");

    private final int lottoPrice;
    private final String lottoMessage;

    LottoPrice(int lottoPrice, String lottoMessage) {
        this.lottoPrice = lottoPrice;
        this.lottoMessage = lottoMessage;
    }

    public static List<String> getLottoMessageBundle() {
        List<String> lottoMessages = new ArrayList<>();
        for (LottoPrice lottoPrice : LottoPrice.values()) {
            lottoMessages.add(lottoPrice.getLottoMessege());
        }
        return lottoMessages;
    }

    public static List<Integer> getLottoPriceBundle() {
        List<Integer> lottoPrices = new ArrayList<>();
        for (LottoPrice lottoPrice : LottoPrice.values()) {
            lottoPrices.add(lottoPrice.getLottoPrice());
        }
        return lottoPrices;
    }

    private String getLottoMessege() {
        return lottoMessage;
    }

    private int getLottoPrice() {
        return lottoPrice;
    }
}
