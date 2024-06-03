package domain;

import java.util.ArrayList;
import java.util.List;

public class PassiveLottoNumber {

    private final List<LottoNumberParser> passiveLottoNumbers;

    public PassiveLottoNumber(List<String> passiveLottoNumber) {
        this.passiveLottoNumbers = makePassiveLottoNumbers(passiveLottoNumber);
    }

    public List<LottoNumberParser> getPassiveLottoNumbers() {
        return passiveLottoNumbers;
    }

    private List<LottoNumberParser> makePassiveLottoNumbers(List<String> passiveLottoNumbers) {
        List<LottoNumberParser> lottoNumbers = new ArrayList<>();
        for (String passiveLottoNumber : passiveLottoNumbers) {
            LottoNumberParser lottoNumberParser = new LottoNumberParser(passiveLottoNumber);
            lottoNumbers.add(lottoNumberParser);
        }
        return lottoNumbers;
    }
}
