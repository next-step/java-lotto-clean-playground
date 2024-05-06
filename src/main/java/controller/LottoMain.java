package controller;

import model.Lottos;
import model.OneLotto;
import model.RandomNumGenerator;
import model.RankCalculator;
import view.LottoInput;
import view.Print;

import java.util.ArrayList;
import java.util.List;

public class LottoMain {

    public static void main(String[] args) {
        int manual, automatic;
        int balance = LottoInput.inputBalance();

        RandomNumGenerator randomNumGenerator = new RandomNumGenerator();
        List<OneLotto> myLottos = new ArrayList<>();

        manual = LottoInput.manual();
        automatic = balance / 1000 - manual;

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manual; i++) {
            List<Integer> manualLotto = LottoInput.manualLottoInput();
            OneLotto oneLotto = new OneLotto(manualLotto);
            myLottos.add(oneLotto);
        }

        for (int i = 0; i < automatic; i++) {
            OneLotto oneLotto = new OneLotto(randomNumGenerator.randomNumGenerate());
            myLottos.add(oneLotto);
        }

        Lottos lottos = new Lottos(balance, balance / 1000, myLottos);
        for (OneLotto myLotto : myLottos) {
            System.out.println(myLotto.getLottoNumbers());
        }
        Print.printing(lottos, manual, automatic);

        List<Integer> lottoAnswer = LottoInput.lottoAnswer();
        RankCalculator rankCalculator = new RankCalculator(lottoAnswer);

        rankCalculator.allLottoRank(lottos);

        LottoInput.bonusAnswer(lottos);

        Print.winning(rankCalculator, lottos);
    }
}
