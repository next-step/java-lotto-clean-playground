package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLotto {

    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public static List<Lotto> generateAutoLotto(int autoLottoCount) { // 개수가 count인 로또 리스트 생성

        if(autoLottoCount < 0) {
            throw new IllegalArgumentException("0 이상의 값을 받아야 합니다.");
        }

        List<Lotto> autoLotto = new ArrayList<>();

        for(int i = 0; i < autoLottoCount; i++) {
            List<LottoNumber> numbers = createNumbers();
            shuffleNumbers(numbers);
            autoLotto.add(new Lotto(getLottoNumbers(numbers)));
        }

        return autoLotto;
    }


    private static List<LottoNumber> createNumbers() { // 1~45 번호 준비
        List<LottoNumber> numbers = new ArrayList<>();

        for (int number = 1; number <= MAX_LOTTO_NUMBER; number++) {
            numbers.add(new LottoNumber(number));
        }

        return numbers;
    }


    private static void shuffleNumbers(List<LottoNumber> numbers) { // 1~45 번호를 무작위로 섞음
        Collections.shuffle(numbers);
    }


    private static List<LottoNumber> extractNumber(List<LottoNumber> numbers) { // 앞자리 6개 추출
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int index = 0; index < LOTTO_NUMBER_COUNT; index++) {
            lottoNumbers.add(numbers.get(index));
        }
        return lottoNumbers;
    }


    private static void sortLotto(List<LottoNumber> lottoNumbers) { // 로또 번호 오름차순 정렬
        Collections.sort(lottoNumbers);
    }


    private static List<LottoNumber> getLottoNumbers(List<LottoNumber> numbers) { // 로또 번호 반환
        List<LottoNumber> lottoNumbers = extractNumber(numbers);
        sortLotto(lottoNumbers);

        return lottoNumbers;
    }
}
