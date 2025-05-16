package dto;

import domain.Lottos;

public record LottoPurchaseRequest(int purchaseAmount, int manualCount, Lottos manualLottos) {
}
