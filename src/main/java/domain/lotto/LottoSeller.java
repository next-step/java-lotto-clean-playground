package domain.lotto;

import domain.draw.DrawLottoNumber;
import domain.lotto.collection.LottoTickets;
import domain.lotto.wrap.money.Money;

import java.util.LinkedList;
import java.util.List;

public class LottoSeller {

    private final BuyingLotto buyingLotto;
    private final int manualCount;
    private final LottoTickets tickets;
    private final DrawLottoNumber drawLottoNumber;

    public LottoSeller(BuyingLotto buyingLotto, List<Lotto> manualLottos, DrawLottoNumber drawLottoNumber) {
        this.buyingLotto = buyingLotto;
        this.drawLottoNumber = drawLottoNumber;
        validateManualCount(manualLottos);
        this.manualCount = manualLottos.size();
        this.tickets = initTickets(manualLottos);
    }

    public LottoTickets getTickets() {
        return this.tickets;
    }

    public int getManualCount() {
        return this.manualCount;
    }

    public int getAutoCount() {
        return buyingLotto.purchasableCount() - this.manualCount;
    }

    public Money getPaid() {
        return buyingLotto.paid();
    }

    public int getChange() {
        return buyingLotto.change().getAmount();
    }

    public boolean hasChange() {
        return buyingLotto.hasChange();
    }

    private void validateManualCount(List<Lotto> manualLottos) {
        if (manualLottos.size() > buyingLotto.purchasableCount()) {
            throw new IllegalArgumentException("수동 구매 수는 구입 금액으로 살 수 있는 개수를 초과할 수 없습니다.");
        }
    }

    private LottoTickets initTickets(List<Lotto> manualLottos) {

        List<Lotto> tickets = new LinkedList<>(manualLottos);

        for (int i = manualLottos.size(); i < buyingLotto.purchasableCount(); i++) {
            tickets.add(drawLottoNumber.draw());
        }

        return new LottoTickets(tickets);
    }
}
