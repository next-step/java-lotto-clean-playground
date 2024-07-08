package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStatus {

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    private final LottoGenerator lottoGenerator = new LottoGenerator();
    private final List<Lotto> lottoList = new ArrayList<>();
    private Lotto winningNumber;
    private LottoNumber bonusNumber;

    public LottoStatus(List<Lotto> manualLottoList) {
        this.lottoList.addAll(manualLottoList);
    }

    public void addAutoLotto(int autoLottoCount) {
        for (int i = 0; i < autoLottoCount; i++) {
            Lotto autoLotto = lottoGenerator.generateAutoLotto(randomNumberGenerator.getNumbers());
            lottoList.add(autoLotto);
        }
    }

    public void setWinningNumber(String input) {
        this.winningNumber = lottoGenerator.generateManualLotto(input);
    }

    public void setBonusNumber(int bonusNumber) {
        LottoNumber bonusLotto = new LottoNumber(bonusNumber);
        if (winningNumber.getLottoNumber().contains(bonusLotto)) {
            throw new IllegalArgumentException("이미 뽑힌 번호 입니다.");
        }
        this.bonusNumber = bonusLotto;
    }

    public List<Lotto> getLottoList() {
        return this.lottoList;
    }

    public LottoNumber getBonusNumber() {
        return this.bonusNumber;
    }

    public Lotto getWinningNumber() {
        return this.winningNumber;
    }
}
