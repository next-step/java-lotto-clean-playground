package Model;

import Creator.NumberCreator;
import Creator.RandomNumberCreator;

import java.util.ArrayList;


public class LottoCreator {
    NumberCreator randomNumberCreator;

    public LottoCreator(RandomNumberCreator randomNumberCreator) {
        this.randomNumberCreator = randomNumberCreator;
    }

    public ArrayList<LottoNumber> getRandomNumbers(ArrayList<LottoNumber> lottoNumbers,LottoValidater lottoValidater) {
        while (lottoNumbers.size() < 6) {
            int returnNum = randomNumberCreator.returnNumber();

            isNotInList(lottoNumbers, returnNum);
        }
        lottoValidater.validateLottos(lottoNumbers);
        return lottoNumbers;
    }

    private static void isNotInList(ArrayList<LottoNumber> lottoNumbers, int returnNum) {
        if (lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .noneMatch(num -> num == returnNum)) {
            // `returnNum`과 일치하는 숫자가 없으면 true 반환
            lottoNumbers.add(new LottoNumber(returnNum));
        }
    }

}
