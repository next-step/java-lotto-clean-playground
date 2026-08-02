package domain;

import java.util.ArrayList;
import java.util.List;

public class CorrectLotto {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> correctLotto;
    private final LottoNumber bonusBall;

    public CorrectLotto(String[] values, LottoNumber bonusBall){
        correctLotto = verifyCorrectLotto(values);
        verifyBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }


    private void verifyBonusBall(LottoNumber bonusBall) { // 로또 번호와 중복 확인
        if(correctLotto.contains(bonusBall)){
            throw new IllegalArgumentException("보너스볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }


    private List<LottoNumber> verifyCorrectLotto(String[] values) { // 로또 번호 검증
        List<LottoNumber> numbers;
        numbers = createCorrectLotto(values);
        if(numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("6개의 숫자를 입력해주세요.");
        }
        return numbers;
    }


    private List<LottoNumber> createCorrectLotto(String[] values) { // 로또 번호 LottoNumber 객체로 변환

        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            numbers.add(new LottoNumber(Integer.parseInt(values[i])));
        }

        return numbers;
    }


    public List<LottoNumber> getCorrectLotto() { // 로또 번호 반환
        return correctLotto;
    } // 로또 번호 반환


    public LottoNumber getBonusBall() { // 보너스 볼 반환
        return bonusBall;
    } // 보너스 볼 반환
}
