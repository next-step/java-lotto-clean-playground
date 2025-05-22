package dto;

import domain.Lotto;

import java.util.List;

public record LottoPurchaseDto(int totalAmount, int manualLottoCount, List<Lotto> manualLottos, int autoLottoCount) {
}
