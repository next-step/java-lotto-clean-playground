package dto;

import domain.LottoGroup;

public record PurchaseLottosResponse(
        LottoGroup lottoGroup
) {

    public static PurchaseLottosResponse from(LottoGroup lottoGroup) {
        return new PurchaseLottosResponse(lottoGroup);
    }
}
