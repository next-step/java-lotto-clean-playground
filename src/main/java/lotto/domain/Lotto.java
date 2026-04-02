package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
        Collections.sort(this.numbers);
    }
    //Lotto는 반환 타입. 메서드가 끝나면 Lotto 객체 하나를 돌려준다는 뜻
    public static Lotto from(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream() //리스트를 하나씩 꺼내서 가공할 수 있는 흐름으로 바꾸는 것
                .map(LottoNumber::new) //stream 안의 각 요소를 변환하는 역할. integer을 new LottoNumber(1)과 같이 바꾼다
                //.map(number -> new LottoNumber(number)과 같은 코드
                .collect(Collectors.toList()); //변환된 결과를 다시 리스트로 모음
        return new Lotto(lottoNumbers);
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    public int countMatch(Lotto winningLotto) { //내 로또 번호와 당첨 번호가 몇개 같은지 세는 함수
        return (int) numbers.stream() //내 로또 번호 하나씩 꺼내서 처리 시작
                .filter(winningLotto.numbers::contains)//당첨 번호에 포함된 것만 남겨라
                .count(); //개수 세기
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    //toString을 또 써주는 이유 : 각 클래스는 자기 역할에 맞게 따로 toString을 가져야 함
    //LottoNumber에서는 하나의 숫자를 string으로 가지며, 여기서는 6개 묶음을.
}
