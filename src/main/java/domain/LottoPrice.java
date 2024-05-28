package domain;

import java.util.ArrayList;
import java.util.List;

public enum LottoPrice {

    THREE("3", 5000, "3개 일치 (5000원)- "),
    FOUR("4", 50000, "4개 일치 (50000원)- "),
    FIVE("5", 1500000, "5개 일치 (1500000원)- "),
    BONUS("5BONUS", 30000000, "5개 일치, 보너스 볼 일치(30000000)- "),
    SIX("6", 2000000000, "6개 일치 (2000000000원)- ");


    private static final String BONUS_SAME_LOTTO_NUMBER = "5BONUS";
    private final String sameLottoNumber;
    private final int lottoPrice;
    private final String lottoMessage;

    LottoPrice(String sameLottoNumber, int lottoPrice, String lottoMessage) {
        this.sameLottoNumber = sameLottoNumber;
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

    public static List<String> getSameLottoNumberBundle() {
        List<String> sameLottoNumbers = new ArrayList<>();
        for (LottoPrice lottoPrice : LottoPrice.values()) {
            sameLottoNumbers.add(lottoPrice.getSameLottoNumber());
        }
        return sameLottoNumbers;
    }

    public static String getBonusSameLottoNumber(){
        for(LottoPrice lottoPrice : LottoPrice.values()){
            if(BONUS_SAME_LOTTO_NUMBER.equals(lottoPrice.getSameLottoNumber())){
                return lottoPrice.getSameLottoNumber();
            }
        }
        return null;
    }

    private String getSameLottoNumber() {
        return sameLottoNumber;
    }

    private String getLottoMessege() {
        return lottoMessage;
    }

    private int getLottoPrice() {
        return lottoPrice;
    }
}
