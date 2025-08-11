package vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.LottoValidator;

public class Lotto {

    private final List<LottoNumber> numbers;

    // 로또 번호 6개로 로또 객체를 생성하는 생성자 (검증 및 정렬 포함)
    public Lotto(ArrayList<LottoNumber> numbers) {
        LottoValidator.validateLottoNumbers(numbers);
        this.numbers = new ArrayList<>(numbers);
        sortNumbers();
    }

    private void sortNumbers() {
        Collections.sort(this.numbers, (a, b) -> Integer.compare(a.getNumber(), b.getNumber()));
    }

    // 다른 로또와 일치하는 번호의 개수를 반환하는 메서드
    public int countMatchingNumbers(Lotto other) {
        int matchCount = 0;
        for (LottoNumber number : this.numbers) {
            if (other.numbers.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public ArrayList<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

}
