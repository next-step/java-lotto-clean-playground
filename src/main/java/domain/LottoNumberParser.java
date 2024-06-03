package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumberParser {

    private static final String SPLIT_STRING_DELIMITER = ",";
    private static final int FIRST_LOTTO_NUMBER = 1;
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final int FIRST_ASCII_NUMBER = 48;
    private static final int LAST_ASCII_NUMBER = 57;
    private static final String EXCEPTION_ASCII_NUMBER = "올바른 숫자 입력하세요.";
    private static final String EXCEPTION_LOTTO_RANGE = "로또 범위 아님";

    private final List<Integer> realLottoNumber;

    public LottoNumberParser(String lottoNumber) {
        this.realLottoNumber = makeRealLottoNumber(lottoNumber);
    }

    public List<Integer> getRealLottoNumber() {
        return realLottoNumber;
    }

    public void addBonusBall(int bonusBall){
        realLottoNumber.add(bonusBall);
    }

    private List<Integer> makeRealLottoNumber(String lottoNumber) {
        List<String> inputLottoNumber = parseLottoNumber(lottoNumber);
        List<Integer> realLottoNumber = new ArrayList<>();
        for (String lottoNumberElement : inputLottoNumber) {
            validateLottoNumber(lottoNumberElement);
            int realLottoNumberElement = Integer.parseInt(lottoNumberElement);
            validateLottoNumberRange(realLottoNumberElement);
            realLottoNumber.add(realLottoNumberElement);
        }
        return realLottoNumber;
    }

    private List<String> parseLottoNumber(String lottoNumber) {
        String[] parseLottoNumber = lottoNumber.split(SPLIT_STRING_DELIMITER);
        return List.of(parseLottoNumber);
    }

    private void validateLottoNumber(String lottoNumber){
        for(char lottoNumberElement : lottoNumber.toCharArray()){
            if(!validateLottoNumberElement(lottoNumberElement)){
                throw new RuntimeException(EXCEPTION_ASCII_NUMBER);
            }
        }
    }

    private boolean validateLottoNumberElement(char lottoNumberElement){
        return lottoNumberElement >= FIRST_ASCII_NUMBER && lottoNumberElement <= LAST_ASCII_NUMBER;
    }

    private void validateLottoNumberRange(int realLottoNumber){
        if(realLottoNumber<FIRST_LOTTO_NUMBER || realLottoNumber>LAST_LOTTO_NUMBER){
            throw new RuntimeException(EXCEPTION_LOTTO_RANGE);
        }
    }
}
