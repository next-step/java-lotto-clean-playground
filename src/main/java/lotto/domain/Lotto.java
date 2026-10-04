package lotto.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    public static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;


    public Lotto(List<Integer> numbers) {
        validateDuplicate(numbers);
        validateSize(numbers);
        this.lottoNumbers = toLottoNumbers(numbers);
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        Collections.sort(lottoNumbers);
        //[학습] compareTo()는 누가 더 큰지 비교하는 법을 가르쳐준 것이고, Collections.sort(lottoNumbers)는 비교하는 법을 써서 지금 실제로 줄 세워봐라고 시키는 명령
        return lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }
    //contains 메서드를 사용하여 LottoNumber가 포함되어 있는지 확인

    //매칭책임을 winningNumbers에게 넘김 --> 양방향의존성 해소를 위해


}
