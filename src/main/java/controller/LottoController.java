package controller;

import domain.LottoNumber;
import domain.Money;
import domain.Lotto;
import domain.Lottos;
import service.CalculateProfit;
import service.FindBingo;
import service.RandomLottoGenerator;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private static Lottos userLottos = new Lottos();
    private static int manualLottoNum;

    public void start() {
        Money userMoney = getUserMoney();
        int ticket = userMoney.ticket();

        addAllLottos(ticket);
        showAllLotto(ticket);

        Lotto lastWeekLotto = new Lotto(getLastWeekLotto());
        int bonusNum = getBonusLottoNum();

        makeBingo(userLottos, lastWeekLotto, bonusNum);

        showLottoResult();
        showProfit(userMoney.getMoney());
    }

    public static Money getUserMoney() {
        String userMoney = InputView.readMoney();
        return new Money(userMoney);
    }

    public static void addAllLottos(int ticket) {
        manualLottoNum = getManualLottoNum();
        for (int i = 0; i < manualLottoNum; i++) {
            addManualLottos();
        }
        RandomLottoGenerator.makeAutomaticLottos(ticket - manualLottoNum, userLottos);
    }

    public static int getManualLottoNum() {
        Integer manualLottoNum = InputView.readManualLottoNum();
        OutputView.printManualLottoMessage();
        return manualLottoNum;
    }

    public static void addManualLottos() {

        String manualLotto = InputView.readManualLotto();
        userLottos.addLotto(new Lotto(makeLottoList(manualLotto)));
    }

    public static void showAllLotto(int ticket) {
        OutputView.printLottos(manualLottoNum, ticket - manualLottoNum, userLottos);
    }

    public static List<LottoNumber> getLastWeekLotto() {
        String lastWeekLotto = InputView.readlastWeekLotto();
        return makeLottoList(lastWeekLotto);
    }

    public static List<LottoNumber> makeLottoList(String lotto) {
        List<LottoNumber> LottoList = new ArrayList<>();
        for (String lottoNumber : lotto.split(",\\s*")) {
            LottoList.add(new LottoNumber(lottoNumber));
        }
        return LottoList;
    }

    public static int getBonusLottoNum() {
        return InputView.readBonusNumber();
    }

    public void makeBingo(Lottos lottos, Lotto userLotto, int bonusNum) {
        FindBingo findBingo = new FindBingo();
        findBingo.findBingos(lottos, userLotto, bonusNum);
    }

    public static void showLottoResult() {
        OutputView.printResult();
    }

    public static double calculate(int money) {
        CalculateProfit.calcProfit();
        return CalculateProfit.calcProfitPercent(money);
    }

    public static void showProfit(int money) {
        OutputView.printPercent(calculate(money));
    }
}
