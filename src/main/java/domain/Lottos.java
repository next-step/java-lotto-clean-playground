package domain;

import java.util.List;
import java.util.ArrayList;

public class Lottos {

    private final int lottoNumberCount;
    List<Lotto> lottos;

    public Lottos(int lottoNumberCount) {
        lottos = new ArrayList<>();
        this.lottoNumberCount = lottoNumberCount;

        for(int i = 0; i < lottoNumberCount; i++){
            lottos.add(new Lotto());
        }
    }


    List<Integer> correctCounts(CorrectLotto correctLotto) { // 실제 로또 번호와 일치하는 번호 개수 리스트 반환
        List<Integer> correctCounts = new ArrayList<>();
        for(int i = 0; i < lottos.size(); i++) {
            correctCounts.add(lottos.get(i).correctCount(correctLotto));
        }
        return correctCounts;
    }


    public int getLottoNumberCount() {
        return lottoNumberCount;
    } // 로또 개수 반환


    public List<String> getLottoForms() { // 로또 출력 폼 리스트 반환
        List<String> lottoForms = new ArrayList<>();
        for(int i = 0; i < lottos.size(); i++){
            lottoForms.add(lottos.get(i).getLottoForm());
        }
        return lottoForms;
    }
}
