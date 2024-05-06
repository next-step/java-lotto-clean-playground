package model;

import java.util.List;

public class OneLotto {
    private final List<Integer> lottoNumbers;
    private boolean bonusCheck;
    private CorrectNum correctCnt;
    private int correctNum;

    public OneLotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public CorrectNum getCorrectCnt() {
        return correctCnt;
    }

    public void setCorrectNum(int correctNum) {
        this.correctNum = correctNum;
    }

    public void setBonusCheck(Lottos lottos) {
        if (correctNum == 4 && lottoNumbers.contains(lottos.getBonusBall()))
            bonusCheck = true;
    }

    public void setCorrectCnt() {
        if (bonusCheck && correctNum == 4) {
            correctCnt = CorrectNum.FIVE_NOT;
            return;
        }
        List<CorrectNum> nums = CorrectNum.getList();

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i).getNum() == correctNum && !bonusCheck) {
                correctCnt = nums.get(i); //왜 설정이 안되지?
                break;
            }
        }
    }
}
