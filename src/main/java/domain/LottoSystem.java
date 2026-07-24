package domain;

import generator.LottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoSystem {
    private final int lottoCount;
    private static final int LOTTO_PRICE = 1000;
    private final List<Lotto> lottos;

    public LottoSystem(int inputMoney, LottoNumberGenerator lottoNumberGenerator){
        this.lottoCount = inputMoney / LOTTO_PRICE;
        this.lottos = new ArrayList<>();
        createLottos(lottoNumberGenerator);
    }

    private void createLottos(LottoNumberGenerator lottoNumberGenerator){
        for(int i = 0; i < lottoCount; i++){
            lottos.add(new Lotto(lottoNumberGenerator.generate()));
        }
    }

    public List<List<Integer>> getPurchasedLottoNumbers(){
        List<List<Integer>> purchasedLottoNumbers = new ArrayList<>();
        for(int i = 0; i < lottoCount; i++){
            purchasedLottoNumbers.add(lottos.get(i).getLottoNumbers());
        }
        return purchasedLottoNumbers;
    }
}

