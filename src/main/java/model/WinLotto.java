package model;

import java.util.*;

public class WinLotto {
    private List<LottoNumber> winnerLotto = new ArrayList<>();
    private List<Lotto> userLottos;
    private Map<LottoPrize, Integer> winningStates = new LinkedHashMap<>();
    private int winPrize;
    private LottoNumber bonusBall;

    public WinLotto(String inputWinnerLotto, List<Lotto> userLottos, String bonusBall) {
        Arrays.stream(inputWinnerLotto.split(Constant.SEPARATOR))
                .forEach(e -> winnerLotto.add(new LottoNumber(e.trim())));
        this.userLottos = userLottos;
        this.winPrize = Constant.ZERO_COUNT;
        LottoNumber tempBonusBall = new LottoNumber(bonusBall);
        if (winnerLotto.stream().anyMatch(winnerNumber -> winnerNumber.getNumber() == tempBonusBall.getNumber())) {
            throw new IllegalArgumentException(Constant.BONUS_DUPLICATE_EXCEPTION);
        }
        this.bonusBall = new LottoNumber(bonusBall);
        for (LottoPrize prize : LottoPrize.values()) {
            winningStates.put(prize, Constant.ZERO_COUNT);
        }
    }

    private int compareHowManyNumberSame(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(number -> winnerLotto.stream().anyMatch(winnerNumber -> winnerNumber.getNumber() == number.getNumber()))
                .count();
    }

    public int getWinPrize() {
        return winPrize;
    }

    public Map<LottoPrize, Integer> getWinningStates() {
        return winningStates;
    }

    public void updateWinningStates(List<Lotto> userLottos) {
        userLottos.forEach(userLotto -> {
            int matchedNumbers = compareHowManyNumberSame(userLotto);
            boolean containsBonus = userLotto.getNumbers().stream().anyMatch(number -> number.getNumber() == bonusBall.getNumber());

            if (matchedNumbers == Constant.SIX_COUNT) {
                updateWinningState(LottoPrize.FIRST);
            }
            if (matchedNumbers == Constant.FIVE_COUNT && containsBonus) {
                updateWinningState(LottoPrize.SECOND);
            }
            if (matchedNumbers == Constant.FIVE_COUNT && !containsBonus) {
                updateWinningState(LottoPrize.THIRD);
            }
            if (matchedNumbers == Constant.FOUR_COUNT) {
                updateWinningState(LottoPrize.FOURTH);
            }
            if (matchedNumbers == Constant.THREE_COUNT) {
                updateWinningState(LottoPrize.FIFTH);
            }
        });
    }

    private void updateWinningState(LottoPrize prize) {
        winningStates.put(prize, winningStates.get(prize) + 1);
        winPrize += prize.getPrizeAmount();
    }
}
