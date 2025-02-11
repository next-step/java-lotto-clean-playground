package dto;

import domain.LottoGroup;

public record PurchaseLottosResponse(
        long lottoCount,
        LottoGroup lottoGroup
) {
}
