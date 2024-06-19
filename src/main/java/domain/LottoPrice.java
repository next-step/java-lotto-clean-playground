package domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public enum LottoPrice {

    THREE("3", 5000),
    FOUR("4", 50000),
    FIVE("5", 1500000),
    BONUS("5BONUS", 30000000),
    SIX("6", 2000000000);

    private static final int MAKE_FIRST_INDEX = 3;
    private static final String LAST_INDEX_NUMBER = "6";

    private final String sameLottoNumber;
    private final int lottoPrice;

    LottoPrice(String sameLottoNumber, int lottoPrice) {
        this.sameLottoNumber = sameLottoNumber;
        this.lottoPrice = lottoPrice;
    }

    public static List<Integer> getLottoPriceBundle() {
        List<Integer> lottoPrices = new ArrayList<>();
        for (LottoPrice lottoPrice : LottoPrice.values()) {
            lottoPrices.add(lottoPrice.getLottoPrice());
        }
        return lottoPrices;
    }

    public static int getLottoPrice(String sameLottoNumber) {
        if (Objects.equals(sameLottoNumber, BONUS.getSameLottoNumber())) {
            return BONUS.getLottoPrice();
        }
        if (Objects.equals(sameLottoNumber, LAST_INDEX_NUMBER)) {
            return SIX.getLottoPrice();
        }
        LottoPrice[] lottoPrices = LottoPrice.values();
        return lottoPrices[Integer.parseInt(sameLottoNumber) - MAKE_FIRST_INDEX].getLottoPrice();
    }

    public static List<String> getSameLottoNumberBundle() {
        List<String> sameLottoNumbers = new ArrayList<>();
        for (LottoPrice lottoPrice : LottoPrice.values()) {
            sameLottoNumbers.add(lottoPrice.getSameLottoNumber());
        }
        return sameLottoNumbers;
    }

    public static String getBonusSameLottoNumber() {
        return BONUS.getSameLottoNumber();
    }

    private String getSameLottoNumber() {
        return sameLottoNumber;
    }

    private int getLottoPrice() {
        return lottoPrice;
    }
}
