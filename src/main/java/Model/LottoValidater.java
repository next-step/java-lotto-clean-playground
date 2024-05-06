package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LottoValidater {
    public void validateLottos(ArrayList<LottoNumber> lottoNumbers) {
        sizeCheck(lottoNumbers);
        duplicationCheck(lottoNumbers);
    }

    public void duplicationCheck(ArrayList<LottoNumber> lottoNumbers) {
        Set<Integer> seen = new HashSet<>();
        if (lottoNumbers.stream()
                .anyMatch(n -> !seen.add(n.getNumber()))) throw new IllegalArgumentException("로또는 중복돼서는 안됩니다.");
    }

    public void sizeCheck(ArrayList<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != 6)  throw new IllegalArgumentException("로또의 개수는 6개가 돼야합니다");
    }
}
