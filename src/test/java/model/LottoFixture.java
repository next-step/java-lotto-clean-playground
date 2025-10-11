package model;

import java.util.List;

public class LottoFixture {

    // 기본 1~6 로또
    public static LottoTicket 기본로또() {
        return new LottoTicket(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
    }

    // 보너스볼(7) 포함된 로또
    public static LottoTicket 보너스7포함로또() {
        return new LottoTicket(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(7)
        ));
    }

    // 4개 일치 로또 (1~4 일치)
    public static LottoTicket 네개일치로또() {
        return new LottoTicket(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(7), new LottoNumber(8)
        ));
    }
}


