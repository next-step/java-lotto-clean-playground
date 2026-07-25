package domain.lotto;

import domain.draw.DrawLottoNumber;
import domain.lotto.collection.LottoTickets;
import domain.lotto.wrap.Money;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LottoSeller {

    private static final Money PRICE = new Money(1_000);

    private final Money amount;
    private final Money paid;
    private final Money change;
    private LottoTickets tickets;
    private final DrawLottoNumber drawLottoNumber;

    public LottoSeller(Money amount, DrawLottoNumber drawLottoNumber) {
        this.drawLottoNumber = drawLottoNumber;
        this.amount = amount;
        this.change = amount.change(PRICE);
        this.paid = amount.subtract(change);
        initTickets();
    }

    public LottoTickets getTickets() {
        return this.tickets;
    }

    public int getAmount() {
        return this.amount.countPurchasable(PRICE);
    }

    public Money getPaid() {
        return paid;
    }

    public int getChange() {
        return this.change.getAmount();
    }

    private void initTickets() {

        List<Lotto> tickets = new LinkedList<>();

        for (int i = 0; i < amount.countPurchasable(PRICE); i++) {
            tickets.add(drawLottoNumber.draw());
        }

        this.tickets = new LottoTickets(tickets);
    }
}
