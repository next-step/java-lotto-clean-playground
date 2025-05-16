package service;

import dto.LottoPurchaseDto;
import domain.Lottos;

public interface LottoPurchaseService {
    Lottos purchase(LottoPurchaseDto request);
}
