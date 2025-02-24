package domain.strategy;

import domain.model.Lotto;

import java.util.List;

public interface LottoStrategy {
    Lotto generateLotto(List<Lotto> manualNumbers);
}
