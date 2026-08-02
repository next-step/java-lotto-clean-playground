package domain;

import java.util.List;

public class LottoForm { // 로또 출력 형식 포장

    private final String lottoForm;

    public LottoForm(List<LottoNumber> lotto){
        this.lottoForm = getLottoForm(lotto);
    }

    private String getLottoForm(List<LottoNumber> lotto){
        StringBuilder form = new StringBuilder("[");
        for (int i = 0; i < lotto.size() - 1; i++){
            form.append(lotto.get(i)).append(", ");
        }
        form.append(lotto.get(lotto.size() - 1) + "]");
        return form.toString();
    }

    public String getLottoForm() {
        return lottoForm;
    }
}
