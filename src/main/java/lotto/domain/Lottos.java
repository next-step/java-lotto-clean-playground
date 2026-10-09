package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottoList;

    public Lottos(List<Lotto> lottoList) {
        this.lottoList = lottoList;
    }

    public static Lottos generate(int count, LottoNumberGenerator generator) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(new Lotto(generator.generateLottoNumbers()));
        }
        return new Lottos(lottoList);
    }
    //밑에 제너레이트는 수동으로 입력한 번호와 자동으로 생성된 번호를 합쳐서 Lottos 객체를 생성하는 메서드입니다.
    public static Lottos generate(List<List<Integer>> manualNumbers, int autoCount, LottoNumberGenerator generator) {
        List<Lotto> lottoList = new ArrayList<>();
        for (List<Integer> numbers : manualNumbers) {
            lottoList.add(new Lotto(numbers)); //그 한 줄(6개 번호)로 Lotto 하나 만듦
        }
        lottoList.addAll(generate(autoCount, generator).getLottoList());
        return new Lottos(lottoList);
    }

    public List<Lotto> getLottoList() {
        return Collections.unmodifiableList(lottoList);
    }

    public int size() {
        return lottoList.size();
    }


}
