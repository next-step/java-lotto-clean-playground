package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoNumbersRepository {
    private final List<LottoNumbers> repository = new ArrayList<>();

    public void addLottoNumbers(LottoNumbers lottoNumbers) {
        repository.add(lottoNumbers);
    }

    public List<LottoNumbers> readLottoNumbersRepository() {
        return Collections.unmodifiableList(repository);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumbersRepository)) return false;
        LottoNumbersRepository allLotteries = (LottoNumbersRepository) o;
        return Objects.equals(repository, allLotteries.repository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repository);
    }
}
