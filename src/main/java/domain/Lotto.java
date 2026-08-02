package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lotto;

    public Lotto() {
        List<LottoNumber> numbers = createNumbers();

        shuffleNumbers(numbers);

        this.lotto = getLottoNumbers(numbers);
    }


    private List<LottoNumber> createNumbers() { // 1~45 번호 준비
        List<LottoNumber> numbers = new ArrayList<>();

        for (int number = 1; number <= MAX_LOTTO_NUMBER; number++) {
            numbers.add(new LottoNumber(number));
        }

        return numbers;
    }

    private void shuffleNumbers(List<LottoNumber> numbers) { // 1~45 번호를 무작위로 섞음
        Collections.shuffle(numbers);
    }

    private List<LottoNumber> extractNumber(List<LottoNumber> numbers) { // 앞자리 6개 추출
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int index = 0; index < LOTTO_NUMBER_COUNT; index++) {
            lottoNumbers.add(numbers.get(index));
        }
        return lottoNumbers;
    }

    private void sortLotto(List<LottoNumber> lottoNumbers) { // 로또 번호 오름차순 정렬
        Collections.sort(lottoNumbers);
    }

    private List<LottoNumber> getLottoNumbers(List<LottoNumber> numbers) { // 로또 번호 반환
        List<LottoNumber> lottoNumbers = extractNumber(numbers);
        sortLotto(lottoNumbers);

        return lottoNumbers;
    }


    public List<LottoNumber> getLotto() { // lotto 번호 반환
        return lotto;
    }


    public int correctCount(CorrectLotto correctLotto) { // 실제 로또 번호와 일치하는 번호 개수 반환
        int count = 0;
        for(int i = 0; i < lotto.size(); i++){
            if(correctLotto.getCorrectLotto().contains(lotto.get(i))){
                count++;
            }
        }

        return count;
    }


    public String getLottoForm() { // 로또 출력 폼 반환
        return new LottoForm(lotto).getLottoForm();
    }
}
