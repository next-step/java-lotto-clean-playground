package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class CustomLotto {
    private final List<Lotto> lottoList = new ArrayList<>();
    private final List<List<Integer>> customLottoes;

    public CustomLotto(List<List<Integer>> customLottoes) {
        this.customLottoes = customLottoes;
        makeCustomLottoes();
    }

    private void makeCustomLottoes() {
        for (List<Integer> lotto : customLottoes) {
            lottoList.add(new Lotto(lotto));
        }
    }

    public List<Lotto> getCustomLottoList() {
        return lottoList;
    }

    public int getCustomLottoCount() {
        return lottoList.size();
    }
}
