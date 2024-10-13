package controller;

import java.util.List;
import domain.FindWinningLotto;
import dto.LottoDTO;
import view.InputView;
import view.OutputView;

import static domain.Lotto.multipleLottoGenerator;

public class LottoController {
    private final int totalInvestment; // 구입금액을 입력해주세요. (입력)
    private final int numberOfManualLottos; // 수동으로 구매할 로또 수를 입력해주세요. (입력)
    private final int numberOfAutoLottos; // 자동으로 구매할 로또 수(매개변수)
    private List<List<Integer>> manualLottoInputs; // 수동 로또 리스트
    private List<Integer> lastWeekWinningNumbers; // 지난주 당첨 번호
    private int bonusWinningNumber; // 보너스 번호

    public LottoController() {
        this.totalInvestment = InputView.inputToBuyLotto();
        this.numberOfManualLottos = InputView.inputToBuyManualLotto();
        this.numberOfAutoLottos = totalInvestment / 1000 - numberOfManualLottos;
    }

    public void howManyOfLottos() {
        manualLottoInputs = InputView.inputToManualLotto(numberOfManualLottos); // 수동으로 구매할 번호를 입력해 주세요.
        OutputView.printResultOfBuying(numberOfManualLottos, numberOfAutoLottos); // 수동으로 n 장, 자동으로 k 개를 구매했습니다.
    }

    public void lottoGenerator() {
        List<List<Integer>> lottoCollection = multipleLottoGenerator(numberOfAutoLottos); // 자동 로또 생성
        manualLottoInputs.addAll(lottoCollection); // 수동 로또를 전체 로또 컬렉션에 추가
        OutputView.printResultOfAutoLotto(manualLottoInputs); // 로또 리스트 출력
    }

    public void winningNumberAndBonusNumber() {
        lastWeekWinningNumbers = InputView.lastWeekWinningNumber(); // 지난주 당첨 번호 입력
        bonusWinningNumber = InputView.bonusWinningNumber(); // 보너스 번호 입력
    }

    public void findWinningLotto() {
        FindWinningLotto findWinningLotto = new FindWinningLotto(); // 당첨 로또 찾기

        LottoDTO lottoDTO = findWinningLotto.countCollectNumber(manualLottoInputs, lastWeekWinningNumbers, bonusWinningNumber, totalInvestment);

        OutputView.printLottoWinningStatistics(lottoDTO); // 당첨 통계 출력
    }
}
