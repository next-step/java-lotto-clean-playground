package controller;

import java.util.List;
import model.AutoLottoGenerator;
import model.Lotto;
import model.LottoNumber;
import model.Lottos;
import model.Money;
import model.WinningLotto;

public class LottoManager {

    private final AutoLottoGenerator generator = new AutoLottoGenerator();

    public Lottos createLottos(Money money, int manualCount, List<Lotto> manualLottos) {
        int autoCount = getAutoCount(money, manualCount);
        List<Lotto> autoLottos = generator.generate(autoCount);

        manualLottos.addAll(autoLottos);

        return new Lottos(manualLottos);
    }

    public int getAutoCount(Money money, int manualCount) {
        return money.divideByUnit() - manualCount;
    }

    public WinningLotto createWinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        List<LottoNumber> numbers = winningNumbers.stream()
                .map(LottoNumber::valueOf)
                .toList();

        LottoNumber bonus = LottoNumber.valueOf(bonusNumber);
        return new WinningLotto(new Lotto(numbers,null), bonus);
    }
}
