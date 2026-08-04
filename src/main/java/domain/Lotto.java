package domain;

import java.util.List;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        verifyNumberCount(lotto);
        verifyOverlap(lotto);
        this.lotto = lotto;
    }

    private void verifyNumberCount(List<LottoNumber> lotto) { // 로또 숫자 개수 검증
        if(lotto.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_NUMBER_COUNT + "개의 숫자를 입력해주세요.");
        }
    }

    private void verifyOverlap(List<LottoNumber> lotto) { // 로또 숫자 중복 검증
        long distinct = lotto.stream().distinct().count();
        if(lotto.size() != distinct){
            throw new IllegalArgumentException("로또는 중복될 수 없습니다.");
        }
    }

    public List<LottoNumber> getLotto() { // lotto 번호 반환
        return lotto;
    } // 로또 번호 반환


    private int correctCount(CorrectLotto correctLotto) { // 실제 로또 번호와 일치하는 번호 개수 반환
        int count = 0;
        for(int i = 0; i < lotto.size(); i++){
            if(correctLotto.getCorrectLotto().getLotto().contains(lotto.get(i))){
                count++;
            }
        }

        return count;
    }


    private boolean bonusMatch(CorrectLotto correctLotto) { // 보너스볼과 일치하는 번호가 있는지 확인
        if(lotto.contains(correctLotto.getBonusBall())) {
            return true;
        }
        return false;
    }

    public Rank findRank(CorrectLotto correctLotto) { // 로또가 몇 등인지 확인
        int matchCount = correctCount(correctLotto);
        boolean bonusMatch = bonusMatch(correctLotto);
        return Rank.findRank(matchCount, bonusMatch);
    }


    public String getLottoForm() { // 로또 출력 폼 반환
        return new LottoForm(lotto).getLottoForm();
    } // 로또 출력폼 반환
}
