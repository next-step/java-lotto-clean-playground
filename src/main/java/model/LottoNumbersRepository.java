package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbersRepository {
    private final List<LottoNumbers> repository = new ArrayList<>();

    public void addLottoNumbers(LottoNumbers lottoNumbers) {
        repository.add(lottoNumbers);
    }

    public List<LottoNumbers> readLottoNumbersRepository() {
        return Collections.unmodifiableList(repository);
    }
}
