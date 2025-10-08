package Model;

import java.util.*;
import java.util.function.Consumer;

// 1. 제네릭을 사용하여 Iterable<Lotto>로 타입 명시
public class Lottos implements Iterable<Lotto> {
    private List<Lotto> lottoList;
    private static final int COST_PER_TICKET = 1000;

    public Lottos() {
        this.lottoList = new ArrayList<>();
    }


    public void addLotto (Lotto tmp){
        lottoList.add(tmp);
    }

    public int getAutoLottoCount(int money,int manualLottosNumber) {
        return money / COST_PER_TICKET - manualLottosNumber;
    }

    public void makeAutoLotto(int autoLottosNumber){
        for (int i = 0; i < autoLottosNumber; i++) {
            AutoLotto autoLotto = new AutoLotto();
            lottoList.add(autoLotto);
        }
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoList.iterator();
    }
}
