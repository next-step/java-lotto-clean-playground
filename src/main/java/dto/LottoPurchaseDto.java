package dto;

import domain.Lottos;

public record LottoPurchaseDto(int totalAmount, int manualLottoCount, Lottos manualLottos, int autoLottoCount) {
}
