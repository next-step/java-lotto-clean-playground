package domain.lotto;

import domain.draw.DrawLottoNumber;
import domain.lotto.collection.LottoTickets;
import domain.lotto.wrap.Money;

import java.util.LinkedList;
import java.util.List;

public class LottoSeller {

    private final Payment payment;
    private final int manualCount;
    private final LottoTickets tickets;
    private final DrawLottoNumber drawLottoNumber;

    public LottoSeller(Payment payment, List<Lotto> manualLottos, DrawLottoNumber drawLottoNumber) {
        this.payment = payment;
        this.drawLottoNumber = drawLottoNumber;
        validateManualCount(manualLottos);
        this.manualCount = manualLottos.size();
        this.tickets = issueTickets(manualLottos);
    }

    public LottoTickets getTickets() {
        return this.tickets;
    }

    public int getManualCount() {
        return this.manualCount;
    }

    public int getAutoCount() {
        return payment.purchasableCount() - this.manualCount;
    }

    public Money getPaid() {
        return payment.paid();
    }

    public int getChange() {
        return payment.change().getAmount();
    }

    public boolean hasChange() {
        return payment.hasChange();
    }

    private void validateManualCount(List<Lotto> manualLottos) {
        if (manualLottos.size() > payment.purchasableCount()) {
            throw new IllegalArgumentException("수동 구매 수는 구입 금액으로 살 수 있는 개수를 초과할 수 없습니다.");
        }
    }

    private LottoTickets issueTickets(List<Lotto> manualLottos) {

        List<Lotto> tickets = new LinkedList<>(manualLottos);

        for (int i = manualLottos.size(); i < payment.purchasableCount(); i++) {
            tickets.add(drawLottoNumber.draw());
        }

        return new LottoTickets(tickets);
    }
}
