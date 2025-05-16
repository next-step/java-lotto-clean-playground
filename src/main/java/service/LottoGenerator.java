package service;

import domain.Lottos;

public interface LottoGenerator {
    Lottos generate(int amount);
    Lottos generateByCount(int count);
}
