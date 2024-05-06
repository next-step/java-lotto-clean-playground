package Model;

import View.InputData;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private ArrayList<Lotto> lottos = new ArrayList<>();

    public void addNewLotto(Lotto lotto){
        lottos.add(lotto);
    }

    public ArrayList<Lotto> getLottos() {
        return lottos;
    }

    public int[] matchList(Lotto firstLotto, int bonus){
        int[] matchList = new int[5];
        List<Integer> answerLotto = firstLotto.getLottoNumbers().stream().map(LottoNumber::getNumber).toList();

        for (Lotto lotto:lottos) {
            List<Integer> buyLotto = lotto.getLottoNumbers().stream().map(LottoNumber::getNumber).toList();
            int matchCount = (int) buyLotto.stream().filter(answerLotto::contains).count();
            boolean bonusResult = buyLotto.contains(bonus);

            DataEnum.LottoResult.applyResult(matchList,matchCount,bonusResult);
        }
        return matchList;
    }

}
