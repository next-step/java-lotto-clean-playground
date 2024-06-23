package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private static final int INITIAL_NUMBER = 0;
    private static final int LOTTO_COUNT_NUMBER = 1000;

    private final List<Lotto> lottos;

    public Lottos(CreateLottoNumber createLottoNumber, List<LottoNumberParser> inputPassiveLottoNumber, int lottoMoney) {
        this.lottos = makeLottos(createLottoNumber, inputPassiveLottoNumber, lottoMoney);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private List<Lotto> makeLottos(CreateLottoNumber createLottoNumber, List<LottoNumberParser> inputPassiveLottoNumbers, int lottoMoney) {
        List<Lotto> lottosBundle = new ArrayList<>();
        makePassiveLotto(inputPassiveLottoNumbers, lottosBundle);
        makeAutoLotto(createLottoNumber, inputPassiveLottoNumbers, lottoMoney, lottosBundle);
        return lottosBundle;
    }

    private void makeAutoLotto(CreateLottoNumber createLottoNumber, List<LottoNumberParser> inputPassiveLottoNumbers, int lottoMoney, List<Lotto> lottosBundle) {
        for (int i = INITIAL_NUMBER; i < (lottoMoney / LOTTO_COUNT_NUMBER) - inputPassiveLottoNumbers.size(); i++) {
            Lotto lotto = new Lotto(createLottoNumber);
            lottosBundle.add(lotto);
        }
    }

    private void makePassiveLotto(List<LottoNumberParser> inputPassiveLottoNumbers, List<Lotto> lottosBundle) {
        for (LottoNumberParser passiveLottoNumber : inputPassiveLottoNumbers) {
            Lotto lotto = new Lotto(passiveLottoNumber.getRealLottoNumber());
            lottosBundle.add(lotto);
        }
    }
}
