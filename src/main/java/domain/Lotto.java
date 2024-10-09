package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<Integer> lottoNumbers;

    //로또 번호 자동 생성 생성자
    public Lotto() {
        this.lottoNumbers = generateLottoNumbers();
    }

    //로또 번호 수동 생성 생성자
    public Lotto(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalStateException("로또 번호는 6개여야 합니다.");
        }
        this.lottoNumbers = new ArrayList<>(numbers);
        Collections.sort(this.lottoNumbers);
    }

    //로또 번호 자동 생성 메서드(1~45 사이 중복없는 6개의 숫자)
    private List<Integer> generateLottoNumbers() {

        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<Integer> selectedNumbers = numbers.subList(0, 6);

        Collections.sort(selectedNumbers);

        return new ArrayList<>(selectedNumbers);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    //당첨 번호와 일치하는 번호 개수 반환
    public int countMatches(List<Integer> winningNumbers) {


        return (int) lottoNumbers.stream().filter(winningNumbers::contains).count();
    }

    //보너스 번호가 로또 번호에 포함되는지 확인
    public boolean contains(int bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

}
