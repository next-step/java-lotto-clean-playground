package domain;

import java.util.ArrayList;
import java.util.List;

public class CorrectLotto {

    private final Lotto correctLotto;
    private final LottoNumber bonusBall;

    public CorrectLotto(String[] values, int bonusBall){
        List<LottoNumber> numbers = createCorrectLotto(values);
        correctLotto = new Lotto(List.copyOf(numbers));
        this.bonusBall = verifyBonusBall(bonusBall);
    }

    private LottoNumber verifyBonusBall(int bonusBall) { // 보너스볼과 로또 번호 중복 확인

        LottoNumber LottoNumberBonusBall = new LottoNumber(bonusBall);

        if(correctLotto.getLotto().contains(LottoNumberBonusBall)){
            throw new IllegalArgumentException("보너스볼은 당첨 번호와 중복될 수 없습니다.");
        }

        return LottoNumberBonusBall;
    }


    private List<LottoNumber> createCorrectLotto(String[] values) { // 로또 번호 LottoNumber 객체로 변환

        List<LottoNumber> numbers = new ArrayList<>();

        try {
            for (int i = 0; i < values.length; i++) {
                numbers.add(new LottoNumber(Integer.parseInt(values[i])));
            }

            return numbers;
        }

        catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 숫자로 입력해주세요.");
        }
    }


    public Lotto getCorrectLotto() { // 로또 객체 반환
        return correctLotto;
    }


    public LottoNumber getBonusBall() { // 보너스 볼 반환
        return bonusBall;
    }
}
