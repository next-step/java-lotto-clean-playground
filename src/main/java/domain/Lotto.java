package domain;

import java.util.List;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto (List<LottoNumber> numbers) {
        validatorLotto(numbers);
        this.numbers = numbers;
    }

    public void validatorLotto (List<LottoNumber> numbers){
        if (numbers.size() != LOTTO_SIZE){
            throw new IllegalArgumentException("로또 숫자 개수가 6개여야 합니다.");
        }
    }

    public List<LottoNumber> getLotto(){
        return numbers;
    }

    public int getMatchNumbers(Lotto winnernumbers){
        int matchCount = 0;
        for(LottoNumber number : numbers){
            matchCount += winnernumbers.contain(number);
        }
        return matchCount;
    }
    private int contain(LottoNumber number){
        if(numbers.contains(number)){
            return 1;
        }
        return 0;
    }
}
