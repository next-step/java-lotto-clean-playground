package domain;

import java.util.List;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        verifyNumberCount(lotto);
        verifyOverlap(lotto);
        this.lotto = List.copyOf(lotto);
    }

    private void verifyNumberCount(List<LottoNumber> lotto) {
        if(lotto.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_COUNT + "개의 숫자를 입력해주세요.");
        }
    }

    private void verifyOverlap(List<LottoNumber> lotto) {
        long distinct = lotto.stream().distinct().count();
        if(lotto.size() != distinct){
            throw new IllegalArgumentException("로또는 중복될 수 없습니다.");
        }
    }

    public List<LottoNumber> getLotto() { // lotto 번호 반환
        return lotto;
    }


    private int correctCount(CorrectLotto correctLotto) {
        int count = 0;
        for(int i = 0; i < lotto.size(); i++){
            if(correctLotto.getCorrectLotto().getLotto().contains(lotto.get(i))){
                count++;
            }
        }

        return count;
    }


    private boolean bonusMatch(CorrectLotto correctLotto) {
        return lotto.contains(correctLotto.getBonusBall());
    }

    public Rank findRank(CorrectLotto correctLotto) {
        int matchCount = correctCount(correctLotto);
        boolean bonusMatch = bonusMatch(correctLotto);
        return Rank.findRank(matchCount, bonusMatch);
    }
}
