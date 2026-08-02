package domain;

import java.util.ArrayList;
import java.util.List;

public class CorrectLotto {

    private static final int LOTTO_NUMBER_COUNT = 6;

    List<LottoNumber> correctLotto;

    public CorrectLotto(String[] values){
        correctLotto = verifyCorrectLotto(values);
    }


    private List<LottoNumber> verifyCorrectLotto(String[] values) {
        List<LottoNumber> numbers;
        try {
            numbers = createCorrectLotto(values);
            if(numbers.size() != LOTTO_NUMBER_COUNT) {
                throw new IllegalArgumentException("6개의 숫자를 입력해주세요.");
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
        return numbers;
    }


    private List<LottoNumber> createCorrectLotto(String[] values) {

        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            numbers.add(new LottoNumber(Integer.parseInt(values[i])));
        }

        return numbers;
    }

    public List<LottoNumber> getCorrectLotto() {
        return correctLotto;
    }
}
