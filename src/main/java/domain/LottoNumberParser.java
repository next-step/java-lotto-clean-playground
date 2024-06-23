package domain;

import java.util.List;
import java.util.ArrayList;

public class LottoNumberParser {

    private static final String SPLIT_STRING_DELIMITER = ",";
    private static final String EXCEPTION_NUMBER = "올바른 숫자 입력하세요.";

    private final List<Integer> realLottoNumber;

    public LottoNumberParser(String lottoNumber) {
        this.realLottoNumber = makeRealLottoNumber(lottoNumber);
    }

    public List<Integer> getRealLottoNumber() {
        return realLottoNumber;
    }

    public void addBonusBall(int bonusBall) {
        realLottoNumber.add(bonusBall);
    }

    private List<Integer> makeRealLottoNumber(String lottoNumber) {
        List<String> inputLottoNumber = parseLottoNumber(lottoNumber);
        List<Integer> realLottoNumber = new ArrayList<>();
        for (String lottoNumberElement : inputLottoNumber) {
            validateLottoNumber(lottoNumberElement);
            int realLottoNumberElement = Integer.parseInt(lottoNumberElement);
            realLottoNumber.add(realLottoNumberElement);
        }
        return realLottoNumber;
    }

    private List<String> parseLottoNumber(String lottoNumber) {
        String[] parseLottoNumber = lottoNumber.split(SPLIT_STRING_DELIMITER);
        return List.of(parseLottoNumber);
    }

    private void validateLottoNumber(String lottoNumber) {
        try {
            Integer.parseInt(lottoNumber);
        } catch (NumberFormatException exception) {
            throw new RuntimeException(EXCEPTION_NUMBER);
        }
    }
}
