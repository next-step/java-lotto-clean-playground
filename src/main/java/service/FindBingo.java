package service;

import domain.Bingo;
import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;

public class FindBingo {
    int correctNum = 0;
    int bonusFlag = 0;

    public void findBingos(Lottos lottos, Lotto lastWeekLotto, int bonusNum) {

        for (Lotto lotto : lottos.getLottos()) {
            correctNum = 0;
            bonusFlag = 0;
            compareUserLotto(lastWeekLotto,lotto);
            checkBonusNum(lastWeekLotto, bonusNum);

            checkBingoList(correctNum, bonusFlag);
        }
    }

    public void compareUserLotto(Lotto lastWeekLotto, Lotto userLotto) {
        for (LottoNumber lastWeekNum : lastWeekLotto.getLotto()) {
            if (userLotto.iscontained(lastWeekNum.getLottoNumber())) correctNum++;
        }
    }

    public void checkBonusNum(Lotto userLotto, int bonusNum) {
        if (userLotto.iscontained(bonusNum)) bonusFlag = 1;
    }

    public void checkBingoList(int correctNum, int bonusFlag) {
        findBingo3(correctNum);
        findBingo4(correctNum);
        findBingo5(correctNum, bonusFlag);
        findBingo5wB(correctNum, bonusFlag);
        findBingo6(correctNum);
    }

    public void findBingo3(int correctNum) {
        if (correctNum == 3) Bingo.getBingo3().addCorrectLottoNum();
    }

    public void findBingo4(int correctNum) {
        if (correctNum == 4) Bingo.getBingo4().addCorrectLottoNum();
    }

    public void findBingo5(int correctNum, int bonusFlag) {
        if (correctNum == 5 && bonusFlag == 0) Bingo.getBingo5().addCorrectLottoNum();
    }

    public void findBingo5wB(int correctNum, int bonusFlag) {
        if (correctNum == 5 && bonusFlag == 1) Bingo.getBingo5wB().addCorrectLottoNum();
    }

    public void findBingo6(int correctNum) {
        if (correctNum == 6) Bingo.getBingo6().addCorrectLottoNum();
    }
}
