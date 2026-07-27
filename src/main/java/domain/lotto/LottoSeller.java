package domain.lotto;

import domain.draw.DrawLottoNumber;
import domain.lotto.collection.LottoTickets;
import domain.lotto.wrap.Money;

import java.util.LinkedList;
import java.util.List;

public class LottoSeller {

    private final Money PRICE;

    private final Money amount;
    private final Money paid;
    private final Money change;
    private final int manualCount;
    private LottoTickets tickets;
    private final DrawLottoNumber drawLottoNumber;

    public LottoSeller(Money price, Money amount, List<Lotto> manualLottos, DrawLottoNumber drawLottoNumber) {
        this.PRICE = price;
        this.drawLottoNumber = drawLottoNumber;
        this.amount = amount;
        this.change = amount.change(PRICE);
        this.paid = amount.subtract(change);
        validateManualCount(manualLottos);
        this.manualCount = manualLottos.size();
        initTickets(manualLottos);
    }

    public LottoTickets getTickets() {
        return this.tickets;
    }

    public int getAmount() {
        return this.amount.countPurchasable(PRICE);
    }

    public int getManualCount() {
        return this.manualCount;
    }

    public int getAutoCount() {
        return getAmount() - this.manualCount;
    }

    public Money getPaid() {
        return paid;
    }

    public int getChange() {
        return this.change.getAmount();
    }

    private void validateManualCount(List<Lotto> manualLottos) {
        if (manualLottos.size() > amount.countPurchasable(PRICE)) {
            throw new IllegalArgumentException("수동 구매 수는 구입 금액으로 살 수 있는 개수를 초과할 수 없습니다.");
        }
    }

    private void initTickets(List<Lotto> manualLottos) {

        List<Lotto> tickets = new LinkedList<>(manualLottos);

        for (int i = manualLottos.size(); i < amount.countPurchasable(PRICE); i++) {
            tickets.add(drawLottoNumber.draw());
        }

        this.tickets = new LottoTickets(tickets);
    }
}
