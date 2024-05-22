package model.lotto;

import model.dice.Dice;
import model.lotto.exception.MoneyFormatException;
import model.lotto.exception.MoneyRemainException;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private static final int DEFAULT_LOTTO_COST = 1000;

    private List<Lotto> lottos;

    private Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos createWith(final Dice dice, final String moneyInput) {
        int money = convertMoney(moneyInput);

        validateMoney(money);

        List<Lotto> lottos = makeLottosWithMoney(dice, money);

        return new Lottos(lottos);
    }

    private static int convertMoney(final String moneyInput) {
        try {
            return Integer.parseInt(moneyInput);
        } catch (NumberFormatException exception) {
            throw new MoneyFormatException();
        }
    }

    private static void validateMoney(final int money) {
        validateMoneyPositive(money);
        validateMoneyDivideZero(money);
    }

    private static void validateMoneyPositive(final int money) {
        if (money <= 0) {
            throw new MoneyFormatException();
        }
    }

    private static void validateMoneyDivideZero(final int money) {
        if (money % DEFAULT_LOTTO_COST != 0) {
            throw new MoneyRemainException();
        }
    }

    private static List<Lotto> makeLottosWithMoney(final Dice dice, final int money) {
        int lottoSize = money / DEFAULT_LOTTO_COST;
        List<Lotto> lottos = new ArrayList<>();
        for (int size = 0; size < lottoSize; size++) {
            lottos.add(Lotto.createDefault(dice));
        }

        return lottos;
    }

    public int getLottosSize() {
        return lottos.size();
    }

    public List<List<Integer>> getTotalLottoNumbers() {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
    }
}
