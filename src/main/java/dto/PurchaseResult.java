package dto;

import domain.Lottos;
import domain.Money;


public record PurchaseResult(
        Money purchasePrice,
        Lottos lottos
) {
}
