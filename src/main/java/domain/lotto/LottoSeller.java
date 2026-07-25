package domain.lotto;

import domain.draw.DrawLottoNumber;

import java.util.ArrayList;
import java.util.List;

public class LottoSeller {

    private static final int PRICE = 1_000;

    private final int paid;
    private final int change;
    private final List<Lotto> tickets = new ArrayList<>();
    private final DrawLottoNumber drawLottoNumber;

    public LottoSeller(int paid, DrawLottoNumber drawLottoNumber) {
        this.drawLottoNumber = drawLottoNumber;
        this.change = paid % 1000;
        this.paid = paid - change;
        initTickets();
    }

    public List<Lotto> getTickets() {
        return this.tickets;
    }

    public int getAmount() {
        return this.paid / PRICE;
    }

    public int getPaid() {
        return paid;
    }

    public int getChange() {
        return this.change;
    }

    private void initTickets() {
        for (int i = 0; i < paid / PRICE; i++) {
            tickets.add(new Lotto(drawLottoNumber));
        }
    }
}
