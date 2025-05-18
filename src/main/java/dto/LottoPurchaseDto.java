package dto;

import domain.Lottos;

public record LottoPurchaseDto(int purchaseAmount, int manualCount, Lottos manualLottos, int autoCount) {
}
